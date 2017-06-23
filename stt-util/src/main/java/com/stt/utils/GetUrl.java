package com.stt.utils;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/6/23.
 */
public class GetUrl {
    public static void main(String[] args) throws Exception {
        String logPath = "D:\\workDir\\jar包启动脚本\\dev\\rest\\logs";
        String destPath = "D:\\workDir\\jar包启动脚本\\url.txt";
        File LogDir = new File(logPath);
        File[] listFiles = LogDir.listFiles();
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(destPath)));
        for (int i = 0; i < listFiles.length; i++) {
            File listFile = listFiles[i];
            if (listFile.isDirectory()){
                String listFileName = listFile.getName();
                File[] files = listFile.listFiles();
                File file = files[1];
                BufferedReader reader = new BufferedReader(new FileReader(file));
                writer.write("\n");
                writer.write(listFileName);
                writer.write("\n");
                String tempString = null;
                while ((tempString = reader.readLine())!=null){
                    if (tempString.contains("Mapped \"{[")){
                        String url = tempString.split("\"\\u007B\\u005B")[1].split("]")[0];
                        writer.write(url);
                        writer.write("\n");
                    }
                }
                writer.flush();
                reader.close();
            }
        }
        writer.close();
    }

    @Test
    public void test(){
//        左 大括号的转义：{ ==> u007B
//            左方括号的转义：[ ==> u005B
            System.out.println("\u005B");
    }
}
