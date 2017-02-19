package com.stt.java.base.java8.lambdas.book.examples.chapter3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * 7. 在一个字符串列表中，找出包含最多小写字母的字符串。对于空列表，返回Optional<String> 对象。
 *
 * Created by Administrator on 2017-02-19.
 *
 * @author shitongtong
 */
public class Question7 {

    public static void main(String[] args) {
        List<String> strList = Arrays.asList("dsaDds","dssSDSDA","");
        strList = Arrays.asList("");
        Optional<String> optional = getMostLowercaseString(strList);
        System.out.println(optional);
    }

    //自己没做出来
    //author
    public static Optional<String> getMostLowercaseString(List<String> stringList){
        Optional<String> optional = stringList.stream().max(Comparator.comparing(Question6::getLowerCaseCount));
        return optional;

    }
}
