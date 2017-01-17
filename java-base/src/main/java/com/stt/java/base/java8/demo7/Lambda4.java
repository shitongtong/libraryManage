package com.stt.java.base.java8.demo7;

import com.stt.java.base.java8.demo3.Converter;

/**
 * Created by Administrator on 2017-01-13.
 *
 * @author shitongtong
 */
public class Lambda4 {

    static int outerStaticNum;
    int outerNum;

    void testScopes() {
        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };

        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from);
        };
    }

}
