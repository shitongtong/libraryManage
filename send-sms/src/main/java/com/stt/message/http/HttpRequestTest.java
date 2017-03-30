package com.stt.message.http;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/3/30.
 */
public class HttpRequestTest {
    public static void main(String[] args) {
        HttpRequest.proxyHost("106.14.46.51");
        String body = HttpRequest.get("www.baidu.com").body();
        System.out.println(body);
    }
}
