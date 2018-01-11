package com.stt.java.base.util;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2018/1/10.
 */
public class DrawHeart {
    public static void main(String[] args) {
        //打印上半部分
        for (int i = 0; i < 5; i++) {//最外城循环，控制这部分总共有5行
            for (int j = 0; j < 5; j++) {
                if (j == 4 - i)
                    System.out.print("* ");
                else
                    System.out.print("  ");
            }
            for (int k = 0; k < 5; k++) {
                if (k == 4)
                    continue;
                if (k == i)
                    System.out.print("* ");
                else
                    System.out.print("  ");
            }
            for (int j = 0; j < 5; j++) {
                if (j == 0)
                    continue;
                if (j == 4 - i)
                    System.out.print("* ");
                else
                    System.out.print("  ");
            }
            for (int k = 0; k < 5; k++) {
                if (k == i)
                    System.out.print("* ");
                else
                    System.out.print("  ");
            }
            System.out.println();
        }
        //打印下半部分
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                if (k == i)
                    System.out.print("* ");
                else
                    System.out.print("  ");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 8 - i)
                    System.out.print("* ");
                else
                    System.out.print("  ");
            }
            System.out.println();
        }
    }
}
