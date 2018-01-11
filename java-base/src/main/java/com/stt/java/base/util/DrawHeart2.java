package com.stt.java.base.util;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2018/1/10.
 */
public class DrawHeart2 {
    public static void main(String[] args) {
        for (float y = (float) 1.5; y > -1.5; y -= 0.1) {
            for (float x = (float) -1.5; x < 1.5; x += 0.05) {
                float a = x * x + y * y - 1;
                if ((a * a * a - x * x * y * y * y) <= 0.0) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
    }
}
