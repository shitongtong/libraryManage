package com.stt.document2image.pdf2image;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/9/1.
 */
public class PdfboxUtil {

    /**
     * 判断PDF文件的压缩算法是否为JBIG2Decode
     *
     * @param
     */
    private static boolean isJbig2(File file) throws IOException {
        //返回标志，默认为否
        boolean isJbig2 = false;
        //读pdf文件
        PDDocument pdfDoc = PDDocument.load(file);
        //生成COSDocument
        COSDocument doc = pdfDoc.getDocument();
        COSObject obj = doc.getObjectByType(COSName.XOBJECT);
        if (obj != null) {
            COSBase base = obj.getDictionaryObject(COSName.FILTER);
            String decode = base.toString();
            System.out.println("decode==" + decode);
            if (decode.equals("COSName{JBIG2Decode}")) {
                isJbig2 = true;
            }
        }
        doc.close();
        pdfDoc.close();
        return isJbig2;
    }

    public static void main(String[] args) throws IOException {
        String filePath = "D:\\testoffice2pdf\\高三英语秋季精品课4.pptx";
        boolean jbig2 = isJbig2(new File(filePath));
        System.out.println(jbig2);
    }
}
