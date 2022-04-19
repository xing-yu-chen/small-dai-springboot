package top.smalldai.smalldaispringboot.service.generation.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.smalldai.smalldaispringboot.entity.generation.GenSqlScript;
import top.smalldai.smalldaispringboot.mapper.generation.GenSqlScriptMapper;
import top.smalldai.smalldaispringboot.service.generation.GenSqlScriptService;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 生成SQl脚本Service实现
 * @Data:Created in 2022/3/24 4:30 下午
 */
@Service
public class GenSqlScriptServiceImpl extends ServiceImpl<GenSqlScriptMapper, GenSqlScript> implements GenSqlScriptService {
}
