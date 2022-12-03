package com.crs.common.annotation;

import java.lang.annotation.*;

/**
 * 日志注解类
 * @author LZ
 * @date 2022/12/2 14:50
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.PARAMETER})
public @interface SystemLog {
    String message() default "";
}
