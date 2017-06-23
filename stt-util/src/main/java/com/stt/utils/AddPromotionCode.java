package com.stt.utils;

import com.stt.util2.DBPool;
import com.stt.util2.UUIDUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/6/15.
 */
public class AddPromotionCode {

    static String sql = "insert into promotion_code(uuid,course_price_uuid,promotion_code,promotion_name,promotion_type,promotion_money) values(?,?,?,?,?,?) ";

    public static void main(String[] args) throws SQLException {
        Connection conn = DBPool.getInstance().getDruidConnection();
        add00(conn);
        add0(conn);
        add1(conn);
        add2(conn);
        add3(conn);
        add4(conn);
        add5(conn);
        add6(conn);
        add7(conn);
        add8(conn);
        add9(conn);
        add10(conn);
        add11(conn);
        add12(conn);
        add13(conn);
        add20(conn);
        add21(conn);
        add22(conn);
        add23(conn);
        add24(conn);
        add25(conn);
        add26(conn);
        add27(conn);
        add28(conn);
        add29(conn);
        add30(conn);
        add31(conn);
        add32(conn);
        add33(conn);
        add34(conn);
    }

    private static void add00(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='小学' and cp.activity_type='常规' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{777d,1577d,2177d,2777d,4577d,5777d,8177d,10807d,12168d,16128d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "0618");
        preparedStatement.setString(4, "奖学金+父亲节");
        preparedStatement.setInt(5, 5);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, moneyList.get(i));
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add0(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='小学' and cp.activity_type='常规' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{777d,1577d,2177d,2777d,4577d,5777d,8177d,10848d,12168d,16128d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "5288");
        preparedStatement.setString(4, "父亲节");
        preparedStatement.setInt(5, 6);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, 288);
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add1(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='初一' and cp.activity_type='常规' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{777d,1577d,2177d,2777d,4577d,5777d,8177d,10848d,12168d,16128d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "0618");
        preparedStatement.setString(4, "奖学金+父亲节");
        preparedStatement.setInt(5, 5);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, moneyList.get(i));
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add2(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='初一' and cp.activity_type='常规' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{777d,1577d,2177d,2777d,4577d,5777d,8177d,10848d,12168d,16128d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "5288");
        preparedStatement.setString(4, "父亲节");
        preparedStatement.setInt(5, 6);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, 288);
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add3(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='初二' and cp.activity_type='常规' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{777d,1577d,2177d,2777d,4577d,5777d,8177d,10848d,12168d,16128d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "0618");
        preparedStatement.setString(4, "奖学金+父亲节");
        preparedStatement.setInt(5, 5);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, moneyList.get(i));
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add4(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='初二' and cp.activity_type='常规' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{777d,1577d,2177d,2777d,4577d,5777d,8177d,10848d,12168d,16128d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "5288");
        preparedStatement.setString(4, "父亲节");
        preparedStatement.setInt(5, 6);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, 288);
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add5(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='初三 ' and cp.activity_type='常规' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{777d,1777d,2577d,3177d,5177d,6577d,9177d,12128d,13608d,18048d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "0618");
        preparedStatement.setString(4, "奖学金+父亲节");
        preparedStatement.setInt(5, 5);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, moneyList.get(i));
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add6(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='初三' and cp.activity_type='常规' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{777d,1577d,2177d,2777d,4577d,5777d,8177d,10848d,12168d,16128d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "5288");
        preparedStatement.setString(4, "父亲节");
        preparedStatement.setInt(5, 6);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, 288);
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add7(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='高一 ' and cp.activity_type='常规' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{777d,1777d,2577d,3177d,5177d,6577d,9177d,12128d,13608d,18048d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "0618");
        preparedStatement.setString(4, "奖学金+父亲节");
        preparedStatement.setInt(5, 5);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, moneyList.get(i));
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add8(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='高一' and cp.activity_type='常规' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{777d,1577d,2177d,2777d,4577d,5777d,8177d,10848d,12168d,16128d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "5288");
        preparedStatement.setString(4, "父亲节");
        preparedStatement.setInt(5, 6);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, 288);
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add9(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='高二 ' and cp.activity_type='常规' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{777d,1777d,2577d,3177d,5177d,6577d,9177d,12128d,13608d,18048d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "0618");
        preparedStatement.setString(4, "奖学金+父亲节");
        preparedStatement.setInt(5, 5);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, moneyList.get(i));
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add10(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='高二' and cp.activity_type='常规' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{777d,1577d,2177d,2777d,4577d,5777d,8177d,10848d,12168d,16128d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "5288");
        preparedStatement.setString(4, "父亲节");
        preparedStatement.setInt(5, 6);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, 288);
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add11(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='高三 ' and cp.activity_type='常规' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{1177d,2177d,3177d,4177d,6177d,8177d,12187d,16160d,18144d,24096d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "0618");
        preparedStatement.setString(4, "奖学金+父亲节");
        preparedStatement.setInt(5, 5);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, moneyList.get(i));
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add12(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='高三' and cp.activity_type='常规' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{777d,1577d,2177d,2777d,4577d,5777d,8177d,10848d,12168d,16128d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "5288");
        preparedStatement.setString(4, "父亲节");
        preparedStatement.setInt(5, 6);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, 288);
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    /**
     * 清北
     * @param conn
     * @throws SQLException
     */
    private static void add13(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='清北' and cp.activity_type='常规' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{777d,1577d,2177d,2777d,4577d,5777d,8177d,10848d,12168d,16128d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "0618");
        preparedStatement.setString(4, "奖学金+父亲节");
        preparedStatement.setInt(5, 5);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, 288);
            preparedStatement.executeUpdate();
        }
        preparedStatement.close();

        PreparedStatement preparedStatement22 = conn.prepareStatement(sql);
        preparedStatement22.setString(1, UUIDUtil.randomUUID());
        preparedStatement22.setString(3, "5288");
        preparedStatement22.setString(4, "父亲节");
        preparedStatement22.setInt(5, 6);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement22.setString(2, uuidList.get(i));
            preparedStatement22.setDouble(6, 288);
            preparedStatement22.executeUpdate();
        }
        preparedStatement22.close();

        preparedStatement1.close();
    }

    private static void add20(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='小学' and cp.activity_type='暑秋联报' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{2177d,2777d,2777d,4577d,5777d,9466d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "0618");
        preparedStatement.setString(4, "奖学金+父亲节");
        preparedStatement.setInt(5, 5);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, moneyList.get(i));
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add21(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='小学' and cp.activity_type='暑秋联报' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{777d,1577d,2177d,2777d,4577d,5777d,8177d,10848d,12168d,16128d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "5288");
        preparedStatement.setString(4, "父亲节");
        preparedStatement.setInt(5, 6);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, 288);
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add22(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='初一' and cp.activity_type='暑秋联报' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{2177d,2777d,2777d,4577d,5777d,9466d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "0618");
        preparedStatement.setString(4, "奖学金+父亲节");
        preparedStatement.setInt(5, 5);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, moneyList.get(i));
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add23(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='初一' and cp.activity_type='暑秋联报' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{777d,1577d,2177d,2777d,4577d,5777d,8177d,10848d,12168d,16128d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "5288");
        preparedStatement.setString(4, "父亲节");
        preparedStatement.setInt(5, 6);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, 288);
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add24(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='初二' and cp.activity_type='暑秋联报' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{2177d,2777d,2777d,4577d,5777d,9466d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "0618");
        preparedStatement.setString(4, "奖学金+父亲节");
        preparedStatement.setInt(5, 5);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, moneyList.get(i));
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add25(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='初二' and cp.activity_type='暑秋联报' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{777d,1577d,2177d,2777d,4577d,5777d,8177d,10848d,12168d,16128d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "5288");
        preparedStatement.setString(4, "父亲节");
        preparedStatement.setInt(5, 6);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, 288);
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add26(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='初三 ' and cp.activity_type='暑秋联报' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{2577d,3177d,3177d,5177d,6577d,10666d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "0618");
        preparedStatement.setString(4, "奖学金+父亲节");
        preparedStatement.setInt(5, 5);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, moneyList.get(i));
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add27(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='初三' and cp.activity_type='暑秋联报' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{777d,1577d,2177d,2777d,4577d,5777d,8177d,10848d,12168d,16128d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "5288");
        preparedStatement.setString(4, "父亲节");
        preparedStatement.setInt(5, 6);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, 288);
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add28(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='高一 ' and cp.activity_type='暑秋联报' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{2577d,3177d,3177d,5177d,6577d,10666d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "0618");
        preparedStatement.setString(4, "奖学金+父亲节");
        preparedStatement.setInt(5, 5);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, moneyList.get(i));
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add29(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='高一' and cp.activity_type='暑秋联报' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{777d,1577d,2177d,2777d,4577d,5777d,8177d,10848d,12168d,16128d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "5288");
        preparedStatement.setString(4, "父亲节");
        preparedStatement.setInt(5, 6);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, 288);
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add30(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='高二 ' and cp.activity_type='暑秋联报' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{2577d,3177d,3177d,5177d,6577d,10666d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "0618");
        preparedStatement.setString(4, "奖学金+父亲节");
        preparedStatement.setInt(5, 5);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, moneyList.get(i));
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add31(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='高二' and cp.activity_type='暑秋联报' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{777d,1577d,2177d,2777d,4577d,5777d,8177d,10848d,12168d,16128d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "5288");
        preparedStatement.setString(4, "父亲节");
        preparedStatement.setInt(5, 6);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, 288);
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add32(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='高三 ' and cp.activity_type='暑秋联报' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{3177d,4177d,4177d,6177d,8177d,14076d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "0618");
        preparedStatement.setString(4, "奖学金+父亲节");
        preparedStatement.setInt(5, 5);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, moneyList.get(i));
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    private static void add33(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='高三' and cp.activity_type='暑秋联报' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{777d,1577d,2177d,2777d,4577d,5777d,8177d,10848d,12168d,16128d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "5288");
        preparedStatement.setString(4, "父亲节");
        preparedStatement.setInt(5, 6);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, 288);
            preparedStatement.executeUpdate();
        }
        preparedStatement1.close();
        preparedStatement.close();
    }

    /**
     * 清北
     * @param conn
     * @throws SQLException
     */
    private static void add34(Connection conn) throws SQLException {
        String sql1 = "select cp.uuid from cp_course_price cp where cp.type='清北' and cp.activity_type='暑秋联报' and online_status=1 and length>1";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        List<String> uuidList = new ArrayList<>();
        while (resultSet.next()){
            String uuid = resultSet.getString(1);
            uuidList.add(uuid);
        }
        List<Double> moneyList = Arrays.asList(new Double[]{777d,1577d,2177d,2777d,4577d,5777d,8177d,10848d,12168d,16128d});
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, UUIDUtil.randomUUID());
        preparedStatement.setString(3, "0618");
        preparedStatement.setString(4, "奖学金+父亲节");
        preparedStatement.setInt(5, 5);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement.setString(2, uuidList.get(i));
            preparedStatement.setDouble(6, 288);
            preparedStatement.executeUpdate();
        }
        preparedStatement.close();

        PreparedStatement preparedStatement22 = conn.prepareStatement(sql);
        preparedStatement22.setString(1, UUIDUtil.randomUUID());
        preparedStatement22.setString(3, "5288");
        preparedStatement22.setString(4, "父亲节");
        preparedStatement22.setInt(5, 6);
        for (int i=0; i<uuidList.size();i++){
            preparedStatement22.setString(2, uuidList.get(i));
            preparedStatement22.setDouble(6, 288);
            preparedStatement22.executeUpdate();
        }
        preparedStatement22.close();

        preparedStatement1.close();
    }
}
