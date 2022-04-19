package top.smalldai.smalldaispringboot.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.smalldai.smalldaispringboot.entity.system.SysRole;
import top.smalldai.smalldaispringboot.mapper.system.SysRoleMapper;
import top.smalldai.smalldaispringboot.mapper.system.SysRolePermissionMapper;
import top.smalldai.smalldaispringboot.service.system.SysRoleMenuService;
import top.smalldai.smalldaispringboot.service.system.SysRoleService;
import top.smalldai.smalldaispringboot.to.PermissionTO;
import top.smalldai.smalldaispringboot.to.RoleTO;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统角色Service实现
 * @Data:Created in 2022/3/24 4:50 下午
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public RoleTO getRoleTOById(Long rId) {
        RoleTO roleTO = sysRoleMapper.getRoleTOById(rId);
        List<PermissionTO> permissionTOS = sysRolePermissionMapper.listPermissionByrId(rId);
        roleTO.setPermissionTOS(permissionTOS);
        roleTO.setMenuTOS(sysRoleMenuService.listMenuTOByrId(rId));
        return roleTO;
    }
}
