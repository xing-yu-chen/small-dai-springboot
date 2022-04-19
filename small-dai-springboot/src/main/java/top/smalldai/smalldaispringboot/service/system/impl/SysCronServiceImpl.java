package top.smalldai.smalldaispringboot.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.smalldai.smalldaispringboot.entity.system.SysCron;
import top.smalldai.smalldaispringboot.mapper.system.SysCronMapper;
import top.smalldai.smalldaispringboot.service.system.SysCronService;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统定时任务Service生成
 * @Data:Created in 2022/3/24 4:44 下午
 */
@Service
public class SysCronServiceImpl extends ServiceImpl<SysCronMapper, SysCron> implements SysCronService {
}
