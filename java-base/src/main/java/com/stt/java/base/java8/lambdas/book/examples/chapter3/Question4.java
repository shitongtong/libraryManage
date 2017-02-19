package com.stt.java.base.java8.lambdas.book.examples.chapter3;

/**
 * 4. 高阶函数。下面的Stream 函数是高阶函数吗？为什么？
 *
 a. boolean anyMatch(Predicate<? super T> predicate);
 b. Stream<T> limit(long maxSize);

 按照高阶函数定义：
 高阶函数是指接受另外一个函数作为参数，或返回一个函数的函数

 判断：a是(传入的是一个断言式函数)，b不是
 *
 * Created by Administrator on 2017-02-19.
 *
 * @author shitongtong
 */
public class Question4 {
}
