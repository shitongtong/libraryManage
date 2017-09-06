package com.stt.java.base.socket.demo1;

import java.net.Socket;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/9/1.
 */
public class MySocketClient {
    public static void main(String[] args) throws Exception {
        final int length = 100;
        String host = "localhost";
        int port = 1122;
        Socket[] socket = new Socket[length];
        for (int i = 0; i < length; i++) {
            socket[i] = new Socket(host, port);
            System.out.println("第" + (i + 1) + "次连接成功！");
        }
        Thread.sleep(3000);
        for (int i = 0; i < length; i++) {
            socket[i].close();
        }
    }
}
