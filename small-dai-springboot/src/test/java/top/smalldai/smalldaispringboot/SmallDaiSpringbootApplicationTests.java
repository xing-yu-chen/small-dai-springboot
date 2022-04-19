package top.smalldai.smalldaispringboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import top.smalldai.smalldaispringboot.mapper.system.SysRolePermissionMapper;
import top.smalldai.smalldaispringboot.service.generation.GenTableService;
import top.smalldai.smalldaispringboot.service.system.SysRolePermissionService;
import top.smalldai.smalldaispringboot.to.PermissionTO;
import top.smalldai.smalldaispringboot.to.TableTO;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@SpringBootTest
class SmallDaiSpringbootApplicationTests {


    @Test
    void contextLoads() {

    }

}
