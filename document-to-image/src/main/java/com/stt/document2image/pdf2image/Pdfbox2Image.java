package com.stt.document2image.pdf2image;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2017-01-10.
 *
 * @author shitongtong
 */
public class Pdfbox2Image {


    public static void main(String[] args) throws IOException {
        System.out.println("转换开始。。。");
        long startTime = System.currentTimeMillis();
        String filePath = "D:\\document2image\\准妈妈孕早期注意事项.pdf";
        filePath = "D:\\testoffice2pdf\\test4.pdf";
        filePath = "D:\\testoffice2pdf\\Java基础PPT.pdf"; // TODO: 2017/8/4 文件大小2.44M 图片307张。花费时间144秒(200dpi)
        filePath = "D:\\testoffice2pdf\\页数多大小小.pdf";    //302K 100dpi 14s
        filePath = "D:\\testoffice2pdf\\courseware\\pdf\\第3章第1节　钠及其重要化合物.pdf";
        File file = new File(filePath);
        String fileNameNoSuffix = file.getName().substring(0, file.getName().indexOf("."));
        String savePath = file.getParent() + File.separator + fileNameNoSuffix;
        System.out.println("savePath==" + savePath);
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        PDDocument doc = PDDocument.load(file);
        PDFRenderer renderer = new PDFRenderer(doc);
        int pageCount = doc.getNumberOfPages();
        System.out.println("pdf pageSize==" + pageCount);
        for (int i = 0; i < pageCount; i++) {
            BufferedImage image = renderer.renderImageWithDPI(i, 100);  //296   200:144s; 100:55s; 75:42s   50:28s
            String filename = saveDir + File.separator + fileNameNoSuffix + "_" + (i + 1) + ".jpg";
            ImageIO.write(image, "PNG", new File(filename));

            /* 原始图像的宽度和高度 */
            int width = image.getWidth();
            int height = image.getHeight();
            System.out.println("width=" + width);
            System.out.println("height=" + height);
//            ImageIO.write(image, "PNG", new File(filename));//正常生成图片

            //压缩计算
            float resizeTimes = 0.3f;  //这个参数是要转化成的倍数,如果是1就是转化成1倍
            //调整后的图片的宽度和高度
            int toWidth = (int) (width * resizeTimes);
            toWidth = 1024;
            resizeTimes = new BigDecimal((float)toWidth / width).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
//            resizeTimes = Float.intBitsToFloat(toWidth) / width;
            int toHeight = (int) (height * resizeTimes);
            System.out.println("resizeTimes=" + resizeTimes);
            System.out.println("toWidth=" + toWidth);
            System.out.println("toHeight=" + toHeight);
            //新生成结果图片
            BufferedImage result = new BufferedImage(toWidth, toHeight,
                    BufferedImage.TYPE_INT_RGB);

            result.getGraphics().drawImage(
                    image.getScaledInstance(toWidth, toHeight,
                            java.awt.Image.SCALE_SMOOTH), 0, 0, null);

            ImageIO.write(result, "PNG", new File(filename));   //缩放图片  按固定宽度768

            /*
            Image image1 = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT);
            ImageFilter cropFilter = new CropImageFilter(imageX, imageY, desWidth, desHeight);
            Image img = Toolkit.getDefaultToolkit().createImage(
                    new FilteredImageSource(image1.getSource(), cropFilter));*/
        }
        doc.close();
        System.out.println("pdf2image success");

        long endTime = System.currentTimeMillis();
        System.out.println("转换时间：" + (endTime - startTime) / 1000 + "s");
    }
}
