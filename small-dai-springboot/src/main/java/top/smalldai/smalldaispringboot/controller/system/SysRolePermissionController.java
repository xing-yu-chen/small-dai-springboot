package top.smalldai.smalldaispringboot.controller.system;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.smalldai.smalldaispringboot.common.result.ResultVO;
import top.smalldai.smalldaispringboot.entity.system.SysRolePermission;
import top.smalldai.smalldaispringboot.service.system.SysRolePermissionService;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统角色权限
 * @Data:Created in 2022/3/25 10:06 上午
 */
@RestController
@RequestMapping(value = "/sysRolePermissions")
@Api(tags = {"系统角色权限"})
public class SysRolePermissionController {
    private static final String ROLE_PERMISSION_GET_SUCCESS = "角色权限信息获取成功" ;
    private static final String ROLE_PERMISSION_SAVE_SUCCESS = "角色权限信息保存成功";
    private static final String ROLE_PERMISSION_REMOVE_SUCCESS = "角色权限信息删除成功";
    private static final String ROLE_PERMISSION_BATCH_REMOVE_SUCCESS = "角色权限信息批量删除成功";
    private static final String ROLE_PERMISSION_UPDATE_SUCCESS = "角色权限信息修改成功";

    @Resource
    private SysRolePermissionService sysRolePermissionService;

    /**
     * @Author: xingyuchen
     * @Discription: 查询系统角色权限列表
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "查询系统角色权限列表")
    @GetMapping
    public ResultVO list(){
        List<SysRolePermission> sysRolePermissions = sysRolePermissionService.list();
        return sysRolePermissions.size() > 0 ? ResultVO.success(ROLE_PERMISSION_GET_SUCCESS, sysRolePermissions) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID查询系统角色权限
     * @param rpId 系统角色权限ID
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "根据ID查询系统角色权限")
    @GetMapping(value = "/{rpId}")
    public ResultVO getById(@PathVariable(name = "uId")Long rpId){
        // 判断角色权限是否为空
        Assert.notNull(rpId);
        SysRolePermission sysRolePermission = sysRolePermissionService.getById(rpId);
        return !ObjectUtil.isEmpty(sysRolePermission) ? ResultVO.success(ROLE_PERMISSION_GET_SUCCESS, sysRolePermission) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 新增系统角色权限
     * @param sysRolePermission 系统角色权限对象
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "新增系统角色权限")
    @PostMapping
    public ResultVO save(@RequestBody SysRolePermission sysRolePermission){
        // 判断角色权限对象是否为空
        Assert.notNull(sysRolePermission);
        boolean save = sysRolePermissionService.save(sysRolePermission);
        return save ? ResultVO.success(ROLE_PERMISSION_SAVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID删除系统角色权限
     * @param rpId 系统角色权限ID
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "根据ID删除系统角色权限")
    @DeleteMapping("/{rpId}")
    public ResultVO removeById(@PathVariable(name = "rpId") Long rpId){
        // 判断ID是否为空
        Assert.notNull(rpId);
        boolean remove = sysRolePermissionService.removeById(rpId);
        return remove ? ResultVO.success(ROLE_PERMISSION_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 批量删除系统角色权限
     * @param sysRolePermissions 系统角色权限集合
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "批量删除系统角色权限")
    @DeleteMapping
    public ResultVO removeByIds(@RequestBody Collection<SysRolePermission> sysRolePermissions){
        // 判断角色权限集合是否为空
        Assert.notNull(sysRolePermissions);
        boolean remove = sysRolePermissionService.removeByIds(sysRolePermissions);
        return remove ? ResultVO.success(ROLE_PERMISSION_BATCH_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 修改一条系统角色权限信息
     * @param sysRolePermission 系统角色权限对象
     * @Date: 2022/3/25 12:31 下午
     */
    @ApiOperation(value = "修改一条系统角色权限信息")
    @PutMapping
    public ResultVO update(@RequestBody SysRolePermission sysRolePermission){
        // 判断角色权限是否为空
        Assert.notNull(sysRolePermission);
        boolean update = sysRolePermissionService.updateById(sysRolePermission);
        return update ? ResultVO.success(ROLE_PERMISSION_UPDATE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }
}
