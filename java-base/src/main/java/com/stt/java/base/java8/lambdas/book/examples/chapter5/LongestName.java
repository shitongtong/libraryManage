package com.stt.java.base.java8.lambdas.book.examples.chapter5;

import com.stt.java.base.java8.lambdas.book.examples.chapter1.Artist;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
/**
 * Created by Administrator on 2017-02-20.
 *
 * @author shitongtong
 */
public class LongestName {

    private static Comparator<Artist> byNameLength = comparing(artist -> artist.getName().length());

    public static Artist byReduce(List<Artist> artists){
        return artists.stream().reduce((acc,artist)-> byNameLength.compare(acc,artist)>=0?acc:artist).orElseThrow(RuntimeException::new);
    }

    public static Artist byCollecting(List<Artist> artists){
        return artists.stream().collect(Collectors.maxBy(byNameLength)).orElseThrow(RuntimeException::new);
    }

}
