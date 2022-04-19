package top.smalldai.smalldaispringboot.mapper.generation;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.smalldai.smalldaispringboot.entity.generation.GenTable;
import top.smalldai.smalldaispringboot.to.TableTO;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 生成表Mapper
 * @Data:Created in 2022/3/24 4:05 下午
 */
@Mapper
public interface GenTableMapper extends BaseMapper<GenTable> {
    /**
     * @Author: xingyuchen
     * @Discription: 根据tId获取该表对象
     * @param tId
     * @Date: 2022/3/28 2:59 下午
     */
    TableTO getTableBytId(@Param(value = "tId")Long tId);
}
