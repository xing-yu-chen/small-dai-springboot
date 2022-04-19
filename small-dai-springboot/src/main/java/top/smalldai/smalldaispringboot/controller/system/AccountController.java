package top.smalldai.smalldaispringboot.controller.system;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import top.smalldai.smalldaispringboot.common.annotation.SysOperationLog;
import top.smalldai.smalldaispringboot.common.constant.AccountConstant;
import top.smalldai.smalldaispringboot.common.constant.ResultConstant;
import top.smalldai.smalldaispringboot.common.result.ResultVO;
import top.smalldai.smalldaispringboot.entity.system.SysUser;
import top.smalldai.smalldaispringboot.service.system.SysUserService;
import top.smalldai.smalldaispringboot.to.UserTO;
import top.smalldai.smalldaispringboot.util.EmailUtil;
import top.smalldai.smalldaispringboot.util.SaltUtil;
import top.smalldai.smalldaispringboot.vo.UserVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 登陆/注册/重置密码
 * @Data:Created in 2022/3/25 2:55 下午
 */
@RestController
@RequestMapping(value = "/account")
@Api(tags = {"校验控制层"})
public class AccountController {

    /* 注册用户的Redis前缀 */
    private static final String REGISTER_EMAIL_PREFIX = "RegisterEmailCode";
    /* 密码找回的Redis前缀 */
    private static final String UPDATE_PASSWORD_EMAIL_PREFIX = "UpdatePasswordEmail";

    /* 登陆密码错误 */
    private static final String LOGIN_PASSWORD_ERROR_MSG = "您输入的密码错误";

    /* 登陆邮箱错误 */
    private static final String LOGIN_EMAIL_ERROR_MSG = "您输入的邮箱不存在";

    /* 登陆成功 */
    private static final String LOGIN_SUCCESS_MSG = "登陆成功";



    @Resource
    private SysUserService sysUserService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private EmailUtil emailUtil;

    /**
     * @Author: xingyuchen
     * @Discription: 用户登陆
     * @param userVO
     * @Date: 2022/3/26 10:51 下午
    */
    @SysOperationLog("用户登陆")
    @ApiOperation(value = "用户登陆")
    @PostMapping(value = "/login")
    public ResultVO login(@RequestBody UserVO userVO){
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(new UsernamePasswordToken(userVO.getEmail(), userVO.getPassword()));
        }catch (IncorrectCredentialsException e){
            return ResultVO.failByNotFound(LOGIN_PASSWORD_ERROR_MSG);
        }catch (UnknownAccountException e){
            return ResultVO.failByNotFound(LOGIN_EMAIL_ERROR_MSG);
        }
        UserTO userTOS = sysUserService.listUserById(userVO.getEmail());
        userTOS.setToken(subject.getSession().getId().toString());
        return ResultVO.success(LOGIN_SUCCESS_MSG, userTOS);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 用户注册往数据库里新增数据
     * @param userVO 注册用户VO映射实体
     * @Date: 2022/3/25 3:17 下午
    */
    @SysOperationLog("用户注册")
    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    public ResultVO register(@RequestBody UserVO userVO, HttpServletRequest request){
        // 判断传入的值是否为空
        Assert.notNull(userVO);
        Object code = redisTemplate.opsForValue().get(REGISTER_EMAIL_PREFIX + userVO.getEmail());
        // 如果缓存中验证码是空的，就说没传进来或者验证码已经过期了
        if(ObjectUtil.isNull(code)){
            return ResultVO.failByNull("您的验证码已过期，请重发验证码。");
        }
        // 判断用户名是否有重复情况
        if(ObjectUtil.isNotNull(sysUserService.getOne(new QueryWrapper<SysUser>().eq("u_name", userVO.getName())))){
            return ResultVO.failByNull("您的用户名已存在，请重新校验。");
        }
        // 判断邮箱是否有重复情况
        if(ObjectUtil.isNotNull(sysUserService.getOne(new QueryWrapper<SysUser>().eq("u_email", userVO.getEmail())))){
            return ResultVO.failByNull("您的邮箱已存在，无法再次注册，请联系管理员修正。");
        }
        if(userVO.getCode().equals(code.toString())){
            // 将VO的映射写入TO的映射中
            Random numRandom = new Random();
            // 随机定义6-10位盐位数
            Integer num = numRandom.nextInt(10 - 6 + 1) + 6;
            // 随机盐
            String salt = SaltUtil.getSalt(num);
            // 将信息写入TO对象中
            SysUser sysUser = new SysUser();
            sysUser.setName(userVO.getName())
                    .setSalt(salt)
                    .setEmail(userVO.getEmail())
                    .setTel(userVO.getTel())
                    .setPassword(new Md5Hash(userVO.getPassword(), salt, AccountConstant.HASH_NUMBER).toHex())
                    .setAge(userVO.getAge())
                    .setCreateIp(request.getRemoteAddr())
                    .setDbSource("small-dai-springboot");
            boolean save = sysUserService.save(sysUser);
            return save ? ResultVO.success("注册成功", "恭喜你注册成功,快去登录吧") : ResultVO.failByNull(null);
        }
        return ResultVO.failByInternalServer("您的验证码有误，请重新验证。");
    }

    /**
     * @Author: xingyuchen
     * @Discription: 注册时发送邮箱验证码
     * @param email
     * @Date: 2022/3/26 9:46 下午
    */
    @SysOperationLog("发送注册邮件验证码")
    @ApiOperation(value = "注册邮箱验证码")
    @GetMapping(value = "/email/send")
    public ResultVO sendEmail(String email){
        // 判断邮箱是否为空
        Assert.notNull(email);
        // 判断存储的email是否存在
        Object emailCode = redisTemplate.opsForValue().get(REGISTER_EMAIL_PREFIX + email);
        if(ObjectUtil.isNotNull(emailCode)){
            return ResultVO.failByNull("时间不足3分钟,请于3分钟后再发验证码");
        }
        // 获取6位验证码
        String code = emailUtil.getRandomCode(6);
        // 存入邮箱验证码
        redisTemplate.opsForValue().set(REGISTER_EMAIL_PREFIX + email, code);
        // 设置过期时间
        redisTemplate.expire(REGISTER_EMAIL_PREFIX + email, 3, TimeUnit.MINUTES);
        return ResultVO.success("验证码", emailUtil.sendSimpleMail(email, code));
    }

    /**
     * @Author: xingyuchen
     * @Discription: 用户修改密码发送密码邮件
     * @param email
     * @Date: 2022/3/26 10:06 下午
    */
    @SysOperationLog("发送修改密码邮件验证码")
    @ApiOperation(value = "修改密码验证码")
    @GetMapping(value = "/email/send/password")
    public ResultVO sendEmailForUpdatePassword(String email){
        // 判断邮箱是否为空
        Assert.notNull(email);
        // 判断缓存中是否已经存在这个记录
        Boolean isLive = redisTemplate.hasKey(UPDATE_PASSWORD_EMAIL_PREFIX + email);
        if(isLive){
            return ResultVO.failByNull("您的验证码已发送，如未收到验证码请于三分钟后重新发起流程。");
        }
        String code= emailUtil.getRandomCode(6);
        // 存入找回密码记录
        redisTemplate.opsForValue().set(UPDATE_PASSWORD_EMAIL_PREFIX + email, code);
        // 设置密码过期时间
        redisTemplate.expire(UPDATE_PASSWORD_EMAIL_PREFIX + email, 3, TimeUnit.MINUTES);
        return ResultVO.success("修改密码验证码", emailUtil.sendSimpleMail(email, code));
    }

    /**
     * @Author: xingyuchen
     * @Discription: 用户修改密码
     * @param userVO
     * @Date: 2022/3/26 10:50 下午
    */
    @SysOperationLog("用户修改密码")
    @ApiOperation(value = "修改密码")
    @PutMapping(value = "/password/update")
    public ResultVO updatePasswordByEmail(@RequestBody UserVO userVO){
        System.out.println(userVO.toString());
        // 判断用户邮箱是否为空
        Assert.notNull(userVO.getEmail());

        Object code = redisTemplate.opsForValue().get(UPDATE_PASSWORD_EMAIL_PREFIX + userVO.getEmail());
        // 判断缓存里的验证码是否为空
        if(ObjectUtil.isNull(code)){
            return ResultVO.failByNull("您的验证码已失效，请重新获取");
        }
        // 判断数据库中邮箱数据是否存在
        SysUser sysUser = sysUserService.getOne(new QueryWrapper<SysUser>().eq("u_email", userVO.getEmail()));
        // 如果为空或报异常即不存在，空指针异常时会有异常捕获不用管
        if(ObjectUtil.isNull(sysUser)){
            return ResultVO.failByNull("您的邮箱不存在，请确认您的注册信息");
        }

        // 如果验证码相同则可以修改密码
        if(userVO.getCode().equals(code.toString())){
            sysUser.setPassword(new Md5Hash(userVO.getPassword(), sysUser.getSalt(), AccountConstant.HASH_NUMBER).toHex());
            boolean update = sysUserService.updateById(sysUser);
            return update ? ResultVO.success("修改密码", "密码已修改成功") : ResultVO.failByInternalServer("密码修改失败");
        }
        return ResultVO.failByInternalServer(ResultConstant.INTERNAL_SERVER_MSG);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 退出登陆
     * @Date: 2022/4/6 2:56 上午
    */
    @SysOperationLog("退出登陆")
    @ApiOperation(value = "退出登陆")
    @GetMapping(value = "/logout")
    public ResultVO logout(){
        System.out.println(SecurityUtils.getSubject().getPrincipal());
        SecurityUtils.getSubject().logout();
        return ResultVO.success("退出登陆", "退出登陆成功");
    }
}
