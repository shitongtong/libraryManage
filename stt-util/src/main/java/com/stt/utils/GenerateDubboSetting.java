package com.stt.utils;

import java.io.File;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/6/20.
 */
public class GenerateDubboSetting {

    public static void main(String[] args) {
        generateServiceSetting();
        System.out.println();
        generateRestSetting();
    }

    static String dirPath = "D:\\project_git\\onlyhi-crm\\onlyhi\\onlyhi-service\\src\\main\\java\\cn\\onlyhi\\service";

    public static void generateServiceSetting(){

        File dirFile = new File(dirPath);
        File[] files = dirFile.listFiles();
        for (File file : files){
            StringBuilder sb = new StringBuilder();
            sb.append("<dubbo:service interface=\"");
            String absolutePath = file.getAbsolutePath();
            int i = absolutePath.indexOf("cn");
            String substring = absolutePath.substring(i);
            String basePath = substring.replace("\\",".").replace(".java","");
            String interfacePath = basePath.replace("Impl","");
            sb.append(interfacePath);
            sb.append("\" ref=\"");
            String refPath = basePath.substring(basePath.lastIndexOf(".")+1);
            sb.append(toLowerCaseFirstOne(refPath));
            sb.append("\" />");
            System.out.println(sb);
        }
    }


    public static void generateRestSetting(){

        File dirFile = new File(dirPath);
        File[] files = dirFile.listFiles();
        for (File file : files){
            StringBuilder sb = new StringBuilder();
            sb.append("<dubbo:reference id=\"");
            String absolutePath = file.getAbsolutePath();
            int i = absolutePath.indexOf("cn");
            String substring = absolutePath.substring(i);
            String basePath = substring.replace("\\",".").replace(".java","");
            String interfacePath = basePath.replace("Impl","");

            String idPath = interfacePath.substring(interfacePath.lastIndexOf(".")+1);
            sb.append(toLowerCaseFirstOne(idPath));
            sb.append("\" interface=\"");
            sb.append(interfacePath);
            sb.append("\"  check=\"false\" />");
            System.out.println(sb);
        }
    }

    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}
