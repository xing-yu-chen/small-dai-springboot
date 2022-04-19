package ${springPackageName}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ${springPackageName}.entity.Role;
import ${springPackageName}.to.RoleTO;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    /* 根据角色ID获取菜单和权限集合 */
    RoleTO getRoleTOById(@Param(value = "rId") Long rId);
}
