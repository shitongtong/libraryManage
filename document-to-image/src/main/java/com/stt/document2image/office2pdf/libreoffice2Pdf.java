package com.stt.document2image.office2pdf;


/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/4.
 */
public class libreoffice2Pdf {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        String officeFilePath = "D:\\testoffice2pdf\\Java基础PPT.ppt";    //3.86M 307页 30s
        officeFilePath = "D:\\testoffice2pdf\\精品课程高三物理-力和共点力平衡.pptx";    //6.53M 55页 13s
        officeFilePath = "D:\\testoffice2pdf\\页数多大小小.docx";    //875K 237页 2s
        officeFilePath = "D:\\testoffice2pdf\\test4.docx";    //5.58M 5页 2s
        String pdfFileSavePath = "D:\\testoffice2pdf\\office2pdf1";
        StringBuilder cmd = new StringBuilder();
        cmd.append("soffice --headless --invisible --convert-to pdf ");
        cmd.append(officeFilePath);
        cmd.append(" --outdir ");
        cmd.append(pdfFileSavePath);
        System.out.println("cmd=" + cmd.toString());
        Process pro = Runtime.getRuntime().exec(cmd.toString());
        pro.waitFor();
        System.out.println("****pdf转换成功" + "****");
        long endTime = System.currentTimeMillis();
        System.out.println("转换时间：" + (endTime - startTime) / 1000 + "s");   //30s   13s
    }
}
