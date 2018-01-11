package com.stt.java.base;

import com.stt.java.base.util.SecurityUtil;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/3/24.
 */
public class SimpleTestMain {

    public static void main(String[] args) {
        String coursewarePath = "D:\\project_git\\onlyhi-admin\\target\\courseware\\20170324\\C48D14A4-FE09-44C2-99D6-17BF42262204\\original\\1490326152793_test4.docx";
        String baseUrl = coursewarePath.substring(0, coursewarePath.indexOf("courseware"));
        String courseware = coursewarePath.substring(coursewarePath.indexOf("courseware"));
        System.out.println(baseUrl);
        System.out.println(courseware);
        System.out.println(new File(coursewarePath).getPath());
    }

    @org.junit.Test
    public void getEnv() {
        Map<String, String> map = System.getenv();
        for (Iterator<String> itr = map.keySet().iterator(); itr.hasNext(); ) {
            String key = itr.next();
            System.out.println(key + "=" + map.get(key));
        }

        Properties properties = System.getProperties();
        /*for(Iterator<String> itr = map1.keySet().iterator(); itr.hasNext();){
            String key = itr.next();
            System.out.println(key + "=" + map.get(key));
        }*/
        System.out.println();
    }

    @org.junit.Test
    public void getWinEnv() throws IOException {
        Properties envVars = getEnvVars();
        String libreoffice_home = envVars.getProperty("LIBREOFFICE_HOME");
        System.out.println(libreoffice_home);

    }

    public Properties getEnvVars() throws IOException {
        Process p = null;
        Properties envVars = new Properties();
        Runtime r = Runtime.getRuntime();
        String OS = System.getProperty("os.name").toLowerCase();
        System.out.println(OS);
        if (OS.indexOf("windows 9") > -1) {
            p = r.exec("command.com /c set");
        } else if ((OS.indexOf("nt") > -1) || (OS.indexOf("windows 20") > -1) || (OS.indexOf("windows xp") > -1)) {
            // thanks to JuanFran for the xp fix!
            p = r.exec("cmd.exe /c set");
        } else if (OS.indexOf("windows 7") > -1) {
            p = r.exec("cmd.exe /c set");
        } else {
            // our last hope, we assume Unix (thanks to H. Ware for the fix)
            p = r.exec("env");
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(p
                .getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            int idx = line.indexOf('=');
            String key = line.substring(0, idx);
            String value = line.substring(idx + 1);
            envVars.setProperty(key, value);
            // System.out.println( key + " = " + value );
        }
        return envVars;
    }

    @org.junit.Test
    public void getUUID() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        //c8abbd07-429a-44fb-b8a4-66baacfd1c0f
        //c8abd07429a44fbb8a466baacfd1c0f
    }

    @Test
    public void testRandom() {
        long time1 = System.nanoTime();
        int v = (int) ((Math.random() * 9 + 1) * 1000);
        String s1 = v + "";
        long time2 = System.nanoTime();
        System.out.println(time2 - time1);

        System.out.println("================================");

        long time3 = System.nanoTime();
        String sixRandom = getSixRandom();
        long time4 = System.nanoTime();
        System.out.println(time4 - time3);

        System.out.println(s1 + "||||||" + sixRandom);

    }

    private int count = 0;

    public String getSixRandom() {
        count++;
        Random rad = new Random();
        String result = rad.nextInt(10000) + "";
        if (result.length() != 4) {
            return getSixRandom();
        }
        System.out.println("count==" + count);
        return result;
    }

    @Test
    public void testArray() {
        String courseKnowledgeIds = "1,2,3";
        String[] split = courseKnowledgeIds.split(",");
        String s = split[split.length - 1];
        System.out.println(s);
    }

    @Test
    public void testGetHostName() throws UnknownHostException {
        String getenv = System.getenv("host.name");
        System.out.println(getenv);
        String hostName = InetAddress.getLocalHost().getHostName();
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        System.out.println(hostName);
        System.out.println(hostAddress);
    }

    @Test
    public void testutfcode() throws UnsupportedEncodingException {
        String imageUrl = "http://192.168.1.252:8090/courseware/20170330/uuid00000000004985903243594861584/image/1490855357611_Java基础PPT/0.jpg";
//        imageUrl = "基础";
        imageUrl = "http://www.google.com/search?hl=zh-cn&newwindow=1&q=中国大百科在线全文检索&btng=搜索&lr=";
        String encodeUrl = URLEncoder.encode(imageUrl, "UTF-8");
        System.out.println(encodeUrl);  //http%3A%2F%2F192.168.1.252%3A8090%2Fcourseware%2F20170330%2Fuuid00000000004985903243594861584%2Fimage%2F1490855357611_Java%E5%9F%BA%E7%A1%80PPT%2F0.jpg
        //http://192.168.1.252:8090/courseware/20170330/uuid00000000004985903243594861584/image/1490855357611_Java%E5%9F%BA%E7%A1%80PPT/0.jpg
        //%E5%9F%BA%E7%A1%80
    }

    @Test
    public void testHash() {
        String str = "Java基础PPT";
        str = "dsd_";
        int i = str.hashCode();
        System.out.println(i);
        int a = -234;
        str = str + a;
        System.out.println(str);
    }

    @Test
    public void testTime() {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        long l = System.currentTimeMillis();
        System.out.println(timeInMillis);
        System.out.println(l);

        String str = timeInMillis + "_" + "oo".hashCode();
        System.out.println(str);
    }

    @Test
    public void testSplit() {
        String s = "aaa";
        String[] split = s.split(",");
        System.out.println(Arrays.asList(split).size());
        System.out.println(Arrays.toString(split));
        System.out.println(split);
    }

    @Test
    public void testIntEquest() {
        int a = -1;
        System.out.println(a == -1);
    }

    @Test
    public void test() {
        double i = 6.00 / 60;
        System.out.println(i);
        double b = 3d;
        BigDecimal bigDecimal = new BigDecimal(b / 60);
        double doubleValue = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(doubleValue);
    }

    @Test
    public void testCard() {
//        IDValidator
    }

    @Test
    public void test11() {
        String password = SecurityUtil.hashMD5Hex("");
        System.out.println(password);
    }

    @Test
    public void testI() {
        int maxValue = Integer.MAX_VALUE;
        System.out.println(maxValue);//2147483647
        long value = Long.MAX_VALUE;
        System.out.println(value);//9223372036854775807
    }

    @Test
    public void test1() {
        int amount = 12791;
        System.out.println(String.valueOf(amount / 100d));
        System.out.println(true ? 1 : 0);
    }

    @Test
    public void test2() {
        String s = String.valueOf(null);  //报错
//        String s = (String) null;          //不报错
//        String s = Long.toString(null);
        System.out.println(s);

        Integer a = null;
//        String s1 = a.toString();
        String s1 = String.valueOf(a);  //不报错
        System.out.println(s1);
    }

    @Test
    public void testTime2() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String time1 = "07:00";
        String time2 = "08:00";
        Date date1 = dateFormat.parse(time1);
        Date date2 = dateFormat.parse(time2);
        long time = date2.getTime() - date1.getTime();
        System.out.println(time / 1000);  //3600
    }


    @Test
    public void testCount() {
        long l = System.nanoTime();
        int sum = 0;
        for (int i = 1; i <= 10000; i++) {
            sum += i;
        }
        System.out.println(sum);
        System.out.println("执行时间：" + (System.nanoTime() - l));  //502017
    }

    @Test
    public void testTime3() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date courseStartTime = dateFormat.parse("2017-09-07 16:30:00");
        long time = courseStartTime.getTime();
        Calendar calendar = Calendar.getInstance();
        long begin = calendar.getTimeInMillis();

        long intervalTime = (time - begin) / (1000 * 60);
        System.out.println(intervalTime);
    }

    @Test
    public void testList() {
        List<String> androidPushlist = new ArrayList<>();
        List<String> iosPushlist = new ArrayList<>();
        List<String> pushLeadsUuidList = new ArrayList<>();
        pushLeadsUuidList.addAll(androidPushlist);
        pushLeadsUuidList.addAll(iosPushlist);
        System.out.println(pushLeadsUuidList);
    }

    @Test
    public void test4() {
        double a = 101112.0;
        BigDecimal a2 = new BigDecimal(Double.toString(a));
        BigDecimal b2 = new BigDecimal("100");
        BigDecimal multiply = a2.multiply(b2);
        double v = multiply.doubleValue();
        System.out.println(v);
        System.out.println(multiply.toString());
        System.out.println(String.valueOf(a * 100));
        System.out.println(Double.toString(a * 100));
        System.out.println(new BigDecimal(a * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());

        String money = "101112.0";
        System.out.println(new BigDecimal(Double.valueOf(money) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
    }

    @Test
    public void test5() {
        String a = "231";
//        System.out.println(a/2);
        BigDecimal a2 = new BigDecimal(a);
        BigDecimal b2 = new BigDecimal("2");
        BigDecimal divide = a2.divide(b2);
        System.out.println(divide.doubleValue());

        System.out.println(new BigDecimal(Double.valueOf(a) / 2).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
    }

    @Test
    public void test6() {
        int a = 24 * 3600 * 1000;   //一天的毫秒数
    }

    @Test
    public void test7() {
        List<Long> aa = new ArrayList<>();
        aa.add(2132454545412L);
        aa.add(21324545453332L);
        aa.add(121354312311L);
        System.out.println(aa);
        aa.remove(aa.size() - 2);
        System.out.println(aa);

    }

    @Test
    public void test8() {
        String userUuid = "BFFC8159-FED1-4F53-9900-A462FAAC1BDB";
        String userType = "STUDENT";
        System.out.println(getAgoraUid(userUuid, userType));
        System.out.println(getAgoraUid(null, userType));
    }

    public int getAgoraUid(String userUuid, String userType) {
        return Math.abs((userUuid + userType).hashCode());
    }

    @Test
    public void test9() {
        File recordLogsDir = new File("/www/hktRecord/mylogs/20171121/");
        String basePath = "/www/hktRecord/mylogs/";
        String dateDir = recordLogsDir.getPath().substring(basePath.length());
        System.out.println(dateDir);
    }

    @Test
    public void test10() {
        Map<Integer, String> classMateMap = new ConcurrentHashMap<>();
        classMateMap.put(1, "a");
        classMateMap.put(2, "s");
        classMateMap.put(3, "d");
        classMateMap.put(4, "f");
        Collection<String> values = new ArrayList<>();
        values = classMateMap.values();

        List<String> list = new ArrayList<>();
        list.add("q");
        list.add("w");
        list.add("e");
        list.add("r");
        Collection<String> values2 = list;
        Iterator<String> iterator = values.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }
    }

    private static ConcurrentLinkedQueue<String> cmdQueue = new ConcurrentLinkedQueue<>();

    @Test
    public void test12() {
        for (int i = 0; i < 100000; i++) {
            cmdQueue.add(i + "");
        }
        System.out.println(cmdQueue.size());
        cmdQueue.clear();
        System.out.println(cmdQueue.size());
    }

    @Test
    public void test13() {
        int noJoinClassCount = 3;
        int courseTotalCount = 15;
        String attendanceRate = new BigDecimal((courseTotalCount - noJoinClassCount) * 100 / courseTotalCount).setScale(0, BigDecimal.ROUND_HALF_UP).toString() + "%";
        System.out.println(attendanceRate);
    }

    @Test
    public void test14() {
        List<Long> stuEnterTimeList = Arrays.asList(1511797402893L, 1511797786817L, 1511797887637L, 1511797982686L, 1511798074727L, 1511798197478L);
        List<Long> stuOutTimeList = Arrays.asList(1511797786736L, 1511797887576L, 1511797982606L, 1511798074646L, 1511798197417L, 1511798341618L);
        List<Long> teaEnterTimeList = Arrays.asList(1511797381041L, 1511797819871L, 1511797901445L, 1511797986361L, 1511798086813L, 1511798204226L);
        List<Long> teaOutTimeList = Arrays.asList(1511797668899L, 1511797863318L, 1511797936750L, 1511798013757L, 1511798167411L, 1511798341618L);
        long courseStartTime = 1511794800000L;
        int stuSize = stuEnterTimeList.size();
        int teaSize = teaEnterTimeList.size();
        long totalTime = getTotalTime(stuSize, stuEnterTimeList, stuOutTimeList, courseStartTime, teaSize, teaEnterTimeList, teaOutTimeList);
        System.out.println(totalTime);
    }

    private long getTotalTime(int stuSize, List<Long> stuEnterTimeList, List<Long> stuOutTimeList, long courseStartTime, int teaSize, List<Long> teaEnterTimeList, List<Long> teaOutTimeList) {
        //学生和教师上课共同时长（同时在线时间累加）
        long totalTime = 0;
        for (int i = 0; i < stuSize; i++) {
            long stuEnterTime = stuEnterTimeList.get(i);
            long stuOutTime = stuOutTimeList.get(i);
            if (i == 0) {
                if (stuEnterTime - courseStartTime < 0) {   //进入时间早于课程开始时间
                    stuEnterTime = courseStartTime;
                }
            }
            for (int j = 0; j < teaSize; j++) {
                long teaEnterTime = teaEnterTimeList.get(j);
                long teaOutTime = teaOutTimeList.get(j);
                if (stuEnterTime >= teaEnterTime && stuEnterTime < teaOutTime) {
                    if (stuOutTime < teaOutTime) {
                        totalTime += stuOutTime - stuEnterTime;
                    } else {
                        totalTime += teaOutTime - stuEnterTime;
                    }
                    continue;
                }
                if (stuOutTime >= teaEnterTime && stuEnterTime < teaOutTime) {
                    if (stuEnterTime < teaEnterTime) {
                        totalTime += teaOutTime - teaEnterTime;
                    } else {
                        totalTime += teaOutTime - stuEnterTime;
                    }
                    continue;
                }
            }
        }
        return totalTime;
    }

    @Test
    public void test15() {
        String v1 = "1.1";
        String v2 = "1.2";
        int[] array1 = getIntArray(v1);
        int[] array2 = getIntArray(v2);
        int i = compare(array1, array2);
        if (i == 1) {
            System.out.println("v1 > v2");
        } else if (i == -1) {
            System.out.println("v1 < v2");
        } else {
            System.out.println("v1 == v2");
        }
        /*if (array1[0] > array2[0]) {
            System.out.println("1... v1>v2");
        } else if (array1[1] > array2[1]) {
            System.out.println("2... v1>v2");
        } else if (array1[2] > array2[2]) {
            System.out.println("3... v1>v2");
        } else {
            System.out.println("4... v1<v2");
        }*/
    }

    /*private boolean compare(String v1, String v2) {
        String[] s1 = v1.split("\\.");
        int l1 = s1.length;
        String[] s2 = v2.split("\\.");
        int l2 = s2.length;
        int size = l1 > l2 ? l1 : l2;
        int[] reult = new int[size];
    }*/

    private int compare(int[] array1, int[] array2) {
        int l1 = array1.length;
        int l2 = array2.length;
        int size = l1 > l2 ? l2 : l1;
        for (int i = 0; i < size; i++) {
            if (array1[i] > array2[i]) {
                return 1;
            }
        }
        System.out.println("**************");
        if (l1 > l2) {
            return 1;
        } else if (l1 < l2) {
            return -1;
        } else {
            return 0;
        }
    }

    private int[] getIntArray(String v) {
        String[] split = v.split("\\.");
        int length = split.length;
        int[] reult = new int[length];
        for (int i = 0; i < length; i++) {
            int a = Integer.parseInt(split[i]);
            reult[i] = a;
        }
        return reult;
    }

    @Test
    public void test16() {
        String version1 = "1.2";
        String version2 = "1.2.2.2";
        int i = compareVersion(version1, version2);
        System.out.println("i==" + i);
        if (i > 0) {
            System.out.println("v1 > v2");
        } else if (i < 0) {
            System.out.println("v1 < v2");
        } else {
            System.out.println("v1 == v2");
        }
    }

    private int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");
        int length1 = s1.length;
        int length2 = s2.length;
        int length = Math.min(length1, length2);
        int compare = 0;
        for (int i = 0; i < length; i++) {
            System.out.println("***");
            String str1 = s1[i];
            String str2 = s2[i];
            compare = str1.length() - str2.length();
            if (compare == 0) {
                compare = str1.compareTo(str2);
                if (compare != 0) {
                    return compare;
                }
            } else {
                return compare;
            }
        }
        return length1 - length2;
    }

    @Test
    public void test17() {
        Map<Integer, String[]> boardWHMap = new HashMap<>();
        String[] str = new String[]{"123", "456"};
        boardWHMap.put(2, str);
        boardWHMap.put(1, str);
        boardWHMap.put(0, str);
        boardWHMap.put(3, str);
        Collection<String[]> values = boardWHMap.values();
        List<String[]> boardWHList = new ArrayList<>();
        boardWHList.addAll(values);

//        ArrayList arrayList = (ArrayList) values; //报错

        Map<String, String[]> map = new HashMap<>();
        map.put("2", str);
        map.put("1", str);
        map.put("0", str);
        map.put("3", str);

        map.put("332", str);
        map.put("311", str);
        map.put("377", str);

        map.put("7ss", str);
        map.put("3dd", str);
        map.put("3aa", str);

        map.put("a", str);
        map.put("v", str);
        map.put("e", str);
        System.out.println(map);
    }

    @Test
    public void test18() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i % 10);
        }
    }

    @Test
    public void test19() {
        long maxValue = Long.MAX_VALUE;
        maxValue = 20171211110839111L;
        System.out.println(maxValue);
    }

    @Test
    public void test20() {
        String basePath = "/www/hktRecord/mylogs/";
        File recordLogsDir = new File(basePath + "20171218");
        String dateDir = recordLogsDir.getPath().substring(basePath.length());
        System.out.println(dateDir);
        String dateDir1 = recordLogsDir.getPath().substring(recordLogsDir.getParentFile().getPath().length() + 1);
        System.out.println(dateDir1);
    }

    @Test
    public void test21() {
        String trackFileName = "76C123FA-A462-4132-A42F-FD2D0B58A03C_0.db";
        String saveFileName = trackFileName.substring(0, trackFileName.lastIndexOf(".db")) + "_server.db";
        System.out.println(saveFileName);
    }

    @Test
    public void test22() {
        Map<String, List<String[]>> map = new HashMap<>();
        List<String[]> boardWHList = new ArrayList<>();
        map.put("0", boardWHList);
        String[] boardWH = new String[]{"11", "22"};
        boardWHList.add(boardWH);

        String[] strings = boardWHList.get(0);
        strings[0] = "33";
        strings[1] = "44";

        System.out.println(boardWHList);
    }

    @Test
    public void test23() {
        List<String[]> boardWHList = new ArrayList<>();
        boardWHList.add(null);
        boardWHList.add(null);
        System.out.println(boardWHList);
    }

    @Test
    public void test24() {
        int durationTime = 0;
        List<Integer> timeList = new ArrayList<>();
        timeList.add(durationTime);
        durationTime = 11;
        timeList.add(durationTime);
        System.out.println(timeList);
        int i = timeList.lastIndexOf(1);
        System.out.println(i);
    }

    @Test
    public void test25() {
        Map<Integer, Integer> map = new HashMap<>();
        Integer a = map.get(11);
        int b = map.get(11);
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void test26() {
        String s = "{\"ccAgoraUid\":1564221018,\"stuAgoraUid\":227249972,\"teaAgoraUid\":715036940}";
        System.out.println(s);
        System.out.println("\"" + s.replace("\"", "\\\"") + "\"");
    }

    @Test
    public void test27() throws UnsupportedEncodingException {
        String url = "http://192.168.3.57/uploadDir\\courseware\\2\\20180104155905.pdf";
        String encode = URLEncoder.encode(url, "utf-8");
        System.out.println(encode);
    }

    @Test
    public void test28() {
        ConcurrentLinkedQueue<String> taQueue = new ConcurrentLinkedQueue<>();
        List<String> taList = new ArrayList<>();
        String loginName0 = "qq0";
        String loginName1 = "qq1";
        String loginName2 = "qq2";
        String loginName3 = "qq3";
        String loginName4 = "qq4";
        taList.add(loginName0);
        taList.add(loginName1);
        taList.add(loginName2);
        taList.add(loginName3);
        taList.add(loginName4);
        taList.add("qq5");
        taQueue.add(loginName0);
        taQueue.add(loginName1);
        taQueue.add(loginName2);
        taQueue.add(loginName3);
        taQueue.add(loginName4);
//        taQueue.add("qq6");
//        taQueue.addAll(taList);
        System.out.println(taQueue);
        System.out.println(taQueue.containsAll(taList));
        System.out.println(taList.containsAll(taQueue));
    }

    @Test
    public void test29() {
        Map<Integer, String> map = new HashMap<>();
        map.put(null, null);
        System.out.println(map);
        System.out.println("ddsdd");
        System.out.println("ddddd");
        Map<String, Integer> mapa = new HashMap<>();
    }

    @Test
    public void test30() throws ParseException {
        String dateTime = "2018-01";
        String[] split = dateTime.split("-");
        String yearStr = split[0];
        String monthStr = split[1];
        int year = Integer.parseInt(yearStr);
        int month = Integer.parseInt(monthStr);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println("指定年月天数：" + days);
        int weeks = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        System.out.println("指定年月周数：" + weeks);
        calendar.set(Calendar.DAY_OF_MONTH, days);
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println("指定日期是周几：" + week);
    }

}
