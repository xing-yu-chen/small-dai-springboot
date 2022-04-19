package top.smalldai.smalldaispringboot.mapper.generation;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.smalldai.smalldaispringboot.entity.generation.GenTableColumn;
import top.smalldai.smalldaispringboot.to.ColumnTO;

import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 生成表字段Mapper
 * @Data:Created in 2022/3/24 4:05 下午
 */
@Mapper
public interface GenTableColumnMapper extends BaseMapper<GenTableColumn> {

    /**
     * @Author: xingyuchen
     * @Discription: 根据表ID获取所有字段
     * @param tId
     * @Date: 2022/3/28 2:31 下午
    */
    List<ColumnTO> listColumnBytId(@Param(value = "tId") Long tId);
}
