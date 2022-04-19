package top.smalldai.smalldaispringboot.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import top.smalldai.smalldaispringboot.entity.system.SysRolePermission;
import top.smalldai.smalldaispringboot.to.PermissionTO;

import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统角色权限Service
 * @Data:Created in 2022/3/24 4:16 下午
 */
public interface SysRolePermissionService extends IService<SysRolePermission> {
    /**
     * @Author: xingyuchen
     * @Discription: 根据rId查询所有的权限
     * @Date: 2022/3/27 11:16 上午
     */
    List<PermissionTO> listPermissionByrId(Long rId);
}
