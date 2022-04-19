package top.smalldai.smalldaispringboot.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.smalldai.smalldaispringboot.entity.system.SysPermission;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统权限Mapper
 * @Data:Created in 2022/3/24 4:08 下午
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
}
