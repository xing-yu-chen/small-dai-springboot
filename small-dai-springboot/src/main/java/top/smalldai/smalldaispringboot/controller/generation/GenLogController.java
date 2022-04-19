package top.smalldai.smalldaispringboot.controller.generation;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;
import top.smalldai.smalldaispringboot.common.annotation.GenOperationLog;
import top.smalldai.smalldaispringboot.common.result.ResultVO;
import top.smalldai.smalldaispringboot.entity.generation.GenLog;
import top.smalldai.smalldaispringboot.service.generation.GenLogService;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 生成日志控制层
 * @Data:Created in 2022/3/25 9:56 上午
 */
@RestController
@RequestMapping(value = "/genLogs")
@Api(tags = {"生成系统日志"})
@RequiresRoles("admin")
public class GenLogController {

    private static final String GEN_LOG_GET_SUCCESS = "生成日志信息获取成功" ;
    private static final String GEN_LOG_SAVE_SUCCESS = "生成日志信息保存成功";
    private static final String GEN_LOG_REMOVE_SUCCESS = "生成日志信息删除成功";
    private static final String GEN_LOG_BATCH_REMOVE_SUCCESS = "生成日志信息批量删除成功";
    private static final String GEN_LOG_UPDATE_SUCCESS = "生成日志信息修改成功";

    @Resource
    private GenLogService genLogService;

    /**
     * @Author: xingyuchen
     * @Discription: 查询系统生成日志
     * @Date: 2022/3/25 12:29 下午
     */
    @GenOperationLog(value = "查询系统生成日志", type = false)
    @ApiOperation(value = "查询系统生成日志")
    @GetMapping
    public ResultVO list(){
        List<GenLog> genLogs = genLogService.list();
        return genLogs.size() > 0 ? ResultVO.success(GEN_LOG_GET_SUCCESS, genLogs) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID查询生成日志
     * @param lId
     * @Date: 2022/3/25 12:29 下午
     */
    @RequiresGuest
    @ApiOperation(value = "根据ID查询生成日志")
    @GetMapping(value = "/{lId}")
    public ResultVO getById(@PathVariable(name = "lId")Long lId){
        // 判断ID是否为空
        Assert.notNull(lId);
        GenLog genLog = genLogService.getById(lId);
        return !ObjectUtil.isEmpty(genLog) ? ResultVO.success(GEN_LOG_GET_SUCCESS, genLog) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 新增生成日志
     * @param genLog
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "新增生成日志")
    @PostMapping
    public ResultVO save(@RequestBody GenLog genLog){
        // 判断系统日志对象是否为空
        Assert.notNull(genLog);
        boolean save = genLogService.save(genLog);
        return save ? ResultVO.success(GEN_LOG_SAVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID删除生成日志
     * @param lId
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "根据ID删除生成日志")
    @DeleteMapping("/{lId}")
    public ResultVO removeById(@PathVariable(name = "lId")Long lId){
        // 判断ID是否为空
        Assert.notNull(lId);
        boolean remove = genLogService.removeById(lId);
        return remove ? ResultVO.success(GEN_LOG_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 批量删除生成日志
     * @param genLogs
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "批量删除生成日志")
    @DeleteMapping
    public ResultVO removeByIds(@RequestBody Collection<GenLog> genLogs){
        //判断传入对象是否为空
        Assert.notNull(genLogs);
        boolean remove = genLogService.removeByIds(genLogs);
        return remove ? ResultVO.success(GEN_LOG_BATCH_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 修改一条生成日志信息
     * @param genLog
     * @Date: 2022/3/25 12:31 下午
     */
    @ApiOperation(value = " 修改一条生成日志信息")
    @PutMapping
    public ResultVO update(@RequestBody GenLog genLog){
        // 判断系统日志是否为空
        Assert.notNull(genLog);
        boolean update = genLogService.updateById(genLog);
        return update ? ResultVO.success(GEN_LOG_UPDATE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }
}
