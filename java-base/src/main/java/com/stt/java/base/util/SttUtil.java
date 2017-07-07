package com.stt.java.base.util;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/7/7.
 */
public class SttUtil {

    /**
     * 字符串首字母小写
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

}
