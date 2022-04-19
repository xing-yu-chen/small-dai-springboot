package top.smalldai.smalldaispringboot.common.constant;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description:
 * @Data:Created in 2022/3/31 4:03 下午
 */
public interface XmlConstant {

    // header
    public static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD GenMapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" +
            "<mapper namespace=\"${springPackageName}.mapper.${entity}GenMapper\">\n";

    // 联表查询
    public static final String XML_CENTER_SELECT = "<select id=\"getBy${entityId}\" resultType=\"${springPackageName}.to.${entityTO}\">\n" +
            "        select ${sqlArgs}\n" +
            "        from ${sqlFrom}\n" +
            "        where ${sqlEqual} and ${sqlIdOne}\n" +
            "    </select>\n\n";

    public static final String XML_CENTER_SELECT_LIST = "<select id=\"listBy${entityId}\" resultType=\"java.util.List\">\n" +
            "        select ${sqlArgs}\n" +
            "        from ${sqlFrom}\n" +
            "        where ${sqlEqual}\n" +
            "    </select>\n\n";

    // 联表数据删除
    // DELETE orders,items FROM orders,items WHERE orders.userid = items.userid
    public static final String XML_CENTER_DELETE = "<delete id=\"deleteBy${entityId}\" parameterType=\"java.long.Integer\">\n" +
            "        delete ${sqlFrom} \n" +
            "        from ${sqlFrom} \n" +
            "        where ${sqlEqual} and ${sqlIdThree}\n" +
            "    </delete>\n\n";

    public static final String XML_FOOTER = "</mapper>";
}
