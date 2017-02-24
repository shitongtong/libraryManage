package com.stt.java.base.java8;

import java.util.stream.IntStream;

/**
 * Created by Administrator on 2017-02-23.
 *
 * @author shitongtong
 */
public class TestMain {
    public static void main(String[] args) {
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);
    }
}
