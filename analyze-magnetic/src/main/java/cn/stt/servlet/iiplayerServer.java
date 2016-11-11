package cn.stt.servlet;

import cn.stt.utils.HttpUtil;
import cn.stt.utils.MysqlManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class iiplayerServer extends HttpServlet {
    private Connection cnn;
    private String PHPSESSID;
    private String skey = "@7oRsN1Q65";
    private String g_tk = "919094206";
    private String uin = "o0742740116";//用户qq号 旋风会员账号2047331293密800444777
    //如何获取skey呢？
    //打开http://lixian.qq.com/main.html页面，抓取http://lixian.qq.com/handler/lixian/do_lixian_login.php发送时，带的请求cookie里就包含了，post的参数里有g_tk=20315768

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }


    //如何获取skey呢？
    //打开http://lixian.qq.com/main.html页面，抓取http://lixian.qq.com/handler/lixian/do_lixian_login.php发送时，带的请求cookie里就包含了，post的参数里有g_tk=20315768

    /**
     * Constructor of the object.
     */
    public iiplayerServer() {
        super();
        cnn = MysqlManager.getMysqlManager().getConn();//此处为通过自己写的方法getConn()获得连接
        //离线接口登录qq会员获取
        //	String xfjson2012 = sendloginPost("http://lixian.qq.com/handler/lixian/do_lixian_login.php","g_tk="+g_tk);

    }


    //获取文件大小
    long Getlens(String urlStr, String cookie) {
        try {
            long lenght = 0;
            URL mUrl = new URL(urlStr);
            HttpURLConnection.setFollowRedirects(true);
            HttpURLConnection conn = (HttpURLConnection) mUrl.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Accept-Encoding", "gzip,deflate");
            conn.setDoOutput(true);
            conn.setRequestProperty("Cookie", "FTN5K=" + cookie);
            conn.setRequestProperty("Accept-Language", "zh-cn");
            conn.setRequestProperty("User-Agent",
                    "Mozilla/4.0(compatible; MSIE 6.0; Windows NT 5.0; MyIE2; .NET CLR 1.1.4322)");
            conn.connect();
            int responseCode = conn.getResponseCode();
            // 判断请求是否成功处理
            if (responseCode == 200) {
                lenght = conn.getContentLength();
            }

            conn.disconnect();
            return lenght;
        } catch (IOException e) {

            System.out.println("发送GET请求出现异常！" + e + urlStr);
            e.printStackTrace();
        }

        return 0;
    }

    public String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * The doPost method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        String sql = "select salt,password,uid from pre_ucenter_members where username='" + name + "'";
        System.out.println(sql);
        try {
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String stmtstr = rs.getString(1);
                String md5password = rs.getString(2);
                String temp = MD5(password);
                temp = temp.toLowerCase() + stmtstr;
                String tempPassword = MD5(temp).toLowerCase();
                if (tempPassword.compareTo(md5password) == 0) {
                    //成功
                    jsonObject.put("status", 1);
                    jsonObject.put("info", response.encodeURL(new String("登录成功".getBytes("UTF-8"), "ISO-8859-1")));
                    String mac = request.getParameter("mac");
                    String pc_android_ios = request.getParameter("pc_android_ios");
                    jsonObject.put("uid", rs.getString(3));
                    //写入数据库
                    if (mac != null) {
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                        String insertsql;
                        insertsql = "insert into iiplayer_login_log(user_name,mac,pc_android_ios,login_time) value('";
                        insertsql = insertsql + name + "','";
                        insertsql = insertsql + mac + "','";
                        insertsql = insertsql + pc_android_ios + "','";
                        insertsql = insertsql + df.format(new Date()) + "')";
                        try {
                            PreparedStatement statement = cnn.prepareStatement(insertsql);
                            statement.executeUpdate();
                        } catch (SQLException e) {
                            System.out.println("插入数据库时出错：");
                            e.printStackTrace();
                        }
                    }

                } else {
                    jsonObject.put("status", 0);
                    jsonObject.put("info", response.encodeURL(new String("密码错误".getBytes("UTF-8"), "ISO-8859-1")));
                }
            } else {
                jsonObject.put("status", 0);
                jsonObject.put("info", response.encodeURL(new String("没有用户名".getBytes("UTF-8"), "ISO-8859-1")));
            }

        } catch (SQLException e) {
            jsonObject.put("status", 0);
            jsonObject.put("info", response.encodeURL(new String("服务器异常".getBytes("UTF-8"), "ISO-8859-1")));
            e.printStackTrace();
        }

        out.println(jsonObject.toString());
        out.flush();
        out.close();
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here
    }

    public String sendloginPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //conn.setRequestProperty("Content-Type", "*application/x-www-form-urlencoded");
            conn.setRequestProperty("Referer", "http://lixian.qq.com/main.html");
            //指明
            conn.setRequestProperty("Cookie", "uin=" + uin + "; skey=" + skey);
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
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

            int pos = result.indexOf("nick\":\"");
            if (pos != -1) {
                String name = result.substring(pos + 7, result.indexOf("\",\"used_space"));
                //获取PHPSESSID
                Map<String, List<String>> map = conn.getHeaderFields();
                List<String> list = map.get("Set-Cookie");
                PHPSESSID = list.get(0);
                PHPSESSID = PHPSESSID.substring(PHPSESSID.indexOf("D=") + 2, PHPSESSID.indexOf("; path"));
                System.out.println(name + ":登录成功,PHPSESSID=" + PHPSESSID);
            } else {
                System.out.println("登录失败，请重置服务器");
            }


        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    public String sendCookiePost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            //Content-Type: application/x-www-form-urlencoded
            //Cookie: uin=o0742740116; skey=@4Dy7bpPZm;PHPSESSID=e822351564aaecc1223bde0ab61fa0c1
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //conn.setRequestProperty("Content-Type", "*application/x-www-form-urlencoded");
            conn.setRequestProperty("Referer", "http://lixian.qq.com/main.html");
            conn.setRequestProperty("Cookie", "uin=" + uin + "; skey=@G6Pd5bFqp;PHPSESSID=" + PHPSESSID);
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
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String hash = request.getParameter("hash");
        String strindex = request.getParameter("index");
        Connection conn1 = null;
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbUrl = "jdbc:mysql://128.199.85.175:3306/ultrax?useUnicode=true&characterEncoding=utf-8";//根据实际情况变化
        dbUrl = "jdbc:mysql://127.0.0.1:3306/analyze_magnetic?useUnicode=true&characterEncoding=utf-8";
        String dbUser = "root";
        String dbPass = "root";
        try {
            Class.forName(dbDriver);
            try {
                conn1 = (Connection) DriverManager.getConnection(dbUrl, dbUser, dbPass);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }//注意是三个参数
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        for (int page = 1; page < 2; page++) {
            System.out.println("正在处理第" + page + "页");
            Statement stmt1;
            try {
                stmt1 = cnn.createStatement();
//                String querysql = "select hash,sha1 from magnetic limit " + (page - 1) * 10 + "," + 10;
                String querysql = "select hash,sha1 from magnetic limit 1" ;
                ResultSet rs = stmt1.executeQuery(querysql);
                int count = 1;
                while (rs.next()) {
                    System.out.println("正在处理第" + (count + (page - 1) * 10) + "记录");
                    String sha = rs.getString(2);
                    String torrent_hash = rs.getString(1);
                    //分差torrent_hash
                    String[] sourceStrArray = torrent_hash.split("/");
                    hash = sourceStrArray[0];
                    strindex = sourceStrArray[1];

                    int ciliindex = -1;
                    if (strindex != null) {
                        ciliindex = Integer.valueOf(strindex);
                    }

                    /*String sha1 = null;
                    String ftd = null;
                    try {
                        Statement stmt11;
                        stmt11 = conn1.createStatement();
                        String querysql1 = "select url,hash from magnetic where torrent_hash='" + hash + "' and torrent_index=" + strindex;
                        ResultSet rs1 = stmt11.executeQuery(querysql1);
                        if (rs1.next()) {
                            sha1 = rs1.getString(2);
                            ftd = rs1.getString(1);
                        }

                    } catch (SQLException e1) {
                        System.out.println("数据库查询异常！");
                        e1.printStackTrace();
                    }

                    count++;

                    if (sha1 != null) {
                        System.out.println("已经存在");
                        continue;
                    }*/


                    hash = hash.replace("magnet:?xt=urn:btih:", "");
                    if (hash != null) {
                        String url = "http://i.vod.xunlei.com/req_subBT/info_hash/" + hash + "/req_num/1000/req_offset/0";
                        String xunleiUrl = HttpUtil.doGet(url);
                        JSONObject magnetJson;
                        String record_num;
                        try {
                            magnetJson = JSONObject.fromObject(xunleiUrl).getJSONObject("resp");
                            record_num = magnetJson.getString("record_num");
                        } catch (JSONException e1) {
                            continue;
                        }


                        if (!record_num.isEmpty()) {
                            int num = Integer.valueOf(record_num);
                            if (num > 0) {
                                JSONArray listArray = new JSONArray();
                                JSONArray array = magnetJson.getJSONArray("subfile_list");
                                JSONObject avlist = new JSONObject();
                                for (int i = 0; i < num; i++) {
                                    JSONObject filelist = (JSONObject) array.get(i);
                                    int index = filelist.getInt("index");
                                    long file_size = filelist.getLong("file_size");
                                    if (ciliindex != -1) {
                                        if (index != ciliindex) {
                                            continue;
                                        }
                                    }
                                    String fileName = URLDecoder.decode(filelist.getString("name"), "UTF-8");
                                    fileName = response.encodeURL(new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
                                    String file_hash = sha;
                                    String getxuanfengurl = "http://fenxiang.qq.com/upload/index.php/share/handler_c/getComUrl";
                                    String getxuanfengParm = "";
                                    getxuanfengParm = getxuanfengParm + "filehash=" + file_hash + "&filename=m.mkv";
                                    String get_http_url = "";
                                    Map<String, Object> params = new HashMap<String, Object>();
                                    params.put("filehash", file_hash);
                                    params.put("filename", "m.mkv");
                                    get_http_url = HttpUtil.doPost(getxuanfengurl, params);

                                    String com_cookie = null;
                                    String patCookie = "\"com_cookie\":\"(.*?)\"";
                                    Pattern com_cookiepattern = Pattern.compile(patCookie);
                                    Matcher com_cookiematcher = com_cookiepattern.matcher(get_http_url);
                                    StringBuffer com_cookiebuffer = new StringBuffer();
                                    while (com_cookiematcher.find()) {
                                        com_cookiebuffer.append(com_cookiematcher.group(1));
                                        com_cookie = com_cookiebuffer.toString();
                                    }


                                    Pattern pattern = Pattern.compile("com_url\":\"(.*?)/m.mkv");
                                    Matcher matcher = pattern.matcher(get_http_url);
                                    StringBuffer buffer = new StringBuffer();
                                    String code = null;

                                    while (matcher.find()) {
                                        buffer.append(matcher.group(1));
                                        code = buffer.toString();
                                    }


                                    if (code == null || code.isEmpty()) {
                                        System.out.println(hash + "_" + index + "寻找真实地址出错" + get_http_url);
                                        continue;
                                    }

                                    if (com_cookie.compareTo("00000000") == 0) {
                                        System.out.println("FTN5K=00000000" + hash + "_" + index + get_http_url);
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
                                    long sizes = Getlens(code, com_cookie);
                                    if (sizes == 0) {
                                        System.out.println("警告错误：1" + hash + "_" + index + "_" + sizes + "_" + code);
                                        continue;
                                    }


                                    avlist.put("url", code);
                                    avlist.put("cookie", "FTN5K=" + com_cookie);
                                    avlist.put("name", fileName);
                                    listArray.add(avlist);

                                    try {
                                        int ins = code.indexOf("ftn_handler/");
                                        code = code.substring(0, ins);
                                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                                        String insertsql;
                                        insertsql = "insert into video_info(torrent_hash,torrent_index,hash,url,create_time,name,size) value('";
                                        insertsql = insertsql + hash + "',";
                                        insertsql = insertsql + index + ",'";
                                        insertsql = insertsql + file_hash + "','";
                                        insertsql = insertsql + code + "','";
//                                        insertsql = insertsql + df.format(new Date()) + "','";
                                        insertsql = insertsql + new Date().getTime() + "','";
                                        insertsql = insertsql + URLDecoder.decode(filelist.getString("name"), "UTF-8") + "','";
                                        insertsql = insertsql + file_size + "')";
                                        PreparedStatement statement = conn1.prepareStatement(insertsql);
                                        statement.executeUpdate();

                                        System.out.println("fileSize=="+file_size);
                                        System.out.println("sizes=="+sizes);
                                        System.out.println(df.format(new Date()) + "_" + "插入数据库" + hash + "_" + index);
                                    } catch (SQLException e) {
                                        System.out.println("插入数据库时出错：");
                                        e.printStackTrace();
                                    }

                                }

                            }

                        }
                    }
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}





