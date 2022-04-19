package top.smalldai.smalldaispringboot.common.constant;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description:
 * @Data:Created in 2022/3/31 1:19 下午
 */
public interface MapperConstant {
    public static final String MAPPER_BASE_PACKAGE = "package ${springPackageName}.mapper;\n\n";

    // mapper 导包
    public static final String MAPPER_IMPORT = "import com.baomidou.mybatisplus.core.mapper.BaseMapper;\n" +
            "import org.apache.ibatis.annotations.Mapper;\n" +
            "import org.apache.ibatis.annotations.Param;\n" +
            "import ${springPackageName}.entity.${entity};\n\n";

    // mapper 头部
    public static final String MAPPER_HEADER = "@Mapper\n" +
            "public interface ${entity}Mapper extends BaseMapper<${entity}> {\n\n";

    // mapper center
    // 联表查询
    public static final String MAPPER_CENTER_ENTITY_TO = "import ${springPackageName}.to.${entityTO};\n\n";
    public static final String MAPPER_CENTER_SELECT = "    /* 联表查询获取TO */\n" +
            "    ${entityTO} getBy${entityId}(@Param(value = \"${entityId}\")Long ${entityId});\n\n";

    // 联表查询集合
    public static final String MAPPER_CENTER_SELECT_LIST_IMPORT = "import java.util.List;\n\n";
    public static final String MAPPER_CENTER_SELECT_LIST = "    /* 联表查询获取全部集合 */\n" +
            "    List<${entityTO}> listBy${entityId}();\n\n";

    // 联表数据删除
    public static final String MAPPER_CENTER_DELETE = "    /* 联表数据删除 */\n" +
            "    Integer deleteBy${entityId}(@Param(value = \"${entityId}\")Long ${entityId});\n\n";

    // mapper 结束
    public static final String MAPPER_FOOTER = "}";
}
