package com.stt.java.base.java8.demo3;

/**
 * Created by Administrator on 2017-01-13.
 *
 * @author shitongtong
 */
@FunctionalInterface
public interface Converter<F,T> {

    T convert(F from);
}
