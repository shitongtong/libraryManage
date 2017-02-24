package com.stt.java.base.java8.lambdas.book.examples.chapter5;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-02-21.
 *
 * @author shitongtong
 */
public class Fibonacci {

    private final Map<Integer, Long> cache;

    public Fibonacci() {
        cache = new HashMap<>();
        cache.put(0, 0L);
        cache.put(1, 1L);
    }

    public Long fibonacci(int x) {
        return cache.computeIfAbsent(x, n -> fibonacci(n - 1) + fibonacci(n - 2));
    }

}
