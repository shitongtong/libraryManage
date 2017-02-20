package com.stt.java.base.java8.lambdas.book.examples.chapter4;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by Administrator on 2017-02-20.
 *
 * @author shitongtong
 */
public class TestMain {

    public static void main(String[] args) {
        overloadedMethod("adc");
    }

    public static void overloadedMethod(Object o){
        System.out.println("o"+o);
    }

    public static void overloadedMethod(String s){
        System.out.println("s"+s);
    }

    @Test
    public void testOptional(){
        Optional<Object> empty = Optional.empty();
        Optional<Object> o = Optional.ofNullable(null);
//        System.out.println(o.get());
        Assert.assertFalse(empty.isPresent());
        Assert.assertFalse(o.isPresent());

        Assert.assertEquals("b", empty.orElse("b"));
        Assert.assertEquals("c", empty.orElseGet(() -> "c"));

        String aaa = Optional.ofNullable("aaa").orElseGet(() -> "ccc");
        Object o1 = Optional.ofNullable(null).orElseGet(() -> "ccc");
        System.out.println(aaa);
        System.out.println(o1);
    }
}
