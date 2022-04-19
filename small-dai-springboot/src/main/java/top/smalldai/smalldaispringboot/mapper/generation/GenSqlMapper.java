package top.smalldai.smalldaispringboot.mapper.generation;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.smalldai.smalldaispringboot.entity.generation.GenSql;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 生成SQL GenMapper
 * @Data:Created in 2022/3/24 4:04 下午
 */
@Mapper
public interface GenSqlMapper extends BaseMapper<GenSql> {
}
