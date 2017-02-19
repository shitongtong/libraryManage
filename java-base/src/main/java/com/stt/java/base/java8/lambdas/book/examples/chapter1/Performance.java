package com.stt.java.base.java8.lambdas.book.examples.chapter1;

import java.util.stream.Stream;

/**
 * Created by Administrator on 2017-02-19.
 *
 * @author shitongtong
 */
public interface Performance {

    String getName();

    Stream<Artist> getMusicians();

    //TODO:test
    default Stream<Artist> getAllMusians(){
        return getMusicians().flatMap(artist -> {
            return Stream.concat(Stream.of(artist),artist.getMembers());
        });
    }

}
