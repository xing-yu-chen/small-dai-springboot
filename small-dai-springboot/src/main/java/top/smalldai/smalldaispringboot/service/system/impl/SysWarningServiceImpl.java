package top.smalldai.smalldaispringboot.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.smalldai.smalldaispringboot.entity.system.SysWarning;
import top.smalldai.smalldaispringboot.mapper.system.SysWarningMapper;
import top.smalldai.smalldaispringboot.service.system.SysWarningService;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统警告Service实现
 * @Data:Created in 2022/3/24 4:57 下午
 */
@Service
public class SysWarningServiceImpl extends ServiceImpl<SysWarningMapper, SysWarning> implements SysWarningService {
}
