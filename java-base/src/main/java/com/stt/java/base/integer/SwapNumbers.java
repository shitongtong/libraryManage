package com.stt.java.base.integer;

/**
 * 面试被问到交换函数，写个程序测试下，直接交换和用包装类交换都不能交换两个数的值，而采用数组交换和成员变量交换是可以做到的
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/10/10.
 */
public class SwapNumbers {
    public int a;
    public int b;

    /**
     * 包装类交换
     *
     * @param a
     * @param b
     */
    public static void swap(Integer a, Integer b) {
        Integer temp = a;
        a = b;
        b = temp;
    }

    /**
     * 直接交换
     *
     * @param a
     * @param b
     */
    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    /**
     * 使用成员变量交换
     *
     * @param a
     * @param b
     */
    public void swapNum(int a, int b) {
        this.b = a;
        this.a = b;
    }

    /**
     * 使用数组交换
     *
     * @param arr
     */
    public static void swap(int[] arr) {
        int temp = arr[0];
        arr[0] = arr[1];
        arr[1] = temp;
    }

    /**
     * 包装类打印
     *
     * @param a
     * @param b
     */
    public static void print(Integer a, Integer b) {
        System.out.println("a=" + a + ",b=" + b);
    }

    /**
     * 直接打印
     *
     * @param a
     * @param b
     */
    public static void print(int a, int b) {
        System.out.println("a=" + a + ",b=" + b);
    }

    /**
     * 对象打印
     */
    public void print() {
        System.out.println("a=" + this.a + ",b=" + this.b);
    }

    /**
     * 数组打印
     *
     * @param arr
     */
    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        System.out.println("------直接交换----------");
        int a = 2, b = 3;

        print(a,b);
        swap(a, b);
        print(a,b);

        System.out.println("------包装类交换----------");
        Integer m = new Integer(2);
        Integer n = new Integer(3);

        print(m,n);
        swap(m, n);
        print(m,n);

        System.out.println("-------数组交换---------");
        int[] arr = {2,3};
        print(arr);
        swap(arr);
        print(arr);

        System.out.println("-------成员变量交换---------");
        print(a,b);
        SwapNumbers sn = new SwapNumbers();
        sn.swapNum(a, b);
        sn.print();

    }
}
