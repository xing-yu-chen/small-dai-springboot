package ${springPackageName}.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ${springPackageName}.entity.User;
import ${springPackageName}.mapper.UserMapper;
import ${springPackageName}.service.RoleService;
import ${springPackageName}.service.UserService;
import ${springPackageName}.to.AccountTO;
import ${springPackageName}.to.RoleTO;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private RoleService roleService;

    @Override
    public AccountTO listUserById(String email) {
        AccountTO accountTO = new AccountTO();
        User user = getOne(new QueryWrapper<User>().eq("u_email", email));
        RoleTO roleTOById = roleService.getRoleTOById(user.getURoleId());

        accountTO.setUId(user.getUId())
                .setUName(user.getUName())
                .setUEmail(user.getUEmail())
                .setUPhone(user.getUPhone());
        if(roleTOById == null) {
            accountTO.setRoleTO(null);
        }else {
            accountTO.setRoleTO(roleTOById);
        }
        return accountTO;
    }
}
