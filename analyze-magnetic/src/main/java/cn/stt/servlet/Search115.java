package cn.stt.servlet;

import cn.stt.utils.MysqlManager;
import net.sf.json.JSONArray;
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


public class Search115 extends HttpServlet {
    private Connection cnn;

    public Search115() {
        super();
        cnn = MysqlManager.getMysqlManager().getConn();//此处为通过自己写的方法getConn()获得连接
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        long starTime = System.currentTimeMillis();
        String page = request.getParameter("page");

        int count = 20;
        if (page == null) {
            page = "1";
            count = 50;
        }
        int iPage = Integer.valueOf(page);

        String name = request.getParameter("name");
        JSONObject jsonObject = new JSONObject();
        JSONArray listArray = new JSONArray();
        try {
            Statement stmt;
            stmt = cnn.createStatement();
            //分页查询
            String querysql = null;
            if (name != null && !name.isEmpty()) {
                name = new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
                querysql = "select chepai,name,picture_small,picture_big,115_name,code,type from iiplayer_115 where name like '%" + name + "%' limit " + count * (iPage - 1) + "," + count;
            } else {
                querysql = "select chepai,name,picture_small,picture_big,115_name,code,type from iiplayer_115 limit " + count * (iPage - 1) + "," + count;
            }

            ResultSet rs = stmt.executeQuery(querysql);
            while (rs.next()) {
                JSONObject avlist = new JSONObject();
                avlist.put("chepai", rs.getString(1));
                avlist.put("name", rs.getString(2));
                avlist.put("picture_small", rs.getString(3));
                avlist.put("picture_big", rs.getString(4));
                avlist.put("115_name", rs.getString(5));
                avlist.put("code", rs.getString(6));
                avlist.put("type", rs.getString(7));
                listArray.add(avlist);
            }

            long endTime2 = System.currentTimeMillis();
            long Time2 = endTime2 - starTime;
            jsonObject.put("time", Time2);

            jsonObject.put("data", listArray);
            jsonObject.put("status", 1);
            jsonObject.put("info", "");

        } catch (SQLException e1) {
            System.out.println("数据库查询异常！");
            e1.printStackTrace();
        }


        out.println(jsonObject.toString());
        out.flush();
        out.close();
    }


}
