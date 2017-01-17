package com.stt.java.base.java8.demo10;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Java 8的注解还增加到两种新的target上了：
 *
 * Created by Administrator on 2017-01-17.
 *
 * @author shitongtong
 */

@Target({ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
public @interface MyAnnotation {
}
