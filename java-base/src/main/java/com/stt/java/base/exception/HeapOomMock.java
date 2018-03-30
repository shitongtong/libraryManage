package com.stt.java.base.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置jvm参数：-Xms16m -Xmn8m -Xmx16m
 * 执行结果：
 * count=13
 java.lang.OutOfMemoryError: Java heap space
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2018/3/1.
 */
public class HeapOomMock {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<byte[]>();
        int i = 0;
        boolean flag = true;
        while (flag){
            try {
                i++;
                list.add(new byte[1024 * 1024]);//每次增加一个1M大小的数组对象
            }catch (Throwable e){
                e.printStackTrace();
                flag = false;
                System.out.println("count="+i);//记录运行的次数
            }
        }
    }
}
