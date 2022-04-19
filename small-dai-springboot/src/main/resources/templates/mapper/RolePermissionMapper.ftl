package ${springPackageName}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ${springPackageName}.entity.RolePermission;
import ${springPackageName}.to.PermissionTO;

import java.util.List;

@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    /* 根据rId查询所有的权限 */
    List<PermissionTO> listPermissionByrId(@Param(value = "rId") Long rId);
}
