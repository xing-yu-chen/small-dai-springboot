package top.smalldai.smalldaispringboot.common.constant;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description:
 * @Data:Created in 2022/3/31 1:05 下午
 */
public interface EntityConstant {
    public static final String ENTITY_BASE_PACKAGE = "package ${springPackageName}.entity;\n";

    // 实体类导包
    public static final String ENTITY_IMPORT = "import com.baomidou.mybatisplus.annotation.*;\n" +
            "import io.swagger.annotations.ApiModel;\n" +
            "import io.swagger.annotations.ApiModelProperty;\n" +
            "import lombok.AllArgsConstructor;\n" +
            "import lombok.Data;\n" +
            "import lombok.NoArgsConstructor;\n" +
            "import lombok.experimental.Accessors;\n" +
            "import com.fasterxml.jackson.annotation.JsonProperty;\n" +
            "import java.io.Serializable;\n" +
            "\n";
    // 头部
    public static final String ENTITY_HEADER = "@Data\n" +
            "@NoArgsConstructor\n" +
            "@AllArgsConstructor\n" +
            "@Accessors(chain = true)\n" +
            "@ApiModel(description = \"${dbTableRemark}\")\n" +
            "@TableName(value = \"${dbTableName}\")\n" +
            "public class ${entity} implements Serializable {\n" +
            "\n";

    // swagger 注释
    public static final String ENTITY_CENTER_SWAGGER_VALUE_PREFIX = "    @ApiModelProperty(value = \"";
    public static final String ENTITY_CENTER_SWAGGER_VALUE_CENTER = "\", name = \"";
    public static final String ENTITY_CENTER_SWAGGER_VALUE_SUFFIX = "\")\n";

    // mybatis ID
    public static final String ENTITY_CENTER_MYBATIS_ID_PREFIX = "    @TableId(value = \"";
    public static final String ENTITY_CENTER_MYBATIS_ID_SUFFIX = "\", type = IdType.AUTO)\n";

    // mybatis Value
    public static final String ENTITY_CENTER_MYBATIS_VALUE_PREFIX = "    @TableField(value = \"";
    public static final String ENTITY_CENTER_MYBATIS_VALUE_SUFFIX = "\")\n";

    // gmtCreate
    public static final String ENTITY_CENTER_GMT_CREATE = "    @TableField(value = \"创建时间\", fill = FieldFill.INSERT)\n";

    // gmtModified
    public static final String ENTITY_CENTER_GMT_MODIFIED = "    @TableField(value = \"修改时间\", fill = FieldFill.INSERT_UPDATE)\n";


    // 返回接收的JSON格式
    public static final String ENTITY_CENTER_JSON_PREFIX = "    @JsonProperty(value = \"";
    public static final String ENTITY_CENTER_JSON_SUFFIX = "\")\n";

    // 字段详细
    public static final String ENTITY_CENTER_COLUMN_PREFIX = "    private ";
    public static final String ENTITY_CENTER_COLUMN_EMPTY = " ";
    public static final String ENTITY_CENTER_COLUMN_SUFFIX = ";\n\n";


    // entity end
    public static final String ENTITY_FOOTER = "}";
}
