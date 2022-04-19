package top.smalldai.smalldaispringboot.controller.generation;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.smalldai.smalldaispringboot.common.annotation.GenOperationLog;
import top.smalldai.smalldaispringboot.common.result.ResultVO;
import top.smalldai.smalldaispringboot.entity.generation.GenDatabase;
import top.smalldai.smalldaispringboot.entity.generation.GenSql;
import top.smalldai.smalldaispringboot.entity.generation.GenTable;
import top.smalldai.smalldaispringboot.entity.generation.GenTableColumn;
import top.smalldai.smalldaispringboot.service.generation.GenDatabaseService;
import top.smalldai.smalldaispringboot.service.generation.GenSqlService;
import top.smalldai.smalldaispringboot.service.generation.GenTableColumnService;
import top.smalldai.smalldaispringboot.service.generation.GenTableService;
import top.smalldai.smalldaispringboot.util.DBUtil;
import top.smalldai.smalldaispringboot.vo.ColumnVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 生成表字段控制层
 * @Data:Created in 2022/3/25 10:01 上午
 */
@RestController
@RequestMapping(value = "/genTableColumn")
@CrossOrigin(maxAge = 3600, origins = "*")
@Api(tags = {"生成表字段控制层"})
public class GenTableColumnController {

    @Resource
    private GenTableColumnService genTableColumnService;

    @Resource
    private GenTableService genTableService;

    @Resource
    private GenDatabaseService genDatabaseService;

    @Resource
    private GenSqlService genSqlService;

    @GenOperationLog(value = "建字段", type = true)
    @ApiOperation(value = "建字段")
    @PostMapping(value = "/create")
    public ResultVO setColumnForTable(@RequestBody List<ColumnVO> columnVOs, HttpServletRequest request){
        Assert.notNull(columnVOs);
        GenTable genTable = genTableService.getById(columnVOs.get(0).getTId());
        GenDatabase genDatabase = genDatabaseService.getById(genTable.getDId());
        String sql =  "create table " + genTable.getName() + " (";
        String primaryKey = "";
        // 主键只能有一个有多个就报错
        // 自增也是
        Integer ipkey = 0;
        Integer iauto = 0;
        for (ColumnVO column : columnVOs) {
            if(column.getPrimaryKey()){
                ipkey++;
            }
            if(column.getAuto()){
                iauto++;
            }
        }
        if(ipkey > 1 || iauto > 1){
            return ResultVO.failByInternalServer("主键/自增不能有多个");
        }else if(ipkey == 0){
            return ResultVO.failByInternalServer("主键必须有一个");
        }

        for (ColumnVO column : columnVOs) {
            //一、 数据库里新增字段类型
            GenTableColumn genTableColumn = new GenTableColumn();
            genTableColumn.setUId(column.getUId())
                    .setType(column.getType())
                    .setTId(column.getTId())
                    .setSize(column.getSize())
                    .setRemark(column.getRemark())
                    .setPrimaryKey(column.getPrimaryKey())
                    .setName(column.getName())
                    .setAuto(column.getAuto())
                    .setIfNull(column.getIfNull()).setGmtCreate(new Date());
            boolean save = genTableColumnService.save(genTableColumn);
            if(!save){
                return ResultVO.failByInternalServer("表数据保存失败");
            }

            //三、 向数据库里增加一张表
            if(!ObjectUtil.isNull(column.getName())){
                sql += column.getName() + " ";
                if(!ObjectUtil.isNull(column.getType())){
                    sql += column.getType();
                }
                if(column.getSize() != 0){
                    sql += "("+ column.getSize() +")";
                }
                if(column.getIfNull() && column.getAuto()){
                    sql += " auto_increment ";
                }else if(column.getIfNull() && !column.getAuto()){
                    sql += " not null ";
                }
                if(column.getPrimaryKey()){
                    primaryKey = column.getName();
                }
                if(ObjectUtil.isNotNull(column.getRemark())){
                    sql += "comment \'" + column.getRemark() + "\'";
                }
                sql += ",";
            }
        }
        // 更新表的自增字段
        genTable.setPrimaryKey(primaryKey);
        boolean update = genTableService.updateById(genTable);
        if(!update){
            return ResultVO.failByInternalServer("表自增修改失败");
        }

        // 建表
        sql += "primary key(" + primaryKey +")";
        sql += ")engine = innodb default charset=utf8 collate=utf8_general_ci comment \'" + genTable.getRemark() + "\';";
        request.getSession().setAttribute("databaseName", genDatabase.getName());
        request.getSession().setAttribute("sql", sql);

        boolean create = genSqlService.save(new GenSql().setType("create").setContent(sql).setDescription("创建数据 - 建表。"));
        if(!create){
            return ResultVO.failByInternalServer("建表SQL插入失败");
        }
        try {
            Boolean saveOrUpdate = DBUtil.saveOrUpdate(genDatabase, sql, false);
        } catch (IOException e) {
            return ResultVO.failByInternalServer("I/O吸写入异常");
        } catch (SQLException throwables) {
            return ResultVO.failByInternalServer("SQL执行异常");
        }
        return ResultVO.success("字段初始化","字段初始化完成");
    }

    /**
     * @Author: xingyuchen
     * @Discription: 新增字段
     * @param columnVO
     * @Date: 2022/3/30 9:26 上午
    */
    @GenOperationLog(value = "新增字段", type = true)
    @ApiOperation(value = "新增字段")
    @PostMapping(value = "/save")
    public ResultVO saveColumn(@RequestBody ColumnVO columnVO, HttpServletRequest request){
        Assert.notNull(columnVO);
        GenTable genTable = genTableService.getById(columnVO.getTId());
        GenDatabase database = genDatabaseService.getById(genTable.getDId());
        // 写入数据库

        GenTableColumn genTableColumn = new GenTableColumn();
        genTableColumn.setTId(columnVO.getTId()).setName(columnVO.getName()).setType(columnVO.getType()).setUId(columnVO.getUId());
        // 为创建的数据表新增字段
        String sql = "ALTER TABLE "+ genTable.getName() +" ADD COLUMN "+ columnVO.getName() + " " + columnVO.getType();
        if(columnVO.getSize() != 0){
            sql += "("+ columnVO.getSize() +")";
            genTableColumn.setSize(columnVO.getSize());
        }
        if(columnVO.getIfNull() && columnVO.getAuto()){
            sql += " auto_increment ";
            genTableColumn.setAuto(true).setIfNull(true);
        }else if(columnVO.getIfNull() && !columnVO.getAuto()){
            sql += " not null ";
            genTableColumn.setIfNull(true);
        }
        if(ObjectUtil.isNotNull(columnVO.getRemark())){
            sql += " comment \'" + columnVO.getRemark() + "\'";
            genTableColumn.setRemark(columnVO.getRemark());
        }

        // 插入数据库
        genTableColumn.setGmtCreate(new Date());
        boolean save = genTableColumnService.saveOrUpdate(genTableColumn);
        if(!save){
            return ResultVO.failByInternalServer("字段新增插入失败");
        }

        sql += ";";

        boolean create = genSqlService.save(new GenSql().setType("create").setContent(sql).setDescription("创建数据 - 新增字段。"));
        if(!create){
            return ResultVO.failByInternalServer("新增SQL插入失败");
        }

        request.getSession().setAttribute("databaseName", database.getName());
        request.getSession().setAttribute("sql", sql);

        try {
            Boolean saveOrUpdate = DBUtil.saveOrUpdate(database, sql, false);
            if(!saveOrUpdate){
                return ResultVO.failByInternalServer("字段新增失败");
            }
        } catch (IOException e) {
            return ResultVO.failByInternalServer("I/O吸写入异常");
        } catch (SQLException throwables) {
            return ResultVO.failByInternalServer("SQL执行异常");
        }
        return ResultVO.success("字段新增","字段新增成功");
    }
    
    /**
     * @Author: xingyuchen
     * @Discription: 修改备注(sql正确改不成功，后续修补)
     * @param columnVO
     * @Date: 2022/3/30 9:26 上午
    */
    @GenOperationLog(value = "修改备注", type = true)
    @ApiOperation(value = "修改备注")
    @PostMapping(value = "/commit/update")
    public ResultVO updateCommit(@RequestBody ColumnVO columnVO, HttpServletRequest request){
        Assert.notNull(columnVO);

        GenTableColumn tc = genTableColumnService.getOne(new QueryWrapper<GenTableColumn>().eq("tc_name", columnVO.getName()));

        GenTable genTable = genTableService.getById(tc.getTId());
        GenDatabase genDatabase = genDatabaseService.getById(genTable.getDId());

        tc.setRemark(columnVO.getRemark());

        String updateSql = "alter table " + genTable.getName() + " modify column " + tc.getName() + " " + tc.getType();
        if(tc.getSize() != 0){
            updateSql += "(" + tc.getSize() + ")";
        }
        if(tc.getIfNull() && tc.getAuto()){
            updateSql += " auto_increment ";
        }else if(tc.getIfNull() && !tc.getAuto()){
            updateSql += " not null ";
        }
        if(ObjectUtil.isNotNull(tc.getRemark())){
            updateSql += " comment \'" + tc.getRemark() + "\'";
        }

        boolean create = genSqlService.save(new GenSql().setType("create").setContent(updateSql).setDescription("创建数据 - 修改备注。"));
        if(!create){
            return ResultVO.failByInternalServer("修改备注SQL插入失败");
        }

        // 修改数据库字段属性
        boolean updateColumnCommit = genTableColumnService.updateById(tc);
        if(!updateColumnCommit){
            return ResultVO.failByInternalServer("修改数据库字段属性失败");
        }

        request.getSession().setAttribute("databaseName", genDatabase.getName());
        request.getSession().setAttribute("sql", updateSql);

        try {
            Boolean update = DBUtil.saveOrUpdate(genDatabase, updateSql, false);
            if(!update){
                return ResultVO.failByInternalServer("字段备注修改失败");
            }
        } catch (IOException e) {
            return ResultVO.failByInternalServer("I/O写入异常");
        } catch (SQLException throwables) {
            return ResultVO.failByInternalServer("SQL执行异常");
        }
        return ResultVO.success("字段备注修改","字段备注修改成功");
    }

    /**
     * @Author: xingyuchen
     * @Discription: 修改字段类型
     * @param columnVO
     * @Date: 2022/3/30 10:40 上午
    */
    @GenOperationLog(value = "修改字段类型", type = true)
    @ApiOperation(value = "修改字段类型")
    @PostMapping(value = "/type/update")
    public ResultVO updateType(@RequestBody ColumnVO columnVO, HttpServletRequest request){
        GenTableColumn column = genTableColumnService.getById(columnVO.getTcId());
        GenTable table = genTableService.getById(column.getTId());
        GenDatabase database = genDatabaseService.getById(table.getDId());
        String sql = "alter table " + table.getName() + " modify " +column.getName() + " " + columnVO.getType();
        column.setType(columnVO.getType());
        column.setSize(0);
        if(ObjectUtil.isNotNull(columnVO.getSize()) && columnVO.getSize() != 0){
            sql += "(" + columnVO.getSize() + ")";
            column.setSize(columnVO.getSize());
        }
        if(column.getIfNull() && column.getAuto()){
            sql += " auto_increment ";
        }else if(column.getIfNull() && !column.getAuto()){
            sql += " not null ";
        }
        if(ObjectUtil.isNotNull(column.getRemark())){
            sql += " comment \'" + column.getRemark() + "\'";
        }
        boolean create = genSqlService.save(new GenSql().setType("create").setContent(sql).setDescription("创建数据 - 修改字段类型。"));
        if(!create){
            return ResultVO.failByInternalServer("修改字段类型SQL插入失败");
        }

        // 保存数据库
        boolean b = genTableColumnService.updateById(column);
        if(!b){
            return ResultVO.failByInternalServer("数据库保存失败");
        }

        request.getSession().setAttribute("databaseName", database.getName());
        request.getSession().setAttribute("sql", sql);

        try {
            Boolean saveOrUpdate = DBUtil.saveOrUpdate(database, sql, false);
            if(!saveOrUpdate){
                return ResultVO.failByInternalServer("修改字段类型失败");
            }
        } catch (IOException e) {
            return ResultVO.failByInternalServer("I/O写入异常");
        } catch (SQLException throwables) {
            return ResultVO.failByInternalServer("SQL执行异常");
        }
        return ResultVO.success("修改字段类型", "修改字段类型成功");
    }
    
    /**
     * @Author: xingyuchen
     * @Discription:  修改字段名称
     * @param columnVO
     * @Date: 2022/3/30 1:28 下午
    */
    @GenOperationLog(value = "修改字段名称", type = true)
    @ApiOperation(value = "修改字段名称")
    @PostMapping(value = "/name/update")
    public ResultVO updateColumnName(@RequestBody ColumnVO columnVO, HttpServletRequest request){
        GenTableColumn column = genTableColumnService.getById(columnVO.getTcId());
        GenTable table = genTableService.getById(column.getTId());
        GenDatabase database = genDatabaseService.getById(table.getDId());
        String sql = "alter table " + table.getName() + " change " + column.getName() + " " + columnVO.getName() + " " + column.getType();
        column.setName(columnVO.getName());
        if(column.getSize() != 0){
            sql += "(" + column.getSize() + ")";
        }
        if(column.getIfNull() && column.getAuto()){
            sql += " auto_increment ";
        }else if(column.getIfNull() && !column.getAuto()){
            sql += " not null ";
        }
        if(ObjectUtil.isNotNull(column.getRemark())){
            sql += " comment \'" + column.getRemark() + "\'";
        }
        sql += ";";

        boolean create = genSqlService.save(new GenSql().setType("create").setContent(sql).setDescription("创建数据 - 修改字段名称。"));
        if(!create){
            return ResultVO.failByInternalServer("修改字段名称SQL插入失败");
        }

        // 保存数据库
        boolean b = genTableColumnService.updateById(column);
        if(!b){
            return ResultVO.failByInternalServer("保存数据库失败");
        }

        request.getSession().setAttribute("databaseName", database.getName());
        request.getSession().setAttribute("sql", sql);

        // jdbc修改数据
        try {
            Boolean saveOrUpdate = DBUtil.saveOrUpdate(database, sql, false);
            if (!saveOrUpdate){
                return ResultVO.failByInternalServer("修改字段名称失败");
            }
        } catch (IOException e) {
            return ResultVO.failByInternalServer("I/O写入异常");
        } catch (SQLException throwables) {
            return ResultVO.failByInternalServer("SQL执行异常");
        }
        return ResultVO.success("修改字段名称", "修改字段名称成功");
    }


    /**
     * @Author: xingyuchen
     * @Discription: 删除一个字段
     * @param columnVO
     * @Date: 2022/3/30 2:09 下午
    */
    @GenOperationLog(value = "删除一个字段", type = true)
    @ApiOperation(value = "删除个一字段")
    @PostMapping(value = "/drop")
    public ResultVO dropColumn(@RequestBody ColumnVO columnVO, HttpServletRequest request){
        GenTableColumn column = genTableColumnService.getById(columnVO.getTcId());
        GenTable table = genTableService.getById(column.getTId());
        GenDatabase database = genDatabaseService.getById(table.getDId());
        String sql = "ALTER TABLE " + table.getName() + " DROP " + columnVO.getName() + ";";
        boolean create = genSqlService.save(new GenSql().setType("create").setContent(sql).setDescription("创建数据 - 删除字段。"));
        if(!create){
            return ResultVO.failByInternalServer("删除字段SQL插入失败");
        }
        // 从数据库删除记录
        boolean b = genTableColumnService.removeById(column.getTcId());
        if(!b){
            return ResultVO.failByInternalServer("字段数据库记录删除失败");
        }

        request.getSession().setAttribute("databaseName", database.getName());
        request.getSession().setAttribute("sql", sql);

        // jdbc删除字段
        try {
            Boolean saveOrUpdate = DBUtil.saveOrUpdate(database, sql, false);
            if (!saveOrUpdate){
                return ResultVO.failByInternalServer("字段删除失败");
            }
        } catch (IOException e) {
            return ResultVO.failByInternalServer("I/O写入异常");
        } catch (SQLException throwables) {
            return ResultVO.failByInternalServer("SQL执行异常");
        }
        return ResultVO.success("删除字段", "字段删除成功");
    }

    private static final String GEN_TABLE_COLUMN_GET_SUCCESS = "生成表字段信息获取成功" ;
    private static final String GEN_TABLE_COLUMN_SAVE_SUCCESS = "生成表字段信息保存成功";
    private static final String GEN_TABLE_COLUMN_REMOVE_SUCCESS = "生成表字段信息删除成功";
    private static final String GEN_TABLE_COLUMN_BATCH_REMOVE_SUCCESS = "生成表字段信息批量删除成功";
    private static final String GEN_TABLE_COLUMN_UPDATE_SUCCESS = "生成表字段信息修改成功";

    /**
     * @Author: xingyuchen
     * @Discription: 查询系统生成表字段
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "查询系统字段生成表")
    @GetMapping
    public ResultVO list(){
        List<GenTableColumn> genTableColumns = genTableColumnService.list();
        return genTableColumns.size() > 0 ? ResultVO.success(GEN_TABLE_COLUMN_GET_SUCCESS, genTableColumns) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID查询生成表字段
     * @param tcId
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "根据ID查询生成表字段")
    @GetMapping(value = "/{tcId}")
    public ResultVO getById(@PathVariable(name = "tcId")Long tcId){
        // 判断ID是否为空
        Assert.notNull(tcId);
        GenTableColumn genTableColumn = genTableColumnService.getById(tcId);
        return !ObjectUtil.isEmpty(genTableColumn) ? ResultVO.success(GEN_TABLE_COLUMN_GET_SUCCESS, genTableColumn) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 新增生成表字段
     * @param genTableColumn
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "新增生成表字段")
    @PostMapping
    public ResultVO save(@RequestBody GenTableColumn genTableColumn){
        // 判断系统日志对象是否为空
        Assert.notNull(genTableColumn);
        boolean save = genTableColumnService.saveOrUpdate(genTableColumn);
        return save ? ResultVO.success(GEN_TABLE_COLUMN_SAVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID删除生成表字段
     * @param tcId
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "根据ID删除生成表字段")
    @DeleteMapping("/{tcId}")
    public ResultVO removeById(@PathVariable(name = "tcId")Long tcId){
        // 判断ID是否为空
        Assert.notNull(tcId);
        boolean remove = genTableService.removeById(tcId);
        return remove ? ResultVO.success(GEN_TABLE_COLUMN_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 批量删除生成表字段
     * @param genTableColumns
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "批量删除生成表字段")
    @DeleteMapping
    public ResultVO removeByIds(@RequestBody Collection<GenTableColumn> genTableColumns){
        //判断传入对象是否为空
        Assert.notNull(genTableColumns);
        boolean remove = genTableColumnService.removeByIds(genTableColumns);
        return remove ? ResultVO.success(GEN_TABLE_COLUMN_BATCH_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 修改一条生成表字段信息
     * @param genTableColumn
     * @Date: 2022/3/25 12:31 下午
     */
    @ApiOperation(value = " 修改一条生成表字段信息")
    @PutMapping
    public ResultVO update(@RequestBody GenTableColumn genTableColumn){
        // 判断系统日志是否为空
        Assert.notNull(genTableColumn);
        boolean update = genTableColumnService.updateById(genTableColumn);
        return update ? ResultVO.success(GEN_TABLE_COLUMN_UPDATE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    @ApiOperation(value = "获取某表下所有的字段")
    @GetMapping(value = "/columnsBytId")
    public ResultVO listColumnsBytId(Long tId){
        List<GenTableColumn> columnList = genTableColumnService.list(new QueryWrapper<GenTableColumn>().eq("t_id", tId));
        return ResultVO.success("获取成功", columnList);
    }

}
