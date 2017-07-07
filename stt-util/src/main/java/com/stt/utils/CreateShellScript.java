package com.stt.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/6/22.
 */
public class CreateShellScript {

    public static void main(String[] args) throws Exception {
        String templateServicePath = "D:\\workDir\\jar包启动脚本\\template-service-restart.sh";
        String templateRestPath = "D:\\workDir\\jar包启动脚本\\template-rest-restart.sh";
        File templateServiceFile = new File(templateServicePath);
        File templateRestFile = new File(templateRestPath);
        String templateServiceContent = readFile(templateServiceFile);
        String templateRestContent = readFile(templateRestFile);


        String destBasePath = "D:\\workDir\\jar包启动脚本\\dev";
        String env = "dev";

        destBasePath = "D:\\workDir\\jar包启动脚本\\test";
        env = "test";

        String[] baseNames = {"common", "user", "agent", "teacher", "cc", "cr", "sr", "client", "finance", "marketopt", "order", "teachdept", "kpi"};
        for (int i = 0; i < baseNames.length; i++) {
            String baseName = baseNames[i];
            String serviceName = baseName + "-service";
            String serviceShellName = baseName + "-service-restart.sh";
            String restName = baseName + "-rest";
            String restShellName = baseName + "-rest-restart.sh";
            File serviceFile = new File(destBasePath + File.separator + "service", serviceShellName);
            File parentFile = serviceFile.getParentFile();
            if (!parentFile.exists()){
                parentFile.mkdirs();
            }
            File restFile = new File(destBasePath + File.separator + "rest", restShellName);
            File parentFile1 = restFile.getParentFile();
            if (!parentFile1.exists()){
                parentFile1.mkdirs();
            }
            String serviceContent = templateServiceContent.replace("#{template}", serviceName).replace("#{config}",env);
            String restContent = templateRestContent.replace("#{template}", restName).replace("#{config}",env);
            writerFile(serviceFile, serviceContent);
            writerFile(restFile, restContent);
        }



    }

    public static String readFile(File file) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String tempString = null;
        // 一次读入一行，直到读入null为文件结束
        StringBuilder sb = new StringBuilder();
        while ((tempString = reader.readLine()) != null) {
            // 显示行号
            sb.append(tempString);
//            sb.append("\n\r");
            sb.append("\n");
        }
        reader.close();

        return sb.toString();
    }

    public static void writerFile(File file, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(content);
        writer.close();
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}
