package ${springPackageName}.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import ${springPackageName}.entity.Role;
import ${springPackageName}.to.RoleTO;

public interface RoleService extends IService<Role> {
    /* 根据角色ID获取菜单和权限集合 */
    RoleTO getRoleTOById(@Param(value = "rId") Long rId);
}
