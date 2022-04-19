package top.smalldai.smalldaispringboot.controller.generation;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import top.smalldai.smalldaispringboot.common.constant.GenerateFileConstant;
import top.smalldai.smalldaispringboot.common.result.ResultVO;
import top.smalldai.smalldaispringboot.entity.generation.GenDatabase;
import top.smalldai.smalldaispringboot.entity.generation.GenSqlScript;
import top.smalldai.smalldaispringboot.entity.generation.GenTable;
import top.smalldai.smalldaispringboot.service.generation.GenDatabaseService;
import top.smalldai.smalldaispringboot.service.generation.GenSqlScriptService;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 生成SQL脚本控制层
 * @Data:Created in 2022/3/25 9:59 上午
 */
@RestController
@RequestMapping(value = "/genSqlScript")
@Api(tags = {"生成SQL脚本控制层"})
public class GenSqlScriptController {
    @Value(value = "${smalldai.mysql.username}")
    private String username;

    @Value(value = "${smalldai.mysql.password}")
    private String password;

    @Value(value = "${smalldai.mysql.host}")
    private String host;

    @Value(value = "${smalldai.mysql.mysqldump}")
    private String mysqldump;

    @Resource
    private GenDatabaseService genDatabaseService;

    @Resource
    private GenSqlScriptService genSqlScriptService;

    /**
     * @Author: xingyuchen
     * @Discription: 生成SQL脚本地址
     * @param dId
     * @Date: 2022/3/30 3:09 下午
     */
    @ApiOperation(value = "生成SQL脚本地址")
    @GetMapping(value = "/sqldump")
    public ResultVO generateSqlScript(Long dId) throws InterruptedException, IOException {
        GenDatabase database = genDatabaseService.getById(dId);
        String path = GenerateFileConstant.PROJECT_FILE_PATH_DOWNLOAD + "/" + database.getName() + "/" +database.getName();
        GenSqlScript genSqlScript = new GenSqlScript().setName(database.getName() + ".sql").setPath(path).setDId(dId);
        // 存入数据库记录
        boolean save = genSqlScriptService.saveOrUpdate(genSqlScript);
        if(!save){
            return ResultVO.failByInternalServer("保存sql脚本记录成功");
        }
        //调用系统黑窗口，运行命令
        Process p = new ProcessBuilder(mysqldump + "mysqldump","-u","root","-p123456",database.getName()).start();
        ByteArrayOutputStream dmp = new ByteArrayOutputStream();
        // Use FileOutputStream dmp = ... in real cases.
        Thread t1 = copyStreamsInBackground(p.getInputStream(), dmp);
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        Thread t2 = copyStreamsInBackground(p.getErrorStream(), err);
        t1.join();
        t2.join();
        int exitCode = p.waitFor();
        if (exitCode != 0) {
            String errors = new String(err.toByteArray(), Charset.forName("utf-8"));
            System.err.println(errors);
        } else {
            String dumps = new String(dmp.toByteArray(), Charset.forName("utf-8"));
            FileWriter writer = new FileWriter(path + ".sql");
            writer.write(dumps);
        }
        return ResultVO.success("保存SQL脚本", "保存SQL脚本成功");
    }

    private static Thread copyStreamsInBackground(final InputStream is, final OutputStream os) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    IOUtils.copy(is, os);
                    os.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new IllegalStateException(ex);
                }
            }
        });
        t.start();
        return t;
    }

    private static final String GEN_SQL_SCRIPT_GET_SUCCESS = "生成SQL脚本信息获取成功" ;
    private static final String GEN_SQL_SCRIPT_SAVE_SUCCESS = "生成SQL脚本信息保存成功";
    private static final String GEN_SQL_SCRIPT_REMOVE_SUCCESS = "生成SQL脚本信息删除成功";
    private static final String GEN_SQL_SCRIPT_BATCH_REMOVE_SUCCESS = "生成SQL脚本信息批量删除成功";
    private static final String GEN_SQL_SCRIPT_UPDATE_SUCCESS = "生成SQL脚本信息修改成功";

    /**
     * @Author: xingyuchen
     * @Discription: 查询系统生成SQL脚本信息
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "查询系统生成SQL脚本信息")
    @GetMapping
    public ResultVO list(){
        List<GenSqlScript> genSqlScripts = genSqlScriptService.list();
        return genSqlScripts.size() > 0 ? ResultVO.success(GEN_SQL_SCRIPT_GET_SUCCESS, genSqlScripts) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID查询生成SQL脚本信息
     * @param ssId
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "根据ID查询生成SQL脚本信息")
    @GetMapping(value = "/{ssId}")
    public ResultVO getById(@PathVariable(name = "ssId")Long ssId){
        // 判断ID是否为空
        Assert.notNull(ssId);
        GenSqlScript genSqlScript = genSqlScriptService.getById(ssId);
        return !ObjectUtil.isEmpty(genSqlScript) ? ResultVO.success(GEN_SQL_SCRIPT_GET_SUCCESS, genSqlScript) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 新增生成SQL脚本信息
     * @param genSqlScript
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "新增生成SQL脚本信息")
    @PostMapping
    public ResultVO save(@RequestBody GenSqlScript genSqlScript){
        // 判断系统日志对象是否为空
        Assert.notNull(genSqlScript);
        boolean save = genSqlScriptService.save(genSqlScript);
        return save ? ResultVO.success(GEN_SQL_SCRIPT_SAVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID删除生成SQL脚本信息
     * @param ssId
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "根据ID删除生成SQL脚本信息")
    @DeleteMapping("/{ssId}")
    public ResultVO removeById(@PathVariable(name = "ssId")Long ssId){
        // 判断ID是否为空
        Assert.notNull(ssId);
        boolean remove = genSqlScriptService.removeById(ssId);
        return remove ? ResultVO.success(GEN_SQL_SCRIPT_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 批量删除生成SQL脚本信息
     * @param genSqlScripts
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "批量删除生成SQL脚本信息")
    @DeleteMapping
    public ResultVO removeByIds(@RequestBody Collection<GenSqlScript> genSqlScripts){
        //判断传入对象是否为空
        Assert.notNull(genSqlScripts);
        boolean remove = genSqlScriptService.removeByIds(genSqlScripts);
        return remove ? ResultVO.success(GEN_SQL_SCRIPT_BATCH_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 修改一条生成SQL脚本信息
     * @param genSqlScript
     * @Date: 2022/3/25 12:31 下午
     */
    @ApiOperation(value = " 修改一条生成SQL脚本信息")
    @PutMapping
    public ResultVO update(@RequestBody GenSqlScript genSqlScript){
        // 判断系统日志是否为空
        Assert.notNull(genSqlScript);
        boolean update = genSqlScriptService.updateById(genSqlScript);
        return update ? ResultVO.success(GEN_SQL_SCRIPT_UPDATE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }
}
