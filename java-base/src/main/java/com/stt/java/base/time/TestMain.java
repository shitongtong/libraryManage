package com.stt.java.base.time;

import org.junit.Test;

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

    @Test
    public void test1(){
        System.out.println(getNextYearDate(0));
        System.out.println(new Date());
    }

    public static Date getNextYearDate(int num){
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
//	        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        calendar.add(Calendar.YEAR, num);
        date = calendar.getTime();
        System.out.println(date);
        return date;
    }
}
