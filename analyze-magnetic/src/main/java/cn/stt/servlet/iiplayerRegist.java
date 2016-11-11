package cn.stt.servlet;

import cn.stt.utils.MysqlManager;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.sql.*;


public class iiplayerRegist extends HttpServlet {
    private Connection cnn;

    public iiplayerRegist() {
        super();
        cnn = MysqlManager.getMysqlManager().getConn();//此处为通过自己写的方法getConn()获得连接
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        String sql = "select salt,password,uid from pre_ucenter_members where username='" + name + "' and email='" + email + "'";
        try {
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                jsonObject.put("status", 1);
                //修改密码
                String salt = "6b5a2b";
                String temp = MD5(password);
                temp = temp.toLowerCase() + salt;
                String tempPassword = MD5(temp).toLowerCase();
                String insertsql;
                insertsql = "update pre_ucenter_members set password='" + tempPassword + "',salt='" + salt + "' where username='" + name + "'";

                PreparedStatement statement = cnn.prepareStatement(insertsql);
                statement.executeUpdate();
                jsonObject.put("info", response.encodeURL(new String("修改成功".getBytes("UTF-8"), "ISO-8859-1")));

            } else {
                jsonObject.put("status", 0);
                jsonObject.put("info", response.encodeURL(new String("用户名不存在或者邮箱不正确".getBytes("UTF-8"), "ISO-8859-1")));

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

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        String sql = "select salt,password,uid from pre_ucenter_members where username='" + name + "'";
        try {
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                jsonObject.put("status", 0);
                jsonObject.put("info", response.encodeURL(new String("用户名已经被注册".getBytes("UTF-8"), "ISO-8859-1")));

            } else {
                //写入数据库
                String salt = "6b5a2b";
                String temp = MD5(password);
                temp = temp.toLowerCase() + salt;
                String tempPassword = MD5(temp).toLowerCase();
                String insertsql;
                insertsql = "insert into pre_ucenter_members(username,password,salt,regip,regdate,email) value('";
                insertsql = insertsql + name + "','";
                insertsql = insertsql + tempPassword + "','";
                insertsql = insertsql + salt + "','";
                insertsql = insertsql + "122.96.41.21" + "',";
                insertsql = insertsql + "1458241173" + ",'";
                insertsql = insertsql + email + "')";
                try {
                    PreparedStatement statement = cnn.prepareStatement(insertsql);
                    statement.executeUpdate();
                    ResultSet rs1 = stmt.executeQuery(sql);
                    if (rs1.next()) {
                        jsonObject.put("uid", rs1.getString(3));
                    }

                    jsonObject.put("status", 1);
                    jsonObject.put("info", response.encodeURL(new String("注册成功".getBytes("UTF-8"), "ISO-8859-1")));

                } catch (SQLException e) {
                    jsonObject.put("status", 0);
                    jsonObject.put("info", response.encodeURL(new String("注册失败".getBytes("UTF-8"), "ISO-8859-1")));
                    System.out.println("插入数据库时出错：");
                    e.printStackTrace();
                }

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

    public final static String MD5(String s) {
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
}
