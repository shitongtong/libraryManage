package com.stt.utils;

import com.stt.util2.UUIDUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/5/15.
 */
public class DBDataTransfrom {

    @Test
    public void createUUID() {
        int count = 16;
        for (int i = 0; i < count; i++) {
            System.out.println(UUIDUtil.randomUUID());
        }
    }

    @Test
    public void modifyExcel() {
        String excelFilePath = "D:\\workDir\\sql导入导出\\school.xls";
        int coloum = 0; // 第1列
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(excelFilePath));
            HSSFSheet sheet = workbook.getSheet("Sheet1");

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {  //从第二行开始
                HSSFRow row = sheet.getRow(i);
                if (null == row) {
                    continue;
                } else {
                    HSSFCell cell = row.getCell(coloum);
                    if (null == cell) {
                        continue;
                } else {
                        cell.setCellValue(UUIDUtil.randomUUID());
                    }
                }
            }
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(excelFilePath);
                workbook.write(out);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
