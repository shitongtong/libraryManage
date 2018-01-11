package com.stt;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/7/7.
 */
public class SttUtil {

    /**
     * 字符串首字母小写
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 音频时长，失败
     * @param file
     * @return
     * @throws Exception
     */
    @Deprecated
    public static long getAudioLength(File file) throws Exception {
        Clip clip = AudioSystem.getClip();
        AudioInputStream ais = AudioSystem.getAudioInputStream(file);
        clip.open(ais);
        return clip.getMicrosecondLength();

    }

    public static void main(String[] args) {
        String path = "d:/0_20171101025515162.m4a";
        File file = new File(path);
        long length = file.length();
//        7.67 * 1024 * 1024 * 8/(256*1000);
        System.out.println(length);
        double i = length * 8 / (58 * 1000);
        System.out.println(i);

        int i1 = 24 * 60 * 60 * 1000;
        System.out.println(i1);
    }

}
