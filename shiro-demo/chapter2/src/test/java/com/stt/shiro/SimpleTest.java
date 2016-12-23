package com.stt.shiro;

import org.junit.Test;

/**
 * Created by Administrator on 2016-12-19.
 *
 * @author shitongtong
 */
public class SimpleTest {
    /*
     *测试对象转字符串
     */
    @Test
    public void testToString(){
        Object obj = null;
        System.out.println(obj);
        try {
            String s = obj.toString();
            System.out.println(s);
        }catch (Exception e){
            System.out.println("异常1");
            e.printStackTrace();
        }

        try {
            String s = (String)obj;
            System.out.println(s);
        }catch (Exception e){
            System.out.println("异常2");
            e.printStackTrace();
        }

        try {
            String s = String.valueOf(obj);
            System.out.println(s);
            if (s == null){
                System.out.println("s is null object");
            }
            if ("".equals(s)){
                System.out.println("s is empty string");
            }
            if ("null".equals(s)){
                System.out.println("s is null string");
            }
        }catch (Exception e){
            System.out.println("异常3");
            e.printStackTrace();
        }
    }
}
