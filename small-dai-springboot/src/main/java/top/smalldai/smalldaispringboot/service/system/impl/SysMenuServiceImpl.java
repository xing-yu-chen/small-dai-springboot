package top.smalldai.smalldaispringboot.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.smalldai.smalldaispringboot.entity.system.SysMenu;
import top.smalldai.smalldaispringboot.mapper.system.SysMenuMapper;
import top.smalldai.smalldaispringboot.service.system.SysMenuService;
import top.smalldai.smalldaispringboot.to.MenuTO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Descriaption: 系统菜单Service实现
 * @Data:Created in 2022/3/24 4:46 下午
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    /**
     * @Author: xingyuchen
     * @Discription: 获取所有MenuTO
     * @Date: 2022/3/27 2:45 下午
     */
    @Override
    public List<MenuTO> listMenus() {
        return sysMenuMapper.listMenus();
    }

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID获取所有菜单的子菜单
     * @param mId
     * @Date: 2022/3/27 11:22 下午
    */
    @Override
    public MenuTO listMenuTOById(Long mId) {
        return sysMenuMapper.listMenuTOById(mId);
    }
}
