package top.smalldai.smalldaispringboot.common.constant;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description:
 * @Data:Created in 2022/3/31 4:21 下午
 */
public interface ToConstant {
    /* 基础包 */
    public static final String TO_BASE_PACKAGE = "package ${springPackageName}.to;\n\n";

    /* import */
    public static final String TO_IMPORT = "import com.baomidou.mybatisplus.annotation.*;\n" +
            "import io.swagger.annotations.ApiModel;\n" +
            "import io.swagger.annotations.ApiModelProperty;\n" +
            "import lombok.AllArgsConstructor;\n" +
            "import lombok.Data;\n" +
            "import lombok.NoArgsConstructor;\n" +
            "import lombok.experimental.Accessors;\n" +
            "import com.fasterxml.jackson.annotation.JsonProperty;\n" +
            "import java.io.Serializable;\n" +
            "import java.util.List;\n" +
            "\n";

    /* header */
    public static final String TO_HEADER = "@Data\n" +
            "@NoArgsConstructor\n" +
            "@AllArgsConstructor\n" +
            "@Accessors(chain = true)\n" +
            "@ApiModel(description = \"${toDescription}TO\")\n" +
            "public class ${toName}TO implements Serializable {\n" +
            "\n";

    // swagger 注释
    public static final String TO_CENTER_SWAGGER_VALUE_PREFIX = "    @ApiModelProperty(value = \"";
    public static final String TO_CENTER_SWAGGER_VALUE_CENTER = "\", name = \"";
    public static final String TO_CENTER_SWAGGER_VALUE_SUFFIX = "\")\n";

    public static final String TO_CENTER_JSON_PROPERTIES_PREFIX = "    @JsonProperty(value = \"";
    public static final String TO_CENTER_JSON_PROPERTIES_SUFFIX = "\")\n";

    // 字段详细
    public static final String TO_CENTER_COLUMN_ENTITY_IMPORT_PREFIX = "    import ${springPackageName}.entity.";
    public static final String TO_CENTER_COLUMN_ENTITY_IMPORT_SUFFIX = ";\n";


    public static final String TO_CENTER_COLUMN_PREFIX = "    private ";
    public static final String TO_CENTER_COLUMN_CENTER = " ";
    public static final String TO_CENTER_COLUMN_SUFFIX = ";\n\n";


    public static final String TO_CENTER_COLUMN_LIST_PREFIX = "    private List<";
    public static final String TO_CENTER_COLUMN_LIST_CENTER = "> ";
    public static final String TO_CENTER_COLUMN_LIST_SUFFIX = " ;\n\n";
    /* footer */
    public static final String TO_FOOTER = "}";
}
