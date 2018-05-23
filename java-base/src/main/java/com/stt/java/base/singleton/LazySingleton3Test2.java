package com.stt.java.base.singleton;

import java.io.*;

/**
 * 序列化单例
 * Created by stt on 2018/5/22.
 */
public class LazySingleton3Test2 {
    public static void main(String[] args) {
        try {
            LazySingleton3 instance1 = LazySingleton3.getInstance();
            ObjectOutput out = null;
            out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
            out.writeObject(instance1);
            out.close();
            //deserialize from file to object
            ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"));
            LazySingleton3 instance2 = (LazySingleton3) in.readObject();
            in.close();
            System.out.println("instance1 hashCode=" + instance1.hashCode());
            System.out.println("instance2 hashCode=" + instance2.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
instance1 hashCode=1554874502
instance2 hashCode=1791741888
*/

/*
LazySingleton3加上readResolve方法后
instance1 hashCode=1554874502
instance2 hashCode=1554874502
*/
