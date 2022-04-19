package top.smalldai.smalldaispringboot.service.generation;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import top.smalldai.smalldaispringboot.entity.generation.GenTableColumn;
import top.smalldai.smalldaispringboot.to.ColumnTO;

import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 生成表字段Service
 * @Data:Created in 2022/3/24 4:23 下午
 */
public interface GenTableColumnService extends IService<GenTableColumn> {
    /**
     * @Author: xingyuchen
     * @Discription: 根据表ID获取所有字段
     * @param tId
     * @Date: 2022/3/28 2:31 下午
     */
    List<ColumnTO> listColumnBytId(Long tId);
}
