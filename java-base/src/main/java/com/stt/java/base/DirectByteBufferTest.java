package com.stt.java.base;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2018/4/9.
 */
public class DirectByteBufferTest {
    /**
     * 设置JVM参数
     * -Xmx100m
     * java.lang.OutOfMemoryError: Direct buffer memory
     * 运行异常，因为如果没设置-XX:MaxDirectMemorySize，则默认与-Xmx参数值相同，分配128M直接内存超出限制范围。
     * <p>
     * <p>
     * 设置JVM参数
     * -Xmx256m，
     * 运行正常，因为128M小于256M，属于范围内分配。
     * <p>
     * <p>
     * 设置JVM参数
     * -Xmx256m -XX:MaxDirectMemorySize=100M
     * java.lang.OutOfMemoryError: Direct buffer memory
     * 运行异常，分配的直接内存128M超过限定的100M。
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        //分配128MB直接内存
        ByteBuffer bb = ByteBuffer.allocateDirect(1024 * 1024 * 128);
        TimeUnit.SECONDS.sleep(10);
        System.out.println("ok");
    }
}
