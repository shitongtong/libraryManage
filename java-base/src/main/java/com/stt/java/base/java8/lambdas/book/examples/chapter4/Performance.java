package com.stt.java.base.java8.lambdas.book.examples.chapter4;

import com.stt.java.base.java8.lambdas.book.examples.chapter1.Artist;

import java.util.stream.Stream;

import static java.util.stream.Stream.concat;

/**
 * 该接口表示艺术家的演出——专辑或演唱会
 *
 * Created by Administrator on 2017-02-20.
 *
 * @author shitongtong
 */
public interface Performance {

    String getName();

    Stream<Artist> getMusicians();

    /*
    1. 在例4-25 所示的Performance 接口基础上，添加getAllMusicians 方法，该方法返回包
含所有艺术家名字的Stream，如果对象是乐队，则返回每个乐队成员的名字。例如，如
果getMusicians 方法返回甲壳虫乐队，则getAllMusicians 方法返回乐队名和乐队成员，
如约翰· 列侬、保罗· 麦卡特尼等。
     */
    /*default Stream<String> getAllMusicians(){
        Stream<Object> objectStream = getMusicians().filter(artist -> !artist.isSolo()).flatMap(artist -> Stream.of(artist.getName(), artist.getMembers().map(artist1 -> artist1.getName())));
        return null;
    }*/

    //author:感觉不符合题目要求啊，我审题出错啦？？？
    default Stream<Artist> getAllMusicians() {
        return getMusicians()
                .flatMap(artist -> concat(Stream.of(artist), artist.getMembers()));
    }
}
