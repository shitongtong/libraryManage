package com.stt.poi;

import com.stt.util2.SecurityUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/5/11.
 */
public class TestAccount {

    public static void main(String[] args) throws Exception {
        String excelFilePath = "D:\\公网测试环境客户端测试账号.xlsx";
        /*List<String> list = readExcelWithTitle(excelFilePath,0);    //获取教师手机号列表
        for (String phone : list) {
            String password = SecurityUtil.hashSha512Hex(phone + "&" + "123456" + ":onlyhi");
            System.out.println(password);   //教师密码
        }*/

        /*List<String> list = readExcelWithTitle(excelFilePath, 3);    //leads_uuid
        for (String leads_uuid : list) {
            System.out.print("'" + leads_uuid + "',");
        }*/

        List<String> list = readExcelWithTitle(excelFilePath, 4);    //学生手机号
        for (String phone : list) {
            String password = SecurityUtil.hashSha512Hex(phone + "&" + "123456" + ":onlyhi");
            System.out.println(password);   //学生密码
        }
    }

    public static List<String> readExcelWithTitle(String filepath, int cellNumber) throws Exception {
        String fileType = filepath.substring(filepath.lastIndexOf(".") + 1, filepath.length());
        InputStream is = null;
        Workbook wb = null;
        try {
            is = new FileInputStream(filepath);

            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook(is);
            } else if (fileType.equals("xlsx")) {
                wb = new XSSFWorkbook(is);
            } else {
                throw new Exception("读取的不是excel文件");
            }
            List<String> list = new ArrayList<>();
            Sheet sheet = wb.getSheetAt(1);
            int rowSize = sheet.getLastRowNum() + 1;
            for (int j = 1; j < rowSize; j++) { //遍历行
                Row row = sheet.getRow(j);
                Cell cell = row.getCell(cellNumber);
                String value = cell.getStringCellValue();
//                String value = cell.toString();
                list.add(value);
            }
            return list;
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            if (wb != null) {
                wb.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }
}
