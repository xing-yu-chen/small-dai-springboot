package ${springPackageName}.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ${springPackageName}.entity.RoleMenu;
import ${springPackageName}.mapper.RoleMenuMapper;
import ${springPackageName}.service.RoleMenuService;
import ${springPackageName}.to.MenuTO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu>  implements RoleMenuService {

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<MenuTO> listMenuTOByrId(Long rId) {
        List<MenuTO> menuTOS = new ArrayList();
        List<MenuTO> allMenuTOS = roleMenuMapper.listMenuTOByrId(rId);
        // 获取根节点
        for (MenuTO menuTO : allMenuTOS) {
            if(menuTO.getMParentId() == 0){
                menuTOS.add(menuTO);
            }
        }

        // 获取每个根结点下面的所有子节点
        for (MenuTO menuTO:menuTOS) {
            menuTO.setChildren(listChildMenu(menuTO.getMId(), allMenuTOS));
        }
        return menuTOS;
    }

    public List<MenuTO> listChildMenu(Long mId, List<MenuTO> allMenuTOByRole){
        ArrayList<MenuTO> childList = new ArrayList<>();
        // 父节点所有的所有菜单下
        for (MenuTO menuTO : allMenuTOByRole) {
            // 父节点和子节点相等的时候
            if(menuTO.getMParentId() == mId){
                childList.add(menuTO);
            }
        }
        // 遍历所有子节点
        for (MenuTO menuTO : childList) {
            menuTO.setChildren(listChildMenu(menuTO.getMId(), allMenuTOByRole));
        }
        // 如果子节点为空就跳出
        if(childList.size() == 0){
            return new ArrayList<>();
        }
        return childList;
    }
}
