//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.utils;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

public class SecurityImage {
    public SecurityImage() {
    }

    public static BufferedImage createImage(String securityCode) {
        int codeLength = securityCode.length();
        byte fSize = 15;
        int fWidth = fSize + 1;
        int width = codeLength * fWidth + 6;
        int height = fSize * 2 + 1;
        BufferedImage image = new BufferedImage(width, height, 1);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Arial", 1, height - 2));
        g.drawRect(0, 0, width - 1, height - 1);
        Random rand = new Random();
        g.setColor(Color.LIGHT_GRAY);

        int codeY;
        int i;
        for(codeY = 0; codeY < codeLength * 6; ++codeY) {
            i = rand.nextInt(width);
            int y = rand.nextInt(height);
            g.drawRect(i, y, 1, 1);
        }

        codeY = height - 10;
        g.setColor(new Color(19, 148, 246));
        g.setFont(new Font("Georgia", 1, fSize));

        for(i = 0; i < codeLength; ++i) {
            g.drawString(String.valueOf(securityCode.charAt(i)), i * 16 + 5, codeY);
        }

        g.dispose();
        return image;
    }

    public static ByteArrayInputStream getImageAsInputStream(String securityCode) {
        BufferedImage image = createImage(securityCode);
        return convertImageToStream(image);
    }

    private static ByteArrayInputStream convertImageToStream(BufferedImage image) {
        ByteArrayInputStream inputStream = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(bos);

        try {
            jpeg.encode(image);
            byte[] e = bos.toByteArray();
            inputStream = new ByteArrayInputStream(e);
        } catch (ImageFormatException var5) {
            var5.printStackTrace();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return inputStream;
    }
}
