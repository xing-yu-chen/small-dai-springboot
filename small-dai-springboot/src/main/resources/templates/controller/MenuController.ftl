package ${springPackageName}.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import ${springPackageName}.common.response.ResponseVO;
import ${springPackageName}.entity.Menu;
import ${springPackageName}.service.MenuService;

import javax.annotation.Resource;
import java.util.Collection;

@Api(tags = "菜单管理")
@RestController
@RequestMapping("/menus")
public class MenuController {

    @Resource
    private MenuService menuService;

    @GetMapping
    public ResponseVO list(){
        return ResponseVO.success("success", menuService.list());
    }

    @GetMapping("/{id}")
    public ResponseVO get(@PathVariable(name = "id") Long id){
        return ResponseVO.success("success", menuService.getById(id));
    }

    @PostMapping
    public ResponseVO save(@RequestBody Menu menu){
        return ResponseVO.success("success", menuService.save(menu));
    }

    @PutMapping
    public ResponseVO update(@RequestBody Menu menu){
        return ResponseVO.success("success", menuService.updateById(menu));
    }

    @DeleteMapping("/{id}")
    public ResponseVO remove(@PathVariable(name = "id") Long id){
        return ResponseVO.success("success", menuService.removeById(id));
    }

    @DeleteMapping("/batch/{ids}")
    public ResponseVO removeBatch(@PathVariable(name = "ids") Collection<Long> ids){
        return ResponseVO.success("success", menuService.removeByIds(ids));
    }
}
