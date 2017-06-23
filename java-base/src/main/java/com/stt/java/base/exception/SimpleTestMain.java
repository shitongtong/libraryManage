package com.stt.java.base.exception;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/6/14.
 */
public class SimpleTestMain {

    public static void main(String[] args) {
        try {
            String str = null;
            str.length();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage() == null);
            System.out.println("===" + e.getMessage() + "===");
            System.out.println("===" + e.toString() + "===");
            System.out.println("===" + e.getCause() + "===");
            System.out.println("===" + e.getLocalizedMessage() + "===");
        }
    }
}
