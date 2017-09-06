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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

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
//        String s = String.valueOf(null);  //报错
        String s = (String) null;          //不报错
//        String s = Long.toString(null);
        System.out.println(s);
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

}
