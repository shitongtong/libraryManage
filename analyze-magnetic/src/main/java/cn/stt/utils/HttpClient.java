package cn.stt.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {
    //发送一个GET请求
    public String get(String path) throws Exception {
        HttpURLConnection httpConn = null;
        BufferedReader in = null;
        try {
            URL url = new URL(path);
            httpConn = (HttpURLConnection) url.openConnection();

            //读取响应
            if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                StringBuffer content = new StringBuffer();
                String tempStr = "";
                in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                while ((tempStr = in.readLine()) != null) {
                    content.append(tempStr);
                }
                return content.toString();
            } else {
                throw new Exception("请求出现了问题!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
            httpConn.disconnect();
        }
        return null;
    }

    //发送一个GET请求,参数形式key1=value1&key2=value2...
    public static String post(String path, String params) throws Exception {
        HttpURLConnection httpConn = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            URL url = new URL(path);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);

            //发送post请求参数
            out = new PrintWriter(httpConn.getOutputStream());
            out.println(params);
            out.flush();

            //读取响应
            if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                StringBuffer content = new StringBuffer();
                String tempStr = "";
                in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                while ((tempStr = in.readLine()) != null) {
                    content.append(tempStr);
                }
                return content.toString();
            } else {
                throw new Exception("请求出现了问题!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
            out.close();
            httpConn.disconnect();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        //String resMessage=HttpClient.get("http://localhost:3000/hello?hello=hello get");
        //String resMessage=HttpClient.post("http://localhost:3000/hello", "hello=hello post");
        // System.out.println(resMessage);
    }

}