package ${springPackageName}.service;

import com.baomidou.mybatisplus.extension.service.IService;

import ${springPackageName}.entity.Menu;
import ${springPackageName}.to.MenuTO;

import java.util.List;

public interface MenuService extends IService<Menu> {
    /* 获取所有MenuTO */
    List<MenuTO> listMenus();

    /* 根据ID获取所有菜单的子菜单 */
    MenuTO listMenuTOById( Long mId);
}
