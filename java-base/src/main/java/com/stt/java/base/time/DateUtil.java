package com.stt.java.base.time;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2018/1/12.
 */
public class DateUtil {
    public static void main(String[] args) {
        Date date = getFirstDayOfWeek(2017, 12, 1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);
        System.out.println(format);
    }

    public static Date getFirstDayOfWeek(int year, int month, int week) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.WEEK_OF_MONTH, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return calendar.getTime();
    }

    @Test
    public void test() {
        int year = 2017;
        int month = 12;
        int week = 6;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.WEEK_OF_MONTH, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH) + 1);
        System.out.println(calendar.get(Calendar.DATE));
        calendar.set(Calendar.DAY_OF_WEEK, 2);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void test1() {
        String s1 = "2018-01-12";
        String s2 = "2018-01-12";
        int i = s1.compareTo(s2);
        if (i > 0) {
            System.out.println(">0");
        } else if (i < 0) {
            System.out.println("<0");
        } else {
            System.out.println("==0");
        }
    }
}
