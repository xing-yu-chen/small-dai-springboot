package top.smalldai.smalldaispringboot.common.constant;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description:
 * @Data:Created in 2022/3/31 1:56 下午
 */
public interface ServiceImplConstant {
    // 基础包
    public static final String SERVICE_IMPL_BASE_PACKAGE = "package ${springPackageName}.service.impl;\n\n";

    // 导包
    public static final String SERVICE_IMPL_IMPORT = "import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;\n" +
            "import org.springframework.stereotype.Service;\n" +
            "import ${springPackageName}.entity.${entity};\n" +
            "import ${springPackageName}.mapper.${entity}Mapper;\n" +
            "import ${springPackageName}.service.${entity}Service;\n" +
            "import javax.annotation.Resource;\n\n";

    // header
    public static final String SERVICE_IMPL_HEADER = "@Service\n" +
            "public class ${entity}ServiceImpl extends ServiceImpl<${entity}Mapper, ${entity}> implements ${entity}Service {\n\n";

    // center
    public static final String SERVICE_IMPL_CENTER_RESOURCE = "    @Resource\n" +
            "    private ${entity}Mapper ${entity?uncap_first}Mapper;\n\n";

    public static final String SERVICE_IMPL_CENTER_EXTEND = "    @Override\n";

    // 联表查询
    public static final String SERVICE_IMPL_CENTER_ENTITY_TO = "import ${springPackageName}.to.${entity}TO;\n";
    public static final String SERVICE_IMPL_CENTER_SELECT = "    public ${entityTO} getBy${entityId}(Long ${entityId}) {\n" +
            "        return ${entity?uncap_first}Mapper.getBy${entityId}(${entityId});\n" +
            "    }\n\n";

    // 联表查询集合
    public static final String SERVICE_IMPL_CENTER_SELECT_LIST_IMPORT = "import java.util.List;\n\n";
    public static final String SERVICE_IMPL_CENTER_SELECT_LIST = "    public List<${entityTO}> listBy${entityId}(){\n" +
            "        return ${entity?uncap_first}Mapper.listBy${entityId}();\n" +
            "    }\n\n";

    // 联表数据删除
    public static final String SERVICE_IMPL_CENTER_DELETE = "    public Integer deleteBy${entityId}(Long ${entityId}){\n" +
            "        return ${entity?uncap_first}Mapper.deleteBy${entityId}(${entityId});\n" +
            "    }\n\n";

    // footer
    public static final String SERVICE_IMPL_FOOTER = "}";
}
