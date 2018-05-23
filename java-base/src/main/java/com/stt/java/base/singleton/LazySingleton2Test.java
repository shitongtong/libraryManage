package com.stt.java.base.singleton;

import java.lang.reflect.Constructor;

/**
 * Created by stt on 2018/5/22.
 */
public class LazySingleton2Test {
    public static void main(String[] args) {
        //创建第一个实例
        LazySingleton2 instance1 = LazySingleton2.getInstance();
        //通过反射创建第二个实例
        LazySingleton2 instance2 = null;
        try {
            Class<LazySingleton2> clazz = LazySingleton2.class;
            Constructor<LazySingleton2> cons = clazz.getDeclaredConstructor();
            cons.setAccessible(true);
            instance2 = cons.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //检查两个实例的hash值
        System.out.println("Instance 1 hash:" + instance1.hashCode());
        System.out.println("Instance 2 hash:" + instance2.hashCode());
        /*
        反射破坏了单例的特性
        Instance 1 hash:621009875
        Instance 2 hash:1265094477
         */
    }
}
