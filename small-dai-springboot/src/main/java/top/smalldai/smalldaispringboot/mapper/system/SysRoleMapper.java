package top.smalldai.smalldaispringboot.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.smalldai.smalldaispringboot.entity.system.SysPermission;
import top.smalldai.smalldaispringboot.entity.system.SysRole;
import top.smalldai.smalldaispringboot.to.RoleTO;

import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统角色Mapper
 * @Data:Created in 2022/3/24 4:09 下午
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * @Author: xingyuchen
     * @Discription: 根据角色ID获取菜单和权限集合
     * @param rId
     * @Date: 2022/3/27 2:00 下午
     */
    RoleTO getRoleTOById(@Param(value = "rId") Long rId);
}
