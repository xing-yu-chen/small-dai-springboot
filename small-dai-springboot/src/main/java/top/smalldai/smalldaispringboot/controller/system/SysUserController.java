package top.smalldai.smalldaispringboot.controller.system;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.smalldai.smalldaispringboot.common.result.ResultVO;
import top.smalldai.smalldaispringboot.entity.system.SysUser;
import top.smalldai.smalldaispringboot.service.system.SysUserService;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统用户控制层
 * @Data:Created in 2022/3/25 10:07 上午
 */
@RestController
@RequestMapping(value = "/sysUsers")
@Api(tags = {"系统用户控制层"})
public class SysUserController {
    private static final String USER_GET_SUCCESS = "用户信息获取成功" ;
    private static final String USER_SAVE_SUCCESS = "用户信息保存成功";
    private static final String USER_REMOVE_SUCCESS = "用户信息删除成功";
    private static final String USER_BATCH_REMOVE_SUCCESS = "用户信息批量删除成功";
    private static final String USER_UPDATE_SUCCESS = "用户信息修改成功";

    @Resource
    private SysUserService sysUserService;

    /**
     * @Author: xingyuchen
     * @Discription: 查询系统用户列表
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "查询系统用户列表")
    @GetMapping
    public ResultVO list(){
        List<SysUser> sysUsers = sysUserService.list();
        return sysUsers.size() > 0 ? ResultVO.success(USER_GET_SUCCESS, sysUsers) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID查询系统用户
     * @param uId 系统用户ID
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "根据ID查询系统用户")
    @GetMapping(value = "/{uId}")
    public ResultVO getById(@PathVariable(name = "uId")Long uId){
        // 判断ID是否为空
        Assert.notNull(uId);
        SysUser sysUser = sysUserService.getById(uId);
        return !ObjectUtil.isEmpty(sysUser) ? ResultVO.success(USER_GET_SUCCESS, sysUser) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 新增系统用户
     * @param sysUser 系统用户对象
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "新增系统用户")
    @PostMapping
    public ResultVO save(@RequestBody SysUser sysUser){
        // 判断用户是否为空
        Assert.notNull(sysUser);
        boolean save = sysUserService.save(sysUser);
        return save ? ResultVO.success(USER_SAVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID删除系统用户
     * @param uId 系统用户ID
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "根据ID删除系统用户")
    @DeleteMapping("/{uId}")
    public ResultVO removeById(@PathVariable(name = "uId")Long uId){
        // 判断ID是否为空
        Assert.notNull(uId);
        boolean remove = sysUserService.removeById(uId);
        return remove ? ResultVO.success(USER_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 批量删除系统用户
     * @param sysUsers 系统用户集合
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "批量删除系统用户")
    @DeleteMapping
    public ResultVO removeByIds(@RequestBody Collection<SysUser> sysUsers){
        // 判断用户集合是否为空
        Assert.notNull(sysUsers);
        boolean remove = sysUserService.removeByIds(sysUsers);
        return remove ? ResultVO.success(USER_BATCH_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 修改一条系统用户信息
     * @param sysUser 系统用户对象
     * @Date: 2022/3/25 12:31 下午
     */
    @ApiOperation(value = "修改一条系统用户信息")
    @PutMapping
    public ResultVO update(@RequestBody SysUser sysUser){
        // 判断用户是否为空
        Assert.notNull(sysUser);
        boolean update = sysUserService.updateById(sysUser);
        return update ? ResultVO.success(USER_UPDATE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }
}
