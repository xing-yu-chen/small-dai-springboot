package ${springPackageName}.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ${springPackageName}.entity.Role;
import ${springPackageName}.mapper.RoleMapper;
import ${springPackageName}.service.RoleMenuService;
import ${springPackageName}.service.RolePermissionService;
import ${springPackageName}.service.RoleService;
import ${springPackageName}.to.PermissionTO;
import ${springPackageName}.to.RoleTO;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RolePermissionService rolePermissionService;

    @Resource
    private RoleMenuService roleMenuService;

    @Override
    public RoleTO getRoleTOById(Long rId) {
        RoleTO roleTO = roleMapper.getRoleTOById(rId);
        if(roleTO == null){
            return null;
        }
        List<PermissionTO> permissionTOS = rolePermissionService.listPermissionByrId(rId);
        roleTO.setPermissionTOList(permissionTOS);
        roleTO.setMenuTOList(roleMenuService.listMenuTOByrId(rId));
        return roleTO;
    }
}
