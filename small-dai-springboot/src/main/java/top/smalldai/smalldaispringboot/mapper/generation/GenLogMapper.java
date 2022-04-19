package top.smalldai.smalldaispringboot.mapper.generation;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.smalldai.smalldaispringboot.entity.generation.GenLog;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 生成日志Mapper
 * @Data:Created in 2022/3/24 4:03 下午
 */
@Mapper
public interface GenLogMapper extends BaseMapper<GenLog> {
}
