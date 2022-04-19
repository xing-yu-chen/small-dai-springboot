package top.smalldai.smalldaispringboot.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import top.smalldai.smalldaispringboot.entity.system.SysUser;
import top.smalldai.smalldaispringboot.to.UserTO;

import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统用户Service
 * @Data:Created in 2022/3/24 4:16 下午
 */
public interface SysUserService extends IService<SysUser> {
    /**
     * @Author: xingyuchen
     * @Discription: 根据邮箱获取用户信息
     * @Date: 2022/3/27 11:05 上午
     */
    UserTO listUserById(String email);
}
