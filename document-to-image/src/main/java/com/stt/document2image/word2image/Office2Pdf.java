////package com.stt.document2image.word2image;
////
////import jp.ne.so_net.ga2.no_ji.jcom.IDispatch;
////import jp.ne.so_net.ga2.no_ji.jcom.ReleaseManager;
////
/////**
//// * Created by Administrator on 2017-01-10.
//// *
//// * @author shitongtong
//// */
////public class Office2Pdf {
////
////    public void createPDF(String officePath,String pdfPath) throws Exception {
////        ReleaseManager rm = null;
////        IDispatch app = null;
////        try {
////            rm=new ReleaseManager();
////            app = new IDispatch(rm, "PDFMakerAPI.PDFMakerApp");
////            app.method("CreatePDF",new Object[]{officePath,pdfPath});
////        } catch (Exception e) {
////            throw e;
////        } finally {
////            try {
////                app=null;
////                rm.release();
////                rm = null;
////            } catch (Exception e) {
////                throw e;
////            }
////        }
////    }
////
////    public static void main(String[] args) throws Exception {
////        Office2Pdf one=new Office2Pdf();
//////        one.createPDF("E:\\codigg.ppt","E:\\codigg-ppt.pdf");
//////        one.createPDF("E:\\codigg.doc","E:\\codigg-doc.pdf");
//////        one.createPDF("E:\\codigg.xls","E:\\codigg-xls.pdf");
////        one.createPDF("D:\\document2image\\注意事项.doc","D:\\document2image\\注意事项.pdf");
////    }
//
////}
//
//public String doDocToFdpLibre() {
//
//// File inputFile = new File("d:/1.txt");
//// File inputFile = new File("d:/ppt.ppt");
//// File inputFile = new File("d:/pptx.pptx");
//// File inputFile = new File("d:/doc.doc");
//// File inputFile = new File("d:/docx.docx");
//// File inputFile = new File("d:/xls.xls");
//        File inputFile = new File("d:/jpg.jpg");
//// File inputFile = new File("d:/gif.gif");
//        System.out.println("libreOffice开始转换..............................");
//        Long startTime = new Date().getTime();
////
//// txt:使用libreOffice来转换pdf，转换成功，但是中文有乱码!!!!!
//// doc:这是office中的doc文档，可以转换成功，并且中文没有乱码
////docx:这是office中的docx文档，可以转换成功，并且中文没有乱码
////ppt和pptx：转换成功。
////xls:转换成功，没有中文乱码
////xlsx：转换成功
////jpg和png:成功
//        String libreOfficePath = "D:/Program Files (x86)/LibreOffice 4";
//// 此类在jodconverter中3版本中存在，在2.2.2版本中不存在
//        DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
//// libreOffice的安装目录
//        configuration.setOfficeHome(new File(libreOfficePath));
//// 端口号
//        configuration.setPortNumber(8100);
//// configuration.setTaskExecutionTimeout(1000 * 60 * 5L);//
//// 设置任务执行超时为5分钟
//// configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);//
//// 设置任务队列超时为24小时
//        OfficeManager officeManager = configuration.buildOfficeManager();
//        officeManager.start();
//        System.out.println("...start.....");
//        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
//        File outputFile = new File("d:/jpgLibre.pdf");
//        converter.convert(inputFile, outputFile);
//// converter.convert(inputFile, stw, outputFile, pdf);
//// 转换结束
//        System.out.println("转换结束。。。。。");
//        String pdfPath = outputFile.getPath();
//        long endTime = new Date().getTime();
//        long time = endTime - startTime;
//        System.out.println("libreOffice转换所用时间为："+time);
//        return pdfPath;
//        }
////
////}
