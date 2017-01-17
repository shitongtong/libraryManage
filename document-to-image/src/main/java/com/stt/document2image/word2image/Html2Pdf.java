package com.stt.document2image.word2image;

import com.lowagie.text.pdf.BaseFont;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * 失败的例子
 * <p>
 * Created by Administrator on 2017-01-10.
 *
 * @author shitongtong
 */
public class Html2Pdf {

    public boolean convertHtmlToPdf(String inputFile, String outputFile)
            throws Exception {

        OutputStream os = new FileOutputStream(outputFile);
        ITextRenderer renderer = new ITextRenderer();
        String url = new File(inputFile).toURI().toURL().toString();
        renderer.setDocument(url);
        // 解决中文支持问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont("C:/Windows/Fonts/simsunb.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        //解决图片的相对路径问题
//        renderer.getSharedContext().setBaseURL("file:/D:/test");
        renderer.layout();
        renderer.createPDF(os);
        os.flush();
        os.close();
        return true;
    }


    public static void main(String[] args) {
        Html2Pdf html2Pdf = new Html2Pdf();
        try {
            html2Pdf.convertHtmlToPdf("D:\\document2image\\注意事项.html", "D:\\document2image\\注意事项.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
