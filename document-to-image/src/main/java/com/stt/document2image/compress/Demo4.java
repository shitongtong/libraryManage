package com.stt.document2image.compress;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/9/13.
 */
public class Demo4 {


    @Test
    public void test() throws IOException {
        String srcPath = "D:\\图片\\图片压缩\\clipboard.png";
        srcPath = "D:\\图片\\图片压缩\\test1.png";
        BufferedImage sourceImage = ImageIO.read(new File(srcPath));
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        BufferedImage tempImage;
        Graphics2D g2D;
        for (int i = 1; i <= 13; i++) { //当i=13时 目标图片没有失色且大小最小
            tempImage = new BufferedImage(width, height, i);
            g2D = (Graphics2D) tempImage.getGraphics();
            g2D.drawImage(sourceImage, 0, 0, null);
            ImageIO.write(tempImage, "png", new File("D:\\图片\\图片压缩\\test1\\" + i + ".png"));
        }
    }
}
