package com.stt.java.base.java8.lambdas.book.examples.chapter5;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1. 方法引用
 回顾第3 章中的例子，使用方法引用改写以下方法：
 a. 转换大写的map 方法；
 b. 使用reduce 实现count 方法；
 c. 使用flatMap 连接列表。
 *
 * Created by Administrator on 2017-02-20.
 *
 * @author shitongtong
 */
public class Question1 {

    /*
    a.转换大写的map 方法；例子代码：
    List<String> collected = Stream.of("a", "b", "hello")
                                .map(string -> string.toUpperCase()) 􀁮
                                .collect(toList());
     */
    @Test
    public void toUpperCase(){
        List<String> collect = Stream.of("a", "b", "hello").map(String::toUpperCase).collect(Collectors.toList());
        Assert.assertEquals(Arrays.asList("A", "B", "HELLO"), collect);
    }

    /*
     b. 使用reduce 实现count 方法；
     */
    @Test
    public void count(){

    }

}
