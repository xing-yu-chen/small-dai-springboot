package top.smalldai.smalldaispringboot.service.generation.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.smalldai.smalldaispringboot.entity.generation.GenMapper;
import top.smalldai.smalldaispringboot.mapper.generation.GenMapperMapper;
import top.smalldai.smalldaispringboot.service.generation.GenMapperService;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description:
 * @Data:Created in 2022/4/3 1:34 下午
 */
@Service
public class GenMapperServiceImpl extends ServiceImpl<GenMapperMapper, GenMapper> implements GenMapperService {
}
