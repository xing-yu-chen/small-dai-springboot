package top.smalldai.smalldaispringboot.service.generation.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.smalldai.smalldaispringboot.entity.generation.GenSql;
import top.smalldai.smalldaispringboot.mapper.generation.GenSqlMapper;
import top.smalldai.smalldaispringboot.service.generation.GenSqlService;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 生成SQL Service生成
 * @Data:Created in 2022/3/24 4:31 下午
 */
@Service
public class GenSqlServiceImpl extends ServiceImpl<GenSqlMapper, GenSql> implements GenSqlService {
}
