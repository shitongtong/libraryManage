package com.stt.java.base.string;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/10/10.
 */
public class SwapStrings {
    public String s1;
    public String s2;

    /**
     * 直接交换
     *
     * @param s1
     * @param s2
     */
    public static void swap(String s1, String s2) {
        String temp = s1;
        s1 = s2;
        s2 = temp;
    }

    /**
     * 使用数组交换
     *
     * @param arr
     */
    public static void swap(String[] arr) {
        String temp = arr[0];
        arr[0] = arr[1];
        arr[1] = temp;
    }

    /**
     * 使用成员变量交换
     *
     * @param s1
     * @param s2
     */
    public void swapStr(String s1, String s2) {
        this.s1 = s2;
        this.s2 = s1;
    }

    /**
     * 直接打印
     *
     * @param s1
     * @param s2
     */
    public static void print(String s1, String s2) {
        System.out.println("s1=" + s1 + ",s2=" + s2);
    }

    /**
     * 数组打印
     *
     * @param arr
     */
    public static void print(String[] arr) {
        for (String s : arr) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    /**
     * 对象打印
     */
    public void print() {
        System.out.println("this.s1=" + this.s1 + ",this.s2=" + this.s2);
    }

    public static void main(String[] args) {
        System.out.println("------直接交换------");
        String s1 = "s1";
        String s2 = "s2";
        print(s1, s2);
        swap(s1, s2);
        print(s1, s2);

        System.out.println("------数组交换------");
        String[] arr = {"s1", "s2"};
        print(arr);
        swap(arr);
        print(arr);

        System.out.println("------成员变量交换------");
        print(s1,s2);
        SwapStrings swapStrings = new SwapStrings();
        swapStrings.swapStr(s1,s2);
        swapStrings.print();
    }
}
