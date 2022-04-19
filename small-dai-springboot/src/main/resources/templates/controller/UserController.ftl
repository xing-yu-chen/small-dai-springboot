package ${springPackageName}.controller;

import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.web.bind.annotation.*;
import ${springPackageName}.common.response.ResponseVO;
import ${springPackageName}.entity.User;
import ${springPackageName}.service.UserService;

import javax.annotation.Resource;
import java.util.Collection;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    private UserService userService;

    @RequiresUser
    @GetMapping
    public ResponseVO list(){
        return ResponseVO.success("success", userService.list());
    }

    @GetMapping("/{id}")
    public ResponseVO get(@PathVariable(name = "id") Long id){
        return ResponseVO.success("success", userService.getById(id));
    }

    @PostMapping
    public ResponseVO save(@RequestBody User user){
        return ResponseVO.success("success", userService.save(user));
    }

    @PutMapping
    public ResponseVO update(@RequestBody User user){
        return ResponseVO.success("success", userService.updateById(user));
    }

    @DeleteMapping("/{id}")
    public ResponseVO remove(@PathVariable(name = "id") Long id){
        return ResponseVO.success("success", userService.removeById(id));
    }

    @DeleteMapping("/batch/{ids}")
    public ResponseVO removeBatch(@PathVariable(name = "ids")Collection<Long> ids){
        return ResponseVO.success("success", userService.removeByIds(ids));
    }
}
