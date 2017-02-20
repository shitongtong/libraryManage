package com.stt.java.base.java8.lambdas.book.examples.chapter5;

import org.junit.Test;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.maxBy;

/**
 * 2. 收集器
 *
 * Created by Administrator on 2017-02-20.
 *
 * @author shitongtong
 */
public class Question2 {

    /*
    a. 找出名字最长的艺术家，分别使用收集器和第3 章介绍过的reduce 高阶函数实现。
68 ｜ 第5 章
然后对比二者的异同：哪一种方式写起来更简单，哪一种方式读起来更简单？以下面
的参数为例，该方法的正确返回值为"Stuart Sutcliffe"：
Stream<String> names = Stream.of("John Lennon", "Paul McCartney",
"George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
     */
    public String methodA1(Stream<String> names){
        Optional<String> optional = names.collect(maxBy(Comparator.comparing(String::length)));
        return optional.get();
    }
    @Test
    public void testMethod1(){
        Stream<String> names = Stream.of("John Lennon", "Paul McCartney",
                "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
        String s = methodA1(names);
        System.out.println(s);
    }

    /*public String methodA2(Stream<String> names){
        names.reduce()
        return "";
    }*/


    /*******************************************************************
     *b. 假设一个元素为单词的流，计算每个单词出现的次数。假设输入如下，则返回值为一
     个形如[John → 3, Paul → 2, George → 1] 的Map：
     Stream<String> names = Stream.of("John", "Paul", "George", "John",
     "Paul", "John");
     *
     ********************************************************************/
    public Map<String,Long> countWords(Stream<String> names){
        return names.collect(Collectors.groupingBy(word -> word,Collectors.counting()));
    }
    @Test
    public void testCountWords(){
        Stream<String> names = Stream.of("John", "Paul", "George", "John", "Paul", "John");
        Map<String, Long> map = countWords(names);
        Stream.of(map).forEach(System.out::println);
    }

    /**
     * c. 用一个定制的收集器实现Collectors.groupingBy 方法，不需要提供一个下游收集器，
     只需实现一个最简单的即可。别看JDK 的源码，这是作弊！提示：可从下面这行代
     码开始：
     public class GroupingBy<T, K> implements Collector<T, Map<K, List<T>>, Map<K,
     List<T>>>
     */

}
