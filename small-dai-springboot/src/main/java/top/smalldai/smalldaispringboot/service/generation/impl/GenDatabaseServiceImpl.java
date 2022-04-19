package top.smalldai.smalldaispringboot.service.generation.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.smalldai.smalldaispringboot.entity.generation.GenDatabase;
import top.smalldai.smalldaispringboot.mapper.generation.GenDatabaseMapper;
import top.smalldai.smalldaispringboot.service.generation.GenDatabaseService;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 生成数据库Service实现
 * @Data:Created in 2022/3/24 4:24 下午
 */
@Service
public class GenDatabaseServiceImpl extends ServiceImpl<GenDatabaseMapper, GenDatabase> implements GenDatabaseService {
}
