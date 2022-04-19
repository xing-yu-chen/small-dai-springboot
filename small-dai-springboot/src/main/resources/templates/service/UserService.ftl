package ${springPackageName}.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ${springPackageName}.entity.User;
import ${springPackageName}.to.AccountTO;

public interface UserService extends IService<User> {
    /* 根据邮箱获取用户信息 */
    AccountTO listUserById(String email);
}
