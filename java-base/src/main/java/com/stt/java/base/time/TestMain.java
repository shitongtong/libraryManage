package com.stt.java.base.time;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/4/6.
 */
public class TestMain {
    public static void main(String[] args) {
        Calendar instance = Calendar.getInstance();
        long timeInMillis = instance.getTimeInMillis();
        long time = new Date().getTime();
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(timeInMillis);
        System.out.println(time);
        System.out.println(currentTimeMillis);
    }
}
