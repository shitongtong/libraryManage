package com.stt.java.base.exception;

/**
 * 栈溢出测试 java.lang.StackOverflowError
 * 每次溢出深处不一样
 * 第一次：Stack deep : 21338
 * 第二次：Stack deep : 33181
 * 第三次：Stack deep : 26462
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2018/3/1.
 */
public class StackErrorMock {
    private static int index = 1;

    public void call() {
        index++;
        call();
    }

    public static void main(String[] args) {
        StackErrorMock mock = new StackErrorMock();
        try {
            mock.call();
        } catch (Throwable e) {
            System.out.println("Stack deep : " + index);
            e.printStackTrace();
        }
    }
}
