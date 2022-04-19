package top.smalldai.smalldaispringboot.service.generation;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import top.smalldai.smalldaispringboot.entity.generation.GenTable;
import top.smalldai.smalldaispringboot.to.TableTO;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 生成表Serivce
 * @Data:Created in 2022/3/24 4:22 下午
 */
public interface GenTableService extends IService<GenTable> {
    /**
     * @Author: xingyuchen
     * @Discription: 根据tId获取该表对象
     * @param tId
     * @Date: 2022/3/28 2:59 下午
    */
    TableTO getTableBytId(Long tId);
}
