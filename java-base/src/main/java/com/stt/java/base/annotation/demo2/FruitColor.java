package com.stt.java.base.annotation.demo2;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 水果颜色
 *
 * Created by Administrator on 2017-01-05.
 *
 * @author shitongtong
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FruitColor {

    /**
     * 颜色枚举
     */
    public enum Color{BULE,RED,GREEN};

    /**
     * 颜色属性
     * @return
     */
    Color fruitColor() default Color.GREEN;
}
