package com.stt.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 在mapperXML文件中找出select查询标签中没有设置resultType或resultMap的id
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/7/10.
 */
public class FindNoResultFromMapper {
    public static void main(String[] args) throws Exception {
        String path = "D:\\project_git\\onlyhi-crm";
        List<File> fileList = new ArrayList<>();
        fileList = getMapperXmlFiles(fileList, new File(path));
        System.out.println(fileList.size());
        for (File file : fileList){
            getMapperIds(file);
        }
    }

    private static void getMapperIds(File file) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String temp = null;
        while ((temp = bufferedReader.readLine())!=null){
            if (temp.contains("<select") && !temp.contains("result")){
                System.out.println(temp);
            }
        }
        bufferedReader.close();
    }

    private static List<File> getMapperXmlFiles(List<File> fileList,File file){
        if (file.isFile()){
            if (file.getName().contains("Mapper.xml")){
                fileList.add(file);
            }
        }else{
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                fileList = getMapperXmlFiles(fileList,files[i]);
            }
        }
        return fileList;
    }
}
