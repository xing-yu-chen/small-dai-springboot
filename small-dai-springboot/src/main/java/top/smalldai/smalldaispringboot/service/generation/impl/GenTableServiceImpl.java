package top.smalldai.smalldaispringboot.service.generation.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.smalldai.smalldaispringboot.entity.generation.GenTable;
import top.smalldai.smalldaispringboot.entity.generation.GenTableColumn;
import top.smalldai.smalldaispringboot.mapper.generation.GenTableMapper;
import top.smalldai.smalldaispringboot.service.generation.GenTableColumnService;
import top.smalldai.smalldaispringboot.service.generation.GenTableService;
import top.smalldai.smalldaispringboot.to.TableTO;

import javax.annotation.Resource;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 生成表Service实现
 * @Data:Created in 2022/3/24 4:42 下午
 */
@Service
public class GenTableServiceImpl extends ServiceImpl<GenTableMapper, GenTable> implements GenTableService {

    @Resource
    private GenTableMapper genTableMapper;

    @Resource
    private GenTableColumnService genTableColumnService;

    @Override
    public TableTO getTableBytId(Long tId) {
        TableTO tableBytId = genTableMapper.getTableBytId(tId);
        tableBytId.setColumnTOS(genTableColumnService.listColumnBytId(tableBytId.getTId()));
        return tableBytId;
    }
}
