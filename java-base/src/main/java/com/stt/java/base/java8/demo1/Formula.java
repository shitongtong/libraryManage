package com.stt.java.base.java8.demo1;

/**
 * Created by Administrator on 2017-01-13.
 *
 * @author shitongtong
 */
public interface Formula {

    double calculate(int a);

    default double sqrt(int a){
       return Math.sqrt(a);
    }

}
