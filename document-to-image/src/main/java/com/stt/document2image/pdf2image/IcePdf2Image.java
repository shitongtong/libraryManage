package com.stt.document2image.pdf2image;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/4.
 */
public class IcePdf2Image {
    public static void main(String[] args) throws Exception {
        System.out.println("pdf2image start");
        long startTime = System.currentTimeMillis();
        String filePath = "D:\\testoffice2pdf\\Java基础PPT.pdf";
        String imagePath = "D:\\testoffice2pdf\\Java基础PPT_icepdf";
        File imageDir = new File(imagePath);
        if (!imageDir.exists()) {
            imageDir.mkdirs();
        }
        Document document = new Document();
        document.setFile(filePath);
        float scale = 2.5f;//缩放比例
        scale = 1f;
        float rotation = 0f;//旋转角度
        int pageCount = document.getNumberOfPages();
        System.out.println("pdf pageSize==" + pageCount);
        for (int i = 0; i < pageCount; i++) {
            BufferedImage image = (BufferedImage)
                    document.getPageImage(i, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
            RenderedImage rendImage = image;
            File file = new File(imagePath, "iecPDF_" + (i + 1) + ".jpg");
            ImageIO.write(rendImage, "png", file);
            image.flush();
        }
        document.dispose();
        System.out.println("pdf2image success");

        long endTime = System.currentTimeMillis();
        System.out.println("转换时间：" + (endTime - startTime) / 1000 + "s");   //32s
    }
}
