//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.stt.util2;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class DBPool {
    private static Logger logger = LoggerFactory.getLogger(DBPool.class);
    private static final String JDBC_FILE = "jdbc.properties";
    private static DBPool dbPool;
    private static DruidDataSource druidDataSource;
    private static PropertiesConfiguration configuration;

    static {
        try {
            configuration = PropertiesUtil.load(JDBC_FILE, "UTF-8");
        } catch (ConfigurationException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private DBPool() {
    }

    public static final DBPool getInstance() {
        if (dbPool == null) {
            Class var0 = DBPool.class;
            synchronized (DBPool.class) {
                if (dbPool == null) {
                    dbPool = new DBPool();
                }
            }
        }

        return dbPool;
    }

    public final Connection getDruidConnection() {
        try {
            if (druidDataSource != null) {
                return druidDataSource.getConnection();
            } else {
                return getDruidDataSource().getConnection();
            }

        } catch (SQLException var2) {
            throw new RuntimeException("从Druid数据源获取链接失败! ", var2);
        }
    }

    public final void releaseConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }

        } catch (SQLException var3) {
            throw new RuntimeException("释放数据库连接失败! ", var3);
        }
    }

    /**
     * 获取Druid数据源
     *
     * @return
     */
    public static DruidDataSource getDruidDataSource() {
        druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(configuration.getString("jdbc.username"));
        druidDataSource.setUrl(configuration.getString("jdbc.url"));
        druidDataSource.setPassword(configuration.getString("jdbc.password"));
//        druidDataSource.setDriverClassName(configuration.getString("jdbc.driverClassName"));//可不配置
        druidDataSource.setInitialSize(configuration.getInt("jdbc.initSize", 1));
        druidDataSource.setMaxActive(configuration.getInt("jdbc.maxActive", 20));
        druidDataSource.setMaxWait(configuration.getLong("jdbc.maxWait", 60000));
        druidDataSource.setTimeBetweenEvictionRunsMillis(configuration.getLong("jdbc.timeBetweenEvictionRunsMillis", 60000));
        druidDataSource.setMinEvictableIdleTimeMillis(configuration.getLong("jdbc.minEvictableIdleTimeMillis", 300000));
        druidDataSource.setValidationQuery(configuration.getString("jdbc.validationQuery", "SELECT 'x'"));
        druidDataSource.setTestWhileIdle(configuration.getBoolean("jdbc.testWhileIdle", true));
        druidDataSource.setTestOnReturn(configuration.getBoolean("jdbc.testOnReturn", false));
        druidDataSource.setTestOnBorrow(configuration.getBoolean("jdbc.testOnBorrow", false));
        druidDataSource.setRemoveAbandoned(configuration.getBoolean("jdbc.removeAbandoned", true));
        druidDataSource.setPoolPreparedStatements(configuration.getBoolean("jdbc.poolPreparedStatements", true));
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(configuration.getInt("jdbc.maxPoolPreparedStatementPerConnectionSize", 50));

        return druidDataSource;
    }
}
