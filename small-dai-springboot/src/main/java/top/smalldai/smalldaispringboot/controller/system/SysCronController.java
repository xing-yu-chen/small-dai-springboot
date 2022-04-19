package top.smalldai.smalldaispringboot.controller.system;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.smalldai.smalldaispringboot.common.result.ResultVO;
import top.smalldai.smalldaispringboot.entity.system.SysCron;
import top.smalldai.smalldaispringboot.service.system.SysCronService;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统定时任务控制层
 * @Data:Created in 2022/3/25 10:02 上午
 */
@RestController
@RequestMapping(value = "/sysCrons")
@Api(tags = {"系统定时任务控制层"})
public class SysCronController {
    private static final String CRON_GET_SUCCESS = "定时任务信息获取成功" ;
    private static final String CRON_SAVE_SUCCESS = "定时任务信息保存成功";
    private static final String CRON_REMOVE_SUCCESS = "定时任务信息删除成功";
    private static final String CRON_BATCH_REMOVE_SUCCESS = "定时任务信息批量删除成功";
    private static final String CRON_UPDATE_SUCCESS = "定时任务信息修改成功";

    @Resource
    private SysCronService sysCronService;

    /**
     * @Author: xingyuchen
     * @Discription: 查询系统定时任务列表
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "查询系统定时任务列表")
    @GetMapping
    public ResultVO list(){
        List<SysCron> sysCrons = sysCronService.list();
        return sysCrons.size() > 0 ? ResultVO.success(CRON_GET_SUCCESS, sysCrons) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID查询系统定时任务
     * @param cId 系统定时任务ID
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "根据ID查询系统定时任务")
    @GetMapping(value = "/{cId}")
    public ResultVO getById(@PathVariable(name = "cId")Long cId){
        // 判断传进来的是否为空值
        Assert.notNull(cId);
        SysCron sysCron = sysCronService.getById(cId);
        return !ObjectUtil.isEmpty(sysCron) ? ResultVO.success(CRON_GET_SUCCESS, sysCron) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 新增系统定时任务
     * @param sysCron 系统定时任务对象
     * @Date: 2022/3/25 12:29 下午
     */
    @ApiOperation(value = "新增系统定时任务")
    @PostMapping
    public ResultVO save(@RequestBody SysCron sysCron){
        // 判断定时任务对象是否为空的，否则报异常不执行
        Assert.notNull(sysCron);
        boolean save = sysCronService.save(sysCron);
        return save ? ResultVO.success(CRON_SAVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID删除系统定时任务
     * @param cId 系统定时任务ID
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "根据ID删除系统定时任务")
    @DeleteMapping("/{uId}")
    public ResultVO removeById(@PathVariable(name = "cId")Long cId){
        // 判断是否有ID传入
        Assert.notNull(cId);
        boolean remove = sysCronService.removeById(cId);
        return remove ? ResultVO.success(CRON_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 批量删除系统定时任务
     * @param sysCrons 系统定时任务集合
     * @Date: 2022/3/25 12:30 下午
     */
    @ApiOperation(value = "批量删除系统定时任务")
    @DeleteMapping
    public ResultVO removeByIds(@RequestBody Collection<SysCron> sysCrons){
        // 判断是否有定时任务集合传入
        Assert.notNull(sysCrons);
        boolean remove = sysCronService.removeByIds(sysCrons);
        return remove ? ResultVO.success(CRON_BATCH_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 修改一条系统定时任务信息
     * @param sysCron 系统定时任务对象
     * @Date: 2022/3/25 12:31 下午
     */
    @ApiOperation(value = "修改一条系统定时任务信息")
    @PutMapping
    public ResultVO update(@RequestBody SysCron sysCron){
        // 判断是否有定时任务传入
        Assert.notNull(sysCron);
        boolean update = sysCronService.updateById(sysCron);
        return update ? ResultVO.success(CRON_UPDATE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }
}
