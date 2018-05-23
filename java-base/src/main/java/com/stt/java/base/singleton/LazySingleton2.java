package com.stt.java.base.singleton;

/**
 * Created by stt on 2018/5/22.
 */
public class LazySingleton2 {
    private LazySingleton2() {
    }

    static class SingletonHolder {
        private static final LazySingleton2 instance = new LazySingleton2();
    }

    public static LazySingleton2 getInstance() {
        return SingletonHolder.instance;
    }
}
