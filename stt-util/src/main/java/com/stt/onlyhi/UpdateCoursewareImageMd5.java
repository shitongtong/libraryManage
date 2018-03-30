package com.stt.onlyhi;

import com.stt.util2.DBPool;
import com.stt.util2.SecurityUtil;
import org.junit.Test;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2018/3/12.
 */
public class UpdateCoursewareImageMd5 {
    public static void main(String[] args) throws SQLException {
        for (int i = 0; i < 1; i++) {
            Connection conn = DBPool.getInstance().getDruidConnection();
            update(conn);
            conn.close();
        }
    }

    private static void update(Connection conn) {
        try {

            String sql = "select courseware_image_uuid, image_url from courseware_image where image_md5 is null";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            String updateSql = "update courseware_image set image_md5=?  where courseware_image_uuid=?";
            PreparedStatement preparedStatement_update = conn.prepareStatement(updateSql);
            while (resultSet.next()) {
                String courseware_image_uuid = resultSet.getString(1);
                String image_url = resultSet.getString(2);
//            System.out.println("courseware_image_uuid==" + courseware_image_uuid);
//            System.out.println("image_url==" + image_url);
                if (!image_url.contains("http")) {   //系统课件图片
                    continue;
                }
                try {
                    String imageMd5 = SecurityUtil.hashMD5Hex(new URL(image_url).openStream());
                    preparedStatement_update.setString(1, imageMd5);
                    preparedStatement_update.setString(2, courseware_image_uuid);
//                preparedStatement_update.addBatch();
                    preparedStatement_update.executeUpdate();
                } catch (Exception e) {
                    System.out.println(image_url + " 加密失败: " + e);
                    e.printStackTrace();
                }

            }
//        preparedStatement_update.executeBatch();
            preparedStatement_update.close();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test() {

    }
}
