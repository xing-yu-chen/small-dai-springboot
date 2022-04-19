package top.smalldai.smalldaispringboot.controller.generation;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import top.smalldai.smalldaispringboot.common.annotation.GenOperationLog;
import top.smalldai.smalldaispringboot.common.constant.GenerateFileConstant;
import top.smalldai.smalldaispringboot.common.result.ResultVO;
import top.smalldai.smalldaispringboot.entity.generation.GenDatabase;
import top.smalldai.smalldaispringboot.entity.generation.GenLog;
import top.smalldai.smalldaispringboot.entity.generation.GenProject;
import top.smalldai.smalldaispringboot.entity.generation.GenSql;
import top.smalldai.smalldaispringboot.service.generation.GenDatabaseService;
import top.smalldai.smalldaispringboot.service.generation.GenLogService;
import top.smalldai.smalldaispringboot.service.generation.GenProjectService;
import top.smalldai.smalldaispringboot.service.generation.GenSqlService;
import top.smalldai.smalldaispringboot.util.DBUtil;
import top.smalldai.smalldaispringboot.util.FileUtil;
import top.smalldai.smalldaispringboot.vo.DatabaseVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 生成数据库控制层
 * @Data:Created in 2022/3/25 9:54 上午
 */
@RestController
@RequestMapping(value = "/genDatabases")
@Api(tags = "生成数据库控制层")
public class GenDatabaseController {

    /* Spring生成相关链接地址与参数 */
    private static final String SPRING_PROJECT_DOWNLOAD_URL =  "https://start.spring.io/starter.zip";
    private static final String SPRING_PROJECT_DOWNLOAD_URL_TYPE = "type=maven-project";
    private static final String SPRING_PROJECT_DOWNLOAD_URL_LANGUAGE = "language=java";
    private static final String SPRING_PROJECT_DOWNLOAD_URL_BOOT_VERSION = "bootVersion=2.6.5";
    private static final String SPRING_PROJECT_DOWNLOAD_URL_BASE_DIR = "baseDir=";
    private static final String SPRING_PROJECT_DOWNLOAD_URL_GROUP_ID = "groupId=";
    private static final String SPRING_PROJECT_DOWNLOAD_URL_ARTIFACT_ID = "artifactId=";
    private static final String SPRING_PROJECT_DOWNLOAD_NAME = "name=";
    private static final String SPRING_PROJECT_DOWNLOAD_DESCRIPTION = "description=";
    private static final String SPRING_PROJECT_DOWNLOAD_PACKAGE_NAME = "packageName=";
    private static final String SPRING_PROJECT_DOWNLOAD_PACKAGING= "packaging=jar";
    private static final String SPRING_PROJECT_DOWNLOAD_JAVA_VERSION= "javaVersion=1.8";

    @Resource
    private GenDatabaseService genDatabaseService;

    @Resource
    private GenProjectService genProjectService;

    @Resource
    private GenSqlService genSqlService;

    @Resource
    private RestTemplate restTemplate;

    /**
     * @Author: xingyuchen
     * @Discription: 创建一个数据库
     * @param databaseVO
     * @Date: 2022/3/28 9:37 上午
    */
    @GenOperationLog(value = "创建数据库sql/项目", type = false)
    @ApiOperation(value = "创建数据库sql/项目")
    @PostMapping(value = "/create")
    public ResultVO createDatabase(@RequestBody DatabaseVO databaseVO, HttpServletRequest request) {
        // 传空就报错
        Assert.notNull(databaseVO);
        // 在本地系统数据库插入数据
        GenDatabase genDatabase = new GenDatabase();
        // 设置base64格式的UUID就不会重复
        String databaseName = Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
        genDatabase.setName(databaseName)
                .setRemark(databaseVO.getRemark())
                .setUId(databaseVO.getUId())
                .setAddress(databaseVO.getAddress())
                .setPort(databaseVO.getPort())
                .setUsername(databaseVO.getUsername())
                .setPassword(databaseVO.getPassword());
        boolean saveDatabase = genDatabaseService.save(genDatabase);
        if(!saveDatabase){
            return ResultVO.failByInternalServer("库数据库写入失败，请联系管理员");
        }
        // 创建项目
        // 写入数据库表
        GenProject genProject = new GenProject();
        genProject.setName(databaseName)
                .setDId(genDatabase.getDId())
                .setPath(GenerateFileConstant.PROJECT_FILE_PATH_DOWNLOAD)
                .setNameZh(databaseVO.getRemark())
                .setUId(databaseVO.getUId())
                // 工件名和文件名相同
                .setSpringArtifact(databaseVO.getSpringArtifact())
                .setSpringFileName(databaseVO.getSpringArtifact())
                .setSpringGroup(databaseVO.getSpringGroup())
                // 包名是GroupId + Artifact
                .setSpringPackageName(databaseVO.getSpringGroup() + "." +databaseVO.getSpringArtifact())
                .setSpringDescription(databaseVO.getSpringDescription());
        // 请求下载文件
        ResponseEntity<byte[]> zip = restTemplate.exchange(SPRING_PROJECT_DOWNLOAD_URL + "?" + SPRING_PROJECT_DOWNLOAD_URL_TYPE + "&"
                + SPRING_PROJECT_DOWNLOAD_URL_LANGUAGE + "&" + SPRING_PROJECT_DOWNLOAD_URL_BOOT_VERSION + "&"
                + SPRING_PROJECT_DOWNLOAD_URL_BASE_DIR + databaseVO.getSpringArtifact() + "&" + SPRING_PROJECT_DOWNLOAD_URL_GROUP_ID + databaseVO.getSpringGroup() + "&"
                + SPRING_PROJECT_DOWNLOAD_URL_ARTIFACT_ID + databaseVO.getSpringArtifact() + "&" + SPRING_PROJECT_DOWNLOAD_NAME + databaseVO.getSpringArtifact() + "&"
                + SPRING_PROJECT_DOWNLOAD_DESCRIPTION + databaseVO.getSpringDescription() + "&" + SPRING_PROJECT_DOWNLOAD_PACKAGE_NAME + databaseVO.getSpringGroup() + "." + databaseVO.getSpringArtifact() + "&"
                + SPRING_PROJECT_DOWNLOAD_PACKAGING + "&" + SPRING_PROJECT_DOWNLOAD_JAVA_VERSION, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), byte[].class);
        byte[] zipBody = zip.getBody();
        try {
            FileUtil.saveFile(GenerateFileConstant.PROJECT_FILE_PATH_DOWNLOAD, databaseName + ".zip", zipBody);
            FileUtil.tarZip(GenerateFileConstant.PROJECT_FILE_PATH_DOWNLOAD + "/" + databaseName + ".zip");
        } catch (IOException e) {
            return ResultVO.failByInternalServer("I/O异常");
        }

        // 下载完成，写入数据库
        boolean saveProject = genProjectService.save(genProject);
        if(!saveProject){
            return ResultVO.failByInternalServer("项目数据库写入失败，请联系管理员。");
        }

        // 创建数据库
        Connection connection = null;
        PreparedStatement createDatabase = null;
        String sql =  "create database " + databaseName + " character set utf8 collate utf8_general_ci";
        request.getSession().setAttribute("sql", sql);
        boolean save = genSqlService.save(new GenSql().setType("create").setContent(sql).setDescription("创建数据-创建数据库"));
        if(!save){
            return ResultVO.failByInternalServer("SQL保存失败");
        }
        try {
            request.getSession().setAttribute("databaseName", "small_dai_springboot");
            Boolean saveOrUpdate = DBUtil.saveOrUpdate(genDatabase, sql, true);
            if(!saveOrUpdate){
                return ResultVO.failByInternalServer("生成数据库失败");
            }
        } catch (IOException e) {
            return ResultVO.failByInternalServer("I/O吸写入异常");
        } catch (SQLException throwables) {
            return ResultVO.failByInternalServer("SQL执行异常");
        }

        GenDatabase database = genDatabaseService.getOne(new QueryWrapper<GenDatabase>().eq("d_name", databaseName));
        return ResultVO.success("项目初始化", database);
    }

    private static final String GEN_DATABASE_GET_SUCCESS = "生成数据库信息获取成功" ;
    private static final String GEN_DATABASE_SAVE_SUCCESS = "生成数据库信息保存成功";
    private static final String GEN_DATABASE_REMOVE_SUCCESS = "生成数据库信息删除成功";
    private static final String GEN_DATABASE_BATCH_REMOVE_SUCCESS = "生成数据库信息批量删除成功";
    private static final String GEN_DATABASE_UPDATE_SUCCESS = "生成数据库信息修改成功";

    /**
     * @Author: xingyuchen
     * @Discription: 查询系统生成数据库
     * @Date: 2022/3/25 12:29 下午
     */
    @GenOperationLog(value = "查询系统生成数据库", type = false)
    @ApiOperation(value = "查询系统生成数据库")
    @GetMapping
    public ResultVO list(){
        List<GenDatabase> genDatabases = genDatabaseService.list();
        return genDatabases.size() > 0 ? ResultVO.success(GEN_DATABASE_GET_SUCCESS, genDatabases) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID查询生成数据库
     * @param dId
     * @Date: 2022/3/25 12:29 下午
     */
    @GenOperationLog(value = "根据ID查询生成数据库", type = false)
    @ApiOperation(value = "根据ID查询生成数据库")
    @GetMapping(value = "/{dId}")
    public ResultVO getById(@PathVariable(name = "dId")Long dId){
        // 判断ID是否为空
        Assert.notNull(dId);
        GenDatabase database = genDatabaseService.getById(dId);
        return !ObjectUtil.isEmpty(database) ? ResultVO.success(GEN_DATABASE_GET_SUCCESS, database) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 新增生成数据库
     * @param genDatabase
     * @Date: 2022/3/25 12:29 下午
     */
    @GenOperationLog(value = "新增生成数据库", type = false)
    @ApiOperation(value = "新增生成数据库")
    @PostMapping
    public ResultVO save(@RequestBody GenDatabase genDatabase){
        // 判断系统日志对象是否为空
        Assert.notNull(genDatabase);
        boolean save = genDatabaseService.save(genDatabase);
        return save ? ResultVO.success(GEN_DATABASE_SAVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID删除生成数据库
     * @param dId
     * @Date: 2022/3/25 12:30 下午
     */
    @GenOperationLog(value = "根据ID删除生成数据库", type = false)
    @ApiOperation(value = "根据ID删除生成数据库")
    @DeleteMapping("/{dId}")
    public ResultVO removeById(@PathVariable(name = "dId")Long dId){
        // 判断ID是否为空
        Assert.notNull(dId);
        boolean remove = genDatabaseService.removeById(dId);
        return remove ? ResultVO.success(GEN_DATABASE_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 批量删除生成数据库
     * @param genDatabases
     * @Date: 2022/3/25 12:30 下午
     */
    @GenOperationLog(value = "批量删除生成数据库", type = false)
    @ApiOperation(value = "批量删除生成数据库")
    @DeleteMapping
    public ResultVO removeByIds(@RequestBody Collection<GenDatabase> genDatabases){
        //判断传入对象是否为空
        Assert.notNull(genDatabases);
        boolean remove = genDatabaseService.removeByIds(genDatabases);
        return remove ? ResultVO.success(GEN_DATABASE_BATCH_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 修改一条生成数据库信息
     * @param genDatabase
     * @Date: 2022/3/25 12:31 下午
     */
    @GenOperationLog(value = "修改一条生成数据库信息", type = false)
    @ApiOperation(value = " 修改一条生成数据库信息")
    @PutMapping
    public ResultVO update(@RequestBody GenDatabase genDatabase){
        // 判断系统日志是否为空
        Assert.notNull(genDatabase);
        boolean update = genDatabaseService.updateById(genDatabase);
        return update ? ResultVO.success(GEN_DATABASE_UPDATE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

}
