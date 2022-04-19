package top.smalldai.smalldaispringboot.controller.system;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.smalldai.smalldaispringboot.common.result.ResultVO;
import top.smalldai.smalldaispringboot.entity.system.SysPermission;
import top.smalldai.smalldaispringboot.service.system.SysPermissionService;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统权限控制层
 * @Data:Created in 2022/3/25 10:05 上午
 */
@RestController
@RequestMapping(value = "/sysPermissions")
@Api(tags = {"系统权限控制层"})
public class SysPermissionController {
    private static final String PERMISSION_GET_SUCCESS = "权限信息获取成功" ;
    private static final String PERMISSION_SAVE_SUCCESS = "权限信息保存成功";
    private static final String PERMISSION_REMOVE_SUCCESS = "权限信息删除成功";
    private static final String PERMISSION_BATCH_REMOVE_SUCCESS = "权限信息批量删除成功";
    private static final String PERMISSION_UPDATE_SUCCESS = "权限信息修改成功";

    @Resource
    private SysPermissionService sysPermissionService;

    /**
     * @Author: xingyuchen
     * @Discription: 查询系统权限列表
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "查询系统权限列表")
    @GetMapping
    public ResultVO list(){
        List<SysPermission> sysPermissions = sysPermissionService.list();
        return sysPermissions.size() > 0 ? ResultVO.success(PERMISSION_GET_SUCCESS, sysPermissions) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID查询系统权限
     * @param pId 系统权限ID
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "根据ID查询系统权限")
    @GetMapping(value = "/{pId}")
    public ResultVO getById(@PathVariable(name = "pId")Long pId){
        // 判断ID是否为空
        Assert.notNull(pId);
        SysPermission sysPermission = sysPermissionService.getById(pId);
        return !ObjectUtil.isEmpty(sysPermission) ? ResultVO.success(PERMISSION_GET_SUCCESS, sysPermission) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 新增系统权限
     * @param sysPermission 系统权限对象
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "新增系统权限")
    @PostMapping
    public ResultVO save(@RequestBody SysPermission sysPermission){
        // 判断权限是否为空
        Assert.notNull(sysPermission);
        boolean save = sysPermissionService.save(sysPermission);
        return save ? ResultVO.success(PERMISSION_SAVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID删除系统权限
     * @param pId 系统权限ID
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "根据ID删除系统权限")
    @DeleteMapping("/{pId}")
    public ResultVO removeById(@PathVariable(name = "pId")Long pId){
        // 判断ID是否为空
        Assert.notNull(pId);
        boolean remove = sysPermissionService.removeById(pId);
        return remove ? ResultVO.success(PERMISSION_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 批量删除系统权限
     * @param sysPermissions 系统权限集合
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "批量删除系统权限")
    @DeleteMapping
    public ResultVO removeByIds(@RequestBody Collection<SysPermission> sysPermissions){
        // 判断权限集合是否为空
        Assert.notNull(sysPermissions);
        boolean remove = sysPermissionService.removeByIds(sysPermissions);
        return remove ? ResultVO.success(PERMISSION_BATCH_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 修改一条系统权限信息
     * @param sysPermission 系统权限对象
     * @Date: 2022/3/25 12:31 下午
     */
    @ApiOperation(value = "修改一条系统权限信息")
    @PutMapping
    public ResultVO update(@RequestBody SysPermission sysPermission){
        // 判断权限是否为空
        Assert.notNull(sysPermission);
        boolean update = sysPermissionService.updateById(sysPermission);
        return update ? ResultVO.success(PERMISSION_UPDATE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

}
