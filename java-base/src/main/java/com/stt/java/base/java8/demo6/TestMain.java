package com.stt.java.base.java8.demo6;

import com.stt.java.base.java8.demo3.Converter;

/**
 * Created by Administrator on 2017-01-13.
 *
 * @author shitongtong
 */
public class TestMain {

    public static void main(String[] args) {
        //直接在lambda表达式中访问外层的局部变量：
        int num = 1;//final可不必声明，num已被隐性声明为final,不可改变
        Converter<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num);
        String convert = stringConverter.convert(2);
        System.out.println(convert);//3

//        num = 4;//编译报错
    }
}
