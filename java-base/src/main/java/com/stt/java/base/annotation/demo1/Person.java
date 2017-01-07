package com.stt.java.base.annotation.demo1;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 注解类：
 *
 * @Documented 标记生成javadoc

　　@Inherited 标记继承关系

　　@Retention 注解的生存期

　　@Target 标注的目标
 *
 * Created by Administrator on 2017-01-05.
 *
 * @author shitongtong
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Person {
    String name();
    int age();
}
