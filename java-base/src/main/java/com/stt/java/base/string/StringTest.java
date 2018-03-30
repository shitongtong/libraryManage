package com.stt.java.base.string;

import org.junit.Test;

/**
 * Created by Administrator on 2017-01-09.
 *
 * @author shitongtong
 */
public class StringTest {
    public static void main(String[] args) {
        String result = "";
        result += "ok";

        String a = "ab" + "cd" + "ef";
    }

    /**
     * concat方法与+的区别？
     * 1、一个方法，一个表达式
     * 2、一个只能连接字符串，一个可以连接其他
     * concat能做的+也能做，还需要concat做什么，还有其他区别吗？？
     */
    @Test
    public void testConcat() {
        String str1 = "asd";
        String str2 = "asdqwe";
        String str3 = str1.concat("qwe");
        String str4 = "asd" + "qwe";
        String str5 = str1 + "qwe";

        System.out.println(str2 == str3);//false
        System.out.println(str2 == str4);//true
        System.out.println(str2 == str5);//false
    }

    @Test
    public void testException() {
        String s = "a";
        while (true) {
            String s1 = "s1";
            s = s + s1;
        }
//        System.out.println(s);
    }

    @Test
    public void test() {
        String freetimeDate = "2018-01-01"; //年月日
        System.out.println(freetimeDate.substring(0, freetimeDate.length() - 3));
    }

    @Test
    public void test1() {
        String a = "fff" + null;
        System.out.println(a);
    }

}
