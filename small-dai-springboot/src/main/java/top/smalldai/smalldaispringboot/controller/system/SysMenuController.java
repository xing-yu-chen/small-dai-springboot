package top.smalldai.smalldaispringboot.controller.system;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.smalldai.smalldaispringboot.common.result.ResultVO;
import top.smalldai.smalldaispringboot.entity.system.SysMenu;
import top.smalldai.smalldaispringboot.service.system.SysMenuService;
import top.smalldai.smalldaispringboot.util.DBUtil;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统菜单控制层
 * @Data:Created in 2022/3/25 10:04 上午
 */
@RestController
@RequestMapping(value = "/sysMenus")
@Api(tags = {"系统菜单控制层"})
public class SysMenuController {
    private static final String MENU_GET_SUCCESS = "菜单信息获取成功" ;
    private static final String MENU_SAVE_SUCCESS = "菜单信息保存成功";
    private static final String MENU_REMOVE_SUCCESS = "菜单信息删除成功";
    private static final String MENU_BATCH_REMOVE_SUCCESS = "菜单信息批量删除成功";
    private static final String MENU_UPDATE_SUCCESS = "菜单信息修改成功";

    @Resource
    private SysMenuService sysMenuService;

    /**
     * @Author: xingyuchen
     * @Discription: 查询系统菜单列表
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "查询系统菜单列表")
    @GetMapping
    public ResultVO list(){
        List<SysMenu> sysMenus = sysMenuService.list();
        return sysMenus.size() > 0 ? ResultVO.success(MENU_GET_SUCCESS, sysMenus) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID查询系统菜单
     * @param mId 系统菜单ID
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "根据ID查询系统菜单")
    @GetMapping(value = "/{mId}")
    public ResultVO getById(@PathVariable(name = "mId")Long mId){
        // 判断ID是否为空
        Assert.notNull(mId);
        SysMenu sysMenu = sysMenuService.getById(mId);
        return !ObjectUtil.isEmpty(sysMenu) ? ResultVO.success(MENU_GET_SUCCESS, sysMenu) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 新增系统菜单
     * @param sysMenu 系统菜单对象
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "新增系统菜单")
    @PostMapping
    public ResultVO save(@RequestBody SysMenu sysMenu){
        //判断系统菜单是否为空
        Assert.notNull(sysMenu);
        boolean save = sysMenuService.save(sysMenu);
        return save ? ResultVO.success(MENU_SAVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID删除系统菜单
     * @param mId 系统菜单ID
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "根据ID删除系统菜单")
    @DeleteMapping("/{mId}")
    public ResultVO removeById(@PathVariable(name = "mId")Long mId){
        // 判断ID是否为空
        Assert.notNull(mId);
        boolean remove = sysMenuService.removeById(mId);
        return remove ? ResultVO.success(MENU_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 批量删除系统菜单
     * @param sysMenus 系统菜单集合
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "批量删除系统菜单")
    @DeleteMapping
    public ResultVO removeByIds(@RequestBody Collection<SysMenu> sysMenus){
        // 判断菜单集合是否为空
        Assert.notNull(sysMenus);
        boolean remove = sysMenuService.removeByIds(sysMenus);
        return remove ? ResultVO.success(MENU_BATCH_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 修改一条系统菜单信息
     * @param sysMenu 系统菜单对象
     * @Date: 2022/3/25 12:31 下午
     */
    @ApiOperation(value = "修改一条系统菜单信息")
    @PutMapping
    public ResultVO update(@RequestBody SysMenu sysMenu){
        // 判断菜单对象是否为空
        Assert.notNull(sysMenu);
        boolean update = sysMenuService.updateById(sysMenu);
        return update ? ResultVO.success(MENU_UPDATE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 获取某张表的所有字段
     * @param tableName
     * @Date: 2022/4/14 2:10 下午
    */
    @ApiOperation(value = "获取某张表的所有字段")
    @GetMapping(value = "/table")
    public ResultVO getTableColumn(String tableName) throws SQLException, IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("remark", DBUtil.queryTableComment(tableName));
        map.put("columns", DBUtil.queryAllColumn(tableName));
        return ResultVO.success("字段获取成功", map);
    }
}
