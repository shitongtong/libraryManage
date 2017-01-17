package com.stt.java.base.java8.demo3;

/**
 * Created by Administrator on 2017-01-13.
 *
 * @author shitongtong
 */
public class TestConverter {

    public static void main(String[] args) {
        Converter<String,Integer> converter = (from) -> Integer.valueOf(from);

        Integer convert = converter.convert("333");
        System.out.println(convert);//333
    }

}
