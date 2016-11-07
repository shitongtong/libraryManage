package cn.stt.controller;

import java.io.IOException;
import java.io.PrintWriter;
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


public class iiplayerPay extends HttpServlet{

	private Connection cnn;
	public iiplayerPay() {
		super();
		cnn = MysqlManager.getMysqlManager().getConn();//此处为通过自己写的方法getConn()获得连接
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String user_name = request.getParameter("user_name");
		String expiration = request.getParameter("expiration");
		String password = request.getParameter("password");
		String money = request.getParameter("money");
		String tongdao = request.getParameter("tongdao");
		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		if(user_name==null||uid==null||user_name.isEmpty()||uid.isEmpty()||expiration==null||expiration.isEmpty()
				||tongdao==null||tongdao.isEmpty()||money==null||money.isEmpty()	)
		{
          	jsonObject.put("status", 0);
			jsonObject.put("info",user_name+":"+uid+":"+expiration+":"+ response.encodeURL(new String("非法测试，我已经记录了你的踪迹,并保存证据，请立马停止这种行为，不然后果自负".getBytes("UTF-8"),"ISO-8859-1")));
		}
		else
		{

			int expir = Integer.valueOf(expiration);
			if(expir>6)
			{
				jsonObject.put("status", 0);
				jsonObject.put("info", response.encodeURL(new String("非法测试，我已经记录了你的踪迹,并保存了相关证据，请立即停止这种非法行为，不然后果自负".getBytes("UTF-8"),"ISO-8859-1")));
			}
			else
			{
				String sql = "select expiration from iiplayer_vip where user_name='"+user_name +"' and user_id=" + uid;  
				 try  
			        {   
			            Statement stmt = cnn.createStatement();  
			            ResultSet rs = stmt.executeQuery(sql);  
			            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			            if(rs.next())  
			            {  
							//已经开通过，如果已经过期，那么从当前时间开始算起，如果没有过期，从过期那天加起
			            	long time = 0;
							if(new Date().getTime()/1000>Integer.valueOf(rs.getString(1)))
							{
				            	 time =Long.valueOf((long)expir*2678400*1000) +new Date().getTime();
							}
							else
							{
								 time = Long.valueOf((long)expir*2678400*1000) +Long.valueOf((long)Integer.valueOf(rs.getString(1))*1000);
							}
							
							String insertsql;
					      	insertsql = "update iiplayer_vip set expiration=";
					      	insertsql=insertsql + time/1000;
					      	insertsql=insertsql +" where user_id=";
					      	insertsql=insertsql + uid;
					      	insertsql=insertsql + " and user_name='";
					    	insertsql=insertsql + user_name;
					    	insertsql=insertsql + "'";
					      	try {  
					        	PreparedStatement statement = cnn.prepareStatement(insertsql);  
					            statement.executeUpdate();  
					        } catch (SQLException e) {  
					            System.out.println("充值错误");  
					            e.printStackTrace();  
					        }
			            	
					      	String date = sdf.format(new Date(time));
			            	jsonObject.put("expir", rs.getString(1));
				          	jsonObject.put("status", 1);
							jsonObject.put("info", response.encodeURL(new String("会员到期时间".getBytes("UTF-8"),"ISO-8859-1")+":"+date));
			            }
			            else
			            {
                                //第一次开通，插入数据库中
			            	long time = new Date().getTime()+Long.valueOf((long)expir*2678400*1000);
			            	String insertsql;
					      	insertsql = "insert into iiplayer_vip(user_id,user_name,expiration) value(";
					      	insertsql=insertsql +uid + ",'";
					      	insertsql=insertsql + user_name + "',";
					      	insertsql=insertsql + time/1000 +")";
					      	try {  
					        	PreparedStatement statement = cnn.prepareStatement(insertsql);  
					            statement.executeUpdate();  
					        } catch (SQLException e) {  
					            System.out.println("充值错误");  
					            e.printStackTrace();  
					        }
			            	
					      	String date = sdf.format(new Date(time));
			            	jsonObject.put("expir", time);
				          	jsonObject.put("status", 1);
							jsonObject.put("info", response.encodeURL(new String("会员到期时间".getBytes("UTF-8"),"ISO-8859-1")+":"+date));
			            }
			        }  
			        catch (SQLException e)  
			        {  
			          	jsonObject.put("status", 0);
						jsonObject.put("info", response.encodeURL(new String("充值错误".getBytes("UTF-8"),"ISO-8859-1")));
			            e.printStackTrace();  
			        } 
			}
			
			
	

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String time = df.format(new Date());

				//记入充值log
				String insertsql;
		      	insertsql = "insert into iiplayer_pay_log(user_id,user_name,pay_time,pay_qudao,pay_money,month) values(";
		      	insertsql=insertsql +uid + ",'";
		      	insertsql=insertsql + user_name + "','";
		      	insertsql=insertsql + time + "','";
		      	insertsql=insertsql + tongdao + "','";
		    	insertsql=insertsql + money + "','";
		      	insertsql=insertsql+expiration+"')";
		      	try {  
		        	PreparedStatement statement = cnn.prepareStatement(insertsql);  
		            statement.executeUpdate();  
		        } catch (SQLException e) {  
		            System.out.println("插入数据库时出错：");  
		            e.printStackTrace();  
		        }
		}
		
		out.println(jsonObject.toString());
		out.flush();
		out.close();	
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String user_name = request.getParameter("user_name");
		user_name = new String(user_name.getBytes("iso-8859-1"),"utf-8");
		String mac = request.getParameter("mac");
		String pc_android_ios = request.getParameter("pc_android_ios");
		int bYidi = 0;
		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		if(user_name==null||uid==null||user_name.isEmpty()||uid.isEmpty())
		{
          	jsonObject.put("status", 0);
			jsonObject.put("info", response.encodeURL(new String("非法测试，我已经记录了你的踪迹,并保存了相关证据，请立即停止这种行为，不然后果自负".getBytes("UTF-8"),"ISO-8859-1")));
		}
		else
		{
			 String sql = "select expiration from iiplayer_vip where user_name='"+user_name +"' and user_id=" + uid;  
			 try  
		        {   
		            Statement stmt = cnn.createStatement();  
		            ResultSet rs = stmt.executeQuery(sql);  
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
		            if(rs.next())  
		            {  
		            	String date = sdf.format(new Date(Long.valueOf(rs.getString(1))*1000));
		            	
		            	if(new Date().getTime()>Long.valueOf(rs.getString(1))*1000)
		            	{
		            		jsonObject.put("isVIP", 0);
		            	}
		            	else
		            	{
		            		jsonObject.put("isVIP", 1);
		            	}
		            	
		            	jsonObject.put("expir", rs.getString(1));
			          	jsonObject.put("status", 1);
						jsonObject.put("info", response.encodeURL(new String("会员到期时间".getBytes("UTF-8"),"ISO-8859-1")+":"+date));
		            }
		            else
		            {
		            	long time = (long)1449849600*1000;
		            	String date = sdf.format(new Date(time));
		            	jsonObject.put("expir", 1449849600);
			          	jsonObject.put("status", 1);
			          	jsonObject.put("isVIP", 0);
						jsonObject.put("info", response.encodeURL(new String("会员到期时间".getBytes("UTF-8"),"ISO-8859-1")+":"+date));
		            }
		        }  
		        catch (SQLException e)  
		        {  
		          	jsonObject.put("status", 0);
					jsonObject.put("info", response.encodeURL(new String("查询错误".getBytes("UTF-8"),"ISO-8859-1")));
		            e.printStackTrace();  
		        } 
			 
			 //是否今天是否有这条记录，如果没有就插入
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			 String yisql = "select mac from iiplayer_login_log where user_name='"+user_name 
			 +"' and pc_android_ios='"+pc_android_ios+"' and login_time='"+df.format(new Date())+"'";
			 Statement stmt1;
			 int count = 0;
			try {
				stmt1 = cnn.createStatement();
				 ResultSet rs1 = stmt1.executeQuery(yisql); 
		         while(rs1.next())  
		         { 
		        	 if(count>1)
		        	 {
		        		 count=2;
		        		 break;
		        	 }
		        	 String logmac = rs1.getString(1);
		        	 if(!logmac.equals(mac))
		        	 {
		        		 //写入登录记录
							if(mac!=null)
							{
						      	String insertsql;
						      	insertsql = "insert into iiplayer_login_log(user_name,mac,pc_android_ios,login_time) value('";
						      	insertsql=insertsql +user_name + "','";
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
							}
		        	 }
		        	 count++;
		         }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	        
			 if(count>1)
			 {
				 //异地
				 bYidi = 1;
			 }
			 else if(count==0)
			 {
				 bYidi = 0;
				 //写入登录记录
					if(mac!=null)
					{
				      	String insertsql;
				      	insertsql = "insert into iiplayer_login_log(user_name,mac,pc_android_ios,login_time) value('";
				      	insertsql=insertsql +user_name + "','";
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
					}
			 }
			 else
			 {
				 bYidi = 0;
			 }
			 
		}
		

		jsonObject.put("one", 6.66);
		jsonObject.put("three",19.00);
		jsonObject.put("six",38.00);
		jsonObject.put("bPayEnable", 1);//是否允许支付，防止第三方支付方不稳定
		jsonObject.put("bweixin", 1);   //是否支持微信支付
		jsonObject.put("bZhifubao", 0); //是否支持支付宝支付
		                                
		jsonObject.put("bYidi", bYidi);//是否是异地登录
		out.println(jsonObject.toString());
		out.flush();
		out.close();
	}
}
