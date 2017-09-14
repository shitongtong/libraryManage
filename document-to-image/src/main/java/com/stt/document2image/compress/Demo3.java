package com.stt.document2image.compress;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

/**
 * 使用Thumbnailator
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/9/13.
 */
public class Demo3 {
    public static void main(String[] args) throws IOException {
        String srcPath = "D:\\图片\\图片压缩\\Java基础PPT_23.jpg";
        String destPath = "D:\\图片\\图片压缩\\Java基础PPT_23-Demo3.jpg";

//        srcPath = "D:\\图片\\图片压缩\\clipboard.png";
//        destPath = "D:\\图片\\图片压缩\\clipboard-demo3.png";//大小几乎没变

        File fromPic = new File(srcPath);
        File toPic = new File(destPath);
        //图片尺寸不变，压缩图片文件大小outputQuality实现,参数1为最高质量
        Thumbnails.of(fromPic).scale(1f).outputQuality(0.25f).toFile(toPic);
    }
}
