package top.smalldai.smalldaispringboot.service.generation.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.smalldai.smalldaispringboot.entity.generation.GenProject;
import top.smalldai.smalldaispringboot.mapper.generation.GenProjectMapper;
import top.smalldai.smalldaispringboot.service.generation.GenProjectService;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 生成项目Service实现
 * @Data:Created in 2022/3/24 4:29 下午
 */
@Service
public class GenProjectServiceImpl extends ServiceImpl<GenProjectMapper, GenProject>  implements GenProjectService {
}
