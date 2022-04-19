package top.smalldai.smalldaispringboot.service.generation.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.smalldai.smalldaispringboot.entity.generation.GenLog;
import top.smalldai.smalldaispringboot.mapper.generation.GenLogMapper;
import top.smalldai.smalldaispringboot.service.generation.GenLogService;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 生成日志Service实现
 * @Data:Created in 2022/3/24 4:28 下午
 */
@Service
public class GenLogServiceImpl extends ServiceImpl<GenLogMapper, GenLog> implements GenLogService {
}
