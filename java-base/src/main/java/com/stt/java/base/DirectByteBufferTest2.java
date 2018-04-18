package com.stt.java.base;

import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2018/4/9.
 */
public class DirectByteBufferTest2 {
    /**
     * 设置JVM参数-Xmx768m，运行程序观察内存使用变化，会发现clean()后内存马上下降，说明使用clean()方法能有效及时回收直接缓存。
     * ps:貌似不明显
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        //分配512MB直接缓存
        ByteBuffer bb = ByteBuffer.allocateDirect(1024 * 1024 * 512);
        TimeUnit.SECONDS.sleep(20);
        //清除直接缓存
        ((DirectBuffer) bb).cleaner().clean();
        TimeUnit.SECONDS.sleep(10);
        System.out.println("ok");
    }
}
