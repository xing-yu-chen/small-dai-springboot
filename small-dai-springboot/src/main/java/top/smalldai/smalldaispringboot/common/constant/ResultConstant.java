package top.smalldai.smalldaispringboot.common.constant;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 返回值信息常量
 * @Data:Created in 2022/3/25 10:41 上午
 */
public interface ResultConstant {
    /* 成功code: 200 */
    public static final Integer SUCCESS_CODE = 200;

    /* 成功请求创建了资源但返回null: 201 */
    public static final Integer CREATED_CODE = 201;
    public static final String CREATED_MSG = "暂无数据";

    /* 客户端请求的语法错误，服务器无法理解code: 400 */
    public static final Integer BAD_REQUEST_CODE = 400;
    public static final String BAD_REQUEST_MSG = "请求的参数不合法";

    /* 资源未找到code: 404 */
    public static final Integer NOT_FOUND_CODE = 404;
    public static final String NOT_FOUND_MSG = "资源未找到";

    /* 不合法的请求方法code: 405 */
    public static final Integer METHOD_NOT_ALLOWED_CODE = 405;
    public static final String METHOD_FOUND_ALLOWED_MSG = "不合法的请求方法";

    /* 服务器内部错误: 500 */
    public static final Integer INTERNAL_SERVER_CODE = 500;
    public static final String INTERNAL_SERVER_MSG = "服务器内部错误";

}
