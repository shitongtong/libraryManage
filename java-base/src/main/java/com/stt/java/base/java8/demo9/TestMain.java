package com.stt.java.base.java8.demo9;

import org.junit.Test;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

/**
 * Created by Administrator on 2017-01-16.
 *
 * @author shitongtong
 */
public class TestMain {

    /*
    Clock 时钟
    Clock类提供了访问当前日期和时间的方法，Clock是时区敏感的，可以用来取代 System.currentTimeMillis() 来获取当前的微秒数。
    某一个特定的时间点也可以使用Instant类来表示，Instant类也可以用来创建老的java.util.Date对象。
     */
    @Test
    public void testClock() {
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println("clock millis ==" + millis);//clock millis ==1484538365685

        Instant instant = clock.instant();
        long epochSecond = instant.getEpochSecond();
        int nano = instant.getNano();
        long toEpochMilli = instant.toEpochMilli();
        System.out.println("epochSecond==" + epochSecond);//epochSecond==1484538365
        System.out.println("nano==" + nano);//nano==685000000
        System.out.println("toEpochMilli==" + toEpochMilli);//toEpochMilli==1484538365685

        Date date = Date.from(instant);
        long dateTime = date.getTime();
        System.out.println("dateTime==" + dateTime);//dateTime==1484538365685
    }

    /*
    Timezones 时区
    在新API中时区使用ZoneId来表示。时区可以很方便的使用静态方法of来获取到。
    时区定义了到UTS时间的时间差，在Instant时间点对象到本地日期对象之间转换的时候是极其重要的。
     */
    @Test
    public void testTimezones(){
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        // prints all available timezone ids
//        for (String zoneId :availableZoneIds){
//            System.out.println(zoneId);
//        }

        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
//        System.out.println(zone1.getRules());//ZoneRules[currentStandardOffset=+01:00]
//        System.out.println(zone2.getRules());//ZoneRules[currentStandardOffset=-03:00]

        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(zoneId);//Asia/Shanghai
        ZoneId zoneId3 = ZoneId.of("Asia/Shanghai");
        System.out.println(zoneId3.getRules());//ZoneRules[currentStandardOffset=+08:00]

    }

    /*
    LocalTime 本地时间
    LocalTime 定义了一个没有时区信息的时间，例如 晚上10点，或者 17:30:15。下面的例子使用前面代码创建的时区创建了两个本地时间。
    之后比较时间并以小时和分钟为单位计算两个时间的时间差：
     */
    @Test
    public void testLocalTime(){
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        System.out.println(now1.isBefore(now2));//fasle

        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
        System.out.println(hoursBetween);       // -2
        System.out.println(minutesBetween);     // -179

        //LocalTime 提供了多种工厂方法来简化对象的创建，包括解析时间字符串。
        LocalTime time = LocalTime.of(23, 21, 45);
        System.out.println(time);// 23:21:45

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.GERMAN);
        LocalTime leetTime = LocalTime.parse("13:25", dateTimeFormatter);
        System.out.println(leetTime);
    }

    /*
    LocalDate 本地日期
    LocalDate 表示了一个确切的日期，比如 2014-03-11。该对象值是不可变的，用起来和LocalTime基本一致。
    下面的例子展示了如何给Date对象加减天/月/年。另外要注意的是这些对象是不可变的，操作返回的总是一个新实例。
     */
    @Test
    public void testDate(){
//        int monthValue = LocalDate.now().getMonthValue();
//        System.out.println(monthValue);//1
//        int year = LocalDate.MIN.getYear();
//        System.out.println(year);//999999999

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);

        LocalDate independenceDay = LocalDate.of(2017, Month.JANUARY, 16);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        System.out.println(dayOfWeek);    // MONDAY

        //从字符串解析一个LocalDate类型和解析LocalTime一样简单：
        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.MEDIUM)
                        .withLocale(Locale.GERMAN);
        LocalDate xmas = LocalDate.parse("16.01.2017", germanFormatter);
        System.out.println(xmas);   // 2017-01-16

    }

    /*
    LocalDateTime 本地日期时间
    LocalDateTime 同时表示了时间和日期，相当于前两节内容合并到一个对象上了。
    LocalDateTime和LocalTime还有LocalDate一样，都是不可变的。LocalDateTime提供了一些能访问具体字段的方法。
     */
    @Test
    public void testLocalDateTime(){
        LocalDateTime localDateTime = LocalDateTime.of(2017, 01, 17, 9, 53, 55);
        System.out.println(localDateTime);//2017-01-17T09:53:55
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        System.out.println(dayOfWeek);//TUESDAY
        int monthValue = localDateTime.getMonthValue();
        Month month = localDateTime.getMonth();
        int value = month.getValue();
        System.out.println(monthValue);//1
        System.out.println(value);//1
        System.out.println(month);//JANUARY
        long minuteOfDay = localDateTime.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(minuteOfDay);//593   今天已过去的分钟

        //只要附加上时区信息，就可以将其转换为一个时间点Instant对象，Instant时间点对象可以很容易的转换为老式的java.util.Date。
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        System.out.println(instant);//2017-01-17T01:53:55Z

        Date date = Date.from(instant);
        System.out.println(date);//Tue Jan 17 09:53:55 CST 2017

        //格式化LocalDateTime和格式化时间和日期一样的，除了使用预定义好的格式外，我们也可以自己定义格式：
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM dd,yyyy - HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("01 17,2017 - 10:08", dateTimeFormatter);
        System.out.println(dateTime);//2017-01-17T10:08
        String format = dateTimeFormatter.format(dateTime);
        System.out.println(format);//01 17,2017 - 10:08
        //和java.text.NumberFormat不一样的是新版的DateTimeFormatter是不可变的，所以它是线程安全的。

    }

}
