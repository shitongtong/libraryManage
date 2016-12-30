package com.stt.shiro.chapter6.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Administrator on 2016-12-28.
 *
 * @author shitongtong
 */
public class JdbcTemplateUtil {

    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate jdbcTemplate(){
        if (jdbcTemplate == null){
            jdbcTemplate = createJdbcTemplate();
        }
        return jdbcTemplate;
    }

    private static JdbcTemplate createJdbcTemplate(){
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/shiro?autoReconnect=true&failOverReadOnly=false&useUnicode=true&characterEncoding=utf-8");
        ds.setUsername("root");
        ds.setPassword("root");
        return new JdbcTemplate(ds);
    }

}
