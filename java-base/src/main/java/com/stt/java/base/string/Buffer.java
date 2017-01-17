package com.stt.java.base.string;

/**
 * Created by Administrator on 2017-01-09.
 *
 * @author shitongtong
 */
public class Buffer {
    public static void main(String[] args) {
        String s1 = "aaaaa";
        String s2 = "bbbbb";
        String r = null;
        int i = 3694;
        r = s1 + i + s2;

        for(int j=0;j<10;j++){
            r+="23124";
        }
    }
}
