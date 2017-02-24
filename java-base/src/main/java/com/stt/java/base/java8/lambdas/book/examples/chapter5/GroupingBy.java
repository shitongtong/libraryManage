package com.stt.java.base.java8.lambdas.book.examples.chapter5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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

    private static final Set<Characteristics> characteristicses = new HashSet<>();
    static {
        characteristicses.add(Characteristics.IDENTITY_FINISH);
    }

    private final Function<? super T,? extends K> classifier;

    public GroupingBy(Function<? super T,? extends K> classifier){
        this.classifier = classifier;
    }

    @Override
    public Supplier<Map<K, List<T>>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<K, List<T>>, T> accumulator() {
        return (map,element) ->{
            K key = classifier.apply(element);
            List<T> elements = map.computeIfAbsent(key, k -> new ArrayList<T>());
            elements.add(element);
        };
    }

    @Override
    public BinaryOperator<Map<K, List<T>>> combiner() {
        return (left,right)->{
            right.forEach((key,value)->{
                left.merge(key,value,(leftValue,rightValue)->{
                    leftValue.addAll(rightValue);
                    return leftValue;
                });
            });
            return left;
        };
    }

    @Override
    public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
        return map->map;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return characteristicses;
    }
}
