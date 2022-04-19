package top.smalldai.smalldaispringboot.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.smalldai.smalldaispringboot.entity.system.SysLog;
import top.smalldai.smalldaispringboot.mapper.system.SysLogMapper;
import top.smalldai.smalldaispringboot.service.system.SysLogService;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统日志Service实现
 * @Data:Created in 2022/3/24 4:45 下午
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {
}
