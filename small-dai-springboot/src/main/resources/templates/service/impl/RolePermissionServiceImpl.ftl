package ${springPackageName}.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ${springPackageName}.entity.RolePermission;
import ${springPackageName}.mapper.RolePermissionMapper;
import ${springPackageName}.service.RolePermissionService;
import ${springPackageName}.to.PermissionTO;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>  implements RolePermissionService {

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<PermissionTO> listPermissionByrId(Long rId) {
        List<PermissionTO> permissionTOS = rolePermissionMapper.listPermissionByrId(rId);
        return permissionTOS;
    }
}
