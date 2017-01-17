package com.stt.java.base.java8.demo10;

import java.lang.annotation.Repeatable;

/**
 * Java 8允许我们把同一个类型的注解使用多次，只需要给该注解标注一下@Repeatable即可。
 *
 * Created by Administrator on 2017-01-17.
 *
 * @author shitongtong
 */
@Repeatable(Hints.class)
public @interface Hint {
    String value();
}
