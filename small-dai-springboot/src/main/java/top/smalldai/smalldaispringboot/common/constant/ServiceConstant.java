package top.smalldai.smalldaispringboot.common.constant;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description:
 * @Data:Created in 2022/3/31 1:49 下午
 */
public interface ServiceConstant {
    // 基础包
    public static final String SERVICE_BASE_PACKAGE = "package ${springPackageName}.service;\n\n";

    // 引用包
    public static final String SERVICE_IMPORT = "import com.baomidou.mybatisplus.extension.service.IService;\n" +
            "import ${springPackageName}.entity.${entity};\n\n";

    // header
    public static final String SERVICE_HEADER = "public interface ${entity}Service extends IService<${entity}> {\n\n";

    // service center
    // 联表查询
    public static final String SERVICE_CENTER_ENTITY_TO = "import ${springPackageName}.to.${entity}TO;\n";
    public static final String SERVICE_CENTER_SELECT = "    /* 联表查询获取TO */\n" +
            "    ${entityTO} getBy${entityId}(Long ${entityId});\n\n";

    // 联表查询集合
    public static final String SERVICE_CENTER_SELECT_LIST_IMPORT = "import java.util.List;\n";
    public static final String SERVICE_CENTER_SELECT_LIST = "    /* 联表查询获取全部集合 */\n" +
            "    List<${entityTO}> listBy${entityId}();\n\n";

    // 联表数据删除
        public static final String SERVICE_CENTER_DELETE = "    /* 联表数据删除 */\n" +
            "    Integer deleteBy${entityId}(Long ${entityId});\n\n";

    // 结束
    public static final String SERVICE_FOOTER = "}";
}
