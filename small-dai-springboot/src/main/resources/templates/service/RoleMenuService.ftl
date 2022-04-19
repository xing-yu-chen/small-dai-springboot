package ${springPackageName}.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import ${springPackageName}.entity.RoleMenu;
import ${springPackageName}.to.MenuTO;

import java.util.List;

public interface RoleMenuService extends IService<RoleMenu> {
    /* 根据角色ID获得菜单 */
    List<MenuTO> listMenuTOByrId(@Param(value = "rId") Long rId);
}
