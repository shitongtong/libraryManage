package com.stt.java.base.annotation.demo2;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 水果供应者注解
 *
 * Created by Administrator on 2017-01-05.
 *
 * @author shitongtong
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FruitProvider {

    /**
     *供应商编号
     * @return
     */
    public int id() default -1;

    /**
     *供应商名称
     * @return
     */
    public String name() default "";

    /**
     *供应商地址
     * @return
     */
    public String address() default "";
}
