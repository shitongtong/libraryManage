package com.stt.java.base.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import static com.stt.java.base.util.SttUtil.toLowerCaseFirstOne;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/7/7.
 */
public class CreateServiceFile {
    public static void main(String[] args) throws Exception {
        String serviceDir = "D:\\mybatis-generator-core\\mybatis-generator-core-custom\\serviceFile";
        String serviceImplDir = "D:\\mybatis-generator-core\\mybatis-generator-core-custom\\serviceImplFile";
        String dirPath = "D:\\mybatis-generator-core\\mybatis-generator-core-custom\\cn\\onlyhi\\dao";
        File dirFile = new File(dirPath);
        File[] mapperFiles = dirFile.listFiles();
        for (int i = 0; i < mapperFiles.length; i++) {
            File mapperFile = mapperFiles[i];
            String mapperFileName = mapperFile.getName();
            String serviceFileName = mapperFileName.replace("Mapper", "Service");
            String serviceImplFileName = mapperFileName.replace("Mapper", "ServiceImpl");
            File serviceFile = new File(serviceDir,serviceFileName);
            File serviceImplFile = new File(serviceImplDir,serviceImplFileName);
            createServiceFile(mapperFile,serviceFile);
            createServiceImplFile(mapperFile,serviceImplFile);
        }
    }

    private static void createServiceImplFile(File mapperFile,File serviceImplFile) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(mapperFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(serviceImplFile));
        StringBuilder sb = new StringBuilder();
        sb.append("package cn.onlyhi.service;");
        sb.append("\r\n");
        sb.append("\r\n");
        //import cn.onlyhi.dao.ChannelMapper;
        sb.append("import cn.onlyhi.dao.");
        String mapperFileName = mapperFile.getName().replace(".java", "");
        sb.append(mapperFileName);
        sb.append(";");
        sb.append("\r\n");
        //import cn.onlyhi.po.Channel;
        sb.append("import cn.onlyhi.po.");

        String poName = mapperFileName.substring(0,mapperFileName.lastIndexOf("Mapper"));
        sb.append(poName);
        sb.append(";");
        sb.append("\r\n");
        sb.append("import org.springframework.beans.factory.annotation.Autowired;");
        sb.append("\r\n");
        sb.append("import org.springframework.stereotype.Service;");
        sb.append("\r\n");
        sb.append("\r\n");
        sb.append("/**\n" +
                " * @Author shitongtong\n" +
                " * <p>\n" +
                " * Created by shitongtong on 2017/7/6.\n" +
                " */");
        sb.append("\r\n");
        sb.append("@Service");
        sb.append("\r\n");
        //public class ChannelServiceImpl implements ChannelService {
        sb.append("public class ");
        String implName = serviceImplFile.getName().replace(".java", "");
        sb.append(implName);
        sb.append(" implements ");
        String serviceName = implName.replace("Impl", "");
        sb.append(serviceName);
        sb.append(" {\r\n\r\n\t");
        sb.append("@Autowired\r\n\tprivate ");
        sb.append(mapperFileName);
        sb.append(" ");
        String lowerMapperFileName = toLowerCaseFirstOne(mapperFileName);
        sb.append(lowerMapperFileName);
        sb.append(";\r\n\r\n\t");
        sb.append("@Override");
        sb.append("\r\n\t");
        //public int save(Channel channel) {
        sb.append("public int save(");
        sb.append(poName);
        String lowerPoName = toLowerCaseFirstOne(poName);
        sb.append(" ");
        sb.append(lowerPoName);
        sb.append(") {");
        sb.append("\r\n\t\t");
        //return channelMapper.insertSelective(channel);
        sb.append("return ");
        sb.append(lowerMapperFileName);
        sb.append(".insertSelective(");
        sb.append(lowerPoName);
        sb.append(");");
        sb.append("\r\n\t}");
        sb.append("\r\n");
        String temp = null;
        while ((temp = reader.readLine())!=null){
            if (temp.contains("updateByUuidSelective")){
                sb.append("\r\n\t");
                sb.append("@Override");
                sb.append("\r\n");
                //public int update(Channel channel) {
                sb.append("\tpublic int update(");
                sb.append(poName);
                sb.append(" ");
                sb.append(lowerPoName);
                sb.append(") {");
                sb.append("\r\n");
                sb.append("\t\t");
                //return channelMapper.updateByUuidSelective(channel);
                sb.append("return ");
                sb.append(lowerMapperFileName);
                sb.append(".updateByUuidSelective(");
                sb.append(lowerPoName);
                sb.append(");");
                sb.append("\r\n\t}");
                sb.append("\r\n");

            }
            if (temp.contains("selectByUuid")){
                sb.append("\r\n\t");
                sb.append("@Override");
                sb.append("\r\n");
                //public Channel findByUuid(String uuid) {
                sb.append("\tpublic ");
                sb.append(poName);
                sb.append(" findByUuid(String uuid) {");
                sb.append("\r\n");
                sb.append("\t\t");
                //return channelMapper.selectByUuid(uuid);
                sb.append("return ");
                sb.append(lowerMapperFileName);
                sb.append(".selectByUuid(uuid);");
                sb.append("\r\n\t}");
                sb.append("\r\n");
            }
        }
        sb.append("\r\n");
        sb.append("}");
        writer.write(sb.toString());
        writer.close();
        reader.close();
    }

    private static void createServiceFile(File mapperFile,File serviceFile) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(mapperFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(serviceFile));
        StringBuilder sb = new StringBuilder();
        sb.append("package cn.onlyhi.service;");
        sb.append("\r\n");
        sb.append("\r\n");
        sb.append("import cn.onlyhi.po.");
        String mapperFileName = mapperFile.getName();
        String poName = mapperFileName.substring(0,mapperFileName.lastIndexOf("Mapper"));
        sb.append(poName);
        sb.append(";");
        sb.append("\r\n");
        sb.append("\r\n");
        sb.append("/**\n" +
                " * @Author shitongtong\n" +
                " * <p>\n" +
                " * Created by shitongtong on 2017/7/6.\n" +
                " */");
        sb.append("\r\n");
        sb.append("public interface ");
        sb.append(serviceFile.getName().replace(".java",""));
        sb.append(" {\r\n");//int save(Channel channel);
        sb.append("\r\n");
        sb.append("\tint save(");
        String lowerPoName = toLowerCaseFirstOne(poName);
        sb.append(poName);
        sb.append(" ");
        sb.append(lowerPoName);
        sb.append(");");
        sb.append("\r\n");
        sb.append("\r\n\t");
        String temp = null;
        while ((temp = reader.readLine())!=null){
            if (temp.contains("updateByUuidSelective")){
                sb.append("/**\n" +
                        "     * 根据uuid更新\n" +
                        "     * @param "+lowerPoName+"\n" +
                        "     * @return\n" +
                        "     */");
                sb.append("\r\n");
                //int update(Channel channel);
                sb.append("\tint update(");
                sb.append(poName);
                sb.append(" ");
                sb.append(lowerPoName);
                sb.append(");");
                sb.append("\r\n");
                sb.append("\r\n\t");
            }
            if (temp.contains("selectByUuid")){
                //Channel findByUuid(String uuid);
                sb.append(poName);
                sb.append(" findByUuid(String uuid);");
                sb.append("\r\n");
                sb.append("\r\n\t");
            }
        }
        sb.append("\r\n");
        sb.append("}");
        writer.write(sb.toString());
        writer.close();
        reader.close();
    }
}
