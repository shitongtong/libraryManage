package com.stt.java.base.singleton;

import java.io.*;

/**
 * Created by stt on 2018/5/22.
 */
public class SingletonEnumTest {
    public static void main(String[] args) {
        try {
            SingletonEnum instance1 = SingletonEnum.INSTANCE;
            ObjectOutput out = null;
            out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
            out.writeObject(instance1);
            out.close();
            //deserialize from file to object
            ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"));
            SingletonEnum instance2 = (SingletonEnum) in.readObject();
            in.close();
            System.out.println("instance1 hashCode=" + instance1.hashCode());
            System.out.println("instance2 hashCode=" + instance2.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
