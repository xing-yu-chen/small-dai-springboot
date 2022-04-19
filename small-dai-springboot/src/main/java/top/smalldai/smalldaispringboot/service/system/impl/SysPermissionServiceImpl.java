package top.smalldai.smalldaispringboot.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.smalldai.smalldaispringboot.entity.system.SysPermission;
import top.smalldai.smalldaispringboot.mapper.system.SysPermissionMapper;
import top.smalldai.smalldaispringboot.service.system.SysPermissionService;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统权限Service实现
 * @Data:Created in 2022/3/24 4:47 下午
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {
}
