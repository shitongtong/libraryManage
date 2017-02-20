package com.stt.java.base.java8.lambdas.book.examples.chapter5;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by Administrator on 2017-02-20.
 *
 * @author shitongtong
 */
public class GroupingBy<K,T> implements Collector<T, Map<K, List<T>>, Map<K,List<T>>> {
    @Override
    public Supplier<Map<K, List<T>>> supplier() {
        return null;
    }

    @Override
    public BiConsumer<Map<K, List<T>>, T> accumulator() {
        return null;
    }

    @Override
    public BinaryOperator<Map<K, List<T>>> combiner() {
        return null;
    }

    @Override
    public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}
