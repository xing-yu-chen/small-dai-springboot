package top.smalldai.smalldaispringboot.common.annotation;

import java.lang.annotation.*;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统日志注解
 * @Data:Created in 2022/4/6 2:27 上午
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SysOperationLog {
    String value() default "";
}
