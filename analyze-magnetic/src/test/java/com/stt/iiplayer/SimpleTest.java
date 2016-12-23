package com.stt.iiplayer;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016-12-23.
 *
 * @author shitongtong
 */
public class SimpleTest {

    @Test
    public void testBufferReader() throws FileNotFoundException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\书籍\\Java与模式(清晰书签版).pdf")));
        for (int i = 0;i <10000; i++){
//            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\书籍\\Java与模式(清晰书签版).pdf")));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    /*try {
                        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\书籍\\Java与模式(清晰书签版).pdf")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }*/
                    char[] cb = new char[8192];
                    String s = sendGet("http://hosadmin.yabibuy.com/wema-hos-admin/home.do");
                    System.out.println(s);
                    System.out.println("================================================================");
                }
            }).start();
//            char[] cb = new char[819200000];
//            sendGet("http://hosadmin.yabibuy.com/wema-hos-admin/home.do");
        }

//        sendGet("https://chrome.google.com/webstore?utm_source=chrome-ntp-icon");

    }

    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            // 建立实际的连接
            connection.connect();

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Connection conn1=null;
        String dbDriver="com.mysql.jdbc.Driver";
        String dbUrl="jdbc:mysql://183.232.235.30:3306/ultrax?useUnicode=true&characterEncoding=utf-8";//根据实际情况变化
        String dbUser="root";
        String dbPass="woaini";
        try {
            Class.forName(dbDriver);
            try {
                conn1 = (Connection) DriverManager.getConnection(dbUrl,dbUser,dbPass);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }//注意是三个参数
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        for(int page=24593;page<160000;page++)
        {
            System.out.println("正在处理第+"+(page+1)+"页");
            Statement stmt1;
            try {
                stmt1 = conn1.createStatement();
                String querysql = "select * from iiplayer_sha limit "+(page+1)*10+","+10;
                ResultSet rs = stmt1.executeQuery(querysql);
                int count = 1;
                while(rs.next())
                {
                    System.out.println("正在处理第"+(count+page*10)+"记录");
                    String sha = rs.getString(4);
                    String hash =  rs.getString(2);
                    String index =  rs.getString(3);
                    String code =  rs.getString(5);
                    String name =  rs.getString(7);
                    String file_size =  rs.getString(8);
                    String url="http://183.232.235.30/weiyu.php?sha="+sha;
                    String get_http_url;
                    get_http_url = sendGet(url);
                    name = name.replace("【无效链接】 ", "");
                    name = name.replace("【处理中,请等待】 ", "");
                    if(get_http_url.length()>80)
                    {
                        //写入到数据库中
                        try {
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                            String insertsql;
                            insertsql = "insert into iiplayer_sha(torrent_hash,torrent_index,hash,url,add_time,name,size) value('";
                            insertsql=insertsql + hash + "',";
                            insertsql=insertsql + index + ",'";
                            insertsql=insertsql + sha + "','";
                            insertsql=insertsql + code + "','";
                            insertsql=insertsql+df.format(new Date())+ "','";
                            insertsql=insertsql+ name + "','";
                            insertsql=insertsql + file_size +"')";
                            PreparedStatement statement = conn1.prepareStatement(insertsql);
                            statement.executeUpdate();

                            System.out.println(df.format(new Date())+"_"+"插入数据库"+hash+"_"+index);
                        } catch (SQLException e) {
                            System.out.println("插入数据库时出错：");
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        System.out.println(get_http_url);
                    }

                    count++;

                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    }

    //java.lang.OutOfMemoryError: Java heap space
    @Test
    public void testOutOfMemoryError(){
        for(int page=0;page<160000;page++){
            System.out.println("正在处理第"+(page+1)+"页");
            for (int i=0; i<10;i++){
                String s = getString();
                System.out.println(s);
//                s = null;
            }
        }
    }


    private static String getString(){
        String result = "";
        BufferedReader bufferedReader = null;
        try {
//                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\书籍\\Java与模式(清晰书签版).pdf")));
            bufferedReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream("D:\\个人\\文学书籍\\成为作家.txt")));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }// 使用finally块来关闭输入流
        finally {
            System.out.println("finally");
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return result;
    }

}
