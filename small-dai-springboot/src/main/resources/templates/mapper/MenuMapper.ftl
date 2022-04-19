package ${springPackageName}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ${springPackageName}.entity.Menu;
import ${springPackageName}.to.MenuTO;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /* 获取所有MenuTO */
    List<MenuTO> listMenus();

    /* 根据ID获取所有菜单的子菜单 */
    MenuTO listMenuTOById(@Param(value = "mId") Long mId);
}
