package com.stt.java.base.test;

/**
 * @ClassName Test2
 * @Description 输出结果：finallyfinished
 * @Author shitt7
 * @Date 2019/4/25 16:14
 * @Version 1.0
 */
public class Test2 {
    public static int aMethod(int i) throws Exception {
        try {
            return i / 10;
        } catch (Exception ex) {
            throw new Exception("exception in a aMothod");
        } finally {
            System.out.print("finally");
        }
    }

    public static void main(String[] args) {
        try {
            aMethod(0);
        } catch (Exception ex) {
            System.out.print("exception in main");
        }
        System.out.print("finished");
    }

}
