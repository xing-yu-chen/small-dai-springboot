package ${springPackageName}.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ${springPackageName}.entity.Menu;
import ${springPackageName}.mapper.MenuMapper;
import ${springPackageName}.service.MenuService;
import ${springPackageName}.to.MenuTO;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<MenuTO> listMenus() {
        return menuMapper.listMenus();
    }

    @Override
    public MenuTO listMenuTOById(Long mId) {
        return menuMapper.listMenuTOById(mId);
    }
}
