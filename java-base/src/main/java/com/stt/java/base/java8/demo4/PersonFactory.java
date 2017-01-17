package com.stt.java.base.java8.demo4;

/**
 * Created by Administrator on 2017-01-13.
 *
 * @author shitongtong
 */
public interface PersonFactory<P extends Person> {

    P create(String fristName,String lastName);
}
