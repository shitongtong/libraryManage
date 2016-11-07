package cn.stt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;


public class iiplayerLog extends HttpServlet{
	private Connection cnn;
	public iiplayerLog() {
		super();
		cnn = MysqlManager.getMysqlManager().getConn();//此处为通过自己写的方法getConn()获得连接
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String mac = request.getParameter("mac");
		PrintWriter out = response.getWriter();
		String pc_android_ios = request.getParameter("pc_android_ios");
		if(mac==null||pc_android_ios==null)
		{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			jsonObject.put("ismustvip", 1);
			jsonObject.put("suiji", 12306);//随机
			jsonObject.put("info", "mac信息不能为空");
			out.println(jsonObject.toString());
			out.flush();
			out.close();
			return ;
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
      	String insertsql;
      	insertsql = "insert into iiplayer_run_log(mac,pc_android_ios,runtime) value('";
      	insertsql=insertsql + mac + "','";
      	insertsql=insertsql + pc_android_ios + "','";
      	insertsql=insertsql+df.format(new Date())+"')";
      	try {  
        	PreparedStatement statement = cnn.prepareStatement(insertsql);  
            statement.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println("插入数据库时出错：");  
            e.printStackTrace();  
        }
      	
      	JSONObject jsonObject = new JSONObject();
      	Calendar cal = Calendar.getInstance();
      	int week = cal.get(Calendar.DAY_OF_WEEK) ;
      	
        if(week==1||week==4||week==5)
        {
        	jsonObject.put("ismustvip", 0);
        }
        else
        {
        	jsonObject.put("ismustvip", 1);
        }

        jsonObject.put("suiji", 12306);//随机
		jsonObject.put("status", 0);
		jsonObject.put("info", "");
		out.println(jsonObject.toString());
		out.flush();
		out.close();
	}
}
