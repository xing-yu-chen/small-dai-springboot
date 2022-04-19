package top.smalldai.smalldaispringboot.controller.system;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.smalldai.smalldaispringboot.common.result.ResultVO;
import top.smalldai.smalldaispringboot.entity.system.SysLog;
import top.smalldai.smalldaispringboot.service.system.SysLogService;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统日志控制层
 * @Data:Created in 2022/3/25 10:03 上午
 */
@RestController
@RequestMapping(value = "/sysLogs")
@Api(tags = {"系统日志控制层"})
public class SysLogController {
    private static final String LOG_GET_SUCCESS = "日志信息获取成功" ;
    private static final String LOG_SAVE_SUCCESS = "日志信息保存成功";
    private static final String LOG_REMOVE_SUCCESS = "日志信息删除成功";
    private static final String LOG_BATCH_REMOVE_SUCCESS = "日志信息批量删除成功";
    private static final String LOG_UPDATE_SUCCESS = "日志信息修改成功";

    @Resource
    private SysLogService sysLogService;

    /**
     * @Author: xingyuchen
     * @Discription: 查询系统日志列表
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "查询系统日志列表")
    @GetMapping
    public ResultVO list(){
        List<SysLog> sysLogs = sysLogService.list();
        return sysLogs.size() > 0 ? ResultVO.success(LOG_GET_SUCCESS, sysLogs) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID查询系统日志
     * @param lId 系统日志ID
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "根据ID查询系统日志")
    @GetMapping(value = "/{lId}")
    public ResultVO getById(@PathVariable(name = "lId")Long lId){
        // 判断ID是否为空
        Assert.notNull(lId);
        SysLog sysLog = sysLogService.getById(lId);
        return !ObjectUtil.isEmpty(sysLog) ? ResultVO.success(LOG_GET_SUCCESS, sysLog) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 新增系统日志
     * @param sysLog 系统日志对象
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "新增系统日志")
    @PostMapping
    public ResultVO save(@RequestBody SysLog sysLog){
        // 判断系统日志对象是否为空
        Assert.notNull(sysLog);
        boolean save = sysLogService.save(sysLog);
        return save ? ResultVO.success(LOG_SAVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID删除系统日志
     * @param lId 系统日志ID
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "根据ID删除系统日志")
    @DeleteMapping("/{lId}")
    public ResultVO removeById(@PathVariable(name = "lId")Long lId){
        // 判断ID是否为空
        Assert.notNull(lId);
        boolean remove = sysLogService.removeById(lId);
        return remove ? ResultVO.success(LOG_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 批量删除系统日志
     * @param sysLogs 系统日志集合
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "批量删除系统日志")
    @DeleteMapping
    public ResultVO removeByIds(@RequestBody Collection<SysLog> sysLogs){
        //判断传入对象是否为空
        Assert.notNull(sysLogs);
        boolean remove = sysLogService.removeByIds(sysLogs);
        return remove ? ResultVO.success(LOG_BATCH_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 修改一条系统日志信息
     * @param sysLog 系统日志对象
     * @Date: 2022/3/25 12:31 下午
     */
    @ApiOperation(value = "修改一条系统日志信息")
    @PutMapping
    public ResultVO update(@RequestBody SysLog sysLog){
        // 判断系统日志是否为空
        Assert.notNull(sysLog);
        boolean update = sysLogService.updateById(sysLog);
        return update ? ResultVO.success(LOG_UPDATE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }
}
