package com.stt.java.base.util;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2018/1/10.
 */
public class DrawHeart5 {
    /**
     * 使用公式
     * ((0.05*x)^2 + (0.1*y)^2-1)^3-(0.05*x)^2 * (0.1*y)^3 < = 0
     *
     * @param r      半径
     * @param size   大小 0-1
     * @param left   左边显示
     * @param right  右边显示
     * @param center 中间显示
     */
    private static void heart(int r, double size, String left, String right, String center) {
        size = 1 / (1.5 * r * size);
        StringBuilder sb = new StringBuilder();
        for (int y = r; y > -r; y--, sb.append("\n"))
            for (int x = -2 * r; x < 2 * r; x++) {
                String req = center;
                if (x < 0) req = left;
                else if (x > 0) req = right;
                char msg = (req + req).charAt((x - y) % req.length() + req.length());
                sb.append((Math.pow(Math.pow(x * size, 2) + Math.pow(y * 2 * size, 2) - 1, 3) - Math.pow(x * size, 2) * Math.pow(y * 2 * size, 3) <= 0 ? msg + " " : "  "));
            }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        heart(10, 1, ".", ".", ".");
    }
}
