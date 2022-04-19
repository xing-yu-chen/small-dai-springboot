package top.smalldai.smalldaispringboot.common.enums;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 字段类型映射
 * @Data:Created in 2022/4/1 2:25 下午
 */
public enum ColumnTypeEnum {
    STRING_TYPE("String", ""),
    DOUBLE_TYPE("Double", ""),
    INTEGER_TYPE("Integer", ""),
    BOOLEAN_TYPE("Boolean", ""),
    LONG_TYPE("Long", ""),
    DATE_TYPE("Date", "import java.util.Date;"),
    BIG_DECIMAL_TYPE("BigDecimal", "import java.math.BigDecimal;"),
    ERROR_TYPE("", "");


    private String typeName;
    private String typeImport;
    ColumnTypeEnum(String typeName, String typeImport) {
        this.typeName = typeName;
        this.typeImport = typeImport;
    }

    public String getTypeImport() {
        return typeImport;
    }

    public void setTypeImport(String typeImport) {
        this.typeImport = typeImport;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
