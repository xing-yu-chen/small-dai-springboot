package top.smalldai.smalldaispringboot.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.smalldai.smalldaispringboot.entity.system.SysMenu;
import top.smalldai.smalldaispringboot.to.MenuTO;

import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统菜单Mapper
 * @Data:Created in 2022/3/24 4:08 下午
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * @Author: xingyuchen
     * @Discription: 获取所有MenuTO
     * @Date: 2022/3/27 2:45 下午
    */
    List<MenuTO> listMenus();

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID获取所有菜单的子菜单
     * @param mId
     * @Date: 2022/3/27 2:18 下午
     */
    MenuTO listMenuTOById(@Param(value = "mId") Long mId);
}
