package com.stt.java.base.java8.demo2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2017-01-13.
 *
 * @author shitongtong
 */
public class TestLambda {

    //lambda表达式：
    public static void main(String[] args) {

        //对list进行排序
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        //老版本
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        //lambda表达式：
        //1.
        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });
        //2.对于函数体只有一行代码的，你可以去掉大括号{}以及return关键字
        Collections.sort(names, (String a, String b) -> b.compareTo(a));
        //3.Java编译器可以自动推导出参数类型，所以你可以不用再写一次类型
        Collections.sort(names, (a, b) -> b.compareTo(a));
    }


}
