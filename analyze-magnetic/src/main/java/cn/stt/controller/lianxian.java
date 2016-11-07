package cn.stt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class lianxian {
	
	/* public void doGet(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
			 long starTime=System.currentTimeMillis();
			 request.setCharacterEncoding("utf-8");
			 String hash = request.getParameter("hash");
			 String strindex = request.getParameter("index");
			 int ciliindex = -1;
			 if(strindex!=null)
			 {
			 ciliindex = Integer.valueOf(strindex);
			 }
			 PrintWriter out = response.getWriter();
			 JSONObject jsonObject = new JSONObject();
			 hash = hash.replace("magnet:?xt=urn:btih:", "");
			 if(hash != null)
			 {
			 String url = "http://i.vod.xunlei.com/req_subBT/info_hash/" +hash + "/req_num/1000/req_offset/0";
			 String xunleiUrl = sendGet(url);
			 JSONObject magnetJson = JSONObject.fromObject(xunleiUrl).getJSONObject("resp");
			 String record_num = magnetJson.getString("record_num");
			 if(!record_num.isEmpty())
			 {
			 	int num = Integer.valueOf(record_num);
			 	if(num>0)
			 	{
			 		JSONArray listArray = new JSONArray();
			 		JSONArray array = magnetJson.getJSONArray("subfile_list");
			 		JSONObject avlist = new JSONObject();
			 		for(int i=0;i<num;i++)
			 		{	
			             JSONObject filelist = (JSONObject)array.get(i);
			             int index = filelist.getInt("index");
			             long file_size = filelist.getInt("file_size");
			             if(ciliindex!=-1)
			             {
			             	if(index!=ciliindex)
			             	{
			             		continue;
			             	}
			             }
			             String fileName =URLDecoder.decode(filelist.getString("name"),"UTF-8");
			             fileName= response.encodeURL(new String(fileName.getBytes("UTF-8"),"ISO-8859-1")); 
			             String sha = null;
			             String ftd = null;
			 			try {
			 				Statement stmt;
			 				stmt = cnn.createStatement();
			 	            String querysql = "select url,hash from iiplayer_sha where torrent_hash='" + hash + "' and torrent_index="+index;
			 	            ResultSet rs = stmt.executeQuery(querysql);  
			 	            if(rs.next())  
			 	            {   
			 	            	sha = rs.getString(2);
			 	            	ftd = rs.getString(1);
			 	            }
			 	            
			 			} catch (SQLException e1) {
			 				System.out.println("数据库查询异常！");
			 				e1.printStackTrace();
			 			}  
			 			
			 			String file_hash = null;
			             if(sha==null)
			             {
			             	//分享接口
			             	 String parm = "torrent_para={\"uin\":\"123456\",\"hash\":\"";
			 					 parm += hash;
			                  parm += "\",\"taskname\":\"M\",\"data\":[{\"index\":";
			                  parm += index;
			                  parm += ",\"filesize\":\"1\",\"filename\":\"M.mkv\"}]}";
			  				String xuanfengUrl="";
			  				int cishu = 0;
			  				while(true)
			  				{
			  					xuanfengUrl = sendPost("http://fenxiang.qq.com/upload/index.php/upload_c/checkExist",parm); 				
			  					if(xuanfengUrl.isEmpty())
			  					{
			  						System.out.println("第"+(cishu+1)+"次寻找"+ fileName+"出错");
			  						cishu++;
			  					}
			  					else
			  					{
			  						break;
			  					}
			  				}
			  				
			 					JSONObject xuanfengJson = JSONObject.fromObject(xuanfengUrl);
			 					JSONObject data = (JSONObject)(((JSONArray)xuanfengJson.get("data")).get(0));
			 				    file_hash = data.getString("file_hash");
			             	

			              	String xuanfengUrl="";
			    				int cishu = 0;
			  				while(true)
			  				{
			  					xuanfengUrl = sendGet("http://1.112233.sinaapp.com/jx.php?hash="+hash+"&index="+index); 				
			  					if(xuanfengUrl.isEmpty())
			  					{
			  						if(cishu>5)
			  						{
			  							break;
			  						}
			  						System.out.println("第"+(cishu+1)+"次寻找"+ fileName+"出错");
			  						cishu++;
			  					}
			  					else
			  					{
			  						break;
			  					}
			  				}
			             	
			             	
			             	JSONObject xuanfengJson = JSONObject.fromObject(xuanfengUrl);
			 					JSONObject data = (JSONObject)(((JSONArray)xuanfengJson.get("data")).get(0));
			 				    file_hash = data.getString("file_hash");
			 				    if(file_hash==null||file_hash.compareTo("0000000000000000000000000000000000000000")==0)
			 				{
			 					continue;
			 				}
			             	          	
			             	
			             	//离线接口
			             	//Referer: http://lixian.qq.com/main.html
			             	//Cookie: uin=o0742740116; skey=@4Dy7bpPZm;PHPSESSID=e822351564aaecc1223bde0ab61fa0c1
			             	//提交参数格式
			             		String param = "cmd=add_bt_task&hash="+hash
			             	+"&taskname=111" +"&index="+ index
			             	+"&filesize="+file_size
			                 +"&filename="+filelist.getString("name");
			             	String xfjson2012 = sendCookiePost("http://lixian.qq.com/handler/xfjson2012.php",param); //向旋风离线服务器提交下载任务
			             	int pos1 = xfjson2012.indexOf("mid");
			             	if(pos1==-1)
			             	{
			             		System.out.println("提交离线任务失败"+xfjson2012);
			             		continue;
			             	}

			             	//http://lixian.qq.com/handler/lixian/get_lixian_status.php太不稳定了
			             	//xuanfengUrl = sendCookiePost("http://lixian.qq.com/handler/lixian/get_lixian_status.php","");
			             	String xuanfengUrl="";
			             	xuanfengUrl = sendCookiePost("http://lixian.qq.com/handler/lixian/get_lixian_items.php","page=0&limit=200");
			             	if(xuanfengUrl.indexOf("data\":null")!=-1)
			             	{   
			             		//System.out.println("我去，空间啥也没有刷出来"+xuanfengUrl);
			             		continue;
			             	}
			             	JSONObject object = JSONObject.fromObject(xuanfengUrl);
			             	JSONArray data = object.getJSONArray("data");
			             	boolean bExist = false;
			             	String parmMid="mids=";
			             	for(int j=0;j<data.size();j++)
			             	{
			             		JSONObject file = (JSONObject)data.get(j);
			             		if(file.get("file_url").toString().compareTo(hash.toUpperCase()+"_"+index)==0)
			             		{
			             			file_hash = file.getString("hash");
			             			bExist = true;
			             		}

			             		if(j!=0)
			             		{
			             			parmMid = parmMid+","+file.getString("mid");
			             		}
			             		else
			             		{
			             			parmMid += file.getString("mid");
			             		}
			             	}
			             	
			             	if(!bExist)
			             	{
			                 //   System.out.println("我去，刚才提交成功任务没有刷出来，"+hash+"_"+index);
			             	}

			             	//取完后清空空间
			              	String delteString = sendCookiePost("http://lixian.qq.com/handler/lixian/del_lixian_task.php",parmMid);
			             	if(delteString.indexOf("mid")==-1)
			             	{
			             		//System.out.println("删除离线任务失败"+delteString);
			             	}
			 					if(file_hash==null||file_hash.compareTo("0000000000000000000000000000000000000000")==0)
			 					{
			 						//System.out.println("正常的么有sha1"+"0000000000000000000000000000000000000000");
			 						continue;
			 					}

			             }
			             else
			             {
			             	file_hash = sha;
			             }
			            
			 			String getxuanfengurl = "http://lixian.qq.com/handler/lixian/get_http_url.php";
			 			String getxuanfengParm = "";
			 			getxuanfengParm = getxuanfengParm+ "hash=" + file_hash +"&filename=blackcat";
			 			String get_http_url = sendPost(getxuanfengurl,getxuanfengParm);
			 			String com_cookie = null;
			 			String patCookie = "\"com_cookie\":\"(.*?)\"";
			 			Pattern com_cookiepattern = Pattern.compile(patCookie);
			 			Matcher com_cookiematcher = com_cookiepattern.matcher(get_http_url);
			 			StringBuffer com_cookiebuffer = new StringBuffer();
			 			while(com_cookiematcher.find()){              
			 				com_cookiebuffer.append(com_cookiematcher.group(1));        
			 			    com_cookie = com_cookiebuffer.toString();
			 			}
			 			
			 			if(sha==null)
			 			{
			 				Pattern pattern = Pattern.compile("com_url\":\"(.*?)/blackcat");
			 				Matcher matcher = pattern.matcher(get_http_url);
			 				StringBuffer buffer = new StringBuffer();
			 				String code=null;
			 				
			 				while(matcher.find()){              
			 				    buffer.append(matcher.group(1));        
			 				    code = buffer.toString();
			 				}
			 					
			 				
			 				if(code==null||code.isEmpty())
			 				{
			 					//System.out.println(hash+"_"+index+"寻找真实地址出错"+get_http_url);
			 					continue;
			 				}
			 				
			 				if(com_cookie.compareTo("00000000")==0)
			 				{
			 					System.out.println("FTN5K=00000000"+hash+"_"+index+get_http_url);
			 					continue;
			 				}
			 				

			 				code = code.replace("xflx.store.cd.qq.com:443", "xfcd.ctfs.ftn.qq.com");
			 				code = code.replace("xflxsrc.store.qq.com:443", "xfxa.ctfs.ftn.qq.com");
			 				code = code.replace("xflx.cd.ftn.qq.com:80", "cd.ctfs.ftn.qq.com");
			 				code = code.replace("xflx.store.sh.qq.com:443", "xfsh.ctfs.ftn.qq.com");
			 				code = code.replace("xflx.sh.ftn.qq.com:80", "sh.ctfs.ftn.qq.com");
			 				code = code.replace("xflx.xabtfs.ftn.qq.com:80", "xa.btfs.ftn.qq.com");
			 				code = code.replace("xflx.sz.ftn.qq.com:80", "sz.ctfs.ftn.qq.com");
			 				code = code.replace("xflx.hz.ftn.qq.com:80", "hz.ftn.qq.com");
			 				code = code.replace("xflx.tjctfs.ftn.qq.com:80", "tj.ctfs.ftn.qq.com");
			 				code = code.replace("xflx.shbtfs.ftn.qq.com:80", "sh.btfs.ftn.qq.com");
			 				code = code.replace("xflx.szmail.ftn.qq.com:80", "szmail.tfs.ftn.qq.com");
			 				code = code.replace("xflx.xa.ftn.qq.com:80", "xa.ctfs.ftn.qq.com");
			 				code = code.replace("xflx.xabtfs.ftn.qq.com:80", "xflx.xabtfs.ftn.qq.com");
			 				code = code.replace("xflx.cdbtfs.ftn.qq.com:80", "cd.btfs.ftn.qq.com");
			 				code = code.replace("xflx.szbtfs.ftn.qq.com:80", "sz.btfs.ftn.qq.com");
			 				code = code.replace("xflx.xatfs.ftn.qq.com:80", "xa.tfs.ftn.qq.com");
			 				code = code.replace("xflx.tjmail.ftn.qq.com:80", "tjmail.tfs.ftn.qq.com");	
			 				code = code.replace("xflx.tjbtfs.ftn.qq.com:80", "tj.btfs.ftn.qq.com");
			 				code = code.replace("182.131.9.221:80", "xfcd.ctfs.ftn.qq.com");	
			 				long sizes = Getlens (code, com_cookie);
			 				if(sizes==0)
			 				{
			 					System.out.println("警告错误："+hash+"_"+index+"_"+sizes+"_"+code);
			 					continue;
			 				}
			 				
			 				   
			 					avlist.put("url", code);
			 					avlist.put("cookie","FTN5K="+com_cookie);
			 				    avlist.put("name", fileName);
			 					listArray.add(avlist);
			 				
			 				 try {  
			 					    int ins = code.indexOf("ftn_handler/");
			 					    code = code.substring(0,ins);
			 						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			 		              	String insertsql;
			 		              	insertsql = "insert into iiplayer_sha(torrent_hash,torrent_index,hash,url,add_time,name,size) value('";
			  		              	insertsql=insertsql + hash + "',";
			  		              	insertsql=insertsql + index + ",'";
			  		              	insertsql=insertsql + file_hash + "','";
			  		              	insertsql=insertsql + code + "','";
			  		              	insertsql=insertsql+df.format(new Date())+ "','";
			  		              	insertsql=insertsql+URLDecoder.decode(filelist.getString("name"),"UTF-8") + "','";		
			  		              	insertsql=insertsql + file_size +"')";
			 		              	PreparedStatement statement = cnn.prepareStatement(insertsql);  
			 		                  statement.executeUpdate();  
			 		                  
			 		                System.out.println(df.format(new Date())+"_"+"插入数据库"+hash+"_"+index);  
			 		              } catch (SQLException e) {  
			 		                  System.out.println("插入数据库时出错：");  
			 		                  e.printStackTrace();  
			 		              }

			 				 continue;
			 			}
			 			else
			 			{
			 				Pattern pattern = Pattern.compile("ftn_handler/(.*?)/");
			 				Matcher matcher = pattern.matcher(get_http_url);
			 				StringBuffer buffer = new StringBuffer();
			 				String code=null;
			 				
			 				while(matcher.find()){              
			 				    buffer.append(matcher.group(1));        
			 				    code = buffer.toString();
			 				}
			 				avlist.put("url", ftd+"ftn_handler/"+code);
			 				avlist.put("cookie","FTN5K="+com_cookie);
			 				avlist.put("name", fileName);
			 				listArray.add(avlist);
			 				continue;
			 			}
			 			
			 		}
			 		
			 		if(listArray.isEmpty())
			 		{
			 			jsonObject.put("status", 0);
			 			jsonObject.put("data", "");
			 			jsonObject.put("info", "无可播磁力");
			 		}
			 		else
			 		{
			 			jsonObject.put("data", listArray);
			 			jsonObject.put("status", 1);
			 			jsonObject.put("info", "");
			 			//System.out.println(jsonObject.toString());
			 		}
			 	
			 	}
			 	else
			 	{
			 		jsonObject.put("status", 0);
			 		jsonObject.put("data", "");
			 		jsonObject.put("info", "无效磁力");
			 	}
			 }
			 else
			 {
			 	jsonObject.put("status", 0);
			 	jsonObject.put("data", "");
			 	jsonObject.put("info", "磁力格式不正确");
			 }

			 }
			 else
			 {
			 jsonObject.put("status", 0);
			 jsonObject.put("data", "");
			 jsonObject.put("info", "磁力格式不正确");
			 }

			 long endTime=System.currentTimeMillis();
			 long Time=endTime-starTime;
			 jsonObject.put("time", Time);
			 out.println(jsonObject.toString());
			 out.flush();
			 out.close();
			 }
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/* public void doGet(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
			 long starTime=System.currentTimeMillis();
			 request.setCharacterEncoding("utf-8");
			 String hash = request.getParameter("hash");
			 String strindex = request.getParameter("index");
			 int ciliindex = -1;
			 if(strindex!=null)
			 {
			 ciliindex = Integer.valueOf(strindex);
			 }
			 PrintWriter out = response.getWriter();
			 JSONObject jsonObject = new JSONObject();
			 hash = hash.replace("magnet:?xt=urn:btih:", "");
			 if(hash != null)
			 {
			 String url = "http://i.vod.xunlei.com/req_subBT/info_hash/" +hash + "/req_num/1000/req_offset/0";
			 String xunleiUrl = sendGet(url);
			 JSONObject magnetJson = JSONObject.fromObject(xunleiUrl).getJSONObject("resp");
			 String record_num = magnetJson.getString("record_num");
			 if(!record_num.isEmpty())
			 {
			 	int num = Integer.valueOf(record_num);
			 	if(num>0)
			 	{
			 		JSONArray listArray = new JSONArray();
			 		JSONArray array = magnetJson.getJSONArray("subfile_list");
			 		JSONObject avlist = new JSONObject();
			 		for(int i=0;i<num;i++)
			 		{	
			             JSONObject filelist = (JSONObject)array.get(i);
			             int index = filelist.getInt("index");
			             long file_size = filelist.getLong("file_size");
			             if(ciliindex!=-1)
			             {
			             	if(index!=ciliindex)
			             	{
			             		continue;
			             	}
			             }
			             String fileName =URLDecoder.decode(filelist.getString("name"),"UTF-8");
			             fileName= response.encodeURL(new String(fileName.getBytes("UTF-8"),"ISO-8859-1")); 
			             String sha = null;
			             String ftd = null;
			 			try {
			 				Statement stmt;
			 				stmt = cnn.createStatement();
			 	            String querysql = "select url,hash from iiplayer_sha where torrent_hash='" + hash + "' and torrent_index="+index;
			 	            ResultSet rs = stmt.executeQuery(querysql);  
			 	            if(rs.next())  
			 	            {   
			 	            	sha = rs.getString(2);
			 	            	ftd = rs.getString(1);
			 	            }
			 	            
			 			} catch (SQLException e1) {
			 				System.out.println("数据库查询异常！");
			 				e1.printStackTrace();
			 			}  
			 			
			 			String file_hash = null;
			             if(sha==null)
			             {
			             	//分享接口
			             	 String parm = "torrent_para={\"uin\":\"123456\",\"hash\":\"";
			 					 parm += hash;
			                  parm += "\",\"taskname\":\"M\",\"data\":[{\"index\":";
			                  parm += index;
			                  parm += ",\"filesize\":\"1\",\"filename\":\"M.mkv\"}]}";
			  				String xuanfengUrl="";
			  				int cishu = 0;
			  				while(true)
			  				{
			  					xuanfengUrl = sendPost("http://fenxiang.qq.com/upload/index.php/upload_c/checkExist",parm); 				
			  					if(xuanfengUrl.isEmpty())
			  					{
			  						System.out.println("第"+(cishu+1)+"次寻找"+ fileName+"出错");
			  						cishu++;
			  					}
			  					else
			  					{
			  						break;
			  					}
			  				}
			  				
			 					JSONObject xuanfengJson = JSONObject.fromObject(xuanfengUrl);
			 					JSONObject data = (JSONObject)(((JSONArray)xuanfengJson.get("data")).get(0));
			 				    file_hash = data.getString("file_hash");
			             	

			              	String xuanfengUrl="";
			    				int cishu = 0;
			  				while(true)
			  				{
			  					xuanfengUrl = sendGet("http://bd-dy.com/api/checkExist?info_hash="+hash+"&index="+index); 				
			  					if(xuanfengUrl.isEmpty())
			  					{
			  						if(cishu>5)
			  						{
			  							break;
			  						}
			  						//System.out.println("第"+(cishu+1)+"次寻找"+ fileName+"出错");
			  						cishu++;
			  					}
			  					else
			  					{
			  						break;
			  					}
			  				}
			             	
			             	
			             	JSONObject xuanfengJson = JSONObject.fromObject(xuanfengUrl);
			 					JSONObject data = (JSONObject)(((JSONArray)xuanfengJson.get("data")).get(0));
			 				    file_hash = data.getString("file_hash");
			 				    if(file_hash==null||file_hash.compareTo("0000000000000000000000000000000000000000")==0)
			 				{
			 					continue;
			 				}
			             	          	
			             	
			             	//离线接口
			             	//Referer: http://lixian.qq.com/main.html
			             	//Cookie: uin=o0742740116; skey=@4Dy7bpPZm;PHPSESSID=e822351564aaecc1223bde0ab61fa0c1
			             	//提交参数格式
			             		String param = "cmd=add_bt_task&hash="+hash
			             	+"&taskname=111" +"&index="+ index
			             	+"&filesize="+file_size
			                 +"&filename="+filelist.getString("name");
			             	String xfjson2012 = sendCookiePost("http://lixian.qq.com/handler/xfjson2012.php",param); //向旋风离线服务器提交下载任务
			             	int pos1 = xfjson2012.indexOf("mid");
			             	if(pos1==-1)
			             	{
			             		System.out.println("提交离线任务失败"+xfjson2012);
			             		continue;
			             	}

			             	//http://lixian.qq.com/handler/lixian/get_lixian_status.php太不稳定了
			             	//xuanfengUrl = sendCookiePost("http://lixian.qq.com/handler/lixian/get_lixian_status.php","");
			             	String xuanfengUrl="";
			             	xuanfengUrl = sendCookiePost("http://lixian.qq.com/handler/lixian/get_lixian_items.php","page=0&limit=200");
			             	if(xuanfengUrl.indexOf("data\":null")!=-1)
			             	{   
			             		//System.out.println("我去，空间啥也没有刷出来"+xuanfengUrl);
			             		continue;
			             	}
			             	JSONObject object = JSONObject.fromObject(xuanfengUrl);
			             	JSONArray data = object.getJSONArray("data");
			             	boolean bExist = false;
			             	String parmMid="mids=";
			             	for(int j=0;j<data.size();j++)
			             	{
			             		JSONObject file = (JSONObject)data.get(j);
			             		if(file.get("file_url").toString().compareTo(hash.toUpperCase()+"_"+index)==0)
			             		{
			             			file_hash = file.getString("hash");
			             			bExist = true;
			             		}

			             		if(j!=0)
			             		{
			             			parmMid = parmMid+","+file.getString("mid");
			             		}
			             		else
			             		{
			             			parmMid += file.getString("mid");
			             		}
			             	}
			             	
			             	if(!bExist)
			             	{
			                 //   System.out.println("我去，刚才提交成功任务没有刷出来，"+hash+"_"+index);
			             	}

			             	//取完后清空空间
			              	String delteString = sendCookiePost("http://lixian.qq.com/handler/lixian/del_lixian_task.php",parmMid);
			             	if(delteString.indexOf("mid")==-1)
			             	{
			             		//System.out.println("删除离线任务失败"+delteString);
			             	}
			 					if(file_hash==null||file_hash.compareTo("0000000000000000000000000000000000000000")==0)
			 					{
			 						//System.out.println("正常的么有sha1"+"0000000000000000000000000000000000000000");
			 						continue;
			 					}

			             }
			             else
			             {
			             	file_hash = sha;
			             }
			            
			 			String getxuanfengurl = "http://lixian.qq.com/handler/lixian/get_http_url.php";
			 			String getxuanfengParm = "";
			 			getxuanfengParm = getxuanfengParm+ "hash=" + file_hash +"&filename=blackcat";
			 			String get_http_url = sendPost(getxuanfengurl,getxuanfengParm);
			 			String com_cookie = null;
			 			String patCookie = "\"com_cookie\":\"(.*?)\"";
			 			Pattern com_cookiepattern = Pattern.compile(patCookie);
			 			Matcher com_cookiematcher = com_cookiepattern.matcher(get_http_url);
			 			StringBuffer com_cookiebuffer = new StringBuffer();
			 			while(com_cookiematcher.find()){              
			 				com_cookiebuffer.append(com_cookiematcher.group(1));        
			 			    com_cookie = com_cookiebuffer.toString();
			 			}
			 			
			 			if(sha==null)
			 			{
			 				Pattern pattern = Pattern.compile("com_url\":\"(.*?)/blackcat");
			 				Matcher matcher = pattern.matcher(get_http_url);
			 				StringBuffer buffer = new StringBuffer();
			 				String code=null;
			 				
			 				while(matcher.find()){              
			 				    buffer.append(matcher.group(1));        
			 				    code = buffer.toString();
			 				}
			 					
			 				
			 				if(code==null||code.isEmpty())
			 				{
			 					//System.out.println(hash+"_"+index+"寻找真实地址出错"+get_http_url);
			 					continue;
			 				}
			 				
			 				if(com_cookie.compareTo("00000000")==0)
			 				{
			 					//System.out.println("FTN5K=00000000"+hash+"_"+index+get_http_url);
			 					continue;
			 				}
			 				

			 				code = code.replace("xflx.store.cd.qq.com:443", "xfcd.ctfs.ftn.qq.com");
			 				code = code.replace("xflxsrc.store.qq.com:443", "xfxa.ctfs.ftn.qq.com");
			 				code = code.replace("xflx.cd.ftn.qq.com:80", "cd.ctfs.ftn.qq.com");
			 				code = code.replace("xflx.store.sh.qq.com:443", "xfsh.ctfs.ftn.qq.com");
			 				code = code.replace("xflx.sh.ftn.qq.com:80", "sh.ctfs.ftn.qq.com");
			 				code = code.replace("xflx.xabtfs.ftn.qq.com:80", "xa.btfs.ftn.qq.com");
			 				code = code.replace("xflx.sz.ftn.qq.com:80", "sz.ctfs.ftn.qq.com");
			 				code = code.replace("xflx.hz.ftn.qq.com:80", "hz.ftn.qq.com");
			 				code = code.replace("xflx.tjctfs.ftn.qq.com:80", "tj.ctfs.ftn.qq.com");
			 				code = code.replace("xflx.shbtfs.ftn.qq.com:80", "sh.btfs.ftn.qq.com");
			 				code = code.replace("xflx.szmail.ftn.qq.com:80", "szmail.tfs.ftn.qq.com");
			 				code = code.replace("xflx.xa.ftn.qq.com:80", "xa.ctfs.ftn.qq.com");
			 				code = code.replace("xflx.xabtfs.ftn.qq.com:80", "xflx.xabtfs.ftn.qq.com");
			 				code = code.replace("xflx.cdbtfs.ftn.qq.com:80", "cd.btfs.ftn.qq.com");
			 				code = code.replace("xflx.szbtfs.ftn.qq.com:80", "sz.btfs.ftn.qq.com");
			 				code = code.replace("xflx.xatfs.ftn.qq.com:80", "xa.tfs.ftn.qq.com");
			 				code = code.replace("xflx.tjmail.ftn.qq.com:80", "tjmail.tfs.ftn.qq.com");	
			 				code = code.replace("xflx.tjbtfs.ftn.qq.com:80", "tj.btfs.ftn.qq.com");
			 				code = code.replace("182.131.9.221:80", "xfcd.ctfs.ftn.qq.com");	
			 				long sizes = Getlens (code, com_cookie);
			 				if(sizes==0)
			 				{
			 					System.out.println("警告错误："+hash+"_"+index+"_"+sizes+"_"+code);
			 					continue;
			 				}
			 				
			 				   
			 					avlist.put("url", code);
			 					avlist.put("cookie","FTN5K="+com_cookie);
			 				    avlist.put("name", fileName);
			 					listArray.add(avlist);
			 				
			 				 try {  
			 					    int ins = code.indexOf("ftn_handler/");
			 					    code = code.substring(0,ins);
			 						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			 		              	String insertsql;
			 		              	insertsql = "insert into iiplayer_sha(torrent_hash,torrent_index,hash,url,add_time,name,size) value('";
			 		              	insertsql=insertsql + hash + "',";
			 		              	insertsql=insertsql + index + ",'";
			 		              	insertsql=insertsql + file_hash + "','";
			 		              	insertsql=insertsql + code + "','";
			 		              	insertsql=insertsql+df.format(new Date())+ "','";
			 		              	insertsql=insertsql+URLDecoder.decode(filelist.getString("name"),"UTF-8") + "','";		
			 		              	insertsql=insertsql + file_size +"')";
			 		              	PreparedStatement statement = cnn.prepareStatement(insertsql);  
			 		                  statement.executeUpdate();  
			 		                  
			 		                System.out.println(df.format(new Date())+"_"+"插入数据库"+hash+"_"+index);  
			 		              } catch (SQLException e) {  
			 		                  System.out.println("插入数据库时出错：");  
			 		                  e.printStackTrace();  
			 		              }

			 				 continue;
			 			}
			 			else
			 			{
			 				Pattern pattern = Pattern.compile("ftn_handler/(.*?)/");
			 				Matcher matcher = pattern.matcher(get_http_url);
			 				StringBuffer buffer = new StringBuffer();
			 				String code=null;
			 				
			 				while(matcher.find()){              
			 				    buffer.append(matcher.group(1));        
			 				    code = buffer.toString();
			 				}
			 				avlist.put("url", ftd+"ftn_handler/"+code);
			 				avlist.put("cookie","FTN5K="+com_cookie);
			 				avlist.put("name", fileName);
			 				listArray.add(avlist);
			 				continue;
			 			}
			 			
			 		}
			 		
			 		if(listArray.isEmpty())
			 		{
			 			jsonObject.put("status", 0);
			 			jsonObject.put("data", "");
			 			jsonObject.put("info", "无可播磁力");
			 		}
			 		else
			 		{
			 			jsonObject.put("data", listArray);
			 			jsonObject.put("status", 1);
			 			jsonObject.put("info", "");
			 			//System.out.println(jsonObject.toString());
			 		}
			 	
			 	}
			 	else
			 	{
			 		jsonObject.put("status", 0);
			 		jsonObject.put("data", "");
			 		jsonObject.put("info", "无效磁力");
			 	}
			 }
			 else
			 {
			 	jsonObject.put("status", 0);
			 	jsonObject.put("data", "");
			 	jsonObject.put("info", "磁力格式不正确");
			 }

			 }
			 else
			 {
			 jsonObject.put("status", 0);
			 jsonObject.put("data", "");
			 jsonObject.put("info", "磁力格式不正确");
			 }

			 long endTime=System.currentTimeMillis();
			 long Time=endTime-starTime;
			 jsonObject.put("time", Time);
			 out.println(jsonObject.toString());
			 out.flush();
			 out.close();
			 }*/
	
	
	
	/*void namesize() throws UnsupportedEncodingException
	{
		int record = 0;
		for(int i=0;i<7000;i++)
		{
			   String hash = null;
			   int ciliindex = -1;  
				try {
					Statement stmt;
					stmt = cnn.createStatement();
			        String querysql = "select torrent_hash,torrent_index from iiplayer_sha limit "+ i*100+","+100;
			        ResultSet rs = stmt.executeQuery(querysql);  
			        int k = 0;
			        while(rs.next())  
			        {   
			        	k++;
			        	hash = rs.getString(1);
			        	ciliindex = rs.getInt(2);
			        	//查询名称
			        	String url = "http://i.vod.xunlei.com/req_subBT/info_hash/" +hash + "/req_num/1000/req_offset/0";
			        	String xunleiUrl = sendGet(url);
			        	JSONObject magnetJson = JSONObject.fromObject(xunleiUrl).getJSONObject("resp");
			        	String record_num = magnetJson.getString("record_num");
			        	if(!record_num.isEmpty())
			        	{
			        		int num = Integer.valueOf(record_num);
			        		if(num>1000)
			        		{
			        			continue;
			        		}
			        		if(num>0)
			        		{
			        			JSONArray array = magnetJson.getJSONArray("subfile_list");
			        			boolean bFind = false;
			        			for(int j=0;j<num;j++)
			        			{	
			        	            JSONObject filelist = (JSONObject)array.get(j);
			        	            int index = filelist.getInt("index");
			        	            long file_size = filelist.getLong("file_size");
			        	            if(ciliindex!=-1)
			        	            {
			        	            	if(index!=ciliindex)
			        	            	{
			        	            		continue;
			        	            	}
			        	            }
			        	            
			        	            
			        	            bFind = true;
			        	            String fileName =URLDecoder.decode(filelist.getString("name"),"UTF-8");
			        	        	//修改时间和size，名字
			    		        	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			    		        	String modifySql = "Update iiplayer_sha set add_time='"+df.format(new Date())+"' , name=\""+ fileName+"\" , size='"+file_size+"' where torrent_hash='"+hash+"' and torrent_index="+index;
			    		        	PreparedStatement statement = cnn.prepareStatement(modifySql);  
					                  statement.executeUpdate(); 
					                  record++;
					               System.out.println("完成"+record+"记录"+k);
			        			}
			        			
			        			if(!bFind)
			        			{
			        				 System.out.println("数据异常，迅雷没有找到这个hash");
			        			}
			        			
			        		}
			        	}
			        	
			        }
	     
				} catch (SQLException e1) {
					System.out.println("数据库查询异常！");
					e1.printStackTrace();
				}  

				System.out.println("第"+i+"页");
		}
		
	 
	}*/
	
	
	/* void namesize() throws UnsupportedEncodingException
		{
			int record = 498600;
			for(int i=4986;i<5000;i++)
			{
				   String hash = null;
				   int ciliindex = -1;  
					try {
						Statement stmt;
						stmt = cnn.createStatement();
				        String querysql = "select torrent_hash,torrent_index from iiplayer_sha limit "+ i*100+","+100;
				        ResultSet rs = stmt.executeQuery(querysql);  
				        int k = 0;
				        while(rs.next())  
				        {   
				        	k++;
				        	hash = rs.getString(1);
				        	ciliindex = rs.getInt(2);
				        	//查询名称
				        	String url = "http://i.vod.xunlei.com/req_subBT/info_hash/" +hash + "/req_num/1000/req_offset/0";
				        	String xunleiUrl = sendGet(url);
				        	JSONObject magnetJson = JSONObject.fromObject(xunleiUrl).getJSONObject("resp");
				        	String record_num = magnetJson.getString("record_num");
				        	if(!record_num.isEmpty())
				        	{
				        		int num = Integer.valueOf(record_num);
				        		if(num>1000)
				        		{
				        			continue;
				        		}
				        		if(num>0)
				        		{
				        			JSONArray array = magnetJson.getJSONArray("subfile_list");
				        			boolean bFind = false;
				        			for(int j=0;j<num;j++)
				        			{	
				        	            JSONObject filelist = (JSONObject)array.get(j);
				        	            int index = filelist.getInt("index");
				        	            long file_size = filelist.getLong("file_size");
				        	            if(ciliindex!=-1)
				        	            {
				        	            	if(index!=ciliindex)
				        	            	{
				        	            		continue;
				        	            	}
				        	            }
				        	            
				        	            
				        	            bFind = true;
				        	            String fileName =URLDecoder.decode(filelist.getString("name"),"UTF-8");
				        	        	//修改时间和size，名字
				    		        	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				    		        	String modifySql = "Update iiplayer_sha set add_time='"+df.format(new Date())+"' , name=\""+ fileName+"\" , size='"+file_size+"' where torrent_hash='"+hash+"' and torrent_index="+index;
				    		        	PreparedStatement statement = cnn.prepareStatement(modifySql);  
						                  statement.executeUpdate(); 
						                  record++;
						               System.out.println("完成"+record+"记录"+k);
				        			}
				        			
				        			if(!bFind)
				        			{
				        				 System.out.println("数据异常，迅雷没有找到这个hash");
				        			}
				        			
				        		}
				        	}
				        	
				        }
		     
					} catch (SQLException e1) {
						System.out.println("数据库查询异常！");
						e1.printStackTrace();
					}  

					System.out.println("第"+i+"页");
			}
			
		 
		}*/
	
	
	/*public void jianrong(String hash,PrintWriter out, HttpServletResponse response)
			throws ServletException, IOException {
			long starTime=System.currentTimeMillis();
			JSONObject jsonObject = new JSONObject();
			hash = hash.replace("magnet:?xt=urn:btih:", "");
			if(hash != null)
			{
			String url = "http://i.vod.xunlei.com/req_subBT/info_hash/" +hash + "/req_num/1000/req_offset/0";
			String xunleiUrl = sendGet(url);
			JSONObject magnetJson = JSONObject.fromObject(xunleiUrl).getJSONObject("resp");
			String record_num = magnetJson.getString("record_num");
			if(!record_num.isEmpty())
			{
			int num = Integer.valueOf(record_num);
			if(num>0)
			{
				JSONArray listArray = new JSONArray();
				JSONArray array = magnetJson.getJSONArray("subfile_list");
				JSONObject avlist = new JSONObject();
				for(int i=0;i<num;i++)
				{	
			      JSONObject filelist = (JSONObject)array.get(i);
			      int index = filelist.getInt("index");
			      long file_size = filelist.getInt("file_size");
			      String fileName =URLDecoder.decode(filelist.getString("name"),"UTF-8");
			      fileName= response.encodeURL(new String(fileName.getBytes("UTF-8"),"ISO-8859-1")); 
			      String sha = null;
			      String ftd = null;
					 try {
						Statement stmt;
						stmt = cnn.createStatement();
			          String querysql = "select url,hash from iiplayer_sha where torrent_hash='" + hash + "' and torrent_index="+index;
			          ResultSet rs = stmt.executeQuery(querysql);  
			          if(rs.next())  
			          {   
			          	sha = rs.getString(2);
			          	ftd = rs.getString(1);
			          }
			          
					} catch (SQLException e1) {
						System.out.println("数据库查询异常！");
						e1.printStackTrace();
					}  
					
					String file_hash = null;
			      if(sha==null)
			      {
			      	//分享接口
			      	 String parm = "torrent_para={\"uin\":\"123456\",\"hash\":\"";
							 parm += hash;
			           parm += "\",\"taskname\":\"M\",\"data\":[{\"index\":";
			           parm += index;
			           parm += ",\"filesize\":\"1\",\"filename\":\"M.mkv\"}]}";
						String xuanfengUrl="";
						int cishu = 0;
						while(true)
						{
							xuanfengUrl = sendPost("http://fenxiang.qq.com/upload/index.php/upload_c/checkExist",parm); 				
							if(xuanfengUrl.isEmpty())
							{
								System.out.println("第"+(cishu+1)+"次寻找"+ fileName+"出错");
								cishu++;
							}
							else
							{
								break;
							}
						}
						
							JSONObject xuanfengJson = JSONObject.fromObject(xuanfengUrl);
							JSONObject data = (JSONObject)(((JSONArray)xuanfengJson.get("data")).get(0));
						    file_hash = data.getString("file_hash");
			      	

			       	String xuanfengUrl="";
							int cishu = 0;
						while(true)
						{
							xuanfengUrl = sendGet("http://bd-dy.com/api/checkExist?info_hash="+hash+"&index="+index); 				
							if(xuanfengUrl.isEmpty())
							{
								if(cishu>6)
								{
									break;
								}
								System.out.println("第"+(cishu+1)+"次寻找"+ fileName+"出错");
								cishu++;
							}
							else
							{
								break;
							}
						}
			      	
			      	
			      	JSONObject xuanfengJson = JSONObject.fromObject(xuanfengUrl);
							JSONObject data = (JSONObject)(((JSONArray)xuanfengJson.get("data")).get(0));
						    file_hash = data.getString("file_hash");
						    if(file_hash==null||file_hash.compareTo("0000000000000000000000000000000000000000")==0)
						{
							continue;
						}
			      	
			      	//luah 接口
			      	String xuanfengUrl="";
			      	xuanfengUrl = sendGet("http://lixian.ttzx.tv/info_hash/0A/4F/"+hash);
			      	JSONObject xuanfengJson = JSONObject.fromObject(xuanfengUrl);
			      	JSONArray array1 = (JSONArray)xuanfengJson.get("btinfo");
			      	for(int j=0;j<array1.size();j++)
			      	{
			      		JSONObject objecthash = (JSONObject)(array1.get(j));
			      		if(objecthash.getInt("index")==index)
			      		{
			      			file_hash = objecthash.getString("sha1");
			      			break;
			      		}
			      	}
			      	
			      	
			      	//离线接口
			      	//Referer: http://lixian.qq.com/main.html
			      	//Cookie: uin=o0742740116; skey=@4Dy7bpPZm;PHPSESSID=e822351564aaecc1223bde0ab61fa0c1
			      	//提交参数格式
			      		String param = "cmd=add_bt_task&hash="+hash
			      	+"&taskname=111" +"&index="+ index
			      	+"&filesize="+file_size
			          +"&filename="+filelist.getString("name");
			      	String xfjson2012 = sendCookiePost("http://lixian.qq.com/handler/xfjson2012.php",param); //向旋风离线服务器提交下载任务
			      	int pos1 = xfjson2012.indexOf("mid");
			      	if(pos1==-1)
			      	{
			      		System.out.println("提交离线任务失败"+xfjson2012);
			      		continue;
			      	}

			      	//http://lixian.qq.com/handler/lixian/get_lixian_status.php太不稳定了
			      	//xuanfengUrl = sendCookiePost("http://lixian.qq.com/handler/lixian/get_lixian_status.php","");
			      	String xuanfengUrl="";
			      	xuanfengUrl = sendCookiePost("http://lixian.qq.com/handler/lixian/get_lixian_items.php","page=0&limit=200");
			      	if(xuanfengUrl.indexOf("data\":null")!=-1)
			      	{   
			      		System.out.println("我去，空间啥也没有刷出来"+xuanfengUrl);
			      		continue;
			      	}
			      	JSONObject object = JSONObject.fromObject(xuanfengUrl);
			      	JSONArray data = object.getJSONArray("data");
			      	boolean bExist = false;
			      	String parmMid="mids=";
			      	for(int j=0;j<data.size();j++)
			      	{
			      		JSONObject file = (JSONObject)data.get(j);
			      		if(file.get("file_url").toString().compareTo(hash.toUpperCase()+"_"+index)==0)
			      		{
			      			file_hash = file.getString("hash");
			      			bExist = true;
			      		}

			      		if(j!=0)
			      		{
			      			parmMid = parmMid+","+file.getString("mid");
			      		}
			      		else
			      		{
			      			parmMid += file.getString("mid");
			      		}
			      	}
			      	
			      	if(!bExist)
			      	{
			             System.out.println("我去，刚才提交成功任务没有刷出来，第"+hash+"_"+index);
			      	}

			      	//取完后清空空间
			       	String delteString = sendCookiePost("http://lixian.qq.com/handler/lixian/del_lixian_task.php",parmMid);
			      	if(delteString.indexOf("mid")==-1)
			      	{
			      		System.out.println("删除离线任务失败"+delteString);
			      	}
							if(file_hash==null||file_hash.compareTo("0000000000000000000000000000000000000000")==0)
							{
								System.out.println("正常的么有sha1"+"0000000000000000000000000000000000000000");
								continue;
							}

			      }
			      else
			      {
			      	file_hash = sha;
			      }
			     
					String getxuanfengurl = "http://lixian.qq.com/handler/lixian/get_http_url.php";
					String getxuanfengParm = "";
					getxuanfengParm = getxuanfengParm+ "hash=" + file_hash +"&filename=blackcat";
					String get_http_url = sendPost(getxuanfengurl,getxuanfengParm);
					String com_cookie = null;
					String patCookie = "\"com_cookie\":\"(.*?)\"";
					Pattern com_cookiepattern = Pattern.compile(patCookie);
					Matcher com_cookiematcher = com_cookiepattern.matcher(get_http_url);
					StringBuffer com_cookiebuffer = new StringBuffer();
					while(com_cookiematcher.find()){              
						com_cookiebuffer.append(com_cookiematcher.group(1));        
					    com_cookie = com_cookiebuffer.toString();
					}
					
					if(sha==null)
					{
						Pattern pattern = Pattern.compile("com_url\":\"(.*?)/blackcat");
						Matcher matcher = pattern.matcher(get_http_url);
						StringBuffer buffer = new StringBuffer();
						String code=null;
						
						while(matcher.find()){              
						    buffer.append(matcher.group(1));        
						    code = buffer.toString();
						}
							
						
						if(code==null||code.isEmpty())
						{
							//System.out.println(hash+"_"+index+"寻找真实地址出错"+get_http_url);
							continue;
						}
						
						if(com_cookie.compareTo("00000000")==0)
						{
							System.out.println("FTN5K=00000000"+hash+"_"+index+get_http_url);
							continue;
						}
						

						code = code.replace("xflx.store.cd.qq.com:443", "xfcd.ctfs.ftn.qq.com");
						code = code.replace("xflxsrc.store.qq.com:443", "xfxa.ctfs.ftn.qq.com");
						code = code.replace("xflx.cd.ftn.qq.com:80", "cd.ctfs.ftn.qq.com");
						code = code.replace("xflx.store.sh.qq.com:443", "xfsh.ctfs.ftn.qq.com");
						code = code.replace("xflx.sh.ftn.qq.com:80", "sh.ctfs.ftn.qq.com");
						code = code.replace("xflx.xabtfs.ftn.qq.com:80", "xa.btfs.ftn.qq.com");
						code = code.replace("xflx.sz.ftn.qq.com:80", "sz.ctfs.ftn.qq.com");
						code = code.replace("xflx.hz.ftn.qq.com:80", "hz.ftn.qq.com");
						code = code.replace("xflx.tjctfs.ftn.qq.com:80", "tj.ctfs.ftn.qq.com");
						code = code.replace("xflx.shbtfs.ftn.qq.com:80", "sh.btfs.ftn.qq.com");
						code = code.replace("xflx.szmail.ftn.qq.com:80", "szmail.tfs.ftn.qq.com");
						code = code.replace("xflx.xa.ftn.qq.com:80", "xa.ctfs.ftn.qq.com");
						code = code.replace("xflx.xabtfs.ftn.qq.com:80", "xflx.xabtfs.ftn.qq.com");
						code = code.replace("xflx.cdbtfs.ftn.qq.com:80", "cd.btfs.ftn.qq.com");
						code = code.replace("xflx.szbtfs.ftn.qq.com:80", "sz.btfs.ftn.qq.com");
						code = code.replace("xflx.xatfs.ftn.qq.com:80", "xa.tfs.ftn.qq.com");
						code = code.replace("xflx.tjmail.ftn.qq.com:80", "tjmail.tfs.ftn.qq.com");	
						code = code.replace("xflx.tjbtfs.ftn.qq.com:80", "tj.btfs.ftn.qq.com");
						code = code.replace("182.131.9.221:80", "xfcd.ctfs.ftn.qq.com");	
						long sizes = Getlens (code, com_cookie);
						if(sizes==0)
						{
							System.out.println("警告错误："+hash+"_"+index+"_"+sizes+"_"+code);
							continue;
						}
						
						   
							avlist.put("url", code);
							avlist.put("cookie","FTN5K="+com_cookie);
						    avlist.put("name", fileName);
							listArray.add(avlist);
						
						 try {  
							    int ins = code.indexOf("ftn_handler/");
							    code = code.substring(0,ins);
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				              	String insertsql;
				              	insertsql = "insert into iiplayer_sha(torrent_hash,torrent_index,hash,url,add_time) value('";
				              	insertsql=insertsql + hash + "',";
				              	insertsql=insertsql + index + ",'";
				              	insertsql=insertsql + file_hash + "','";
				              	insertsql=insertsql + code + "','";
				              	insertsql=insertsql+df.format(new Date())+"')";
				              	PreparedStatement statement = cnn.prepareStatement(insertsql);  
				                  statement.executeUpdate();  
				                  
				                System.out.println(df.format(new Date())+"_"+"插入数据库"+hash+"_"+index);  
				              } catch (SQLException e) {  
				                  System.out.println("插入数据库时出错：");  
				                  e.printStackTrace();  
				              }

						 continue;
					}
					else
					{
						Pattern pattern = Pattern.compile("ftn_handler/(.*?)/");
						Matcher matcher = pattern.matcher(get_http_url);
						StringBuffer buffer = new StringBuffer();
						String code=null;
						
						while(matcher.find()){              
						    buffer.append(matcher.group(1));        
						    code = buffer.toString();
						}
						avlist.put("url", ftd+"ftn_handler/"+code);
						avlist.put("cookie","FTN5K="+com_cookie);
						avlist.put("name", fileName);
						listArray.add(avlist);
						continue;
					}
					
				}
				
				if(listArray.isEmpty())
				{
					jsonObject.put("status", 0);
					jsonObject.put("data", "");
					jsonObject.put("info", "无可播磁力");
				}
				else
				{
					jsonObject.put("data", listArray);
					jsonObject.put("status", 1);
					jsonObject.put("info", "");
					//System.out.println(jsonObject.toString());
				}

			}
			else
			{
				jsonObject.put("status", 0);
				jsonObject.put("data", "");
				jsonObject.put("info", "无效磁力");
			}
			}
			else
			{
			jsonObject.put("status", 0);
			jsonObject.put("data", "");
			jsonObject.put("info", "磁力格式不正确");
			}

			}
			else
			{
			jsonObject.put("status", 0);
			jsonObject.put("data", "");
			jsonObject.put("info", "磁力格式不正确");
			}

			long endTime=System.currentTimeMillis();
			long Time=endTime-starTime;
			jsonObject.put("time", Time);
			out.println(jsonObject.toString());
			out.flush();
			out.close();
			}
*/
	
	
	
	//采集接口
	
	/*public void doGet(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
			 long starTime=System.currentTimeMillis();
			 request.setCharacterEncoding("utf-8");
			 String hash = request.getParameter("hash");
			 String strindex = request.getParameter("index");
			 int ciliindex = -1;
			 if(strindex!=null)
			 {
			 ciliindex = Integer.valueOf(strindex);
			 }
			 PrintWriter out = response.getWriter();
			 JSONObject jsonObject = new JSONObject();
			 hash = hash.replace("magnet:?xt=urn:btih:", "");
			 if(hash != null)
			 {
			 String url = "http://i.vod.xunlei.com/req_subBT/info_hash/" +hash + "/req_num/1000/req_offset/0";
			 String xunleiUrl = sendGet(url);
			 JSONObject magnetJson = JSONObject.fromObject(xunleiUrl).getJSONObject("resp");
			 String record_num = magnetJson.getString("record_num");
			 if(!record_num.isEmpty())
			 {
			 	int num = Integer.valueOf(record_num);
			 	if(num>0)
			 	{
			 		JSONArray listArray = new JSONArray();
			 		JSONArray array = magnetJson.getJSONArray("subfile_list");
			 		JSONObject avlist = new JSONObject();
			 		for(int i=0;i<num;i++)
			 		{	
			             JSONObject filelist = (JSONObject)array.get(i);
			             int index = filelist.getInt("index");
			             long file_size = filelist.getLong("file_size");
			             if(ciliindex!=-1)
			             {
			             	if(index!=ciliindex)
			             	{
			             		continue;
			             	}
			             }
			             String fileName =URLDecoder.decode(filelist.getString("name"),"UTF-8");
			             fileName= response.encodeURL(new String(fileName.getBytes("UTF-8"),"ISO-8859-1")); 
			             String sha = null;
			             String ftd = null;
			 			try {
			 				Statement stmt;
			 				stmt = cnn.createStatement();
			 	            String querysql = "select url,hash from iiplayer_sha where torrent_hash='" + hash + "' and torrent_index="+index;
			 	            ResultSet rs = stmt.executeQuery(querysql);  
			 	            if(rs.next())  
			 	            {   
			 	            	sha = rs.getString(2);
			 	            	ftd = rs.getString(1);
			 	            }
			 	            
			 			} catch (SQLException e1) {
			 				System.out.println("数据库查询异常！");
			 				e1.printStackTrace();
			 			}  
			 			
			 			String file_hash = null;
			             if(sha==null)
			             {     
			            		String xuanfengUrl="";
			            		xuanfengUrl = sendGet("http://bt.ttzx.tv/info_hash/f7/2b/"+hash);
			            		JSONObject xuanfengUrljsonObject = JSONObject.fromObject(xuanfengUrl);
			            		JSONArray xuanfengUrljsonArray = xuanfengUrljsonObject.getJSONArray("btinfo");
			            		for(int j = 0;j<xuanfengUrljsonArray.size();j++)
			            		{
			            			JSONObject fileobject = xuanfengUrljsonArray.getJSONObject(i);
			            			 int fileindex = fileobject.getInt("index");
			    		             if(ciliindex!=-1)
			    		             {
			    		             	if(fileindex!=ciliindex)
			    		             	{
			    		             		continue;
			    		             	}
			    		             	
			    		             	file_hash = fileobject.getString("sha1");
			    		             }
		
			            		}
			            		if(file_hash==null)
			            		{
			            			continue;
			            		}

			             }
			             else
			             {
			             	file_hash = sha;
			             }
			            
			 			String getxuanfengurl = "http://lixian.qq.com/handler/lixian/get_http_url.php";
			 			String getxuanfengParm = "";
			 			getxuanfengParm = getxuanfengParm+ "hash=" + file_hash +"&filename=blackcat";
			 			String get_http_url = sendPost(getxuanfengurl,getxuanfengParm);
			 			String com_cookie = null;
			 			String patCookie = "\"com_cookie\":\"(.*?)\"";
			 			Pattern com_cookiepattern = Pattern.compile(patCookie);
			 			Matcher com_cookiematcher = com_cookiepattern.matcher(get_http_url);
			 			StringBuffer com_cookiebuffer = new StringBuffer();
			 			while(com_cookiematcher.find()){              
			 				com_cookiebuffer.append(com_cookiematcher.group(1));        
			 			    com_cookie = com_cookiebuffer.toString();
			 			}
			 			
			 			if(sha==null)
			 			{
			 				Pattern pattern = Pattern.compile("com_url\":\"(.*?)/blackcat");
			 				Matcher matcher = pattern.matcher(get_http_url);
			 				StringBuffer buffer = new StringBuffer();
			 				String code=null;
			 				
			 				while(matcher.find()){              
			 				    buffer.append(matcher.group(1));        
			 				    code = buffer.toString();
			 				}
			 					
			 				
			 				if(code==null||code.isEmpty())
			 				{
			 				//	System.out.println(hash+"_"+index+"寻找真实地址出错"+get_http_url);
			 					continue;
			 				}
			 				
			 				if(com_cookie.compareTo("00000000")==0)
			 				{
			 					//System.out.println("FTN5K=00000000"+hash+"_"+index+get_http_url);
			 					continue;
			 				}
			 				

			 				code = code.replace("xflx.store.cd.qq.com:443", "xfcd.ctfs.ftn.qq.com");
			 				code = code.replace("xflxsrc.store.qq.com:443", "xfxa.ctfs.ftn.qq.com");
			 				code = code.replace("xflx.cd.ftn.qq.com:80", "cd.ctfs.ftn.qq.com");
			 				code = code.replace("xflx.store.sh.qq.com:443", "xfsh.ctfs.ftn.qq.com");
			 				code = code.replace("xflx.sh.ftn.qq.com:80", "sh.ctfs.ftn.qq.com");
			 				code = code.replace("xflx.xabtfs.ftn.qq.com:80", "xa.btfs.ftn.qq.com");
			 				code = code.replace("xflx.sz.ftn.qq.com:80", "sz.ctfs.ftn.qq.com");
			 				code = code.replace("xflx.hz.ftn.qq.com:80", "hz.ftn.qq.com");
			 				code = code.replace("xflx.tjctfs.ftn.qq.com:80", "tj.ctfs.ftn.qq.com");
			 				code = code.replace("xflx.shbtfs.ftn.qq.com:80", "sh.btfs.ftn.qq.com");
			 				code = code.replace("xflx.szmail.ftn.qq.com:80", "szmail.tfs.ftn.qq.com");
			 				code = code.replace("xflx.xa.ftn.qq.com:80", "xa.ctfs.ftn.qq.com");
			 				code = code.replace("xflx.xabtfs.ftn.qq.com:80", "xflx.xabtfs.ftn.qq.com");
			 				code = code.replace("xflx.cdbtfs.ftn.qq.com:80", "cd.btfs.ftn.qq.com");
			 				code = code.replace("xflx.szbtfs.ftn.qq.com:80", "sz.btfs.ftn.qq.com");
			 				code = code.replace("xflx.xatfs.ftn.qq.com:80", "xa.tfs.ftn.qq.com");
			 				code = code.replace("xflx.tjmail.ftn.qq.com:80", "tjmail.tfs.ftn.qq.com");	
			 				code = code.replace("xflx.tjbtfs.ftn.qq.com:80", "tj.btfs.ftn.qq.com");
			 				code = code.replace("182.131.9.221:80", "xfcd.ctfs.ftn.qq.com");	
			 				long sizes = Getlens (code, com_cookie);
			 				if(sizes==0)
			 				{
			 					System.out.println("警告错误："+hash+"_"+index+"_"+sizes+"_"+code);
			 					continue;
			 				}
			 				
			 				   
			 					avlist.put("url", code);
			 					avlist.put("cookie","FTN5K="+com_cookie);
			 				    avlist.put("name", fileName);
			 					listArray.add(avlist);
			 				
			 				 try {  
			 					    int ins = code.indexOf("ftn_handler/");
			 					    code = code.substring(0,ins);
			 						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			 		              	String insertsql;
			 		              	insertsql = "insert into iiplayer_sha(torrent_hash,torrent_index,hash,url,add_time,name,size) value('";
			 		              	insertsql=insertsql + hash + "',";
			 		              	insertsql=insertsql + index + ",'";
			 		              	insertsql=insertsql + file_hash + "','";
			 		              	insertsql=insertsql + code + "','";
			 		              	insertsql=insertsql+df.format(new Date())+ "','";
			 		              	insertsql=insertsql+URLDecoder.decode(filelist.getString("name"),"UTF-8") + "','";		
			 		              	insertsql=insertsql + file_size +"')";
			 		              	PreparedStatement statement = cnn.prepareStatement(insertsql);  
			 		                  statement.executeUpdate();  
			 		                  
			 		                System.out.println(df.format(new Date())+"_"+"插入数据库"+hash+"_"+index);  
			 		              } catch (SQLException e) {  
			 		                  System.out.println("插入数据库时出错：");  
			 		                  e.printStackTrace();  
			 		              }

			 				 continue;
			 			}
			 			else
			 			{
			 				Pattern pattern = Pattern.compile("ftn_handler/(.*?)/");
			 				Matcher matcher = pattern.matcher(get_http_url);
			 				StringBuffer buffer = new StringBuffer();
			 				String code=null;
			 				
			 				while(matcher.find()){              
			 				    buffer.append(matcher.group(1));        
			 				    code = buffer.toString();
			 				}
			 				avlist.put("url", ftd+"ftn_handler/"+code);
			 				avlist.put("cookie","FTN5K="+com_cookie);
			 				avlist.put("name", fileName);
			 				listArray.add(avlist);
			 				continue;
			 			}
			 			
			 		}
			 		
			 		if(listArray.isEmpty())
			 		{
			 			jsonObject.put("status", 0);
			 			jsonObject.put("data", "");
			 			jsonObject.put("info", "无可播磁力");
			 		}
			 		else
			 		{
			 			jsonObject.put("data", listArray);
			 			jsonObject.put("status", 1);
			 			jsonObject.put("info", "");
			 			//System.out.println(jsonObject.toString());
			 		}
			 	
			 	}
			 	else
			 	{
			 		jsonObject.put("status", 0);
			 		jsonObject.put("data", "");
			 		jsonObject.put("info", "无效磁力");
			 	}
			 }
			 else
			 {
			 	jsonObject.put("status", 0);
			 	jsonObject.put("data", "");
			 	jsonObject.put("info", "磁力格式不正确");
			 }

			 }
			 else
			 {
			 jsonObject.put("status", 0);
			 jsonObject.put("data", "");
			 jsonObject.put("info", "磁力格式不正确");
			 }

			 long endTime=System.currentTimeMillis();
			 long Time=endTime-starTime;
			 jsonObject.put("time", Time);
			 out.println(jsonObject.toString());
			 out.flush();
			 out.close();
	 }*/
	
	
/*    if(sha==null)
    {     
   		String xuanfengUrl="";
   		xuanfengUrl = sendGet("http://jx.taoka123.com:88/842606559.php?hash="+hash+"&index="+index);
   		if(xuanfengUrl.length()>40)
   		{
   			file_hash = xuanfengUrl.substring(xuanfengUrl.indexOf(":")+3,xuanfengUrl.indexOf(":")+3+40);
   		}
   		
   	
   		if(file_hash==null)
   		{
   			continue;
   		}

    }
    else
    {
    	file_hash = sha;
    }*/
	
	/*  if(sha==null)
      {
        	//离线接口
        	//Referer: http://lixian.qq.com/main.html
        	//Cookie: uin=o0742740116; skey=@4Dy7bpPZm;PHPSESSID=e822351564aaecc1223bde0ab61fa0c1
        	//提交参数格式
        		String param = "cmd=add_bt_task&hash="+hash
        	+"&taskname=111" +"&index="+ index
        	+"&filesize="+file_size
            +"&filename="+filelist.getString("name");
        	String xfjson2012 = sendCookiePost("http://lixian.qq.com/handler/xfjson2012.php",param); //向旋风离线服务器提交下载任务
        	int pos1 = xfjson2012.indexOf("mid");
        	if(pos1==-1)
        	{
        		System.out.println("提交离线任务失败"+xfjson2012);
        		continue;
        	}

        	//http://lixian.qq.com/handler/lixian/get_lixian_status.php太不稳定了
        	//xuanfengUrl = sendCookiePost("http://lixian.qq.com/handler/lixian/get_lixian_status.php","");
        	String xuanfengUrl="";
        	xuanfengUrl = sendCookiePost("http://lixian.qq.com/handler/lixian/get_lixian_items.php","page=0&limit=200");
        	if(xuanfengUrl.indexOf("data\":null")!=-1)
        	{   
        		//System.out.println("我去，空间啥也没有刷出来"+xuanfengUrl);
        		continue;
        	}
        	JSONObject object = JSONObject.fromObject(xuanfengUrl);
        	JSONArray data = object.getJSONArray("data");
        	boolean bExist = false;
        	String parmMid="mids=";
        	for(int j=0;j<data.size();j++)
        	{
        		JSONObject file = (JSONObject)data.get(j);
        		if(file.get("file_url").toString().compareTo(hash.toUpperCase()+"_"+index)==0)
        		{
        			file_hash = file.getString("hash");
        			bExist = true;
        		}

        		if(j!=0)
        		{
        			parmMid = parmMid+","+file.getString("mid");
        		}
        		else
        		{
        			parmMid += file.getString("mid");
        		}
        	}
        	
        	if(!bExist)
        	{
               System.out.println("我去，刚才提交成功任务没有刷出来，"+hash+"_"+index);
        	}


				if(file_hash==null||file_hash.compareTo("0000000000000000000000000000000000000000")==0)
				{
					//System.out.println("正常的么有sha1"+"0000000000000000000000000000000000000000");
					continue;
				}

        }*/
	
	
	/* if(sha==null)
	    {     
	   		String xuanfengUrl="";
	   		xuanfengUrl = sendGet("http://yayabt.com/xf.php?hash="+hash);
	   	    JSONObject strjosn= JSONObject.fromObject(xuanfengUrl);
	 		JSONArray btArray = strjosn.getJSONArray("btinfo");
	 		for(int k=0;k<btArray.size();k++)
	 		{
	 			int indexs = ((JSONObject)btArray.get(i)).getInt("index");
	 			if(indexs==index)
	 			{
	 				file_hash = ((JSONObject)btArray.get(i)).getString("sha1");
	 			}
	 		}
	   		
	   	
	   		if(file_hash==null)
	   		{
	   			continue;
	   		}

	    }*/
	
	
	/*if(sha==null)
     {
		String xuanfengUrl="";
  		xuanfengUrl = sendGet("http://dpj168.com/jk/xf2.php?hash="+hash);
  		int pos = xuanfengUrl.indexOf("hash");
    	xuanfengUrl = "{\""+xuanfengUrl.substring(pos);
  		System.out.println(xuanfengUrl);
  	    JSONObject strjosn= JSONObject.fromObject(xuanfengUrl);
		JSONArray btArray = strjosn.getJSONArray("btinfo");
		for(int k=0;k<btArray.size();k++)
		{
			int indexs = ((JSONObject)btArray.get(i)).getInt("index");
			if(indexs==index)
			{
				file_hash = ((JSONObject)btArray.get(i)).getString("sha1");
			}
		}
  		
  	
  		if(file_hash==null)
  		{
  			continue;
  		}

       }*/
}
