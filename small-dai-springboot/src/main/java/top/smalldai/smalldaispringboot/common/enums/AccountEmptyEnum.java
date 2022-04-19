package top.smalldai.smalldaispringboot.common.enums;

import lombok.Data;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 校验信息枚举类
 * @Data:Created in 2022/3/25 10:11 上午
 */
public enum AccountEmptyEnum {

    /* 用户名为空 */
    USERNAME_EMPTY("用户名为空"),

    /* 密码为空 */
    PASSWORD_EMPTY("密码为空"),

    /* 邮箱信息为空 */
    EMAIL_EMPTY("邮箱信息为空"),

    /* 邮箱验证码为空 */
    EMAIL_CODE_EMPTY("邮箱验证码为空"),

    /* 手机号为空 */
    TEL_EMPTY("手机号为空"),

    /* 年龄为空 */
    AGE_EMPTY("年龄为空");

    private String msg;

    AccountEmptyEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
