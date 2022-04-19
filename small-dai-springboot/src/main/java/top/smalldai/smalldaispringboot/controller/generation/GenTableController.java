package top.smalldai.smalldaispringboot.controller.generation;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;
import top.smalldai.smalldaispringboot.common.annotation.GenOperationLog;
import top.smalldai.smalldaispringboot.common.result.ResultVO;
import top.smalldai.smalldaispringboot.entity.generation.GenDatabase;
import top.smalldai.smalldaispringboot.entity.generation.GenSql;
import top.smalldai.smalldaispringboot.entity.generation.GenTable;
import top.smalldai.smalldaispringboot.entity.generation.GenTableColumn;
import top.smalldai.smalldaispringboot.entity.system.SysLog;
import top.smalldai.smalldaispringboot.entity.system.SysUser;
import top.smalldai.smalldaispringboot.service.generation.GenDatabaseService;
import top.smalldai.smalldaispringboot.service.generation.GenSqlService;
import top.smalldai.smalldaispringboot.service.generation.GenTableColumnService;
import top.smalldai.smalldaispringboot.service.generation.GenTableService;
import top.smalldai.smalldaispringboot.service.system.SysLogService;
import top.smalldai.smalldaispringboot.to.TableTO;
import top.smalldai.smalldaispringboot.util.DBUtil;
import top.smalldai.smalldaispringboot.vo.TableVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 生成表控制层
 * @Data:Created in 2022/3/25 10:00 上午
 */
@RestController
@RequestMapping(value = "/genTables")
@Api(tags = {"生成表控制层"})
public class GenTableController {

    private static final String TABLE_PREFIX = "t_";

    @Resource
    private GenTableService genTableService;

    @Resource
    private GenDatabaseService genDatabaseService;

    @Resource
    private GenTableColumnService genTableColumnService;

    @Resource
    private GenSqlService genSqlService;

    /**
     * @Author: xingyuchen
     * @Discription: 创建表
     * @param tableVO
     * @Date: 2022/3/29 3:15 下午
    */
    @GenOperationLog(value = "建表", type = false)
    @ApiOperation(value = "建表")
    @PostMapping(value = "/create")
    public ResultVO createTable(@RequestBody TableVO tableVO){
        Assert.notNull(tableVO);
        //SysUser principal = (SysUser) SecurityUtils.getSubject().getPrincipal();

        // 有个主键未加入在设置字段的时候再去设置
        GenTable genTable = new GenTable().setName(TABLE_PREFIX + tableVO.getName())
                .setRemark(tableVO.getRemark())
                .setDId(tableVO.getDId())
                .setUId(tableVO.getUId());
        boolean save = genTableService.saveOrUpdate(genTable);
        return save ? ResultVO.success("建表", TABLE_PREFIX + tableVO.getName() + "表创建成功") : ResultVO.failByInternalServer("表创建失败");
    }

    @GetMapping(value = "/already/{dId}")
    public ResultVO createUserTable(@PathVariable(name = "dId") Long dId){
        // 生成t_user表
        GenDatabase genDatabase = genDatabaseService.getById(dId);

        String sql = "create table t_user (\n" +
                "  u_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',\n" +
                "  u_name varchar(255) NOT NULL COMMENT '用户名',\n" +
                "  u_password varchar(50) NOT NULL COMMENT '密码',\n" +
                "  u_salt varchar(20) NOT NULL COMMENT '随机盐',\n" +
                "  u_phone varchar(11) NOT NULL COMMENT '手机号',\n" +
                "  u_email varchar(50) NOT NULL COMMENT '邮箱',\n" +
                "  u_role_id bigint(20) NOT NULL COMMENT '角色ID' default 1,\n" +
                "  is_deleted tinyint NOT NULL DEFAULT '0' COMMENT '状态',\n" +
                "  gmt_create datetime NOT NULL COMMENT '创建时间',\n" +
                "  gmt_modified datetime NOT NULL COMMENT '更新时间',\n" +
                "  PRIMARY KEY (u_id)\n" +
                ") ENGINE=InnoDB default charset=utf8 collate=utf8_general_ci COMMENT='用户表';";

        try {
            Boolean saveOrUpdate = DBUtil.saveOrUpdate(genDatabase, sql, false);
            if(!saveOrUpdate){
                return ResultVO.failByInternalServer("字段初始化失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        GenSql genSql = new GenSql().setContent(sql).setDescription("创建数据 - 用户表").setType("create");
        boolean saveSql = genSqlService.save(genSql);

        // 生成t_role表
        String roleSql = "create table t_role (\n" +
                "  r_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',\n" +
                "  r_name varchar(50) NOT NULL COMMENT '角色名',\n" +
                "  r_remark varchar(50) NOT NULL COMMENT '备注',\n" +
                "  is_deleted tinyint NOT NULL DEFAULT '0' COMMENT '状态',\n" +
                "  gmt_create datetime NOT NULL COMMENT '创建时间',\n" +
                "  gmt_modified datetime NOT NULL COMMENT '更新时间',\n" +
                "  PRIMARY KEY (r_id)\n" +
                ") ENGINE=InnoDB default charset=utf8 collate=utf8_general_ci COMMENT='角色表';";
        try {
            Boolean saveOrUpdate = DBUtil.saveOrUpdate(genDatabase, roleSql, false);
            if(!saveOrUpdate){
                return ResultVO.failByInternalServer("字段初始化失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        GenSql genRoleSql = new GenSql().setContent(roleSql).setDescription("创建数据 - 角色表").setType("create");
        boolean saveRoleSql = genSqlService.save(genRoleSql);

        // 生成t_permission表

        String permissionSql = "create table t_permission (\n" +
                "  p_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',\n" +
                "  p_name varchar(50) NOT NULL COMMENT '权限名',\n" +
                "  p_remark varchar(50) NOT NULL COMMENT '备注',\n" +
                "  is_deleted tinyint NOT NULL DEFAULT '0' COMMENT '状态',\n" +
                "  gmt_create datetime NOT NULL COMMENT '创建时间',\n" +
                "  gmt_modified datetime NOT NULL COMMENT '更新时间',\n" +
                "  PRIMARY KEY (p_id)\n" +
                ") ENGINE=InnoDB default charset=utf8 collate=utf8_general_ci COMMENT='权限表';";

        try {
            Boolean saveOrUpdate = DBUtil.saveOrUpdate(genDatabase, permissionSql, false);
            if(!saveOrUpdate){
                return ResultVO.failByInternalServer("字段初始化失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        GenSql genPermissionSql = new GenSql().setContent(permissionSql).setDescription("创建数据 - 权限表").setType("create");
        boolean savePermissionSql = genSqlService.save(genPermissionSql);

        // 生成t_role_permission表

        String rolePermissionSql = "create table t_role_permission (\n" +
                "  rp_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色权限ID',\n" +
                "  r_id bigint(20) NOT NULL COMMENT '角色ID',\n" +
                "  p_id bigint(20) NOT NULL COMMENT '权限ID',\n" +
                "  is_deleted tinyint NOT NULL DEFAULT '0' COMMENT '状态',\n" +
                "  gmt_create datetime NOT NULL COMMENT '创建时间',\n" +
                "  gmt_modified datetime NOT NULL COMMENT '更新时间',\n" +
                "  PRIMARY KEY (rp_id)\n" +
                ") ENGINE=InnoDB default charset=utf8 collate=utf8_general_ci COMMENT='角色权限表';";

        try {
            Boolean saveOrUpdate = DBUtil.saveOrUpdate(genDatabase, rolePermissionSql, false);
        }catch (IOException e) {
            e.printStackTrace();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        GenSql genRolePermissionSql = new GenSql().setContent(rolePermissionSql).setDescription("创建数据 - 角色权限表").setType("create");
        boolean saveRolePermissionSql = genSqlService.save(genRolePermissionSql);


        // 生成t_menu表
        String menuStr = "create table t_menu (\n" +
                "  m_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',\n" +
                "  m_name varchar(255) NOT NULL COMMENT '菜单名称',\n" +
                "  m_url varchar(255) NOT NULL COMMENT '菜单URL',\n" +
                "  m_icon varchar(255) NOT NULL COMMENT '菜单图标',\n" +
                "  m_parent_id bigint(20) NOT NULL COMMENT '父菜单ID',\n" +
                "  m_sort bigint(20) NOT NULL COMMENT '排序',\n" +
                "  is_deleted tinyint NOT NULL DEFAULT '0' COMMENT '状态',\n" +
                "  gmt_create datetime NOT NULL COMMENT '创建时间',\n" +
                "  gmt_modified datetime NOT NULL COMMENT '更新时间',\n" +
                "  PRIMARY KEY (m_id)\n" +
                ") ENGINE=InnoDB default charset=utf8 collate=utf8_general_ci COMMENT='菜单表';";

        try {
            Boolean saveOrUpdate = DBUtil.saveOrUpdate(genDatabase, menuStr, false);
        }catch (IOException e) {
            e.printStackTrace();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        GenSql genMenuSql = new GenSql().setContent(menuStr).setDescription("创建数据 - 菜单表").setType("create");
        boolean saveMenuSql = genSqlService.save(genMenuSql);

        // 生成角色菜单表
        GenTable roleMenu = genTableService.getOne(new QueryWrapper<GenTable>().eq("t_name", "t_role_menu"));

        String roleMenuSql = "create table t_role_menu (\n" +
                "  rm_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色菜单ID',\n" +
                "  rm_role_id bigint(20) NOT NULL COMMENT '角色ID',\n" +
                "  rm_menu_id bigint(20) NOT NULL COMMENT '菜单ID',\n" +
                "  is_deleted tinyint NOT NULL DEFAULT '0' COMMENT '状态',\n" +
                "  gmt_create datetime NOT NULL COMMENT '创建时间',\n" +
                "  gmt_modified datetime NOT NULL COMMENT '更新时间',\n" +
                "  PRIMARY KEY (rm_id)\n" +
                ") ENGINE=InnoDB default charset=utf8 collate=utf8_general_ci COMMENT='角色菜单表';";

        try {
            Boolean saveOrUpdate = DBUtil.saveOrUpdate(genDatabase, roleMenuSql, false);
        }catch (IOException e) {
            e.printStackTrace();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        GenSql genRoleMenuSql = new GenSql().setContent(roleMenuSql).setDescription("创建数据 - 角色菜单表").setType("create");
        boolean saveRoleMenuSql = genSqlService.save(genRoleMenuSql);

        // 生成日志
        String logSql = "create table t_log (\n" +
                "  l_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志ID',\n" +
                "  l_user_id bigint(20) NOT NULL COMMENT '用户ID',\n" +
                "  l_content varchar(255) NOT NULL COMMENT '日志内容',\n" +
                "  l_ip varchar(255) NOT NULL COMMENT 'IP地址',\n" +
                "  gmt_create datetime NOT NULL COMMENT '创建时间',\n" +
                " gmt_modified datetime NOT NULL COMMENT '更新时间',\n" +
                "  PRIMARY KEY (l_id)\n" +
                ") ENGINE=InnoDB default charset=utf8 collate=utf8_general_ci COMMENT='日志表';";
        try {
            Boolean saveOrUpdate = DBUtil.saveOrUpdate(genDatabase, logSql, false);
        }catch (IOException e) {
            e.printStackTrace();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        GenSql genLogSql = new GenSql().setContent(roleMenuSql).setDescription("创建数据 - 系统日志表").setType("create");
        boolean saveLogSql = genSqlService.save(genRoleMenuSql);

        return saveSql && saveRoleSql && savePermissionSql && saveRolePermissionSql && saveMenuSql && saveRoleMenuSql? ResultVO.success("建表", "t_user/t_role/t_permission/t_role_permission/t_menu/t_role_menu表创建成功") : ResultVO.failByInternalServer("表创建失败");
    }

    /**
     * @Author: xingyuchen
     * @Discription: 修改表名
     * @param tableVO
     * @Date: 2022/3/30 10:24 上午
    */
    @GenOperationLog(value = "修改表名", type = true)
    @ApiOperation(value = "修改表名")
    @PostMapping(value = "/name/update")
    public ResultVO updateTableName(@RequestBody TableVO tableVO, HttpServletRequest request){
        Assert.notNull(tableVO.getTId());
        Assert.notNull(tableVO.getName());

        GenTable table = genTableService.getById(tableVO.getTId());
        GenDatabase database = genDatabaseService.getById(table.getDId());
        String sql = "ALTER TABLE " + table.getName() + " RENAME TO " + tableVO.getName() + ";";
        boolean save = genSqlService.save(new GenSql().setType("create").setContent(sql).setType("create"));
        if(!save){
            return ResultVO.failByInternalServer("SQL插入失败");
        }

        table.setName(tableVO.getName());
        // 存入数据库
        boolean b = genTableService.updateById(table);
        if (!b){
            return ResultVO.failByInternalServer("表改名失败");
        }

        request.getSession().setAttribute("databaseName", database.getName());
        request.getSession().setAttribute("sql", sql);

        // jdbc删表
        try {
            Boolean saveOrUpdate = DBUtil.saveOrUpdate(database, sql, false);
            if (!saveOrUpdate){
                return ResultVO.failByInternalServer("表名修改成功");
            }
        } catch (IOException e) {
            return ResultVO.failByInternalServer("I/O写入异常");
        } catch (SQLException throwables) {
            return ResultVO.failByInternalServer("SQL执行异常");
        }
        return ResultVO.success("表删除", "表名修改成功");
    }

    @GenOperationLog(value = "修改表备注", type = true)
    @ApiOperation(value = "修改表备注")
    @PostMapping(value = "/remark/update")
    public ResultVO updateTableRemark(@RequestBody TableVO tableVO, HttpServletRequest request){
        Assert.notNull(tableVO.getTId());
        Assert.notNull(tableVO.getRemark());

        GenTable table = genTableService.getById(tableVO.getTId());
        GenDatabase database = genDatabaseService.getById(table.getDId());
        String sql = "ALTER TABLE " + table.getName() + " COMMENT '"+ tableVO.getRemark() +"';";
        boolean create = genSqlService.save(new GenSql().setType("create").setDescription("创建数据-修改表备注。").setContent(sql));
        if(!create){
            return ResultVO.failByInternalServer("SQL插入失败");
        }
        table.setRemark(tableVO.getRemark());

        // 存入数据库
        boolean b = genTableService.updateById(table);
        if(!b){
            return ResultVO.failByInternalServer("表备注修改失败");
        }

        request.getSession().setAttribute("databaseName", database.getName());
        request.getSession().setAttribute("sql", sql);

        // jdbc修改表备注
        try {
            Boolean saveOrUpdate = DBUtil.saveOrUpdate(database, sql, false);
            if (!saveOrUpdate){
                return ResultVO.failByInternalServer("表备注修改成功");
            }
        } catch (IOException e) {
            return ResultVO.failByInternalServer("I/O写入异常");
        } catch (SQLException throwables) {
            return ResultVO.failByInternalServer("SQL执行异常");
        }
        return ResultVO.success("表备注修改", "表备注修改成功");
    }

    /**
     * @Author: xingyuchen
     * @Discription: 删除表
     * @param tId
     * @Date: 2022/3/30 2:43 下午
    */
    @GenOperationLog(value = "删除表", type = true)
    @ApiOperation(value = "删除表")
    @GetMapping(value = "/drop/{tId}")
    public ResultVO dropTable(@PathVariable(name = "tId") Long tId, HttpServletRequest request){
        Assert.notNull(tId);

        GenTable table = genTableService.getById(tId);
        GenDatabase database = genDatabaseService.getById(table.getDId());

        String sql = "DROP TABLE " + table.getName() + ";";
        genSqlService.save(new GenSql().setType("create").setDescription("创建数据-删除表。").setContent(sql));

        // 字段
        List<GenTableColumn> columns = genTableColumnService.list(new QueryWrapper<GenTableColumn>().eq("t_id", table.getTId()));
        // 批量删除字段
        boolean batch = genTableColumnService.removeBatchByIds(columns);
        if(!batch){
            return ResultVO.failByInternalServer("批量删除字段失败");
        }

        // 数据库删除记录
        boolean b = genTableService.removeById(tId);
        if(!b){
            return ResultVO.failByInternalServer("删除表失败");
        }

        request.getSession().setAttribute("databaseName", database.getName());
        request.getSession().setAttribute("sql", sql);

        // jdbc删除表
        try {
            Boolean saveOrUpdate = DBUtil.saveOrUpdate(database, sql, false);
            if (!saveOrUpdate){
                return ResultVO.failByInternalServer("表删除成功");
            }
        } catch (IOException e) {
            return ResultVO.failByInternalServer("I/O写入异常");
        } catch (SQLException throwables) {
            return ResultVO.failByInternalServer("SQL执行异常");
        }
        return ResultVO.success("表删除", "表删除成功");
    }

    private static final String GEN_TABLE_GET_SUCCESS = "生成表信息获取成功" ;
    private static final String GEN_TABLE_SAVE_SUCCESS = "生成表信息保存成功";
    private static final String GEN_TABLE_REMOVE_SUCCESS = "生成表信息删除成功";
    private static final String GEN_TABLE_BATCH_REMOVE_SUCCESS = "生成表信息批量删除成功";
    private static final String GEN_TABLE_UPDATE_SUCCESS = "生成表信息修改成功";

    /**
     * @Author: xingyuchen
     * @Discription: 查询系统生成表
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "查询系统生成表")
    @GetMapping
    public ResultVO list(){
        List<GenTable> genTables = genTableService.list();
        return genTables.size() > 0 ? ResultVO.success(GEN_TABLE_GET_SUCCESS, genTables) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID查询生成表
     * @param tId
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "根据ID查询生成表")
    @GetMapping(value = "/{tId}")
    public ResultVO getById(@PathVariable(name = "tId")Long tId){
        // 判断ID是否为空
        Assert.notNull(tId);
        GenTable genTable = genTableService.getById(tId);
        return !ObjectUtil.isEmpty(genTable) ? ResultVO.success(GEN_TABLE_GET_SUCCESS, genTable) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 新增生成表
     * @param genTable
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "新增生成表")
    @PostMapping
    public ResultVO save(@RequestBody GenTable genTable){
        // 判断系统日志对象是否为空
        Assert.notNull(genTable);
        boolean save = genTableService.save(genTable);
        return save ? ResultVO.success(GEN_TABLE_SAVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID删除生成表
     * @param tId
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "根据ID删除生成表")
    @DeleteMapping("/{tId}")
    public ResultVO removeById(@PathVariable(name = "tId")Long tId){
        // 判断ID是否为空
        Assert.notNull(tId);
        boolean remove = genTableService.removeById(tId);
        return remove ? ResultVO.success(GEN_TABLE_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 批量删除生成表
     * @param genTables
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "批量删除生成表")
    @DeleteMapping
    public ResultVO removeByIds(@RequestBody Collection<GenTable> genTables){
        //判断传入对象是否为空
        Assert.notNull(genTables);
        boolean remove = genTableService.removeByIds(genTables);
        return remove ? ResultVO.success(GEN_TABLE_BATCH_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 修改一条生成表信息
     * @param genTable
     * @Date: 2022/3/25 12:31 下午
     */
    @ApiOperation(value = " 修改一条生成表信息")
    @PutMapping
    public ResultVO update(@RequestBody GenTable genTable){
        // 判断系统日志是否为空
        Assert.notNull(genTable);
        boolean update = genTableService.updateById(genTable);
        return update ? ResultVO.success(GEN_TABLE_UPDATE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }


    /**
     * @Author: xingyuchen
     * @Discription: 根据数据库查询所有表
     * @param dId
     * @Date: 2022/4/12 3:25 下午
    */
    @ApiOperation(value = "获取数据库所有表")
    @GetMapping("/listBydId")
    public ResultVO listAllTablesByDId(Long dId){
        List<GenTable> genTables = genTableService.list(new QueryWrapper<GenTable>().eq("d_id", dId));
        return ResultVO.success("获取数据库所有表", genTables);
    }
}
