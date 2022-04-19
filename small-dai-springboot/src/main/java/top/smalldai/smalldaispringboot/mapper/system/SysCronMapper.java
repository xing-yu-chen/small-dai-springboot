package top.smalldai.smalldaispringboot.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.smalldai.smalldaispringboot.entity.system.SysCron;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统定时任务Mapper
 * @Data:Created in 2022/3/24 4:07 下午
 */
@Mapper
public interface SysCronMapper extends BaseMapper<SysCron> {
}
