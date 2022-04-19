package ${springPackageName}.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import ${springPackageName}.common.response.ResponseVO;
import ${springPackageName}.entity.User;
import ${springPackageName}.service.UserService;
import ${springPackageName}.to.AccountTO;
import ${springPackageName}.common.util.EmailUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

@RestController
@Api(tags = "校验接口")
@RequestMapping("/account")
public class AccountController {

    @Resource
    private UserService userService;

    @Resource
    private EmailUtil emailUtil;

    private static HashMap<String, Object> hashMap = new HashMap<>();

    @ApiOperation(value = "用户登陆")
    @PostMapping(value = "/login")
    public ResponseVO login(@RequestBody AccountTO accountTO){
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(new UsernamePasswordToken(accountTO.getUEmail(), accountTO.getUPassword()));
        }catch (IncorrectCredentialsException e){
            return ResponseVO.failByNotFound("密码错误");
        }catch (UnknownAccountException e){
            return ResponseVO.failByNotFound("用户不存在");
        }
        AccountTO userTOS = userService.listUserById(accountTO.getUEmail());
        return ResponseVO.success("登陆成功", userTOS);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    public ResponseVO register(@RequestBody AccountTO accountTO){
        String code = (String) hashMap.get("registerCode");
        Object emailTime = hashMap.get("emailTime");
        System.out.println(new Date().getTime() - ((Date) emailTime).getTime());
        // 如果缓存中验证码是空的，就说没传进来或者验证码已经过期了
        if(code == "" || code == null || new Date().getTime() - ((Date) emailTime).getTime() > 180000){
            return ResponseVO.failByNull("您的验证码已过期，请重发验证码。");
        }
        // 判断用户名是否有重复情况
        if(userService.getOne(new QueryWrapper<User>().eq("u_name", accountTO.getUName())) != null){
            return ResponseVO.failByNull("您的用户名已存在，请重新校验。");
        }
        // 判断邮箱是否有重复情况
        if(userService.getOne(new QueryWrapper<User>().eq("u_email", accountTO.getUEmail()))!= null){
            return ResponseVO.failByNull("您的邮箱已存在，无法再次注册，请联系管理员修正。");
        }
        if(accountTO.getCode().equals(code.toString())){
            // 将VO的映射写入TO的映射中
            Random numRandom = new Random();
            // 随机定义6-10位盐位数
            Integer num = numRandom.nextInt(10 - 6 + 1) + 6;
            // 随机盐
            String salt = emailUtil.getSalt(num);
            // 将信息写入TO对象中
            User user = new User();
            user.setUName(accountTO.getUName())
                    .setUSalt(salt)
                    .setUEmail(accountTO.getUEmail())
                    .setUPhone(accountTO.getUPhone())
                    .setUPassword(new Md5Hash(accountTO.getUPassword(), salt, 1024).toHex());
            boolean save = userService.save(user);
            return save ? ResponseVO.success("注册成功", "恭喜你注册成功,快去登录吧") : ResponseVO.failByNull(null);
        }
        return ResponseVO.failByInternalServer("您的验证码有误，请重新验证。");
    }

    @ApiOperation(value = "注册邮箱验证码")
    @GetMapping(value = "/email/send")
    public ResponseVO sendEmail(String email){
        // 判断存储的email是否存在
        Object emailTime = hashMap.get("emailTime");
        if(hashMap.get("registerCode") != null && new Date().getTime() - ((Date) emailTime).getTime() < 180000 ){
            return ResponseVO.failByNull("时间不足3分钟,请于3分钟后再发验证码");
        }
        // 获取6位验证码
        String code = emailUtil.getRandomCode(6);
        // 存入邮箱验证码
        hashMap.put("registerCode", code);
        hashMap.put("emailTime", new Date());
        return ResponseVO.success("验证码", emailUtil.sendSimpleMail(email, code));
    }

    @ApiOperation(value = "退出登陆")
    @GetMapping(value = "/logout")
    public ResponseVO logout(){
        SecurityUtils.getSubject().logout();
        return ResponseVO.success("推出登陆", "退出登陆成功");
    }
}
