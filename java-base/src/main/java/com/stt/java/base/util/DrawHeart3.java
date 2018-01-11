package com.stt.java.base.util;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2018/1/10.
 */
public class DrawHeart3 {
    public static void main(String[] args) {
        for (float y = (float) 1.5; y > -1.5; y -= 0.1) {
            for (float x = (float) -1.5; x < 1.5; x += 0.05) {
                float a = x * x + y * y - 1;
                if ((a * a * a - x * x * y * y * y) <= 0.0) {
                    if (Math.abs(x + 0.100000712) <= 1e-6 && Math.abs(y - 0.6999998) <= 1e-6) {
                        System.out.print("F");
                        continue;
                    }
                    if (Math.abs(x + 0.050000306) <= 1e-6 && Math.abs(y - 0.6999998) <= 1e-6) {
                        System.out.print("r");
                        continue;
                    }
                    if (Math.abs(x + -3.0621885E-7) <= 1e-6 && Math.abs(y - 0.6999998) <= 1e-6) {
                        System.out.print("a");
                        continue;
                    }
                    if (Math.abs(x - 0.049999695) <= 1e-6 && Math.abs(y - 0.6999998) <= 1e-6) {
                        System.out.print("n");
                        continue;
                    }
                    if (Math.abs(x - 0.100000001) <= 1e-6 && Math.abs(y - 0.6999998) <= 1e-6) {
                        System.out.print("k");
                        continue;
                    }

                    System.out.print("*");

                } else
                    System.out.print(" ");
            }

            for (float x = (float) 1.5; x < 4.5; x += 0.05) {
                float a = (x - 3) * (x - 3) + y * y - 1;
                boolean flag = false;
                if ((a * a * a - (x - 3) * (x - 3) * y * y * y) <= 0.0) {
                    if (Math.abs(x - 3 + 0.100000712) <= 1e-6 && Math.abs(y - 0.6999998) <= 1e-6) {
                        System.out.print("C");
                        flag = true;
                        continue;
                    }
                    if ((Math.abs(x - 2.9499986)) <= 1e-6 && Math.abs(y - 0.6999998) <= 1e-6) {
                        System.out.print("a");
                        continue;
                    }
                    if (Math.abs(x - 2.9999986) <= 1e-6 && Math.abs(y - 0.6999998) <= 1e-6) {
                        System.out.print("s");
                        continue;
                    }
                    if (Math.abs(x - 3.0499985) <= 1e-6 && Math.abs(y - 0.6999998) <= 1e-6) {
                        System.out.print("i");
                        continue;
                    }
                    if (Math.abs(x - 3.0999985) <= 1e-6 && Math.abs(y - 0.6999998) <= 1e-6) {
                        System.out.print("o");
                        continue;
                    }

                    System.out.print("*");

                } else
                    System.out.print(" ");
            }

            System.out.print("\n");
        }
    }
}
