package com.stt.java.base.singleton;

import java.lang.reflect.Constructor;

/**
 * 反射破坏单例测试
 * Created by stt on 2018/5/22.
 */
public class LazySingleton3Test {
    public static void main(String[] args) {
        //创建第一个实例
        LazySingleton3 instance1 = LazySingleton3.getInstance();
        //通过反射创建第二个实例
        LazySingleton3 instance2 = null;
        try {
            Class<LazySingleton3> clazz = LazySingleton3.class;
            Constructor<LazySingleton3> cons = clazz.getDeclaredConstructor();
            cons.setAccessible(true);
            instance2 = cons.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //检查两个实例的hash值
        System.out.println("Instance 1 hash:" + instance1.hashCode());
        System.out.println("Instance 2 hash:" + instance2.hashCode());
        /*
        Instance 1 hash:2125039532
java.lang.reflect.InvocationTargetException
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at com.stt.java.base.singleton.LazySingleton3Test.main(LazySingleton3Test.java:18)
Caused by: java.lang.RuntimeException: 单例已被破坏
	at com.stt.java.base.singleton.LazySingleton3.<init>(LazySingleton3.java:12)
	... 5 more
Exception in thread "main" java.lang.NullPointerException
	at com.stt.java.base.singleton.LazySingleton3Test.main(LazySingleton3Test.java:24)
         */
    }
}
