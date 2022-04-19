package top.smalldai.smalldaispringboot.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import top.smalldai.smalldaispringboot.entity.system.SysRoleMenu;
import top.smalldai.smalldaispringboot.to.MenuTO;

import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description:
 * @Data:Created in 2022/3/24 4:15 下午
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {
    /**
     * @Author: xingyuchen
     * @Discription: 根据角色获取所有菜单
     * @Date: 2022/3/27 11:32 上午
     */
    List<MenuTO> listMenuTOByrId(Long rId);
}
