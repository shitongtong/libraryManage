package com.stt.document2image.pdf2image;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * pdf文件加水印
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/5/16.
 */
public class PdfAddWatermark {
    public static void main(String[] args) throws IOException, DocumentException {
        PdfReader reader = new PdfReader("D:\\pdf2image\\1493374159558_231718158.pdf", "World".getBytes());
        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream("D:\\pdf2image\\1493374159558_231718158_watermark.pdf"));
        Image img = Image.getInstance("D:\\pdf2image\\水印.png");
        img.setAbsolutePosition(25, 0); //位置
        img.setAbsolutePosition(5, 5); //位置
        int total = reader.getNumberOfPages();
//      System.out.println(total);
        for(int i=1;i<=total;i++)
        {
            PdfContentByte under = stamp.getUnderContent(i);
            under.addImage(img);
        }
        stamp.close();
    }
}
