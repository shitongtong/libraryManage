package com.stt.java.base.test;

import org.junit.Test;

/**
 * @ClassName SimpleTest
 * @Description TODO
 * @Author shitt7
 * @Date 2019/4/12 16:49
 * @Version 1.0
 */
public class SimpleTest {

    @Test
    public void test1() {
        int a = 0;
        while (a < 5) {
            switch (a) {
                case 0:
                case 3:
                    a = a + 2;
                case 1:
                case 2:
                    a = a + 3;
                default:
                    a = a + 5;
            }
        }
        System.out.print(a);
    }



}
