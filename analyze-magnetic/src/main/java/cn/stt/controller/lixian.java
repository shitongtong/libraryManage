package cn.stt.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;


public class lixian extends HttpServlet{
	
	public  String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        HttpURLConnection conn = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            conn = (HttpURLConnection)realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setConnectTimeout(5000); 
            conn.setReadTimeout(5000); 
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
            	if(conn!=null)
            	{
            		conn.disconnect();
            		conn = null;
            	}
                if(out!=null){
                    out.close();
                    out = null;
                }
                if(in!=null){
                    in.close();
                    in = null;
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }   
	
	
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
		 String file_hash = request.getParameter("file_hash");
		 PrintWriter out = response.getWriter();
		 JSONObject jsonObject = new JSONObject();
		 if(file_hash==null||file_hash.isEmpty())
		 {
			 jsonObject.put("status", 0);
			 jsonObject.put("data", "");
			 jsonObject.put("info", "参数不正确");
			 return;
		 }
			String getxuanfengurl = "http://lixian.qq.com/handler/lixian/get_http_url.php";
			String getxuanfengParm = "";
			getxuanfengParm = getxuanfengParm+ "hash=" + file_hash +"&filename=blackcat";
		 String get_http_url = sendPost(getxuanfengurl,getxuanfengParm);
		 
		 out.println(get_http_url);
		 out.flush();
		 out.close();	
	 }
}
