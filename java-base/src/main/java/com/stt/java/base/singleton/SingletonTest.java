package com.stt.java.base.singleton;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2018/3/13.
 */
public class SingletonTest {

    @Test
    public void testSingletonEnum(){
        List<SingletonEnum> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(SingletonEnum.INSTANCE);
            }).start();
        }
        Thread.yield();
        System.out.println(list);
    }
}
