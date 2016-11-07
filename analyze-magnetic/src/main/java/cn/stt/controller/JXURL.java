package cn.stt.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;


public class JXURL extends HttpServlet{
	private Connection cnn;
	private String cookie = "ssov_6427934=0_6427934_3a527e9cfe84573ae5fdfe44a6cfc196; OOFL=6427934; UID=6427934_A1_1471919358; CID=3f9f2bad9deee2979d816225695f4e4f; SEID=1e47fa613a7848c9f15b574216d589c0cc8c0ab697da4e665256e056d710e3dc2075e3e329476e6383e7deeaca6ebe1903e34050d1e46068a24b5a35";
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	
	public JXURL() {
		super();
		//cnn = MysqlManager.getMysqlManager().getConn();//此处为通过自己写的方法getConn()获得连接

		//登录115账号
	}
	
	
	public  String sendCookieGet(String url) {
        String result = "";
        BufferedReader in = null;
        HttpURLConnection connection = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            connection = (HttpURLConnection)realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Cookie", cookie);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000); 
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
            System.out.println("发送GET请求出现异常！" + e + url);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
        	if(connection!=null)
        	{
        		connection.disconnect();
        		connection = null;
        	}
            if(in!=null){
                try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                in = null;
            }
        }
        
        return result;
    }
	
	
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		 response.setCharacterEncoding("utf-8");
		 JSONObject jsonObject = new JSONObject();
		 JSONObject object = null;
		 int count = 1;
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		 
		 //查询是否播放地址过期，如果过期就从新请求，如果不过期直接返回
		    String pickcode = null;
		    String name = null;
		    while(true)
		    {
			/*try {
				Statement stmt;
				stmt = cnn.createStatement();
	            String querysql = "select code,name from iiplayer_115 where id=" + count + "";
	            ResultSet rs = stmt.executeQuery(querysql);  
	            boolean bExist= false;
	            if(rs.next())  
	            {   
	            	pickcode = rs.getString(1);
	            	name = rs.getString(2);
	            	bExist = true;
	            }
	            
	            if(bExist)
	            {*/
	            	//更新url
	            	String downurl =  sendCookieGet("http://web.api.115.com/files/download?pickcode="+"eltib2ibs71dyzsqq");
	       		 try {
	       		        object = JSONObject.fromObject(downurl);
	       			} catch (Exception e) {
	       				// TODO Auto-generated catch block
	       				e.printStackTrace();
	       				continue;
	       			}
	       	             
	       		        
	       		 boolean state = object.getBoolean("state");
	       		 if(!state)
	       		 {
	       	 			jsonObject.put("status", 1);
	       	 			jsonObject.put("url", "");
	       	 			jsonObject.put("info", object.getString("error")); 
	       	 			System.out.println("账号异常"+":df.format(new Date())");
	       	 			System.out.println(downurl);
	       	 			break;
	       		 }
	       		 else
	       		 {
	       			/* String downUrl = object.getString("file_url");
	       			 try {
	 					String modifysql = "update iiplayer_115 set  url ='" + downUrl+"',time='"+df.format(new Date()) + "' where code='"+ pickcode+"'";
	 					PreparedStatement statement = cnn.prepareStatement(modifysql);  
	 	                  statement.executeUpdate(); 
	 				} catch (SQLException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 					break;
	 				}*/

	       			 System.out.println("成功次数:"+count+":"+df.format(new Date()));
	       	         count++; 
	       	         try {
	       				Thread.sleep(1000);
	       			} catch (InterruptedException e) {
	       				// TODO Auto-generated catch block
	       				e.printStackTrace();
	       				continue;
	       			}
	       		 }
	          /*  }else
	            {
	            	count = 1;
	            }
	            
	            
			} catch (SQLException e1) {
				System.out.println("数据库查询异常！");
				e1.printStackTrace();
				break;
			} */
		 
		    }
		 
		 
	     out.println("exit;");
         out.flush();
         out.close();
		 
		 
	 }
	
}
