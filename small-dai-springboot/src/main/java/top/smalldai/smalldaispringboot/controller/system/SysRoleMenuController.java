package top.smalldai.smalldaispringboot.controller.system;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.smalldai.smalldaispringboot.common.result.ResultVO;
import top.smalldai.smalldaispringboot.entity.system.SysRoleMenu;
import top.smalldai.smalldaispringboot.service.system.SysRoleMenuService;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统角色菜单控制层
 * @Data:Created in 2022/3/25 10:06 上午
 */
@RestController
@RequestMapping(value = "/sysRoleMenus")
@Api(tags = {"系统角色菜单控制层"})
public class SysRoleMenuController {
    private static final String ROLE_MENU_GET_SUCCESS = "角色菜单信息获取成功" ;
    private static final String ROLE_MENU_SAVE_SUCCESS = "角色菜单信息保存成功";
    private static final String ROLE_MENU_REMOVE_SUCCESS = "角色菜单信息删除成功";
    private static final String ROLE_MENU_BATCH_REMOVE_SUCCESS = "角色菜单信息批量删除成功";
    private static final String ROLE_MENU_UPDATE_SUCCESS = "角色菜单信息修改成功";

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    /**
     * @Author: xingyuchen
     * @Discription: 查询系统角色菜单列表
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "查询系统角色菜单列表")
    @GetMapping
    public ResultVO list(){
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuService.list();
        return sysRoleMenus.size() > 0 ? ResultVO.success(ROLE_MENU_GET_SUCCESS, sysRoleMenus) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID查询系统角色菜单
     * @param rmId 系统角色菜单ID
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "根据ID查询系统角色菜单")
    @GetMapping(value = "/{rmId}")
    public ResultVO getById(@PathVariable(name = "rmId")Long rmId){
        // 判断角色菜单ID是否为空
        Assert.notNull(rmId);
        SysRoleMenu sysRoleMenu = sysRoleMenuService.getById(rmId);
        return !ObjectUtil.isEmpty(sysRoleMenu) ? ResultVO.success(ROLE_MENU_GET_SUCCESS, sysRoleMenu) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 新增系统角色菜单
     * @param sysRoleMenu 系统角色菜单对象
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "新增系统角色菜单")
    @PostMapping
    public ResultVO save(@RequestBody SysRoleMenu sysRoleMenu){
        //判断角色菜单是否为空
        Assert.notNull(sysRoleMenu);
        boolean save = sysRoleMenuService.save(sysRoleMenu);
        return save ? ResultVO.success(ROLE_MENU_SAVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID删除系统角色菜单
     * @param rmId 系统角色菜单ID
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "根据ID删除系统角色菜单")
    @DeleteMapping("/{rmId}")
    public ResultVO removeById(@PathVariable(name = "uId")Long rmId){
        // 判断ID是否为空
        Assert.notNull(rmId);
        boolean remove = sysRoleMenuService.removeById(rmId);
        return remove ? ResultVO.success(ROLE_MENU_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 批量删除系统角色菜单
     * @param sysRoleMenus 系统角色菜单集合
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "批量删除系统角色菜单")
    @DeleteMapping
    public ResultVO removeByIds(@RequestBody Collection<SysRoleMenu> sysRoleMenus){
        // 判断角色菜单集合是否为空
        Assert.notNull(sysRoleMenus);
        boolean remove = sysRoleMenuService.removeByIds(sysRoleMenus);
        return remove ? ResultVO.success(ROLE_MENU_BATCH_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 修改一条系统角色菜单信息
     * @param sysRoleMenu 系统角色菜单对象
     * @Date: 2022/3/25 12:31 下午
     */
    @ApiOperation(value = "修改一条系统角色菜单信息")
    @PutMapping
    public ResultVO update(@RequestBody SysRoleMenu sysRoleMenu){
        // 判断角色菜单是否为空
        Assert.notNull(sysRoleMenu);
        boolean update = sysRoleMenuService.updateById(sysRoleMenu);
        return update ? ResultVO.success(ROLE_MENU_UPDATE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }
}
