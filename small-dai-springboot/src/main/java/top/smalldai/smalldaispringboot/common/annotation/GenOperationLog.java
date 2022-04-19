package top.smalldai.smalldaispringboot.common.annotation;

import org.aspectj.lang.annotation.Aspect;

import java.lang.annotation.*;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description:
 * @Data:Created in 2022/4/4 10:22 下午
 */

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GenOperationLog {
    String value() default "";
    boolean type() default false;
}
