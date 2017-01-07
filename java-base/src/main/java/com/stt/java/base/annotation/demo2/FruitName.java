package com.stt.java.base.annotation.demo2;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 水果名称注解
 *
 * Created by Administrator on 2017-01-05.
 *
 * @author shitongtong
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FruitName {
    String value() default "";
}
