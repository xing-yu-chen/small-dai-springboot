package top.smalldai.smalldaispringboot.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.smalldai.smalldaispringboot.entity.system.SysMenu;
import top.smalldai.smalldaispringboot.entity.system.SysRoleMenu;
import top.smalldai.smalldaispringboot.mapper.system.SysMenuMapper;
import top.smalldai.smalldaispringboot.mapper.system.SysRoleMenuMapper;
import top.smalldai.smalldaispringboot.service.system.SysMenuService;
import top.smalldai.smalldaispringboot.service.system.SysRoleMenuService;
import top.smalldai.smalldaispringboot.to.MenuTO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统角色Service实现
 * @Data:Created in 2022/3/24 4:48 下午
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<MenuTO> listMenuTOByrId(Long rId) {
        List<MenuTO> menuTOS = new ArrayList();
        List<MenuTO> allMenuTOS = sysRoleMenuMapper.listMenuTOByrId(rId);
        // 获取根节点
        for (MenuTO menuTO : allMenuTOS) {
            if(menuTO.getParentId() == 0){
                menuTOS.add(menuTO);
            }
        }

        // 获取每个根结点下面的所有子节点
        for (MenuTO menuTO:menuTOS) {
            menuTO.setMenuTOS(listChildMenu(menuTO.getMId(), allMenuTOS));
        }
        return menuTOS;
    }

    public List<MenuTO> listChildMenu(Long mId, List<MenuTO> allMenuTOByRole){
        ArrayList<MenuTO> childList = new ArrayList<>();
        // 父节点所有的所有菜单下
        for (MenuTO menuTO : allMenuTOByRole) {
            // 父节点和子节点相等的时候
            if(menuTO.getParentId() == mId){
                childList.add(menuTO);
            }
        }
        // 遍历所有子节点
        for (MenuTO menuTO : childList) {
            menuTO.setMenuTOS(listChildMenu(menuTO.getMId(), allMenuTOByRole));
        }
        // 如果子节点为空就跳出
        if(childList.size() == 0){
            return new ArrayList<>();
        }
        return childList;
    }
}
