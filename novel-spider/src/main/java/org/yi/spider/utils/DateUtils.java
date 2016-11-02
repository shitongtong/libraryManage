//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH24:mm:ss";

    public DateUtils() {
    }

    public static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    public static int getThisYear() {
        return getCalendar().get(1);
    }

    public static int getThisMonth() {
        return getCalendar().get(2) + 1;
    }

    public static Date getNow() {
        return getCalendar().getTime();
    }

    public static String getNowStr() {
        return getDateStr(getNow(), "yyyy-MM-dd HH24:mm:ss");
    }

    public static String getDateStr(Date date, String pattern) {
        return (new SimpleDateFormat(pattern)).format(date);
    }
}
