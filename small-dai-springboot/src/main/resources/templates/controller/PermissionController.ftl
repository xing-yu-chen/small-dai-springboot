package ${springPackageName}.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import ${springPackageName}.common.response.ResponseVO;
import ${springPackageName}.entity.Permission;
import ${springPackageName}.service.PermissionService;

import javax.annotation.Resource;
import java.util.Collection;

@Api(tags = "权限管理")
@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @GetMapping
    public ResponseVO list(){
        return ResponseVO.success("success", permissionService.list());
    }

    @GetMapping("/{id}")
    public ResponseVO get(@PathVariable(name = "id") Long id){
        return ResponseVO.success("success", permissionService.getById(id));
    }

    @PostMapping
    public ResponseVO save(@RequestBody Permission permission){
        return ResponseVO.success("success", permissionService.save(permission));
    }

    @PutMapping
    public ResponseVO update(@RequestBody Permission permission){
        return ResponseVO.success("success", permissionService.updateById(permission));
    }

    @DeleteMapping("/{id}")
    public ResponseVO remove(@PathVariable(name = "id") Long id){
        return ResponseVO.success("success", permissionService.removeById(id));
    }

    @DeleteMapping("/batch/{ids}")
    public ResponseVO removeBatch(@PathVariable(name = "ids") Collection<Long> ids){
        return ResponseVO.success("success", permissionService.removeByIds(ids));
    }
}
