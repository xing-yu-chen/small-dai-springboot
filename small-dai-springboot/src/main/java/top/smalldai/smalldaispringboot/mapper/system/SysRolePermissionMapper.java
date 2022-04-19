package top.smalldai.smalldaispringboot.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.smalldai.smalldaispringboot.entity.system.SysRolePermission;
import top.smalldai.smalldaispringboot.to.PermissionTO;

import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统角色权限Mapper
 * @Data:Created in 2022/3/24 4:10 下午
 */
@Mapper
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {
    /**
     * @Author: xingyuchen
     * @Discription: 根据rId查询所有的权限
     * @Date: 2022/3/27 11:16 上午
    */
    List<PermissionTO> listPermissionByrId(@Param(value = "rId") Long rId);
}
