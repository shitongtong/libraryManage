package com.stt.java.base.singleton;

import java.io.Serializable;

/**
 * Created by stt on 2018/5/22.
 */
public class LazySingleton3 implements Serializable {
    private static boolean initialized = false;

    private LazySingleton3() {
        synchronized (LazySingleton3.class) {
            if (initialized) {
                throw new RuntimeException("单例已被破坏");
            } else {
                initialized = true;
            }
        }
    }

    static class SingletonHolder {
        private static final LazySingleton3 instance = new LazySingleton3();
    }

    public static LazySingleton3 getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 加上这个方法，单例序列化和反序列化都是同一个对象
     *
     * @return
     */
    private Object readResolve() {
        return getInstance();
    }
}
