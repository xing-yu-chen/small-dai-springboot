package ${springPackageName}.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ${springPackageName}.entity.Log;
import ${springPackageName}.mapper.LogMapper;
import ${springPackageName}.service.LogService;

@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {
}
