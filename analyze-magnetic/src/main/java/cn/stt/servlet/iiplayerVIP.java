package cn.stt.servlet;

import cn.stt.utils.MysqlManager;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class iiplayerVIP extends HttpServlet {

    private Connection cnn;

    public iiplayerVIP() {
        super();
        cnn = MysqlManager.getMysqlManager().getConn();//此处为通过自己写的方法getConn()获得连接
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String user_name = request.getParameter("user_name");
        user_name = new String(user_name.getBytes("iso-8859-1"), "utf-8");
        String expiration = request.getParameter("expiration");
        String money = request.getParameter("money");
        String tongdao = "支付宝";
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        if (user_name == null || user_name.isEmpty() || expiration == null || expiration.isEmpty()
                || tongdao == null || tongdao.isEmpty() || money == null || money.isEmpty()) {
            jsonObject.put("status", 0);
            jsonObject.put("info", user_name + ":" + expiration + ":" + response.encodeURL(new String("非法测试，我已经记录了你的踪迹,并保存证据，请立马停止这种行为，不然后果自负".getBytes("UTF-8"), "ISO-8859-1")));
        } else {
            //根据用户名获取用户id
            String sql1 = "select * from pre_ucenter_members where username='" + user_name + "'";
            Statement stmt1;
            String uid = null;
            try {
                stmt1 = cnn.createStatement();
                ResultSet rs1 = stmt1.executeQuery(sql1);

                if (rs1.next()) {
                    uid = rs1.getString(1);
                }

            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            if (uid == null) {
                out.println("用户名:" + user_name + "不存在");
                out.flush();
                out.close();
                return;
            }

            int expir = Integer.valueOf(expiration);
            String sql = "select expiration from iiplayer_vip where user_name='" + user_name + "' and user_id=" + uid;
            try {
                Statement stmt = cnn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (rs.next()) {
                    //已经开通过，如果已经过期，那么从当前时间开始算起，如果没有过期，从过期那天加起
                    long time = 0;
                    if (new Date().getTime() / 1000 > Integer.valueOf(rs.getString(1))) {
                        time = Long.valueOf((long) expir * 2678400 * 1000) + new Date().getTime();
                    } else {
                        time = Long.valueOf((long) expir * 2678400 * 1000) + Long.valueOf((long) Integer.valueOf(rs.getString(1)) * 1000);
                    }

                    String insertsql;
                    insertsql = "update iiplayer_vip set expiration=";
                    insertsql = insertsql + time / 1000;
                    insertsql = insertsql + " where user_id=";
                    insertsql = insertsql + uid;
                    insertsql = insertsql + " and user_name='";
                    insertsql = insertsql + user_name;
                    insertsql = insertsql + "'";
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
                    jsonObject.put("info", response.encodeURL(new String("会员到期时间".getBytes("UTF-8"), "ISO-8859-1") + ":" + date));
                } else {
                    //第一次开通，插入数据库中
                    long time = new Date().getTime() + Long.valueOf((long) expir * 2678400 * 1000);
                    String insertsql;
                    insertsql = "insert into iiplayer_vip(user_id,user_name,expiration) value(";
                    insertsql = insertsql + uid + ",'";
                    insertsql = insertsql + user_name + "',";
                    insertsql = insertsql + time / 1000 + ")";
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
                    jsonObject.put("info", response.encodeURL(new String("会员到期时间".getBytes("UTF-8"), "ISO-8859-1") + ":" + date));
                }
            } catch (SQLException e) {
                jsonObject.put("status", 0);
                jsonObject.put("info", response.encodeURL(new String("充值错误".getBytes("UTF-8"), "ISO-8859-1")));
                e.printStackTrace();
            }


            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String time = df.format(new Date());

            //记入充值log
            String insertsql;
            insertsql = "insert into iiplayer_pay_log(user_id,user_name,pay_time,pay_qudao,pay_money,month) values(";
            insertsql = insertsql + uid + ",'";
            insertsql = insertsql + user_name + "','";
            insertsql = insertsql + time + "','";
            insertsql = insertsql + tongdao + "','";
            insertsql = insertsql + money + "','";
            insertsql = insertsql + expiration + "')";
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

}
