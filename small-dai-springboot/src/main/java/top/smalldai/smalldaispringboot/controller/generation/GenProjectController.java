package top.smalldai.smalldaispringboot.controller.generation;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import freemarker.template.TemplateException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.smalldai.smalldaispringboot.common.annotation.GenOperationLog;
import top.smalldai.smalldaispringboot.common.constant.*;
import top.smalldai.smalldaispringboot.common.enums.ColumnTypeEnum;
import top.smalldai.smalldaispringboot.common.result.ResultVO;
import top.smalldai.smalldaispringboot.entity.generation.GenMapper;
import top.smalldai.smalldaispringboot.entity.generation.GenProject;
import top.smalldai.smalldaispringboot.entity.generation.GenTable;
import top.smalldai.smalldaispringboot.entity.generation.GenTableColumn;
import top.smalldai.smalldaispringboot.service.generation.GenMapperService;
import top.smalldai.smalldaispringboot.service.generation.GenProjectService;
import top.smalldai.smalldaispringboot.service.generation.GenTableColumnService;
import top.smalldai.smalldaispringboot.service.generation.GenTableService;
import top.smalldai.smalldaispringboot.util.*;
import top.smalldai.smalldaispringboot.vo.*;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 生成项目文件控制层
 * @Data:Created in 2022/3/25 9:58 上午
 */
@RestController
@RequestMapping(value = "/genProjects")
@Api(tags = {"生成项目文件控制层"})
public class GenProjectController {

    @Resource
    private GenProjectService genProjectService;

    @Resource
    private GenTableService genTableService;

    @Resource
    private GenTableColumnService genTableColumnService;

    @Resource
    private GenMapperService genMapperService;


    /**
     * @param ymlVO
     * @Author: xingyuchen
     * @Discription: 生成yml文件
     * @Date: 2022/4/1 9:47 上午
     */
    @ApiOperation(value = "生成yml文件")
    @PostMapping(value = "/yml")
    public ResultVO generateYmlFile(@RequestBody YmlVO ymlVO) {
        Assert.notNull(ymlVO);

        GenProject genProject = genProjectService.getOne(new QueryWrapper<GenProject>().eq("d_id", ymlVO.getDId()));


        HashMap<String, Object> yml = new HashMap<>();
        yml.put("urlAddress", ymlVO.getAddress());
        yml.put("urlPort", ymlVO.getPort());
        yml.put("mysqlUsername", ymlVO.getUsername());
        yml.put("mysqlPassword", ymlVO.getPassword());
        yml.put("springPackageName", genProject.getSpringPackageName());
        yml.put("urlDatabaseName", genProject.getName());
        yml.put("qqEmail", ymlVO.getQqEmail());
        yml.put("token", ymlVO.getToken());

        // 获取数据库名
        String path = genProject.getPath() + "/" + genProject.getName() + "/" + genProject.getSpringFileName() + GenerateFileConstant.PROJECT_FILE_PATH_YML;

        try {
            FreemarkerUtil.generateTemplate(YmlConstant.YML_APPLICATION_CONTENT, "ymlGenerate", yml, path);
        } catch (IOException e) {
            return ResultVO.failByInternalServer("I/O异常");
        } catch (TemplateException e) {
            return ResultVO.failByInternalServer("模版异常");
        }
        return ResultVO.success("生成yml", "生成yml成功");
    }

    /**
     * @param configVO
     * @Author: xingyuchen
     * @Discription: 生成config文件
     * @Date: 2022/4/1 1:45 下午
     */
    @GenOperationLog(value = "生成Config/Common", type = false)
    @ApiOperation(value = "生成Config/Common")
    @PostMapping(value = "/config")
    public ResultVO generateConfig(@RequestBody ConfigVO configVO) {
        // 生成Common的包
        generateCommonPackage(configVO.getDId());
        // 生成Config的包
        GenProject genProject = genProjectService.getOne(new QueryWrapper<GenProject>().eq("d_id", configVO.getDId()));
        HashMap<String, Object> config = new HashMap<>();
        config.put("springPackageName", genProject.getSpringPackageName());
        config.put("whiteIP", !configVO.getWhiteIP().equals("") ? "," + configVO.getWhiteIP() : "");
        config.put("blackIP", configVO.getBlackIP());
        config.put("loginUsername", configVO.getLoginUsername());
        config.put("loginPassword", configVO.getLoginPassword());
        config.put("swaggerHeader", configVO.getSwaggerHeader());
        config.put("swaggerDescription", configVO.getSwaggerDescription());
        config.put("swaggerVersion", configVO.getSwaggerVersion());
        String basePath = genProject.getPath() + "/" + genProject.getName() + "/" + genProject.getSpringFileName() + GenerateFileConstant.PROJECT_FILE_PATH_JAVA_PACKAGE + "/" + StringUtil.dealSpringPackageName(genProject.getSpringPackageName()) + GenerateFileConstant.PROJECT_FILE_PATH_CONFIG_PACKAGE;
        FileUtil.mkdir(basePath);
        // 生成跨域问题
        String corsString = ConfigConstant.CONFIG_BASE_PACKAGE + ConfigConstant.CROS_CONFIG;

        String corsPath = basePath + "/CorsConfig.java";
        try {
            FreemarkerUtil.generateTemplate(corsString, "corsConfigGenerate", config, corsPath);
        } catch (IOException e) {
            return ResultVO.failByInternalServer("I/O异常");
        } catch (TemplateException e) {
            return ResultVO.failByInternalServer("模版异常");
        }

        // 生成druid配置
        String druidString = ConfigConstant.CONFIG_BASE_PACKAGE + ConfigConstant.DRUID_CONFIG;
        String druidPath = basePath + "/DruidConfig.java";
        try {
            FreemarkerUtil.generateTemplate(druidString, "druidConfigGenerate", config, druidPath);
        } catch (IOException e) {
            return ResultVO.failByInternalServer("I/O异常");
        } catch (TemplateException e) {
            return ResultVO.failByInternalServer("模版异常");
        }

        // 生成mybatis plus配置
        String mybatisPlusString = ConfigConstant.CONFIG_BASE_PACKAGE + ConfigConstant.MYBATIS_PLUS_CONFIG;
        String mybatisPlusPath = basePath + "/MybatisPlusConfig.java";
        try {
            FreemarkerUtil.generateTemplate(mybatisPlusString, "mybatisPlusConfigGenerate", config, mybatisPlusPath);
        } catch (IOException e) {
            return ResultVO.failByInternalServer("I/O异常");
        } catch (TemplateException e) {
            return ResultVO.failByInternalServer("模版异常");
        }

        // 生成swagger配置
        String swaggerString = ConfigConstant.CONFIG_BASE_PACKAGE + ConfigConstant.SWAGGER_CONFIG;
        String swaggerPath = basePath + "/SwaggerConfig.java";
        try {
            FreemarkerUtil.generateTemplate(swaggerString, "swaggerConfigGenerate", config, swaggerPath);
        } catch (IOException e) {
            return ResultVO.failByInternalServer("I/O异常");
        } catch (TemplateException e) {
            return ResultVO.failByInternalServer("模版异常");
        }

        // 生成shiro配置
        String realmPath = basePath + GenerateFileConstant.PROJECT_FILE_PATH_CONFIG_REALM_PACKAGE;
        // 生成realm包
        FileUtil.mkdir(realmPath);

        String realmString = ConfigConstant.REALM_CONFIG;
        String realmPathJava = realmPath + "/ShiroRealm.java";
        try {
            FreemarkerUtil.generateTemplate(realmString, "shiroRealmGenerate", config, realmPathJava);
        } catch (IOException e) {
            return ResultVO.failByInternalServer("I/O异常");
        } catch (TemplateException e) {
            return ResultVO.failByInternalServer("模版异常");
        }

        // 生成shiroConfig.java
        String shiroString = ConfigConstant.CONFIG_BASE_PACKAGE + ConfigConstant.SHIRO_CONFIG;
        String shiroPath = basePath + "/ShiroConfig.java";
        try {
            FreemarkerUtil.generateTemplate(shiroString, "shiroConfigGenerate", config, shiroPath);
        } catch (IOException e) {
            return ResultVO.failByInternalServer("I/O异常");
        } catch (TemplateException e) {
            return ResultVO.failByInternalServer("模版异常");
        }

        return ResultVO.success("创建config", "成功创建config文件");
    }

    /**
     * @param bannerVO
     * @Author: xingyuchen
     * @Discription: 生成banner
     * @Date: 2022/4/1 1:45 下午
     */
    @ApiOperation(value = "生成Banner")
    @PostMapping("/banner")
    public ResultVO generateBanner(@RequestBody BannerVO bannerVO) {
        GenProject genProject = genProjectService.getOne(new QueryWrapper<GenProject>().eq("d_id", bannerVO.getDId()));
        HashMap<String, Object> banner = new HashMap<>();
        banner.put("author", bannerVO.getAuthor());
        banner.put("createTime", bannerVO.getCreateTime());
        String basePath = GenerateFileConstant.PROJECT_FILE_FREEMARKER_BASE_PATH;
        String inputPath = "banner.ftl";
        String outputPath = genProject.getPath() + "/" + genProject.getName() + "/" + genProject.getSpringFileName() + GenerateFileConstant.PROJECT_FILE_PATH_BANNER;
        FreemarkerUtil.fileTemplate(banner, basePath, inputPath, outputPath);
        return ResultVO.success("生成banner", "banner生成成功");
    }

    @GenOperationLog(value = "生成Entity/Pom", type = false)
    @ApiOperation(value = "生成Entity/Pom")
    @GetMapping(value = "/entity/{dId}")
    public ResultVO generateEntity(@PathVariable(name = "dId") Long dId) {
        // 生成POM文件
        generatePom(dId);
        // 生成Entity文件
        GenProject genProject = genProjectService.getOne(new QueryWrapper<GenProject>().eq("d_id", dId));
        List<GenTable> genTables = genTableService.list(new QueryWrapper<GenTable>().eq("d_id", dId));

        // 生成的java的基础路径
        String basePath = genProject.getPath() + "/" + genProject.getName() + "/" + genProject.getSpringFileName() + GenerateFileConstant.PROJECT_FILE_PATH_JAVA_PACKAGE + "/" + StringUtil.dealSpringPackageName(genProject.getSpringPackageName()) + GenerateFileConstant.PROJECT_FILE_PATH_ENTITY_PACKAGE;
        // 创建entity包
        FileUtil.mkdir(basePath);

        // 遍历该数据库下的所有表
        for (int i = 0; i < genTables.size(); i++) {
            // 每读取表一次就创建一个map
            HashMap<String, Object> table = new HashMap<>();
            // 表头的基本信息
            table.put("springPackageName", genProject.getSpringPackageName());
            // 免去表这个字
            table.put("dbTableRemark", NameStrUtil.deleteBiaoName(genTables.get(i).getRemark()));
            table.put("dbTableName", genTables.get(i).getName());
            table.put("entity", NameStrUtil.tableNameStringDealing(genTables.get(i).getName()));

            // 导包 还有个类头没导
            String tableDetailHeader = EntityConstant.ENTITY_BASE_PACKAGE + EntityConstant.ENTITY_IMPORT;

            // 遍历该表下的所有字段
            List<GenTableColumn> columns = genTableColumnService.list(new QueryWrapper<GenTableColumn>().eq("t_id", genTables.get(i).getTId()));
            String tablePath = basePath + "/" + NameStrUtil.tableNameStringDealing(genTables.get(i).getName()) + ".java";
            String tableDetailCenter = "";

            for (int j = 0; j < columns.size(); j++) {
                Integer date = 0;
                Integer bigDecimal = 0;
                ColumnTypeEnum columnClassType = GenerateUtil.getColumnClassType(columns.get(j).getType().toUpperCase());

                tableDetailCenter += EntityConstant.ENTITY_CENTER_SWAGGER_VALUE_PREFIX
                        + columns.get(j).getRemark() + EntityConstant.ENTITY_CENTER_SWAGGER_VALUE_CENTER
                        + NameStrUtil.columnNameStringDealing(columns.get(j).getName()) + EntityConstant.ENTITY_CENTER_SWAGGER_VALUE_SUFFIX;
                if (columns.get(j).getPrimaryKey()) {
                    tableDetailCenter += EntityConstant.ENTITY_CENTER_MYBATIS_ID_PREFIX + columns.get(j).getName() + EntityConstant.ENTITY_CENTER_MYBATIS_ID_SUFFIX;
                } else if (columns.get(j).getName().equals("gmt_create")) {
                    tableDetailCenter += EntityConstant.ENTITY_CENTER_GMT_CREATE;
                } else if (columns.get(j).getName().equals("gmt_modified")) {
                    tableDetailCenter += EntityConstant.ENTITY_CENTER_GMT_MODIFIED;
                } else {
                    tableDetailCenter += EntityConstant.ENTITY_CENTER_MYBATIS_VALUE_PREFIX + columns.get(j).getName() + EntityConstant.ENTITY_CENTER_MYBATIS_VALUE_SUFFIX;
                }
                if (columnClassType.getTypeName().equals(ColumnTypeEnum.DATE_TYPE.getTypeName())) {
                    if (date == 0) {
                        tableDetailHeader += columnClassType.getTypeImport() + "\n";
                    }
                    date++;
                }
                if (columnClassType.getTypeName().equals(ColumnTypeEnum.BIG_DECIMAL_TYPE.getTypeName())) {
                    if (bigDecimal == 0) {
                        tableDetailHeader += columnClassType.getTypeImport() + "\n";
                    }
                    bigDecimal++;
                }
                // 字段详细
                tableDetailCenter += EntityConstant.ENTITY_CENTER_JSON_PREFIX + NameStrUtil.columnNameStringDealing(columns.get(j).getName())
                        + EntityConstant.ENTITY_CENTER_JSON_SUFFIX
                        + EntityConstant.ENTITY_CENTER_COLUMN_PREFIX + columnClassType.getTypeName()
                        + EntityConstant.ENTITY_CENTER_COLUMN_EMPTY + NameStrUtil.columnNameStringDealing(columns.get(j).getName())
                        + EntityConstant.ENTITY_CENTER_COLUMN_SUFFIX;

            }
            tableDetailHeader += EntityConstant.ENTITY_HEADER;
            String tableDetailStr = tableDetailHeader + tableDetailCenter + EntityConstant.ENTITY_FOOTER;

            // 创建文件
            try {
                FreemarkerUtil.generateTemplate(tableDetailStr, NameStrUtil.tableNameStringDealing(genTables.get(i).getName()) + "ConfigGenerate", table, tablePath);
            } catch (IOException e) {
                return ResultVO.failByInternalServer("I/O异常");
            } catch (TemplateException e) {
                return ResultVO.failByInternalServer("模版异常");
            }
        }
        return ResultVO.success("创建entity", "entity创建成功");
    }

    /**
     * @param mapperVO
     * @Author: xingyuchen
     * @Discription: 生成Mapper，前端处理分页此处不做处理
     * @Date: 2022/4/3 11:37 上午
     */
    @GenOperationLog(value = "生成Mapper/Service/ServiceImpl/Controller", type = false)
    @ApiOperation("生成Mapper/Service/ServiceImpl/Controller")
    @PostMapping(value = "/mapper")
    public ResultVO generateMapper(@RequestBody MapperVO mapperVO) {

        GenTable genTable = genTableService.getById(mapperVO.getTId());
        GenProject genProject = genProjectService.getOne(new QueryWrapper<GenProject>().eq("d_id", genTable.getDId()));
        GenMapper genMapper = new GenMapper();
        GenMapper genMapperSelect = genMapperService.getOne(new QueryWrapper<GenMapper>().eq("t_id", mapperVO.getTId()));
        if (genMapperSelect != null) {
            genMapperSelect.setTId(mapperVO.getTId()).setDeleteMore(mapperVO.getDeleteMore()).setSelectByIdMore(mapperVO.getSelectByIdMore()).setSelectListMore(mapperVO.getSelectListMore());
        } else {
            genMapper.setTId(mapperVO.getTId()).setDeleteMore(mapperVO.getDeleteMore()).setSelectByIdMore(mapperVO.getSelectByIdMore()).setSelectListMore(mapperVO.getSelectListMore());
            boolean save = genMapperService.saveOrUpdate(genMapper);
            if (!save) {
                return ResultVO.failByInternalServer("mapper保存失败");
            }
        }


        // 生成的java的基础路径
        String basePath = genProject.getPath() + "/" + genProject.getName() + "/" + genProject.getSpringFileName() + GenerateFileConstant.PROJECT_FILE_PATH_JAVA_PACKAGE + "/" + StringUtil.dealSpringPackageName(genProject.getSpringPackageName()) + GenerateFileConstant.PROJECT_FILE_PATH_MAPPER_PACKAGE;
        // 创建mapper包
        FileUtil.mkdir(basePath);

        HashMap<String, Object> table = new HashMap<>();
        String headerStr = MapperConstant.MAPPER_BASE_PACKAGE + MapperConstant.MAPPER_IMPORT;
        String centerStr = "";
        if (mapperVO.getSelectListMore() || mapperVO.getSelectByIdMore()) {
            headerStr += MapperConstant.MAPPER_CENTER_ENTITY_TO;
        }

        // 如果是联表的查询所有
        if (mapperVO.getSelectListMore()) {
            headerStr += MapperConstant.MAPPER_CENTER_SELECT_LIST_IMPORT;
            centerStr += MapperConstant.MAPPER_CENTER_SELECT_LIST;
        }

        // 如果是用ID查询所有
        if (mapperVO.getSelectByIdMore()) {
            centerStr += MapperConstant.MAPPER_CENTER_SELECT;
        }

        // 如果是联表删除
        if (mapperVO.getDeleteMore()) {
            centerStr += MapperConstant.MAPPER_CENTER_DELETE;
        }
        headerStr += MapperConstant.MAPPER_HEADER;
        // 最终模版拼接
        String str = headerStr + centerStr + MapperConstant.MAPPER_FOOTER;
        // 生成路径
        String path = basePath + "/" + NameStrUtil.tableNameStringDealing(genTable.getName()) + "Mapper.java";
        table.put("springPackageName", genProject.getSpringPackageName());
        table.put("entity", NameStrUtil.tableNameStringDealing(genTable.getName()));
        table.put("entityTO", NameStrUtil.tableNameStringDealing(genTable.getName()) + "TO");
        table.put("entityId", NameStrUtil.columnNameStringDealing(genTable.getPrimaryKey()));

        // 创建文件
        try {
            FreemarkerUtil.generateTemplate(str, NameStrUtil.tableNameStringDealing(genTable.getName()) + "MapperGenerate", table, path);
        } catch (IOException e) {
            return ResultVO.failByInternalServer("I/O异常");
        } catch (TemplateException e) {
            return ResultVO.failByInternalServer("模版异常");
        }

        // 生成service
        generateService(genTable.getTId());
        // 生成serviceImpl
        generateServiceImpl(genTable.getTId());
        // 生成controller
        generateController(genTable.getTId());

        return ResultVO.success("创建mapper/service/serviceImpl/controller", "创建mapper/service/serviceImpl/controller成功");
    }

    /**
     * @param xmlVO
     * @Author: xingyuchen
     * @Discription: 生成xml文件
     * @Date: 2022/4/3 7:32 下午
     */
    @GenOperationLog(value = "生成mapper.xml", type = false)
    @ApiOperation(value = "生成mapper.xml")
    @PostMapping(value = "/xml")
    public ResultVO generateMapperXml(@RequestBody XmlVO xmlVO) {

        GenTable getTable = genTableService.getById(xmlVO.getTId());
        GenProject genProject = genProjectService.getOne(new QueryWrapper<GenProject>().eq("d_id", getTable.getDId()));
        GenMapper genMapper = genMapperService.getOne(new QueryWrapper<GenMapper>().eq("t_id", xmlVO.getTId()));

        // 生成Mapper.xml的基础路径
        String basePath = genProject.getPath() + "/" + genProject.getName() + "/" + genProject.getSpringFileName() + GenerateFileConstant.PROJECT_FILE_PATH_MAPPERS_PACKAGE;
        // 创建mappers包
        FileUtil.mkdir(basePath);

        HashMap<String, Object> xml = new HashMap<>();


        // 拼接Header
        String headerStr = XmlConstant.XML_HEADER;
        // 拼接Center
        String centerStr = "";
        // 抽离
        if (!StringUtils.isEmpty(xmlVO.getSqlFrom())) {
            String[] tables = xmlVO.getSqlFrom().trim().split(",");
            String toHeader = ToConstant.TO_BASE_PACKAGE + ToConstant.TO_IMPORT;
            String toCenter = "";
            Integer date = 0;
            Integer bigDecimal = 0;
            String idName = "";
            GenTable genTableObject = null;
            for (int i = 0; i < tables.length; i++) {
                GenTable table = genTableService.getOne(new QueryWrapper<GenTable>().eq("t_name", tables[i]));
                if (i == 0) {
                    // 该TO类采用第一个表作为类名
                    idName = NameStrUtil.columnNameStringDealing(table.getPrimaryKey());
                    genTableObject = table;
                }
                List<GenTableColumn> columns = genTableColumnService.list(new QueryWrapper<GenTableColumn>().eq("t_id", table.getTId()));
                for (GenTableColumn column : columns) {
                    if (NameStrUtil.columnNameStringDealing(column.getName()).equals(idName) && i != 0) {
                        continue;
                    }
                    toCenter += ToConstant.TO_CENTER_SWAGGER_VALUE_PREFIX + column.getRemark() + ToConstant.TO_CENTER_SWAGGER_VALUE_CENTER + NameStrUtil.columnNameStringDealing(column.getName()) + ToConstant.TO_CENTER_SWAGGER_VALUE_SUFFIX
                            + ToConstant.TO_CENTER_JSON_PROPERTIES_PREFIX + NameStrUtil.columnNameStringDealing(column.getName()) + ToConstant.TO_CENTER_JSON_PROPERTIES_SUFFIX;
                    ColumnTypeEnum columnClassType = GenerateUtil.getColumnClassType(column.getType().toUpperCase());
                    toCenter += ToConstant.TO_CENTER_COLUMN_PREFIX + columnClassType.getTypeName() + ToConstant.TO_CENTER_COLUMN_CENTER + NameStrUtil.columnNameStringDealing(column.getName()) + ToConstant.TO_CENTER_COLUMN_SUFFIX;

                    if (columnClassType.getTypeName().equals(ColumnTypeEnum.DATE_TYPE.getTypeName())) {
                        if (date == 0) {
                            toHeader += columnClassType.getTypeImport() + "\n";
                        }
                        date++;
                    }
                    if (columnClassType.getTypeName().equals(ColumnTypeEnum.BIG_DECIMAL_TYPE.getTypeName())) {
                        if (bigDecimal == 0) {
                            toHeader += columnClassType.getTypeImport() + "\n";
                        }
                        bigDecimal++;
                    }
                }
            }
            // 生成字符串模版
            String toStr = toHeader + ToConstant.TO_HEADER + toCenter + ToConstant.TO_FOOTER;
            String toPath = genProject.getPath() + "/" + genProject.getName() + "/" + genProject.getSpringFileName() + GenerateFileConstant.PROJECT_FILE_PATH_JAVA_PACKAGE + "/" + StringUtil.dealSpringPackageName(genProject.getSpringPackageName()) + GenerateFileConstant.PROJECT_FILE_PATH_TO_PACKAGE;
            ;
            // 创建to包
            FileUtil.mkdir(toPath);
            // 生成TO文件
            String toEntityPath = toPath + "/" + NameStrUtil.tableNameStringDealing(genTableObject.getName()) + "TO.java";
            HashMap<String, Object> to = new HashMap<>();
            to.put("springPackageName", genProject.getSpringPackageName());
            to.put("toName", NameStrUtil.tableNameStringDealing(genTableObject.getName()));
            to.put("toDescription", genTableObject.getRemark());

            // 创建文件
            try {
                FreemarkerUtil.generateTemplate(toStr, idName + "TOGenerate", to, toEntityPath);
            } catch (IOException e) {
                return ResultVO.failByInternalServer("I/O异常");
            } catch (TemplateException e) {
                return ResultVO.failByInternalServer("模版异常");
            }
        }

        // 如果是联表的查询ID
        if (genMapper.getSelectByIdMore()) {
            centerStr += XmlConstant.XML_CENTER_SELECT;
            xml.put("sqlArgs", xmlVO.getSqlArgs());
            xml.put("sqlFrom", xmlVO.getSqlFrom());
            xml.put("sqlEqual", xmlVO.getSqlEqual());
            xml.put("sqlIdOne", xmlVO.getSqlIdOne());
        }
        // 如果是联表的查询所有
        if (genMapper.getSelectListMore()) {
            centerStr += XmlConstant.XML_CENTER_SELECT_LIST;
            xml.put("sqlArgs", xmlVO.getSqlArgs());
            xml.put("sqlFrom", xmlVO.getSqlFrom());
            xml.put("sqlEqual", xmlVO.getSqlEqual());
        }
        // 如果是联表的删除
        if (genMapper.getDeleteMore()) {
            centerStr += XmlConstant.XML_CENTER_DELETE;
            xml.put("sqlFrom", xmlVO.getSqlFrom());
            xml.put("sqlEqual", xmlVO.getSqlEqual());
            xml.put("sqlIdThree", xmlVO.getSqlIdThree());
        }

        // 拼接Footer
        String str = headerStr + centerStr + XmlConstant.XML_FOOTER;
        // 生成路径
        String path = basePath + "/" + NameStrUtil.tableNameStringDealing(getTable.getName()) + "Mapper.xml";
        xml.put("springPackageName", genProject.getSpringPackageName());
        xml.put("entity", NameStrUtil.tableNameStringDealing(getTable.getName()));
        xml.put("entityTO", NameStrUtil.tableNameStringDealing(getTable.getName()) + "TO");
        xml.put("entityId", NameStrUtil.columnNameStringDealing(getTable.getPrimaryKey()));

        // 创建文件
        try {
            FreemarkerUtil.generateTemplate(str, NameStrUtil.tableNameStringDealing(getTable.getName()) + "XmlGenerate", xml, path);
        } catch (IOException e) {
            return ResultVO.failByInternalServer("I/O异常");
        } catch (TemplateException e) {
            return ResultVO.failByInternalServer("模版异常");
        }

        return ResultVO.success("创建mapper.xml", "创建mapper.xml成功");
    }

    /**
     * @param dId
     * @Author: xingyuchen
     * @Discription: 生成固定文件
     * @Date: 2022/4/7 8:53 下午
     */
    @GetMapping("/generateFixedFile/{dId}")
    public ResultVO generateFixedFile(@PathVariable(name = "dId") Long dId) {
        GenProject genProject = genProjectService.getOne(new QueryWrapper<GenProject>().eq("d_id", dId));
        // 生成基础路径
        String basePath = genProject.getPath() + "/" + genProject.getName() + "/" + genProject.getSpringFileName() + GenerateFileConstant.PROJECT_FILE_PATH_JAVA_PACKAGE + "/" + StringUtil.dealSpringPackageName(genProject.getSpringPackageName());
        // 生成Mapper.xml的路径
        String mapperPath = genProject.getPath() + "/" + genProject.getName() + "/" + genProject.getSpringFileName() + GenerateFileConstant.PROJECT_FILE_PATH_MAPPERS_PACKAGE;
        // 创建mappers包
        FileUtil.mkdir(mapperPath);
        // 生成entity的基础路径
        String entityPath = basePath + GenerateFileConstant.PROJECT_FILE_PATH_ENTITY_PACKAGE;
        // 创建entity包
        FileUtil.mkdir(entityPath);
        // 生成mapper的路径
        String mapperFilePath = basePath + GenerateFileConstant.PROJECT_FILE_PATH_MAPPER_PACKAGE;
        // 创建mapper包
        FileUtil.mkdir(mapperFilePath);
        // 生成service的路径
        String servicePath = basePath + GenerateFileConstant.PROJECT_FILE_PATH_SERVICE_PACKAGE;
        // 生成to的路径
        String toPath = basePath + GenerateFileConstant.PROJECT_FILE_PATH_TO_PACKAGE;
        // 创建service包
        FileUtil.mkdir(servicePath);
        // 生成serviceImpl的路径
        String serviceImplPath = basePath + GenerateFileConstant.PROJECT_FILE_PATH_SERVICE_IMPL_PACKAGE;
        // 创建serviceImpl包
        FileUtil.mkdir(serviceImplPath);
        // 生成controller的路径
        String controllerPath = basePath + GenerateFileConstant.PROJECT_FILE_PATH_CONTROLLER_PACKAGE;
        // 创建controller包
        FileUtil.mkdir(controllerPath);
        // 生成to包
        FileUtil.mkdir(toPath);

        String springPackageName = genProject.getSpringPackageName();
        ArrayList<String> entities = new ArrayList<>();
        entities.add("Log");
        entities.add("User");
        entities.add("Role");
        entities.add("Permission");
        entities.add("RoleMenu");
        entities.add("RolePermission");
        entities.add("Menu");

        ArrayList<String> controller = new ArrayList<>();
        controller.add("Account");
        controller.add("Menu");
        controller.add("Role");
        controller.add("User");
        controller.add("Permission");

        ArrayList<String> to = new ArrayList<>();
        to.add("AccountTO");
        to.add("MenuTO");
        to.add("RoleTO");
        to.add("PermissionTO");

        // 生成xml文件
        generateFixedMapperXml(entities, mapperPath, springPackageName);
        // 生成entity文件
        generateFixedEntity(entities, entityPath, springPackageName);
        // 生成mapper文件
        generateFixedMapper(entities, mapperFilePath, springPackageName);
        // 生成service文件
        generateFixedService(entities, servicePath, springPackageName);
        // 生成serviceImpl文件
        generateFixedServiceImpl(entities, serviceImplPath, springPackageName);
        // 生成controller文件
        generateFixedController(controller, controllerPath, springPackageName);
        // 生成to文件
        generateFixedTo(to, toPath, springPackageName);
        return ResultVO.success("固定模块生成成功", "固定模块生成成功");
    }

    /**
     * @param basePath
     * @Author: xingyuchen
     * @Discription: 生成固定的xml文件
     * @Date: 2022/4/7 8:49 下午
     */
    public void generateFixedMapperXml(List<String> entities, String basePath, String springPackageName) {
        HashMap<String, Object> xmlMap = new HashMap<>();
        xmlMap.put("springPackageName", springPackageName);
        for (String entity : entities) {
            FreemarkerUtil.fileTemplate(xmlMap, GenerateFileConstant.PROJECT_FILE_FREEMARKER_BASE_PATH + "/mappers", entity + "Mapper.ftl", basePath + "/" + entity + GenerateFileConstant.PROJECT_FILE_PATH_MAPPER_XML);
        }
    }

    /**
     * @param basePath
     * @Author: xingyuchen
     * @Discription: 生成固定的entity文件
     * @Date: 2022/4/8 3:42 下午
     */
    public void generateFixedEntity(List<String> entities, String basePath, String springPackageName) {
        HashMap<String, Object> entityMap = new HashMap<>();
        entityMap.put("springPackageName", springPackageName);
        for (String entity : entities) {
            FreemarkerUtil.fileTemplate(entityMap, GenerateFileConstant.PROJECT_FILE_FREEMARKER_BASE_PATH + "/entity", entity + ".ftl", basePath + "/" + entity + ".java");
        }
    }

    /**
     * @param basePath
     * @Author: xingyuchen
     * @Discription: 生成固定的mapper文件
     * @Date: 2022/4/8 3:42 下午
     */
    public void generateFixedMapper(List<String> entities, String basePath, String springPackageName) {
        HashMap<String, Object> mapperMap = new HashMap<>();
        mapperMap.put("springPackageName", springPackageName);
        for (String entity : entities) {
            FreemarkerUtil.fileTemplate(mapperMap, GenerateFileConstant.PROJECT_FILE_FREEMARKER_BASE_PATH + "/mapper", entity + "Mapper.ftl", basePath + "/" + entity + "Mapper.java");
        }
    }

    /**
     * @param basePath
     * @Author: xingyuchen
     * @Discription: 生成固定的service文件
     * @Date: 2022/4/8 3:44 下午
     */
    public void generateFixedService(List<String> entities, String basePath, String springPackageName) {
        HashMap<String, Object> serviceMap = new HashMap<>();
        serviceMap.put("springPackageName", springPackageName);
        for (String entity : entities) {
            FreemarkerUtil.fileTemplate(serviceMap, GenerateFileConstant.PROJECT_FILE_FREEMARKER_BASE_PATH + "/service", entity + "Service.ftl", basePath + "/" + entity + "Service.java");
        }
    }

    /**
     * @param basePath
     * @Author: xingyuchen
     * @Discription: 生成固定的serviceImpl文件
     * @Date: 2022/4/8 3:45 下午
     */
    public void generateFixedServiceImpl(List<String> entities, String basePath, String springPackageName) {
        HashMap<String, Object> serviceImplMap = new HashMap<>();
        serviceImplMap.put("springPackageName", springPackageName);
        for (String entity : entities) {
            FreemarkerUtil.fileTemplate(serviceImplMap, GenerateFileConstant.PROJECT_FILE_FREEMARKER_BASE_PATH + "/service/impl", entity + "ServiceImpl.ftl", basePath + "/" + entity + "ServiceImpl.java");
        }
    }

    /**
     * @param basePath
     * @Author: xingyuchen
     * @Discription: 生成固定的controller文件
     * @Date: 2022/4/8 3:45 下午
     */
    public void generateFixedController(List<String> controllers, String basePath, String springPackageName) {
        HashMap<String, Object> controllerMap = new HashMap<>();
        controllerMap.put("springPackageName", springPackageName);
        for (String controller : controllers) {
            FreemarkerUtil.fileTemplate(controllerMap, GenerateFileConstant.PROJECT_FILE_FREEMARKER_BASE_PATH + "/controller", controller + "Controller.ftl", basePath + "/" + controller + "Controller.java");
        }
    }

    /**
     * @param basePath
     * @Author: xingyuchen
     * @Discription: 生成固定的to文件
     * @Date: 2022/4/8 3:50 下午
     */
    public void generateFixedTo(List<String> tos, String basePath, String springPackageName) {
        HashMap<String, Object> toMap = new HashMap<>();
        toMap.put("springPackageName", springPackageName);
        for (String to : tos) {
            FreemarkerUtil.fileTemplate(toMap, GenerateFileConstant.PROJECT_FILE_FREEMARKER_BASE_PATH + "/to", to + ".ftl", basePath + "/" + to + ".java");
        }
    }

    /**
     * @param tId
     * @Author: xingyuchen
     * @Discription: 生成service
     * @Date: 2022/4/6 3:22 上午
     */
    public void generateService(Long tId) {
        GenTable genTable = genTableService.getById(tId);
        GenProject genProject = genProjectService.getOne(new QueryWrapper<GenProject>().eq("d_id", genTable.getDId()));
        GenMapper genMapper = genMapperService.getOne(new QueryWrapper<GenMapper>().eq("t_id", tId));

        // 创建service的基础路径
        String basePath = genProject.getPath() + "/" + genProject.getName() + "/" + genProject.getSpringFileName() + GenerateFileConstant.PROJECT_FILE_PATH_JAVA_PACKAGE + "/" + StringUtil.dealSpringPackageName(genProject.getSpringPackageName()) + GenerateFileConstant.PROJECT_FILE_PATH_SERVICE_PACKAGE;
        FileUtil.mkdir(basePath);

        HashMap<String, Object> service = new HashMap<>();

        // headerStr
        String headerStr = ServiceConstant.SERVICE_BASE_PACKAGE + ServiceConstant.SERVICE_IMPORT;
        // centerStr
        String centerStr = "";

        if (genMapper.getSelectByIdMore()) {
            headerStr += ServiceConstant.SERVICE_CENTER_ENTITY_TO;
            centerStr += ServiceConstant.SERVICE_CENTER_SELECT;
        }
        if (genMapper.getSelectListMore()) {
            headerStr += ServiceConstant.SERVICE_CENTER_SELECT_LIST_IMPORT;
            centerStr += ServiceConstant.SERVICE_CENTER_SELECT_LIST;
        }
        if (genMapper.getDeleteMore()) {
            centerStr += ServiceConstant.SERVICE_CENTER_DELETE;
        }
        headerStr += ServiceConstant.SERVICE_HEADER;
        String str = headerStr + centerStr + ServiceConstant.SERVICE_FOOTER;

        // 生成路径
        String path = basePath + "/" + NameStrUtil.tableNameStringDealing(genTable.getName()) + "Service.java";

        service.put("springPackageName", genProject.getSpringPackageName());
        service.put("entity", NameStrUtil.tableNameStringDealing(genTable.getName()));
        service.put("entityTO", NameStrUtil.tableNameStringDealing(genTable.getName()) + "TO");
        service.put("entityId", NameStrUtil.columnNameStringDealing(genTable.getPrimaryKey()));

        // 创建文件
        try {
            FreemarkerUtil.generateTemplate(str, NameStrUtil.tableNameStringDealing(genTable.getName()) + "ServiceGenerate", service, path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param tId
     * @Author: xingyuchen
     * @Discription: 生成Impl
     * @Date: 2022/4/6 3:24 上午
     */
    public void generateServiceImpl(Long tId) {
        GenTable genTable = genTableService.getById(tId);
        GenProject genProject = genProjectService.getOne(new QueryWrapper<GenProject>().eq("d_id", genTable.getDId()));
        GenMapper genMapper = genMapperService.getOne(new QueryWrapper<GenMapper>().eq("t_id", tId));

        // 创建serviceImpl的基础路径
        String basePath = genProject.getPath() + "/" + genProject.getName() + "/" + genProject.getSpringFileName() + GenerateFileConstant.PROJECT_FILE_PATH_JAVA_PACKAGE + "/" + StringUtil.dealSpringPackageName(genProject.getSpringPackageName()) + GenerateFileConstant.PROJECT_FILE_PATH_SERVICE_IMPL_PACKAGE;
        FileUtil.mkdir(basePath);

        HashMap<String, Object> serviceImpl = new HashMap<>();

        // headerStr
        String headerStr = ServiceImplConstant.SERVICE_IMPL_BASE_PACKAGE + ServiceImplConstant.SERVICE_IMPL_IMPORT;
        // centerStr
        String centerStr = "";

        if (genMapper.getSelectByIdMore()) {
            headerStr += ServiceImplConstant.SERVICE_IMPL_CENTER_ENTITY_TO;
            centerStr += ServiceImplConstant.SERVICE_IMPL_CENTER_EXTEND + ServiceImplConstant.SERVICE_IMPL_CENTER_SELECT;
        }
        if (genMapper.getSelectListMore()) {
            headerStr += ServiceImplConstant.SERVICE_IMPL_CENTER_SELECT_LIST_IMPORT;
            centerStr += ServiceImplConstant.SERVICE_IMPL_CENTER_EXTEND + ServiceImplConstant.SERVICE_IMPL_CENTER_SELECT_LIST;
        }
        if (genMapper.getDeleteMore()) {
            centerStr += ServiceImplConstant.SERVICE_IMPL_CENTER_EXTEND + ServiceImplConstant.SERVICE_IMPL_CENTER_DELETE;
        }
        headerStr += ServiceImplConstant.SERVICE_IMPL_HEADER;
        String str = headerStr + ServiceImplConstant.SERVICE_IMPL_CENTER_RESOURCE + centerStr + ServiceImplConstant.SERVICE_IMPL_FOOTER;

        // 生成路径
        String path = basePath + "/" + NameStrUtil.tableNameStringDealing(genTable.getName()) + "ServiceImpl.java";

        serviceImpl.put("springPackageName", genProject.getSpringPackageName());
        serviceImpl.put("entity", NameStrUtil.tableNameStringDealing(genTable.getName()));
        serviceImpl.put("entityTO", NameStrUtil.tableNameStringDealing(genTable.getName()) + "TO");
        serviceImpl.put("entityId", NameStrUtil.columnNameStringDealing(genTable.getPrimaryKey()));

        // 创建文件
        try {
            FreemarkerUtil.generateTemplate(str, NameStrUtil.tableNameStringDealing(genTable.getName()) + "ServiceImplGenerate", serviceImpl, path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param tId
     * @Author: xingyuchen
     * @Discription: 生成controller
     * @Date: 2022/4/6 3:25 上午
     */
    public void generateController(Long tId) {
        GenTable genTable = genTableService.getById(tId);
        GenProject genProject = genProjectService.getOne(new QueryWrapper<GenProject>().eq("d_id", genTable.getDId()));
        GenMapper genMapper = genMapperService.getOne(new QueryWrapper<GenMapper>().eq("t_id", tId));

        // 创建controller的基础路径
        String basePath = genProject.getPath() + "/" + genProject.getName() + "/" + genProject.getSpringFileName() + GenerateFileConstant.PROJECT_FILE_PATH_JAVA_PACKAGE + "/" + StringUtil.dealSpringPackageName(genProject.getSpringPackageName()) + GenerateFileConstant.PROJECT_FILE_PATH_CONTROLLER_PACKAGE;
        FileUtil.mkdir(basePath);

        HashMap<String, Object> controller = new HashMap<>();

        // headerStr
        String headerStr = ControllerConstant.CONTROLLER_BASE_PACKAGE + ControllerConstant.CONTROLLER_IMPORT;

        // centerStr
        String centerStr = ControllerConstant.CONTROLLER_HEADER + ControllerConstant.CONTROLLER_CENTER_SELECT_LIST_ONE
                + ControllerConstant.CONTROLLER_CENTER_SELECT_ONE + ControllerConstant.CONTROLLER_CENTER_SAVE
                + ControllerConstant.CONTROLLER_CENTER_UPDATE + ControllerConstant.CONTROLLER_CENTER_REMOVE_ONE
                + ControllerConstant.CONTROLLER_CENTER_BATCH_REMOVE_ONE;

        if (genMapper.getSelectByIdMore()) {
            centerStr += ControllerConstant.CONTROLLER_CENTER_SELECT;
        }
        if (genMapper.getSelectListMore()) {
            centerStr += ControllerConstant.CONTROLLER_CENTER_SELECT_LIST;
        }
        if (genMapper.getDeleteMore()) {
            centerStr += ControllerConstant.CONTROLLER_CENTER_REMOVE + ControllerConstant.CONTROLLER_CENTER_BATCH_REMOVE;
        }

        String str = headerStr + centerStr + ControllerConstant.CONTROLLER_FOOTER;
        // 生成路径
        String path = basePath + "/" + NameStrUtil.tableNameStringDealing(genTable.getName()) + "Controller.java";

        controller.put("springPackageName", genProject.getSpringPackageName());
        controller.put("entity", NameStrUtil.tableNameStringDealing(genTable.getName()));
        controller.put("entityId", NameStrUtil.columnNameStringDealing(genTable.getPrimaryKey()));

        // 创建文件
        try {
            FreemarkerUtil.generateTemplate(str, NameStrUtil.tableNameStringDealing(genTable.getName()) + "ControllerGenerate", controller, path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param dId
     * @Author: xingyuchen
     * @Discription: 按dId生成pom文件
     * @Date: 2022/4/4 10:16 下午
     */
    public void generatePom(Long dId) {
        // 获取项目信息
        GenProject genProject = genProjectService.getOne(new QueryWrapper<GenProject>().eq("d_id", dId));
        String copyPath = GenerateFileConstant.PROJECT_FILE_PATH_DOWNLOAD + "/" + genProject.getName() + "/" + genProject.getSpringFileName() + "/" + GenerateFileConstant.PROJECT_FILE_PATH_MAVEN_POM;

        // 记录生成的pom文件里面的内容
        StringBuilder sb = new StringBuilder();

        // 读取文件
        try (BufferedReader br = Files.newBufferedReader(Paths.get(copyPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("<dependencies>")) {
                    sb.append("\n");
                    break;
                }
                // 向stringBuilder中添加内容
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("读取pom文件失败");
        }

        // 读取文件
        try (BufferedReader br = Files.newBufferedReader(Paths.get("src/main/resources/templates/pom-dependency.ftl"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 向stringBuilder中添加内容
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("读取pom文件失败");
        }
        // 生成pom文件
        try {
            FreemarkerUtil.generateTemplate(sb.toString(), "pom", null, copyPath);
        } catch (IOException e) {
            throw new RuntimeException("生成pom文件失败");
        } catch (TemplateException e) {
            throw new RuntimeException("生成pom文件失败");
        }
    }


    /**
     * @param dId
     * @Author: xingyuchen
     * @Discription: 生成common包
     * @Date: 2022/4/5 10:56 上午
     */
    public void generateCommonPackage(Long dId) {
        GenProject genProject = genProjectService.getOne(new QueryWrapper<GenProject>().eq("d_id", dId));
        String path = genProject.getPath() + "/" + genProject.getName() + "/" + genProject.getSpringFileName() + GenerateFileConstant.PROJECT_FILE_PATH_JAVA_PACKAGE + "/" + StringUtil.dealSpringPackageName(genProject.getSpringPackageName()) + GenerateFileConstant.PROJECT_FILE_PATH_COMMON_PACKAGE;
        // 生成common包
        FileUtil.mkdir(path);
        // 生成common包下的annotation包
        generateAnnotationPackage(path, genProject.getSpringPackageName());
        // 生成common包下的exception包
        generateExceptionPackage(path, genProject.getSpringPackageName());
        // 生成common包下的aop包
        generateAopPackage(path, genProject.getSpringPackageName());
        // 生成common包下的response包
        generateResponsePackage(path, genProject.getSpringPackageName());
        // 生成util包
        generateUtilPackage(path, genProject.getSpringPackageName(), genProject.getNameZh());
    }

    /**
     * @param basePath
     * @Author: xingyuchen
     * @Discription: 生成common.annotation包
     * @Date: 2022/4/5 10:50 上午
     */
    public void generateAnnotationPackage(String basePath, String packageName) {
        String path = basePath + GenerateFileConstant.PROJECT_FILE_PATH_ANNOTATION_PACKAGE;
        FileUtil.mkdir(path);
        generateAnnationJava(path, packageName);
    }

    /**
     * @param basePath
     * @Author: xingyuchen
     * @Discription: 生成common.aop包
     * @Date: 2022/4/5 10:56 上午
     */
    public void generateAopPackage(String basePath, String springPackageName) {
        String path = basePath + GenerateFileConstant.PROJECT_FILE_PATH_AOP_PACKAGE;
        FileUtil.mkdir(path);
        generateAopJava(path, springPackageName);
    }

    /**
     * @param basePath
     * @Author: xingyuchen
     * @Discription: 生成common.exception包
     * @Date: 2022/4/5 10:57 上午
     */
    public void generateExceptionPackage(String basePath, String springPackageName) {
        String path = basePath + GenerateFileConstant.PROJECT_FILE_PATH_EXCEPTION_PACKAGE;
        FileUtil.mkdir(path);
        // 生成RunningExceptionHandler.java
        generateRunningExceptionHandlerJava(path, springPackageName);
    }

    /**
     * @param basePath
     * @Author: xingyuchen
     * @Discription: 生成common.response包
     * @Date: 2022/4/5 10:58 上午
     */
    public void generateResponsePackage(String basePath, String springPackageName) {
        String path = basePath + GenerateFileConstant.PROJECT_FILE_PATH_RESPONSE_PACKAGE;
        FileUtil.mkdir(path);
        // 生成ResponseVO.java
        generateResponseJava(path, springPackageName);
    }

    /**
     * @param basePath
     * @Author: xingyuchen
     * @Discription: 生成ResponseVO.java
     * @Date: 2022/4/5 11:08 上午
     */
    public void generateResponseJava(String basePath, String springPackageName) {
        String outputPath = basePath + "/" + GenerateFileConstant.PROJECT_FILE_PATH_RESPONSE_JAVA;
        String inputPath = GenerateFileConstant.PROJECT_FILE_PATH_RESPONSE_INPUT_PATH;


        HashMap<String, Object> responseVO = new HashMap<>();
        responseVO.put("springPackageName", springPackageName);
        // 生成Response文件
        FreemarkerUtil.fileTemplate(responseVO, GenerateFileConstant.PROJECT_FILE_FREEMARKER_BASE_PATH, inputPath, outputPath);
    }

    /**
     * @param basePath
     * @Author: xingyuchen
     * @Discription: 生成RunningExceptionHandler.java
     * @Date: 2022/4/5 11:34 上午
     */
    public void generateRunningExceptionHandlerJava(String basePath, String springPackageName) {
        String outputPath = basePath + "/" + GenerateFileConstant.PROJECT_FILE_PATH_RUNNING_EXCEPTION_HANDLER_JAVA;
        String inputPath = GenerateFileConstant.PROJECT_FILE_PATH_RUNNING_EXCEPTION_HANDLER_PATH;
        HashMap<String, Object> exception = new HashMap<>();
        exception.put("springPackageName", springPackageName);
        // 生成RunningExceptionHandler文件
        FreemarkerUtil.fileTemplate(exception, GenerateFileConstant.PROJECT_FILE_FREEMARKER_BASE_PATH, inputPath, outputPath);
    }

    /**
     * @param basePath
     * @Author: xingyuchen
     * @Discription: 生成AOP包
     * @Date: 2022/4/8 4:16 下午
     */
    public void generateAopJava(String basePath, String springPackageName) {
        String outputPath = basePath + "/OperationLogAop.java";
        String inputPath = "OperationLogAop.ftl";
        HashMap<String, Object> aop = new HashMap<>();
        aop.put("springPackageName", springPackageName);
        // 生成AOP文件
        FreemarkerUtil.fileTemplate(aop, GenerateFileConstant.PROJECT_FILE_FREEMARKER_BASE_PATH, inputPath, outputPath);
    }

    public void generateAnnationJava(String basePath, String springPackageName) {
        String outputPath = basePath + "/OperationLog.java";
        String inputPath = "OperationLog.ftl";
        HashMap<String, Object> annon = new HashMap<>();
        annon.put("springPackageName", springPackageName);
        // 生成AOP文件
        FreemarkerUtil.fileTemplate(annon, GenerateFileConstant.PROJECT_FILE_FREEMARKER_BASE_PATH, inputPath, outputPath);
    }


    /**
     * @param basePath
     * @Author: xingyuchen
     * @Discription: 生成util包
     * @Date: 2022/4/7 8:41 下午
     */
    public void generateUtilPackage(String basePath, String springPackageName, String pNameZh) {
        String path = basePath + GenerateFileConstant.PROJECT_FILE_PATH_UTIL_PACKAGE;
        FileUtil.mkdir(path);
        // 生成util包下的EmailUtil.java
        HashMap<String, Object> util = new HashMap<>();
        util.put("springPackageName", springPackageName);
        util.put("pNameZh", pNameZh);
        // 生成EmailUtil文件
        FreemarkerUtil.fileTemplate(util, GenerateFileConstant.PROJECT_FILE_FREEMARKER_BASE_PATH + GenerateFileConstant.PROJECT_FILE_PATH_UTIL_PACKAGE, "EmailUtil.ftl", path + "/" + GenerateFileConstant.PROJECT_FILE_PATH_UTIL_EMAIL_UTIL_JAVA);
    }

    private static final String GEN_PROJECT_GET_SUCCESS = "生成项目信息获取成功";
    private static final String GEN_PROJECT_SAVE_SUCCESS = "生成项目信息保存成功";
    private static final String GEN_PROJECT_REMOVE_SUCCESS = "生成项目信息删除成功";
    private static final String GEN_PROJECT_BATCH_REMOVE_SUCCESS = "生成项目信息批量删除成功";
    private static final String GEN_PROJECT_UPDATE_SUCCESS = "生成项目信息修改成功";

    /**
     * @Author: xingyuchen
     * @Discription: 查询系统生成项目
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "查询系统生成")
    @GetMapping
    public ResultVO list() {
        List<GenProject> genProjects = genProjectService.list();
        return genProjects.size() > 0 ? ResultVO.success(GEN_PROJECT_GET_SUCCESS, genProjects) : ResultVO.failByNull(null);
    }

    /**
     * @param pId
     * @Author: xingyuchen
     * @Discription: 根据ID查询生成项目
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "根据ID查询生成项目")
    @GetMapping(value = "/{pId}")
    public ResultVO getById(@PathVariable(name = "pId") Long pId) {
        // 判断ID是否为空
        Assert.notNull(pId);
        GenProject genProject = genProjectService.getById(pId);
        return !ObjectUtil.isEmpty(genProject) ? ResultVO.success(GEN_PROJECT_GET_SUCCESS, genProject) : ResultVO.failByNull(null);
    }

    /**
     * @param genProject
     * @Author: xingyuchen
     * @Discription: 新增生成项目
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "新增生成项目")
    @PostMapping
    public ResultVO save(@RequestBody GenProject genProject) {
        // 判断系统日志对象是否为空
        Assert.notNull(genProject);
        boolean save = genProjectService.save(genProject);
        return save ? ResultVO.success(GEN_PROJECT_SAVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @param pId
     * @Author: xingyuchen
     * @Discription: 根据ID删除生成项目
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "根据ID删除生成项目")
    @DeleteMapping("/{pId}")
    public ResultVO removeById(@PathVariable(name = "pId") Long pId) {
        // 判断ID是否为空
        Assert.notNull(pId);
        boolean remove = genProjectService.removeById(pId);
        return remove ? ResultVO.success(GEN_PROJECT_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @param genProjects
     * @Author: xingyuchen
     * @Discription: 批量删除生成表
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "批量删除生成项目")
    @DeleteMapping
    public ResultVO removeByIds(@RequestBody Collection<GenProject> genProjects) {
        //判断传入对象是否为空
        Assert.notNull(genProjects);
        boolean remove = genProjectService.removeByIds(genProjects);
        return remove ? ResultVO.success(GEN_PROJECT_BATCH_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @param genProject
     * @Author: xingyuchen
     * @Discription: 修改一条生成项目信息
     * @Date: 2022/3/25 12:31 下午
     */
    @ApiOperation(value = " 修改一条生成项目信息")
    @PutMapping
    public ResultVO update(@RequestBody GenProject genProject) {
        // 判断系统日志是否为空
        Assert.notNull(genProject);
        boolean update = genProjectService.updateById(genProject);
        return update ? ResultVO.success(GEN_PROJECT_UPDATE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }
}
