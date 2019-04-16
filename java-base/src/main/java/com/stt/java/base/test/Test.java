package com.stt.java.base.test;

import java.util.ArrayList;

/**
 * @ClassName Test
 * @Description TODO
 * @Author shitt7
 * @Date 2019/4/12 19:32
 * @Version 1.0
 */
public class Test {
    public Test() {
    }

    static void print(ArrayList al) {
        al.add(2);
        al = new ArrayList();
        al.add(3);
        al.add(4);
    }

    public static void main(String[] args) {
        Test test = new Test();
        ArrayList al = new ArrayList();
        al.add(1);
        print(al);
        System.out.println(al.get(1));
    }
}
