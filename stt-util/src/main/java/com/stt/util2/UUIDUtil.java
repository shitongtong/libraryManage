package com.stt.util2;

import java.util.UUID;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/3/24.
 */
public class UUIDUtil {

    /**
     * 带"-"分割的
     * @return
     */
    public static String randomUUID(){
        return UUID.randomUUID().toString();
    }

    /**
     * 不带"-"分割的
     * @return
     */
    public static String randomUUID2(){
        return noSplit(randomUUID());
    }

    /**
     * 去"-"分割
     * @param uuid
     * @return
     */
    public static String noSplit(String uuid){
        return uuid.replaceAll("-","");
    }
}
