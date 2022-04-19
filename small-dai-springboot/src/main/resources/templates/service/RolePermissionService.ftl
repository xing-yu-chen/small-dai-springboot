package ${springPackageName}.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ${springPackageName}.entity.RolePermission;
import ${springPackageName}.to.PermissionTO;

import java.util.List;

public interface RolePermissionService extends IService<RolePermission> {
    /* 根据rId查询所有的权限 */
    List<PermissionTO> listPermissionByrId(Long rId);
}
