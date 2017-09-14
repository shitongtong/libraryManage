package com.stt.document2image.compress;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * java压缩图片内存大小，但不改变分辨率大小
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/9/13.
 */
public class Demo2 {
    /**
     * 第一种方式
     *
     * @param quality：0-1
     * @return byte[]
     * @throws
     * @Title: compressPicByQuality
     * @Description: 压缩图片, 通过压缩图片质量，保持原图大小
     */
    public static byte[] compressPicByQuality(byte[] imgByte, float quality) {
        byte[] inByte = null;
        try {
            ByteArrayInputStream byteInput = new ByteArrayInputStream(imgByte);
            BufferedImage image = ImageIO.read(byteInput);

            // 如果图片空，返回空
            if (image == null) {
                return null;
            }
            // 得到指定Format图片的writer
            Iterator<ImageWriter> iter = ImageIO
                    .getImageWritersByFormatName("jpeg");// 得到迭代器
            ImageWriter writer = (ImageWriter) iter.next(); // 得到writer

            // 得到指定writer的输出参数设置(ImageWriteParam )
            ImageWriteParam iwp = writer.getDefaultWriteParam();
            iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // 设置可否压缩
            iwp.setCompressionQuality(quality); // 设置压缩质量参数

            iwp.setProgressiveMode(ImageWriteParam.MODE_DISABLED);

            ColorModel colorModel = ColorModel.getRGBdefault();
            // 指定压缩时使用的色彩模式
            iwp.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel,
                    colorModel.createCompatibleSampleModel(16, 16)));

            // 开始打包图片，写入byte[]
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 取得内存输出流
            IIOImage iIamge = new IIOImage(image, null, null);

            // 此处因为ImageWriter中用来接收write信息的output要求必须是ImageOutput
            // 通过ImageIo中的静态方法，得到byteArrayOutputStream的ImageOutput
            writer.setOutput(ImageIO
                    .createImageOutputStream(byteArrayOutputStream));
            writer.write(null, iIamge, iwp);
            inByte = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            System.out.println("write errro");
            e.printStackTrace();
        }

        return inByte;
    }

    /**
     * 第一种方式
     *
     * @param quality:0-1
     * @return byte[]
     * @throws
     * @Title: compressPic
     * @Description: 压缩图片, 通过压缩图片质量，保持原图大小
     */
    public static byte[] compressPic(byte[] imageByte, float quality) {
        byte[] inByte = null;
        try {
            ByteArrayInputStream byteInput = new ByteArrayInputStream(imageByte);
            Image img = ImageIO.read(byteInput);
            float newWidth = img.getWidth(null);
            float newHeight = img.getHeight(null);

            Image image = img.getScaledInstance((int) newWidth, (int) newHeight, Image.SCALE_SMOOTH);

            // 缩放图像
            BufferedImage tag = new BufferedImage((int) newWidth,
                    (int) newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = tag.createGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            ByteArrayOutputStream out = new ByteArrayOutputStream(imageByte.length);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
            /* 压缩质量 */
            jep.setQuality((float) 0.6, true);
            encoder.encode(tag, jep);
            inByte = out.toByteArray();
            out.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return inByte;
    }
}
