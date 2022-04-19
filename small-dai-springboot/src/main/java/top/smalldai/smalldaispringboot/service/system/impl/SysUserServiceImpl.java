package top.smalldai.smalldaispringboot.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.smalldai.smalldaispringboot.entity.system.SysRolePermission;
import top.smalldai.smalldaispringboot.entity.system.SysUser;
import top.smalldai.smalldaispringboot.mapper.system.SysRoleMapper;
import top.smalldai.smalldaispringboot.mapper.system.SysRoleMenuMapper;
import top.smalldai.smalldaispringboot.mapper.system.SysRolePermissionMapper;
import top.smalldai.smalldaispringboot.mapper.system.SysUserMapper;
import top.smalldai.smalldaispringboot.service.system.SysRoleMenuService;
import top.smalldai.smalldaispringboot.service.system.SysRolePermissionService;
import top.smalldai.smalldaispringboot.service.system.SysRoleService;
import top.smalldai.smalldaispringboot.service.system.SysUserService;
import top.smalldai.smalldaispringboot.to.MenuTO;
import top.smalldai.smalldaispringboot.to.PermissionTO;
import top.smalldai.smalldaispringboot.to.RoleTO;
import top.smalldai.smalldaispringboot.to.UserTO;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统用户Service实现
 * @Data:Created in 2022/3/24 4:56 下午
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysRoleService sysRoleService;


    /**
     * @Author: xingyuchen
     * @Discription: 根据角色ID和菜单ID获取集合
     * @param email
     * @Date: 2022/3/27 11:05 上午
     */
    @Override
    public UserTO listUserById(String email) {
        UserTO userTO = new UserTO();
        // 查询该email名字的信息
        SysUser sysUser = getOne(new QueryWrapper<SysUser>().eq("u_email", email));
        // 放入UserTO中
        userTO.setUId(sysUser.getUId())
                .setEmail(sysUser.getEmail())
                .setAge(sysUser.getAge())
                .setName(sysUser.getName())
                .setTel(sysUser.getTel());
        RoleTO roleTOById = sysRoleService.getRoleTOById(sysUser.getRId());
        userTO.setRoleTO(roleTOById);
        return userTO;
    }
}
