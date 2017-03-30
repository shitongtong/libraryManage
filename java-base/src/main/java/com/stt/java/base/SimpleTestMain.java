package com.stt.java.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/3/24.
 */
public class SimpleTestMain {

    public static void main(String[] args) {
        String coursewarePath = "D:\\project_git\\onlyhi-admin\\target\\courseware\\20170324\\C48D14A4-FE09-44C2-99D6-17BF42262204\\original\\1490326152793_test4.docx";
        String baseUrl = coursewarePath.substring(0,coursewarePath.indexOf("courseware"));
        String courseware = coursewarePath.substring(coursewarePath.indexOf("courseware"));
        System.out.println(baseUrl);
        System.out.println(courseware);
        System.out.println(new File(coursewarePath).getPath());
    }

    @org.junit.Test
    public void getEnv(){
        Map<String, String> map = System.getenv();
        for(Iterator<String> itr = map.keySet().iterator(); itr.hasNext();){
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
        } else if (OS.indexOf("windows 7") > -1){
            p = r.exec("cmd.exe /c set");
        }else {
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
    public void getUUID(){
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        //c8abbd07-429a-44fb-b8a4-66baacfd1c0f
        //c8abbd07429a44fbb8a466baacfd1c0f
    }

}
