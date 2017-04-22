package com.stt.java.base;

import org.junit.Test;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/4/5.
 */
public class ExceptionTest {

    @Test
    public void test1(){
        try {
            int a = 1/0;
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e);
        }
    }
}
