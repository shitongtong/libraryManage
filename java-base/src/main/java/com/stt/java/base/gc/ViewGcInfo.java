package com.stt.java.base.gc;

import java.util.LinkedList;
import java.util.Random;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2018/3/2.
 */
public class ViewGcInfo {
    static int TOTAL_SIZE = 1024 * 5;
    static int floatingSize = 0;
    static int immortalSize = 0;
    static Object[] floatingObjs = new Object[TOTAL_SIZE];
    static LinkedList<Object> immortalObjs = new LinkedList<Object>();

    //释放浮动垃圾
    synchronized static void renewFloatingObjs() {
        System.err.println("存活对象满========================================");
        if (floatingSize + 5 >= TOTAL_SIZE) {
            floatingObjs = new Object[TOTAL_SIZE];
            floatingSize = 0;
        }
    }

    //添加浮动垃圾
    synchronized static void addObjToFloating(Object obj) {
        if (floatingSize++ < TOTAL_SIZE) {
            floatingObjs[floatingSize] = obj;
            if (immortalSize++ < TOTAL_SIZE) {
                immortalObjs.add(obj);
            } else {
                immortalObjs.remove(new Random().nextInt(TOTAL_SIZE));
                immortalObjs.add(obj);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Byte[] garbage = new Byte[1024 * (1 + new Random().nextInt(20))];
                    if (new Random().nextInt(20) < 2) {
                        if (floatingSize + 5 >= TOTAL_SIZE) {
                            renewFloatingObjs();
                        }
                        addObjToFloating(garbage);
                    }
                }
            }).start();
        }
    }
}
