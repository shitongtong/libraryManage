package com.stt.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/7/11.
 */
public class DeleteControllerTryCatch {
    public static void main(String[] args) throws Exception {
        String path = "D:\\project_git\\onlyhi-crm";
        List<File> files = new ArrayList<>();
        getControllerFiles(files,new File(path));
        for (int i = 0; i < files.size(); i++) {
            deleteControllerTryCatch(files.get(i));
        }

    }

    private static void deleteControllerTryCatch(File file) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String temp = null;
        StringBuilder sb = new StringBuilder();
        while ((temp = reader.readLine())!=null){
            if ("".equals(temp)){
                sb.append("\r\n");
                continue;
            }
            if (temp.contains("try")){
                continue;
            }
            if (temp.contains("catch")){
                reader.readLine();
                reader.readLine();
                continue;
            }
            sb.append(temp);
            sb.append("\r\n");
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(sb.toString());
        reader.close();
        writer.close();
    }

    private static void getControllerFiles(List<File> files,File file){
        if (file.isFile()){
            if (file.getName().contains("Controller")){
                files.add(file);
            }
        }else{
            File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                getControllerFiles(files,listFiles[i]);
            }
        }
    }
}
