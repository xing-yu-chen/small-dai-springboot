package ${springPackageName}.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import ${springPackageName}.common.response.ResponseVO;
import ${springPackageName}.entity.Role;
import ${springPackageName}.service.RoleService;

import javax.annotation.Resource;
import java.util.Collection;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping
    public ResponseVO list(){
        return ResponseVO.success("success", roleService.list());
    }

    @GetMapping("/{id}")
    public ResponseVO get(@PathVariable(name = "id") Long id){
        return ResponseVO.success("success", roleService.getById(id));
    }

    @PostMapping
    public ResponseVO save(@RequestBody Role role){
        return ResponseVO.success("success", roleService.save(role));
    }

    @PutMapping
    public ResponseVO update(@RequestBody Role role){
        return ResponseVO.success("success", roleService.updateById(role));
    }

    @DeleteMapping("/{id}")
    public ResponseVO remove(@PathVariable(name = "id") Long id){
        return ResponseVO.success("success", roleService.removeById(id));
    }

    @DeleteMapping("/batch/{ids}")
    public ResponseVO removeBatch(@PathVariable(name = "ids") Collection<Long> ids){
        return ResponseVO.success("success", roleService.removeByIds(ids));
    }
}
