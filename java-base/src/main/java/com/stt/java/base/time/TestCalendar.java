package com.stt.java.base.time;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/3/21.
 */
public class TestCalendar {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018,2,1,4,3);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        year = year>10?year:
        System.out.println((year>10?year:""+year) +"-"+month+"-"+day);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        System.out.println((hour>10?hour:"0"+hour)+":"+minute);
    }

    @Test
    public void testEndTime(){
        Date date = new Date();
        long time = date.getTime();
        System.out.println(time);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String format = dateFormat.format(date);
        System.out.println(format);
    }
}
