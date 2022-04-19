package ${springPackageName}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ${springPackageName}.entity.Permission;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
}
