package com.stt.document2image.ppt2image;

import org.apache.poi.hslf.usermodel.HSLFShape;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFSlideShowImpl;
import org.apache.poi.hslf.usermodel.HSLFTextParagraph;
import org.apache.poi.hslf.usermodel.HSLFTextRun;
import org.apache.poi.hslf.usermodel.HSLFTextShape;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * ppt或pptx 转为图片
 * <p>
 * Created by Administrator on 2017-01-10.
 *
 * @author shitongtong
 */
public class Ppt2Image1 {

    public static void main(String[] args) throws Exception {
        toImage2007();
        toImage2003();
    }

    public static void toImage2007() throws Exception {
        String filePath = "D:\\document2image\\test2.pptx";
        File file = new File(filePath);
        String fileNameNoSuffix = file.getName().substring(0, file.getName().indexOf("."));
        String savePath = file.getParent() + File.separator + fileNameNoSuffix;
        System.out.println("savePath=="+savePath);
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

        FileInputStream is = new FileInputStream(filePath);
        XMLSlideShow ppt = new XMLSlideShow(is);
        is.close();

        Dimension pgsize = ppt.getPageSize();
        System.out.println(pgsize.width + "--" + pgsize.height);

        for (int i = 0; i < ppt.getSlides().size(); i++) {
            try {
                //防止中文乱码
                for (XSLFShape shape : ppt.getSlides().get(i).getShapes()) {
                    if (shape instanceof XSLFTextShape) {
                        XSLFTextShape tsh = (XSLFTextShape) shape;
                        for (XSLFTextParagraph p : tsh) {
                            for (XSLFTextRun r : p) {
                                r.setFontFamily("宋体");
                            }
                        }
                    }
                }
                BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = img.createGraphics();
                // clear the drawing area
                graphics.setPaint(Color.white);
                graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));

                // render
                ppt.getSlides().get(i).draw(graphics);

                // save the output
//                String filename = "D:/demo/07-" + (i + 1) + ".jpg";
                String filename = saveDir + File.separator + fileNameNoSuffix + "_" + (i + 1) + ".jpg";
                System.out.println(filename);
                FileOutputStream out = new FileOutputStream(filename);
                ImageIO.write(img, "png", out);
                out.close();
            } catch (Exception e) {
                System.out.println("第" + i + "张ppt转换出错");
            }
        }
        System.out.println("pptx2image success");
    }

    public static void toImage2003() {
        try {

            String filePath = "D:\\document2image\\test1.ppt";
            File file = new File(filePath);
            HSLFSlideShow ppt = new HSLFSlideShow(new HSLFSlideShowImpl(filePath));

            Dimension pgsize = ppt.getPageSize();
            String fileNameNoSuffix = file.getName().substring(0, file.getName().indexOf("."));
            String savePath = file.getParent() + File.separator + fileNameNoSuffix;
            System.out.println("savePath=="+savePath);
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }
            for (int i = 0; i < ppt.getSlides().size(); i++) {
                //防止中文乱码
                for (HSLFShape shape : ppt.getSlides().get(i).getShapes()) {
                    if (shape instanceof HSLFTextShape) {
                        HSLFTextShape tsh = (HSLFTextShape) shape;
                        for (HSLFTextParagraph p : tsh) {
                            for (HSLFTextRun r : p) {
                                r.setFontFamily("宋体");
                            }
                        }
                    }
                }
                BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = img.createGraphics();
                // clear the drawing area
                graphics.setPaint(Color.white);
                graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));

                // render
                ppt.getSlides().get(i).draw(graphics);

                // save the output
                String filename = saveDir + File.separator + fileNameNoSuffix + "_" + (i + 1) + ".jpg";
                System.out.println(filename);
                FileOutputStream out = new FileOutputStream(filename);
                ImageIO.write(img, "png", out);
                out.close();
//              resizeImage(filename, filename, width, height);

            }
            System.out.println("ppt2image success");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /***
     * 功能 :调整图片大小
     *
     * @param srcImgPath  原图片路径
     * @param distImgPath 转换大小后图片路径
     * @param width       转换后图片宽度
     * @param height      转换后图片高度
     */
    public static void resizeImage(String srcImgPath, String distImgPath,
                                   int width, int height) throws IOException {

        File srcFile = new File(srcImgPath);
        Image srcImg = ImageIO.read(srcFile);
        BufferedImage buffImg = null;
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        buffImg.getGraphics().drawImage(
                srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0,
                0, null);

        ImageIO.write(buffImg, "JPEG", new File(distImgPath));

    }
}
