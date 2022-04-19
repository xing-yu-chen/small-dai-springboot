package top.smalldai.smalldaispringboot.controller.system;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.smalldai.smalldaispringboot.common.result.ResultVO;
import top.smalldai.smalldaispringboot.entity.system.SysWarning;
import top.smalldai.smalldaispringboot.service.system.SysWarningService;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统警告控制层
 * @Data:Created in 2022/3/25 10:08 上午
 */
@RestController
@RequestMapping(value = "/sysWarnings")
@Api(tags = {"系统警告控制层"})
public class SysWarningController {

    private static final String WARNING_GET_SUCCESS = "警告信息获取成功" ;
    private static final String WARNING_SAVE_SUCCESS = "警告信息保存成功";
    private static final String WARNING_REMOVE_SUCCESS = "警告信息删除成功";
    private static final String WARNING_BATCH_REMOVE_SUCCESS = "警告信息批量删除成功";
    private static final String WARNING_UPDATE_SUCCESS = "警告信息修改成功";

    @Resource
    private SysWarningService sysWarningService;

    /**
     * @Author: xingyuchen
     * @Discription: 查询系统警告列表
     * @Date: 2022/3/25 12:29 下午
    */
    @ApiOperation(value = "查询系统警告列表")
    @GetMapping
    public ResultVO list(){
        List<SysWarning> sysWarnings = sysWarningService.list();
        return sysWarnings.size() > 0 ? ResultVO.success(WARNING_GET_SUCCESS, sysWarnings) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID查询系统警告
     * @param wId 系统警告ID
     * @Date: 2022/3/25 12:29 下午
    */
    @ApiOperation(value = "根据ID查询系统警告")
    @GetMapping(value = "/{wId}")
    public ResultVO getById(@PathVariable(name = "wId")Long wId){
        // 警告ID是否为空
        Assert.notNull(wId);
        SysWarning sysWarning = sysWarningService.getById(wId);
        return !ObjectUtil.isEmpty(sysWarning) ? ResultVO.success(WARNING_GET_SUCCESS, sysWarning) : ResultVO.failByNull(null);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 新增系统警告
     * @param sysWarning 系统警告对象
     * @Date: 2022/3/25 12:29 下午
    */
    @ApiOperation(value = "新增系统警告")
    @PostMapping
    public ResultVO save(@RequestBody SysWarning sysWarning){
        // 判断警告对象是否为空
        Assert.notNull(sysWarning);
        boolean save = sysWarningService.save(sysWarning);
        return save ? ResultVO.success(WARNING_SAVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID删除系统警告
     * @param wId 系统警告ID
     * @Date: 2022/3/25 12:30 下午
    */
    @ApiOperation(value = "根据ID删除系统警告")
    @DeleteMapping("/{wId}")
    public ResultVO removeById(@PathVariable(name = "wId")Long wId){
        // 判断ID是否为空
        Assert.notNull(wId);
        boolean remove = sysWarningService.removeById(wId);
        return remove ? ResultVO.success(WARNING_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 批量删除系统警告
     * @param sysWarnings 系统警告集合
     * @Date: 2022/3/25 12:30 下午
    */
    @ApiOperation(value = "批量删除系统警告")
    @DeleteMapping
    public ResultVO removeByIds(@RequestBody Collection<SysWarning> sysWarnings){
        // 判断警告集合是否为空
        Assert.notNull(sysWarnings);
        boolean remove = sysWarningService.removeByIds(sysWarnings);
        return remove ? ResultVO.success(WARNING_BATCH_REMOVE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 修改一条系统警告信息
     * @param sysWarning 系统警告对象
     * @Date: 2022/3/25 12:31 下午
    */
    @ApiOperation(value = "修改一条系统警告信息")
    @PutMapping
    public ResultVO update(@RequestBody SysWarning sysWarning){
        // 判断警告对象是否为空
        Assert.notNull(sysWarning);
        boolean update = sysWarningService.updateById(sysWarning);
        return update ? ResultVO.success(WARNING_UPDATE_SUCCESS, true) : ResultVO.failByInternalServer(false);
    }


}
