package com.stt.document2image.office2pdf;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;

import java.io.File;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/4.
 */
public class Document4j2Pdf {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        String officePath = "D:\\testoffice2pdf\\精品课程高三物理-力和共点力平衡.pptx";
        String pdfPath = "D:\\testoffice2pdf\\office2pdf2\\精品课程高三物理-力和共点力平衡.pdf";

        officePath = "D:\\testoffice2pdf\\test4.docx";
        pdfPath = "D:\\testoffice2pdf\\office2pdf2\\test4.pdf";

        File wordFile = new File(officePath);
        File target = new File(pdfPath);
        File baseFolder = new File("D:\\testoffice2pdf\\tmp");
        IConverter converter =LocalConverter.builder()
                .baseFolder(baseFolder)
                .workerPool(20, 25, 2, TimeUnit.SECONDS)
                .processTimeout(5, TimeUnit.SECONDS)
                .build();
        Future<Boolean> conversion = converter
                .convert(wordFile).as(DocumentType.MS_WORD)
                .to(target).as(DocumentType.PDF)
                .prioritizeWith(1000) // optional
                .schedule();

        long endTime = System.currentTimeMillis();
        System.out.println("转换时间：" + (endTime - startTime) / 1000 + "s");   //30s   13s
    }
}
