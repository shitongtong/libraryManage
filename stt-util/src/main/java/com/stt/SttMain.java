package com.stt;

import com.stt.util.HttpUtil;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/12/11.
 */
public class SttMain {
    public static void main(String[] args) {
        String url = "https://crmapi.onlyhi.cn/course/getAllSubject?token=96393f4a1d29ffe7d3ef47b974432614a88dd648b5d9d3372e126a774f6a494cd3d7a2a918ce438c52cd31e68bd403e7740073517d9d961a17c31d02b8e10f5e";
        String result = HttpUtil.sendGet(url);
        System.out.println(result);
    }

}
