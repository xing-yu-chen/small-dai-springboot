package ${springPackageName}.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ${springPackageName}.entity.Permission;
import ${springPackageName}.mapper.PermissionMapper;
import ${springPackageName}.service.PermissionService;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
}
