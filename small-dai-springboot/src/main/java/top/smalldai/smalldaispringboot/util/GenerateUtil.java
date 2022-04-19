package top.smalldai.smalldaispringboot.util;

import top.smalldai.smalldaispringboot.common.enums.ColumnTypeEnum;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description:
 * @Data:Created in 2022/4/1 2:23 下午
 */
public class GenerateUtil {
    /**
     * @Author: xingyuchen
     * @Discription: 获取Java类型对象
     * @param typeStr
     * @Date: 2022/4/1 2:23 下午
     */
    public static ColumnTypeEnum getColumnClassType(String typeStr) {
        //通过字段的类型获取Java的类型
        switch (typeStr) {
            case "VARCHAR":
            case "LONGTEXT":
            case "TEXT":
                return ColumnTypeEnum.STRING_TYPE;
            case "DOUBLE":
                return ColumnTypeEnum.DOUBLE_TYPE;
            case "INT":
                return ColumnTypeEnum.INTEGER_TYPE;
            case "TINYINT":
                return ColumnTypeEnum.BOOLEAN_TYPE;
            case "BIGINT":
                return ColumnTypeEnum.LONG_TYPE;
            case "DATETIME":
            case "TIMESTAMP":
                return ColumnTypeEnum.DATE_TYPE;
            case "DECIMAL":
                return ColumnTypeEnum.BIG_DECIMAL_TYPE;
            default:
                return ColumnTypeEnum.ERROR_TYPE;
        }
    }
}
