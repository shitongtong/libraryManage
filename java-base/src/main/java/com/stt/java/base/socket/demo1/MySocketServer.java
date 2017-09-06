package com.stt.java.base.socket.demo1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/9/1.
 */
public class MySocketServer {
    private int port = 1122;
    private ServerSocket serverSocket;

    public MySocketServer() throws Exception {
        serverSocket = new ServerSocket(port, 3);
        System.out.println("服务器启动!");
    }

    public void service() {
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                System.out.println("New connection accepted " + socket.getInetAddress() + ":" + socket.getPort());

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        MySocketServer server = new MySocketServer();
//        Thread.sleep(60000 * 10);
        server.service();
    }
}
