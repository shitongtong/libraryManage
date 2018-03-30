package com.stt.java.base.singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * 静态内部类实现
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2018/3/13.
 */
public class Singleton {
    private Singleton() {
    }

    private static class SingletonHolder {
        private static final Singleton instance = new Singleton();
    }

    public static final Singleton getInstance() {
        return SingletonHolder.instance;
    }

    public static void main(String[] args) {
        List<Singleton> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(Singleton.getInstance());
            }).start();
        }
        Thread.yield();
        System.out.println(list);
    }

}
