package com.stt.java.base.java8.lambdas.book.examples.chapter3;

import com.stt.java.base.java8.lambdas.book.examples.chapter1.Artist;
import com.stt.java.base.java8.lambdas.book.examples.chapter1.SampleData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-02-19.
 *
 * @author shitongtong
 */
public class Question2 {
    /*
    2. 迭代。修改如下代码，将外部迭代转换成内部迭代：
        int totalMembers = 0;
        for (Artist artist : artists) {
        Stream<Artist> members = artist.getMembers();
        totalMembers += members.count();
        }
     */
    public static void main(String[] args) {
        List<Artist> artistList = new ArrayList<>();
        artistList.add(SampleData.theBeatles);
        artistList.add(SampleData.theBeatles);
        //我的更好一些？
        int totalMembers = (int) artistList.stream().flatMap(artist -> artist.getMembers()).count();
        //author
//        int totalMembers = artistList.stream().map(artist -> artist.getMembers().count()).reduce(0L, Long::sum).intValue();
        System.out.println(totalMembers);//8
    }
}
