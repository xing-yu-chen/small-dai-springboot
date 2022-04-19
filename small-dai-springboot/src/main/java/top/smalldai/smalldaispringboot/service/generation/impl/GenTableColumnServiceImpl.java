package top.smalldai.smalldaispringboot.service.generation.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.smalldai.smalldaispringboot.entity.generation.GenTableColumn;
import top.smalldai.smalldaispringboot.mapper.generation.GenTableColumnMapper;
import top.smalldai.smalldaispringboot.service.generation.GenTableColumnService;
import top.smalldai.smalldaispringboot.to.ColumnTO;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 生成表字段Service实现
 * @Data:Created in 2022/3/24 4:34 下午
 */
@Service
public class GenTableColumnServiceImpl extends ServiceImpl<GenTableColumnMapper, GenTableColumn> implements GenTableColumnService {

    @Resource
    private GenTableColumnMapper genTableColumnMapper;

    @Override
    public List<ColumnTO> listColumnBytId(Long tId) {
        return genTableColumnMapper.listColumnBytId(tId);
    }
}
