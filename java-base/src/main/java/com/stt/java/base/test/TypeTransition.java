package com.stt.java.base.test;

/**
 * @ClassName TypeTransition
 * @Description TODO
 * @Author shitt7
 * @Date 2019/4/11 19:20
 * @Version 1.0
 */
public class TypeTransition {
    public static void main(String[] args) {
        char a = 'h';
        int i = 100;
        int j = 97;
        int aa = a + i;
        System.out.println("aa=" + aa);
        char bb = (char) j;
        System.out.println("bb=" + bb);

    }
}
