package top.smalldai.smalldaispringboot.config.realm;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import top.smalldai.smalldaispringboot.entity.system.SysRole;
import top.smalldai.smalldaispringboot.entity.system.SysUser;
import top.smalldai.smalldaispringboot.service.system.SysUserService;
import top.smalldai.smalldaispringboot.to.PermissionTO;
import top.smalldai.smalldaispringboot.to.RoleTO;
import top.smalldai.smalldaispringboot.to.UserTO;

import javax.annotation.Resource;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: Shiro Realm配置
 * @Data:Created in 2022/3/26 2:41 下午
 */
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private SysUserService sysUserService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();
        UserTO userTO = sysUserService.listUserById(user.getEmail());
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 添加用户角色
        simpleAuthorizationInfo.addRole(userTO.getRoleTO().getNameEn());
        // 添加用户权限
        for (PermissionTO permissionTO : userTO.getRoleTO().getPermissionTOS()) {
            simpleAuthorizationInfo.addStringPermission(permissionTO.getNameEn());
        }
        return simpleAuthorizationInfo;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String email = (String) authenticationToken.getPrincipal();
        SysUser user = sysUserService.getOne(new QueryWrapper<SysUser>().eq("u_email", email));
        // 如果邮箱不存在就返回空即认证失败
        if(ObjectUtil.isNull(user)){
            return null;
        }
        SimpleAuthenticationInfo info= new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        return info;
    }
}
