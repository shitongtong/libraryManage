package cn.stt.servlet;

import cn.stt.utils.MysqlManager;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JX115 extends HttpServlet {
    private Connection cnn;

    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }


    public JX115() {
        super();
        cnn = MysqlManager.getMysqlManager().getConn();//此处为通过自己写的方法getConn()获得连接

        //登录115账号
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("utf-8");
        String pickcode = request.getParameter("pickcode");
        JSONObject jsonObject = new JSONObject();
        if (pickcode == null) {
            jsonObject.put("status", 1);
            jsonObject.put("url", "");
            jsonObject.put("info", "pickcode is emptry");

        } else {
            //查询是否播放地址过期，如果过期就从新请求，如果不过期直接返回
            String url = null;
            try {
                Statement stmt;
                stmt = cnn.createStatement();
                String querysql = "select url from iiplayer_115 where code='" + pickcode + "'";
                ResultSet rs = stmt.executeQuery(querysql);
                if (rs.next()) {
                    url = rs.getString(1);
                }

            } catch (SQLException e1) {
                System.out.println("数据库查询异常！");
                e1.printStackTrace();
            }


            if (url != null && !pickcode.isEmpty()) {
                //没过期
                jsonObject.put("status", 0);
                jsonObject.put("url", url);
                jsonObject.put("info", "success");
            } else {
                jsonObject.put("status", 0);
                jsonObject.put("url", url);
                jsonObject.put("info", "movie has not url now,try later");
            }


        }


        out.println(jsonObject.toString());
        out.flush();
        out.close();
    }


}
