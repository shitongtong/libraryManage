package cn.stt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class iiplayerSeach  extends HttpServlet{
	private Connection cnn;
	private DateSecret dateSecret;
	public iiplayerSeach() {
		super();
		cnn = MysqlManager.getMysqlManager().getConn();//此处为通过自己写的方法getConn()获得连接
		dateSecret = new DateSecret();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		long starTime=System.currentTimeMillis();
		String page = request.getParameter("page");
		String pingtai =  request.getParameter("pingtai");
		String suiji = request.getParameter("suiji");
		String code = request.getParameter("code");
		String version_name = request.getParameter("version_name");
		String version_code = request.getParameter("version_code");
		if(pingtai==null)
		{
		if(code==null)
		{
			out.println("");
			out.flush();
			out.close();
			return;
		}
		else
		{
			int icode = Integer.valueOf(code);
			if(icode!=9331393)
			{
				out.println("");
				out.flush();
				out.close();
				return;
			}
		}
		}
		int count = 20;
		if(page==null)
		{
			page="1";
			count = 50;
		}
		int iPage = Integer.valueOf(page);
		
		String name = new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");
		JSONObject jsonObject = new JSONObject();
		if(name==null||name.isEmpty())
		{
			jsonObject.put("status", 0);
			jsonObject.put("data", "");
			jsonObject.put("info", "非法参数");
			out.println(jsonObject.toString());
			out.flush();
			out.close();
			return;
		}

		
		JSONArray listArray = new JSONArray();
		try {
			Statement stmt;
			stmt = cnn.createStatement();
			//分页查询
			//order by id desc
			name = name.replace("-", "");
            String querysql = "select name,size,add_time,hash,url,torrent_hash,torrent_index from iiplayer_sha where replace(name,'-','') like '%" + name + "%' limit "+count*(iPage-1) +","+count;
            ResultSet rs = stmt.executeQuery(querysql);  
            while(rs.next())  
            {   
            	JSONObject avlist = new JSONObject();
            	avlist.put("name",  rs.getString(1));
				avlist.put("size",rs.getString(2));
				avlist.put("add_time", rs.getString(3));
				avlist.put("sha1",rs.getString(4));
				avlist.put("url",rs.getString(5));
				avlist.put("torrent_hash",rs.getString(6));
				avlist.put("index",rs.getString(7));
            	listArray.add(avlist);
            }
            
            long endTime2=System.currentTimeMillis();
            long Time2=endTime2-starTime;
            jsonObject.put("time", Time2);
            
			jsonObject.put("data", listArray);
			jsonObject.put("status", 1);
			jsonObject.put("info", "");
            
		} catch (SQLException e1) {
			System.out.println("数据库查询异常！");
			e1.printStackTrace();
		}  
		
		String result = null;
		try {
			result = dateSecret.encryptDES(jsonObject.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(pingtai!=null&&pingtai.equals("pc"))
		{
			// int sj = Integer.valueOf(suiji);
			//if(sj==12306)
			//{
				out.println(jsonObject.toString());
/*			}
			else
			{
				out.println(result);
			}*/
		}
		else
		{
			out.println(result);
		}
		
		out.flush();
		out.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		long starTime=System.currentTimeMillis();
		String code = request.getParameter("code");//9331393 签名hash
		String version_name = request.getParameter("version_name");
		String version_code = request.getParameter("version_code");
		if(code==null)
		{
			out.println("");
			out.flush();
			out.close();
			return;
		}
/*		else
		{
			int icode = Integer.valueOf(code);
			if(icode!=9331393)
			{
				out.println("");
				out.flush();
				out.close();
				return;
			}
		}*/
		JSONObject jsonObject = new JSONObject();
		JSONArray listArray = new JSONArray();
		try {
			Statement stmt;
			stmt = cnn.createStatement();
			 int max=1450000;
		     int min=1;
		     Random random = new Random();
		     int s = random.nextInt(max)%(max-min+1) + min;
            String querysql = "select name,size,add_time,hash,url,torrent_hash,torrent_index from iiplayer_sha LIMIT "+ s +",20";
            ResultSet rs = stmt.executeQuery(querysql);  
            while(rs.next())  
            {   
            	JSONObject avlist = new JSONObject();
            	avlist.put("name",  rs.getString(1));
				avlist.put("size",rs.getString(2));
				avlist.put("add_time", rs.getString(3));
				avlist.put("sha1",rs.getString(4));
				avlist.put("url",rs.getString(5));
				avlist.put("torrent_hash",rs.getString(6));
				avlist.put("index",rs.getString(7));
            	listArray.add(avlist);
            }
            
            long endTime2=System.currentTimeMillis();
            long Time2=endTime2-starTime;
            jsonObject.put("time", Time2);
            
			jsonObject.put("data", listArray);
			jsonObject.put("status", 1);
			jsonObject.put("info", "");
            
		} catch (SQLException e1) {
			System.out.println("数据库查询异常！");
			e1.printStackTrace();
		}  
		
		String result = null;
		int icode = Integer.valueOf(code);
		if(icode==9331393)
		{
			try {
				result = dateSecret.encryptDES(jsonObject.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(icode==9331396)
		{
			result = jsonObject.toString();
		}
		
	
		out.println(result);
		out.flush();
		out.close();
		
	}
	
}
