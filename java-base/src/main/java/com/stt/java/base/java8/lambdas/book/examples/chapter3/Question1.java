package com.stt.java.base.java8.lambdas.book.examples.chapter3;

import com.stt.java.base.java8.lambdas.book.examples.chapter1.Album;
import com.stt.java.base.java8.lambdas.book.examples.chapter1.Artist;
import com.stt.java.base.java8.lambdas.book.examples.chapter1.SampleData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1. 常用流操作。实现如下函数：
 *
 * Created by Administrator on 2017-02-19.
 *
 * @author shitongtong
 */
public class Question1 {

    /**
     * a. 编写一个求和函数， 计算流中所有数之和。例如，int addUp(Stream<Integer> numbers)；
     * @param numbers
     * @return
     */
    private int addUp(Stream<Integer> numbers){
        /*
        acc:累加器（相加的结果）
        element：当前元素，即被加数
         */
        Optional<Integer> integer = numbers.reduce((acc, element) -> acc + element);
        return integer.get();

//          return numbers.reduce(0, (acc, x) -> acc + x);//作者答案
    }


    @Test
    public void testAddUp(){
        System.out.println(addUp(Stream.of(1, 2, 3, 4)));
    }

    /**
     * b. 编写一个函数，接受艺术家列表作为参数，返回一个字符串列表，其中包含艺术家的姓名和国籍；
     */
    private static List<String> getArtistNameAndNationality(List<Artist> artistList){
//        return artistList.stream().map(artist -> artist.getName() + "||" + artist.getNationality()).collect(Collectors.toList());
        //author
        return artistList.stream().flatMap(artist -> Stream.of(artist.getName(),artist.getNationality())).collect(Collectors.toList());
    }

    @Test
    public void testGetArtistNameAndNationality(){
        List<String> list = getArtistNameAndNationality(SampleData.membersOfTheBeatles);
        list.stream().forEach(System.out::println);
    }

    /**
     * c.编写一个函数，接受专辑列表作为参数，返回一个由最多包含3 首歌曲的专辑组成的列表。
     */
    private List<Album> getMoreThen3AlumList(List<Album> albumList){
        //和author一样
        List<Album> list = albumList.stream().filter(album -> album.getTrackList().size() <= 3).collect(Collectors.toList());
        return list;
    }

    @Test
    public void testGetMoreThen3AlumList(){
        List<Album> albumList = new ArrayList<>();
        albumList.add(SampleData.aLoveSupreme);
        albumList.add(SampleData.manyTrackAlbum);
        albumList.add(SampleData.sampleShortAlbum);
        albumList.stream().forEach(System.out::println);
        List<Album> alumList = getMoreThen3AlumList(albumList);
        alumList.stream().forEach(System.out::println);
    }

}
