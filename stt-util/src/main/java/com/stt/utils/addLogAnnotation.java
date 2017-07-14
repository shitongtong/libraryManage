package com.stt.utils;

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
public class AddLogAnnotation {

    public static void main(String[] args) throws Exception {
        String path = "D:\\project_git\\onlyhi-crm\\onlyhi-kpi\\kpi-rest\\src\\main\\java\\cn\\onlyhi\\kpi\\controller";
        path = "D:\\project_git\\onlyhi-crm\\onlyhi-marketopt\\marketopt-rest\\src\\main\\java\\cn\\onlyhi\\marketopt\\controller";
        path = "D:\\project_git\\onlyhi-crm\\onlyhi-order\\order-rest\\src\\main\\java\\cn\\onlyhi\\order\\controller";
        path = "D:\\project_git\\onlyhi-crm\\onlyhi-sr\\sr-rest\\src\\main\\java\\cn\\onlyhi\\sr\\controller";
        path = "D:\\project_git\\onlyhi-crm\\onlyhi-teachdept\\teachdept-rest\\src\\main\\java\\cn\\onlyhi\\teachdept\\controller";
        path = "D:\\project_git\\onlyhi-crm\\onlyhi-teacher\\teacher-rest\\src\\main\\java\\cn\\onlyhi\\teacher\\controller";
        path = "D:\\project_git\\onlyhi-crm\\onlyhi-user\\user-rest\\src\\main\\java\\cn\\onlyhi\\user\\controller";

        int moduleCode = 10000015;
        moduleCode = 10000016;
        moduleCode = 10000017;
        moduleCode = 10000013;
        moduleCode = 10000013;
        moduleCode = 10000012;
        moduleCode = 10000018;
        moduleCode = 10000011;

        String moduleName = "kpi";
        moduleName = "市场与运营";
        moduleName = "订单";
        moduleName = "sr";
        moduleName = "教学";
        moduleName = "师资";
        moduleName = "用户权限";

        File file = new File(path);
        File[] files = file.listFiles();
        int count = 1;
        for (int i = 0; i < files.length; i++) {
            File file1 = files[i];
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(file1));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString);
                sb.append("\r\n");
                if (tempString.contains("package")){
                    sb.append("\r\n");
                    sb.append("\r\n");
                    sb.append("import cn.onlyhi.common.annotation.LogRecordAnnotation;");
                }
                if (tempString.contains("@ResponseBody")) {
                    sb.append("\t@LogRecordAnnotation(moduleCode = ");
                    sb.append(moduleCode);
                    sb.append(", moduleName = \"");
                    sb.append(moduleName);
                    sb.append("\", methodCode = ");
                    String methodCode = moduleCode+""+count++;
                    sb.append(methodCode);
                    sb.append(", methodName = \"\", description = \"\")");
                    sb.append("\r\n");
                }
            }
            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file1));
            writer.write(sb.toString());
            writer.close();
        }
    }
}
