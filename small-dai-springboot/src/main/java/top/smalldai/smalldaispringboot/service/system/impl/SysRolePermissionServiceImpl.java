package top.smalldai.smalldaispringboot.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.smalldai.smalldaispringboot.entity.system.SysRolePermission;
import top.smalldai.smalldaispringboot.mapper.system.SysRolePermissionMapper;
import top.smalldai.smalldaispringboot.service.system.SysRolePermissionService;
import top.smalldai.smalldaispringboot.to.PermissionTO;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统角色权限Service实现
 * @Data:Created in 2022/3/24 4:49 下午
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public List<PermissionTO> listPermissionByrId(Long rId) {
        List<PermissionTO> permissionTOS = sysRolePermissionMapper.listPermissionByrId(rId);
        return permissionTOS;
    }
}
