package top.smalldai.smalldaispringboot.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.smalldai.smalldaispringboot.entity.system.SysRoleMenu;
import top.smalldai.smalldaispringboot.to.MenuTO;

import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统角色菜单Mapper
 * @Data:Created in 2022/3/24 4:09 下午
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
    /**
     * @Author: xingyuchen
     * @Discription: 根据角色ID获得菜单
     * @param rId
     * @Date: 2022/3/28 12:30 上午
    */
    List<MenuTO> listMenuTOByrId(@Param(value = "rId") Long rId);
}
