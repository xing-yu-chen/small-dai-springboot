package ${springPackageName}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ${springPackageName}.entity.RoleMenu;
import ${springPackageName}.to.MenuTO;

import java.util.List;

@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /* 根据角色ID获得菜单 */
    List<MenuTO> listMenuTOByrId(@Param(value = "rId") Long rId);
}
