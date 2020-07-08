package com.stt.java.base.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Test3
 * @Description TODO
 * @Author shitt7
 * @Date 2019/6/19 20:22
 * @Version 1.0
 */
public class Test3 {

    public static void main(String[] args) {
        String s = "♠3,♥3,♦4,♥6,♠7,♥8,♥9,♣9,♠10,♣10,♦J,♦Q,♠K,♣K,♠2,♥2,♦2";
        //需要处理的集合
        List<String> list = Arrays.asList(s.split(","));
        System.out.println("需要处理的集合:" + list);

        List<String> cList = new ArrayList<>();
        List<String> nList = new ArrayList<>();
        for (String s1 : list) {
            String c = String.valueOf(s1.charAt(0));
            String n = String.valueOf(s1.charAt(1));
            int i = nList.indexOf(n);
            if (i == -1) {
                cList.add(c);
                nList.add(n);
            } else {
                cList.remove(i);
                nList.remove(i);
            }
        }
        List<String> dList = new ArrayList<>();
        for (int i = 0; i < cList.size(); i++) {
            dList.add(cList.get(i) + nList.get(i));
        }
        System.out.println("目的集合:" + dList);
    }
}
