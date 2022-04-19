package top.smalldai.smalldaispringboot.controller.system;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.smalldai.smalldaispringboot.common.result.ResultVO;
import top.smalldai.smalldaispringboot.entity.system.SysRole;
import top.smalldai.smalldaispringboot.service.system.SysRoleService;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统角色控制层
 * @Data:Created in 2022/3/25 10:05 上午
 */
@RestController
@RequestMapping(value = "/sysRoles")
@Api(tags = {"系统角色控制层"})
public class SysRoleController {
    private static final String ROLE_GET_SUCCESS = "角色信息获取成功" ;
    private static final String ROLE_SAVE_SUCCESS = "角色信息保存成功";
    private static final String ROLE_REMOVE_SUCCESS = "角色信息删除成功";
    private static final String ROLE_BATCH_REMOVE_SUCCESS = "角色信息批量删除成功";
    private static final String ROLE_UPDATE_SUCCESS = "角色信息修改成功";

    @Resource
    private SysRoleService sysRoleService;

    /**
     * @Author: xingyuchen
     * @Discription: 查询系统角色列表
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "查询系统角色列表")
    @GetMapping
    public ResultVO list(){
        List<SysRole> sysRoles = sysRoleService.list();
        return sysRoles.size() > 0 ? ResultVO.success(ROLE_GET_SUCCESS, sysRoles) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID查询系统角色
     * @param id 系统角色ID
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "根据ID查询系统角色")
    @GetMapping(value = "/{id}")
    public ResultVO getById(@PathVariable(name = "id")Long id){
        // 判断ID是否为空
        Assert.notNull(id);
        SysRole sysRole = sysRoleService.getById(id);
        return !ObjectUtil.isEmpty(sysRole) ? ResultVO.success(ROLE_GET_SUCCESS, sysRole) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 新增系统角色
     * @param sysRole 系统角色对象
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "新增系统角色")
    @PostMapping
    public ResultVO save(@RequestBody SysRole sysRole){
        // 判断角色是否为空
        Assert.notNull(sysRole);
        boolean save = sysRoleService.save(sysRole);
        return save ? ResultVO.success(ROLE_SAVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID删除系统角色
     * @param id 系统角色ID
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "根据ID删除系统角色")
    @DeleteMapping("/{id}")
    public ResultVO removeById(@PathVariable(name = "id")Long id){
        // 判断ID是否为空
        Assert.notNull(id);
        boolean remove = sysRoleService.removeById(id);
        return remove ? ResultVO.success(ROLE_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 批量删除系统角色
     * @param sysRoles 系统角色集合
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "批量删除系统角色")
    @DeleteMapping
    public ResultVO removeByIds(@RequestBody Collection<SysRole> sysRoles){
        // 判断角色集合是否为空
        Assert.notNull(sysRoles);
        boolean remove = sysRoleService.removeByIds(sysRoles);
        return remove ? ResultVO.success(ROLE_BATCH_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 修改一条系统角色信息
     * @param sysRole 系统角色对象
     * @Date: 2022/3/25 12:31 下午
     */
    @ApiOperation(value = "修改一条系统角色信息")
    @PostMapping("/update")
    public ResultVO update(@RequestBody SysRole sysRole){
        // 判断角色是否为空
        Assert.notNull(sysRole);
        boolean update = sysRoleService.updateById(sysRole);
        return update ? ResultVO.success(ROLE_UPDATE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }
}
