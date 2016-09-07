package cn.stt.util;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 2016-09-07.
 */



public class OpenOffice2PDF {

    /**
     * office中各种格式
     */
    private static final String[] OFFICE_POSTFIXS = { "doc", "docx", "xls",
            "xlsx", "ppt", "pptx" };
    private ArrayList<String> Office_Formats = new ArrayList<String>();

    /**
     * pdf格式
     */
    private static final String PDF_POSTFIX= "pdf";

    /**
     * 根据操作系统的名称，获取OpenOffice.org 3的安装目录 如我的OpenOffice.org 3安装在：C:/Program
     * Files/OpenOffice.org 3
     */

    public String getOfficeHome() {
        String osName = System.getProperty("os.name");
        if (Pattern.matches("Linux.*", osName)) {
            return "/opt/openoffice.org3";
        } else if (Pattern.matches("Windows.*", osName)) {
            return "D:/Program Files/OpenOffice.org 3";
        }
        return null;
    }
    /**
     * 转换文件
     * @param inputFilePath
     * @param outputFilePath
     * @param converter
     */
    public void converterFile(String inputFilePath, String outputFilePath,
                              DocumentConverter converter) {
//        File inputFile=new File(inputFilePath);
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;

        try {
            URL url = new URL(inputFilePath);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // 设置连接网络的超时时间
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                inputStream = httpURLConnection.getInputStream();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        File outputFile = new File(outputFilePath);
        // 假如目标路径不存在,则新建该路径
        if (!outputFile.getParentFile().exists()) {
            outputFile.getParentFile().mkdirs();
        }
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(outputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        converter.convert(inputFile, outputFile);
//        String extName = inputFilePath.substring(inputFilePath.lastIndexOf(".") + 1);
//
//        DocumentFormat inputDocumentFormat = new DocumentFormat(inputFile.getName(),extName,extName);
//        DocumentFormat outputDocumentFormat = new DocumentFormat(outputFile.getName(),"pdf","pdf");

        DefaultDocumentFormatRegistry formatReg = new DefaultDocumentFormatRegistry();
//        String name = inputFile.getName();
//        System.out.println("name==="+name);
        DocumentFormat doc = formatReg.getFormatByFileExtension("doc");
        DocumentFormat pdf = formatReg.getFormatByFileExtension("pdf");

//        converter.convert(inputFile, inputDocumentFormat,outputFile,outputDocumentFormat);
//        converter.convert(inputFile, doc,outputFile,pdf);
        converter.convert(inputStream, doc,outputStream,pdf);
        System.out.println("文件：" + inputFilePath + "\n转换为\n目标文件：" + outputFile
                + "\n成功！");
        try {
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使Office2003-2007全部格式的文档(.doc|.docx|.xls|.xlsx|.ppt|.pptx) 转化为pdf文件
     *
     * @param inputFilePath
     *            源文件路径，如："e:/test.docx"
     * @param outputFilePath
     *            如果指定则按照指定方法，如果未指定（null）则按照源文件路径自动生成目标文件路径，如："e:/test_docx.pdf"
     * @return
     */
    public boolean openOffice2Pdf(String inputFilePath, String outputFilePath) {
        boolean flag = false;
        /*
         * 连接OpenOffice.org 并且启动OpenOffice.org
         * OpenOffice4,外部启动
         *//*
        DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();
        // 获取OpenOffice.org 3的安装目录
        String officeHome = getOfficeHome();
        config.setOfficeHome(officeHome);
        // 启动OpenOffice的服务
        OfficeManager officeManager = config.buildOfficeManager();
        officeManager.start();*/
        // 连接OpenOffice
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        try {
            connection.connect();
        } catch (ConnectException e) {
            e.printStackTrace();
        }
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        long begin_time = new Date().getTime();
        File inputFile=new File(inputFilePath);
        Collections.addAll(Office_Formats, OFFICE_POSTFIXS);
        if ((null != inputFilePath) && (inputFile.exists())) {
            // 判断目标文件路径是否为空
            if (Office_Formats.contains(getPostfix(inputFilePath))) {
                if (null == outputFilePath) {
                    // 转换后的文件路径
                    String outputFilePath_new = generateDefaultOutputFilePath(inputFilePath);
                    converterFile(inputFilePath, outputFilePath_new, converter);
                    flag = true;

                } else {
                    converterFile(inputFilePath, outputFilePath, converter);
                    flag = true;
                }
            }

        } else {
            System.out.println("con't find the resource");
        }
        long end_time = new Date().getTime();
        System.out.println("文件转换耗时：[" + (end_time - begin_time) + "]ms");
        return flag;
    }

    /**
     * 如果未设置输出文件路径则按照源文件路径和文件名生成输出文件地址。例，输入为 D:/fee.xlsx 则输出为D:/fee_xlsx.pdf
     */
    public String generateDefaultOutputFilePath(String inputFilePath) {
        String outputFilePath = inputFilePath.replaceAll("."
                + getPostfix(inputFilePath), "_" + getPostfix(inputFilePath)
                + ".pdf");
        return outputFilePath;
    }

    /**
     * 获取inputFilePath的后缀名，如："e:/test.pptx"的后缀名为："pptx"
     */
    public String getPostfix(String inputFilePath) {
        String[] p = inputFilePath.split("\\.");
        if (p.length > 0) {// 判断文件有无扩展名
            // 比较文件扩展名
            return p[p.length - 1];
        } else {
            return null;
        }
    }

    public static void main(String[] args) {

        OpenOffice2PDF office2pdf = new OpenOffice2PDF();
        /*office2pdf.openOffice2Pdf("D:/国家知识产权局第二届运动会拔河及田进比赛的通知.doc",
                "D:/国家知识产权局第二届运动会拔河及田进比赛的通知_" + new Date().getTime() + "."
                        + PDF_POSTFIX);
        office2pdf.openOffice2Pdf("D:/函件自定义调用字段_20130220_GC.xls",null);*/
//        office2pdf.openOffice2Pdf("C:\\Users\\Administrator\\Desktop\\filetest\\打打.docx",null);
//        office2pdf.openOffice2Pdf("http://image.bblink.cn/201603-7ce1fbce9ca94874a2724baed9b09357.doc"
//        ,"C:\\Users\\Administrator\\Desktop\\filetest\\111.pdf");

        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        try {
            connection.connect();
        } catch (ConnectException e) {
            e.printStackTrace();
        }
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        office2pdf.converterFile("http://image.bblink.cn/201603-7ce1fbce9ca94874a2724baed9b09357.doc"
                ,"C:\\Users\\Administrator\\Desktop\\filetest\\111.pdf",converter);
    }


}
