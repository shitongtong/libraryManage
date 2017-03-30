package com.stt.document2image.pdf2image;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017-01-10.
 *
 * @author shitongtong
 */
public class Pdf2Image {

    public static void main(String[] args) throws IOException {
        String filePath = "D:\\document2image\\准妈妈孕早期注意事项.pdf";
        filePath = "D:\\testoffice2pdf\\test4.pdf";
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
            BufferedImage image = renderer.renderImageWithDPI(i, 200);  //296
            String filename = saveDir + File.separator + fileNameNoSuffix + "_" + (i + 1) + ".jpg";
//            ImageIO.write(image, "PNG", new File(filename));

            /* 原始图像的宽度和高度 */
            int width = image.getWidth();
            int height = image.getHeight();
            System.out.println("width=" + width);
            System.out.println("height=" + height);

            //压缩计算
            float resizeTimes = 0.3f;  /*这个参数是要转化成的倍数,如果是1就是转化成1倍*/

            /* 调整后的图片的宽度和高度 */
            int toWidth = (int) (width * resizeTimes);
            int toHeight = (int) (height * resizeTimes);
            System.out.println("toWidth=" + toWidth);
            System.out.println("toHeight=" + toHeight);
            /* 新生成结果图片 */
            BufferedImage result = new BufferedImage(toWidth, toHeight,
                    BufferedImage.TYPE_INT_RGB);

            result.getGraphics().drawImage(
                    image.getScaledInstance(toWidth, toHeight,
                            java.awt.Image.SCALE_SMOOTH), 0, 0, null);

            ImageIO.write(result, "PNG", new File(filename));
            /*Image image1 = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT);
            ImageFilter cropFilter = new CropImageFilter(imageX, imageY, desWidth, desHeight);
            Image img = Toolkit.getDefaultToolkit().createImage(
                    new FilteredImageSource(image1.getSource(), cropFilter));*/
        }
        doc.close();

        System.out.println("pdf2image success");
    }
}
