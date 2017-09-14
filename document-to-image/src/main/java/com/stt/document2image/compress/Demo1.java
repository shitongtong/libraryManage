package com.stt.document2image.compress;

import org.apache.commons.lang3.StringUtils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 图片压缩，只压缩大小，不压缩宽高
 * 将图片压缩到200k以下
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/9/13.
 */
public class Demo1 {
    public static void main(String[] args) {
        String srcPath = "D:\\图片\\图片压缩\\Java基础PPT_23.jpg";
        String destPath = "D:\\图片\\图片压缩\\Java基础PPT_23-Demo1.jpg";

        srcPath = "D:\\图片\\图片压缩\\clipboard.png";
        destPath = "D:\\图片\\图片压缩\\clipboard-demo1.png";//背景色变了（黑色变蓝绿？）

        srcPath = "D:\\图片\\图片压缩\\test1.png";
        destPath = "D:\\图片\\图片压缩\\test1-demo1.png";//背景色变了（黑色变蓝绿？）

        File file = new File(srcPath);
        double b = file.length() / (double) 1024;
        double a = 200 / b;
        System.out.println(b + "KB");
        System.out.println(a);
        System.out.println("=============================================================");
        a = 0.25;
        double image = compressPic(srcPath, destPath, a);
        /*while (image > 200) {
            System.out.println("次数");
            image = compressPic(srcPath, destPath, a - 0.1);
        }*/
        File file1 = new File(destPath);
        double b1 = file1.length() / (double) 1024;
        System.out.println(b1 + "KB");
    }

    public static double compressPic(String srcFilePath, String descFilePath, double a) {
        File file = null;
        BufferedImage src = null;
        FileOutputStream out = null;
        ImageWriter imgWrier;
        ImageWriteParam imgWriteParams;

        // 指定写图片的方式为 jpg
        imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
        imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);
        // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
        imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
        // 这里指定压缩的程度，参数qality是取值0~1范围内，
        imgWriteParams.setCompressionQuality((float) a);
        imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
        ColorModel colorModel = ColorModel.getRGBdefault();
        // 指定压缩时使用的色彩模式
        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel, colorModel
                .createCompatibleSampleModel(16, 16)));

        try {
            if (StringUtils.isBlank(srcFilePath)) {
                System.out.println("压缩失败");

            } else {
                file = new File(srcFilePath);
                src = ImageIO.read(file);
                out = new FileOutputStream(descFilePath);

                imgWrier.reset();
                // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何 OutputStream构造
                imgWrier.setOutput(ImageIO.createImageOutputStream(out));
                // 调用write方法，就可以向输入流写图片
                imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            //return false;
        }
        File file1 = new File(descFilePath);
        double b1 = file1.length() / (double) 1024;
        return b1;
    }
}
