package com.stt.document2image.office2pdf;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.RemoteConverter;
import com.google.common.io.Files;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/4.
 */
public class Document4j2Pdf2 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        String officePath = "D:\\testoffice2pdf\\精品课程高三物理-力和共点力平衡.pptx";
        String pdfPath = "D:\\testoffice2pdf\\office2pdf2\\精品课程高三物理-力和共点力平衡.pdf";

        officePath = "D:\\testoffice2pdf\\test4.doc";
        pdfPath = "D:\\testoffice2pdf\\office2pdf2\\test4.pdf";

        File wordFile = new File(officePath);
        File target = new File(pdfPath);
//        target = Files.createTempDir();
//        File baseFolder = new File("D:\\testoffice2pdf\\tmp");
        File baseFolder = Files.createTempDir();
        System.out.println("baseFolder="+baseFolder);
        System.out.println("baseFolderPath="+baseFolder.getAbsolutePath());
        IConverter converter = RemoteConverter.builder()
                .baseFolder(baseFolder)
        .workerPool(20, 25, 2, TimeUnit.SECONDS)
                .requestTimeout(10, TimeUnit.SECONDS)
                .baseUri("http://localhost:8080")
        .build();
        /*Future<Boolean> conversion = converter
                .convert(wordFile).as(DocumentType.DOC)
                .to(target).as(DocumentType.PDF)
                .prioritizeWith(1000) // optional
                .schedule();*/
        boolean conversion = converter
                .convert(wordFile).as(DocumentType.DOC)
                .to(target).as(DocumentType.PDF)
                .execute();
        System.out.println(conversion);
        long endTime = System.currentTimeMillis();
        System.out.println("转换时间：" + (endTime - startTime) / 1000 + "s");   //30s   13s
    }
}
