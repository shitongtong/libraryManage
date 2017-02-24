package com.stt.java.base.java8.lambdas.book.examples.chapter5;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Created by Administrator on 2017-02-21.
 *
 * @author shitongtong
 */
public class WordCount {

    public static Map<String,Long> countWords(Stream<String> names){
        return names.collect(groupingBy(name -> name,counting()));
    }

}
