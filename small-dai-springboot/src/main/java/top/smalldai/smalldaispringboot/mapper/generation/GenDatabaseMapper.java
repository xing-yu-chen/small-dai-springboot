package top.smalldai.smalldaispringboot.mapper.generation;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.smalldai.smalldaispringboot.entity.generation.GenDatabase;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 生成数据库Mapper
 * @Data:Created in 2022/3/24 4:01 下午
 */
@Mapper
public interface GenDatabaseMapper extends BaseMapper<GenDatabase> {
}
