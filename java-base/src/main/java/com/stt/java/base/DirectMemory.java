package com.stt.java.base;

import java.nio.ByteBuffer;

/**
 * JVM设置:
 * -XX:MaxDirectMemorySize=1M
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2018/4/9.
 */
public class DirectMemory {
    // 分配堆内存
    public static void bufferAccess() {
        long startTime = System.currentTimeMillis();
        ByteBuffer b = ByteBuffer.allocate(500);
        for (int i = 0; i < 1000000; i++) {
            for (int j = 0; j < 99; j++)
                b.putInt(j);
            b.flip();
            for (int j = 0; j < 99; j++)
                b.getInt();
            b.clear();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("access_nondirect:" + (endTime - startTime));
    }

    // 直接分配内存
    public static void directAccess() {
        long startTime = System.currentTimeMillis();
        ByteBuffer b = ByteBuffer.allocateDirect(500);
        for (int i = 0; i < 1000000; i++) {
            for (int j = 0; j < 99; j++)
                b.putInt(j);
            b.flip();
            for (int j = 0; j < 99; j++)
                b.getInt();
            b.clear();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("access_direct:" + (endTime - startTime));
    }

    public static void bufferAllocate() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            ByteBuffer.allocate(1000);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("allocate_nondirect:" + (endTime - startTime));
    }

    public static void directAllocate() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            ByteBuffer.allocateDirect(1000);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("allocate_direct:" + (endTime - startTime));
    }

    public static void main(String args[]) {
        System.out.println("访问性能测试：");
        bufferAccess();
        directAccess();

        System.out.println();

        System.out.println("分配性能测试：");
        bufferAllocate();
        directAllocate();

        /**
         * 结果输出:
         * 访问性能测试：
         access_nondirect:190
         access_direct:119

         分配性能测试：
         allocate_nondirect:321
         allocate_direct:819
         */
    }
}
