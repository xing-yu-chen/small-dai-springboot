package top.smalldai.smalldaispringboot.common.constant;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: Controller常量
 * 只支持查询和删除可以联表，修改和新增都是单表操作
 * @Data:Created in 2022/3/30 8:50 下午
 */
public interface ControllerConstant {
    // 基础包
    public static final String CONTROLLER_BASE_PACKAGE = "package ${springPackageName}.controller;\n\n";

    // import
    public static final String CONTROLLER_IMPORT = "import org.springframework.web.bind.annotation.*;\n" +
            "import javax.annotation.Resource;\n" +
            "import ${springPackageName}.common.response.ResponseVO;\n" +
            "import ${springPackageName}.entity.${entity};\n" +
            "import ${springPackageName}.service.${entity}Service;\n" +
            "import java.util.Collection;\n\n";

    // header
    public static final String CONTROLLER_HEADER = "@RestController\n" +
            "@RequestMapping(value = \"/${entity?uncap_first}\")\n" +
            "public class ${entity}Controller {\n\n" +
            "    @Resource\n" +
            "    private ${entity}Service ${entity?uncap_first}Service;\n\n";

    // 查询单表集合
    public static final String CONTROLLER_CENTER_SELECT_LIST_ONE = "/* 单表查询全部 */\n" +
            "    @GetMapping\n" +
            "    public ResponseVO list(){\n" +
            "        return ResponseVO.success(\"success\", ${entity?uncap_first}Service.list());\n" +
            "    }\n\n";

    // 查询单表ID
    public static final String CONTROLLER_CENTER_SELECT_ONE = " /* 单表查询某一ID */\n" +
            "    @GetMapping(\"/{${entityId}}\")\n" +
            "    public ResponseVO getById(@PathVariable(name = \"${entityId}\")Long ${entityId}){\n" +
            "        return ResponseVO.success(\"success\", ${entity?uncap_first}Service.getById(${entityId}));\n" +
            "    }\n\n";

    // 联表查询集合
    public static final String CONTROLLER_CENTER_SELECT_LIST = "/* 联表查询集合 */\n" +
            "    @GetMapping(\"/list\")\n" +
            "    public ResponseVO listBy${entityId}(){\n" +
            "        return ResponseVO.success(\"success\", ${entity?uncap_first}Service.listBy${entityId}());\n" +
            "    }\n\n";

    // 查询多表ID
    public static final String CONTROLLER_CENTER_SELECT = " /* 查询多表ID */\n" +
            "    @GetMapping(\"/get/{${entityId}}\")\n" +
            "    public ResponseVO getBy${entityId}(@PathVariable(name = \"${entityId}\")Long ${entityId}){\n" +
            "        return ResponseVO.success(\"success\", ${entity?uncap_first}Service.getById(${entityId}));\n" +
            "    }\n\n";

    // 新增entity
    public static final String CONTROLLER_CENTER_SAVE = "/* 新增实体 */\n" +
            "    @PostMapping\n" +
            "    public ResponseVO save(@RequestBody ${entity} ${entity?uncap_first}){\n" +
            "        return ResponseVO.success(\"success\", ${entity?uncap_first}Service.save(${entity?uncap_first}));\n" +
            "    }\n\n";

    // 修改entity
    public static final String CONTROLLER_CENTER_UPDATE = "/* 修改实体 */\n" +
            "    @PutMapping\n" +
            "    public ResponseVO update(@RequestBody ${entity} ${entity?uncap_first}){\n" +
            "        return ResponseVO.success(\"success\", ${entity?uncap_first}Service.updateById(${entity?uncap_first}));\n" +
            "    }\n\n";

    // 删除单表
    public static final String CONTROLLER_CENTER_REMOVE_ONE = "/* 删除单表 */\n" +
            "    @DeleteMapping(\"/{${entityId}}\")\n" +
            "    public ResponseVO remove(@PathVariable(name = \"${entityId}\") Long ${entityId}){\n" +
            "        return ResponseVO.success(\"success\", ${entity?uncap_first}Service.removeById(${entityId}));\n" +
            "    }\n\n";

    // 删除联表
    public static final String CONTROLLER_CENTER_REMOVE = "/* 联表删除 */\n" +
            "    @DeleteMapping(\"/batch/{${entityId}}\")\n" +
            "    public ResponseVO removeBatch(@PathVariable(name = \"${entityId}\") Long ${entityId}){\n" +
            "        return ResponseVO.success(\"success\", ${entity?uncap_first}Service.deleteBy${entityId}(${entityId}));\n" +
            "    }\n\n";

    // 单表批量删除
    public static final String CONTROLLER_CENTER_BATCH_REMOVE_ONE = "/* 批量删除单表 */\n" +
            "    @DeleteMapping(\"/batch\")\n" +
            "    public ResponseVO batchRemoveByIds(@RequestBody Collection<Long> ids){\n" +
            "        return ResponseVO.success(\"success\", ${entity?uncap_first}Service.removeBatchByIds(ids));\n" +
            "    }\n\n";

    // 多表批量删除
    public static final String CONTROLLER_CENTER_BATCH_REMOVE = "/* 批量删除多表+es区分单表 */\n" +
            "    @DeleteMapping(\"/batches\")\n" +
            "    public ResponseVO batchesRemoveByIds(@RequestBody Collection<Long> ids){\n" +
            "        for (Long id : ids) {\n" +
            "            ${entity?uncap_first}Service.deleteBy${entityId}(id);\n" +
            "        }\n" +
            "        return ResponseVO.success(\"success\", \"批量删除联表成功\");\n" +
            "    }\n\n";

    // footer
    public static final String CONTROLLER_FOOTER = "}";
}
