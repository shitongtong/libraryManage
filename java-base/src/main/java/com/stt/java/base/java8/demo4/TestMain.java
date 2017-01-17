package com.stt.java.base.java8.demo4;

import com.stt.java.base.java8.demo3.Converter;

/**
 * Created by Administrator on 2017-01-13.
 *
 * @author shitongtong
 */
public class TestMain {

    public static void main(String[] args) {
        //ava 8 允许你使用 :: 关键字来传递方法或者构造函数引用
        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        System.out.println(converted);   // 123

        //引用一个对象的方法：
//        converter = something::startsWith;
//        String converted = converter.convert("Java");
//        System.out.println(converted);    // "J"

        //构造函数使用::关键字来引用
        PersonFactory<Person> persionFactory = Person::new;
        Person person = persionFactory.create("Peter", "Parker");
        System.out.println(person);
    }
}
