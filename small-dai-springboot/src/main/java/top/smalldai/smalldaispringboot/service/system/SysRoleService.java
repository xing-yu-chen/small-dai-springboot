package top.smalldai.smalldaispringboot.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import top.smalldai.smalldaispringboot.entity.system.SysRole;
import top.smalldai.smalldaispringboot.to.RoleTO;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统角色Service
 * @Data:Created in 2022/3/24 4:15 下午
 */
public interface SysRoleService extends IService<SysRole> {
    /**
     * @Author: xingyuchen
     * @Discription: 根据角色ID获取菜单和权限集合
     * @param rId
     * @Date: 2022/3/27 2:00 下午
     */
    RoleTO getRoleTOById(Long rId);
}
