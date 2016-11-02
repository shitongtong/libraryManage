//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.yi.spider.utils.PropertiesUtils;

public class DBPool {
    private static final String JDBC_FILE = "jdbc.properties";
    private static DBPool dbPool;
    private static ComboPooledDataSource dataSource;

    static {
        try {
            PropertiesConfiguration e = PropertiesUtils.load("jdbc.properties", Charset.forName("UTF-8"));
            dataSource = new ComboPooledDataSource();
            dataSource.setUser(e.getString("jdbc.username"));
            dataSource.setPassword(e.getString("jdbc.password"));
            dataSource.setJdbcUrl(e.getString("jdbc.url"));
            dataSource.setDriverClass(e.getString("jdbc.driverClassName"));
            dataSource.setInitialPoolSize(e.getInt("jdbc.initialPoolSize", 4));
            dataSource.setMinPoolSize(e.getInt("jdbc.minPoolSize", 1));
            dataSource.setMaxPoolSize(e.getInt("jdbc.maxPoolSize", 8));
            dataSource.setMaxIdleTime(e.getInt("jdbc.maxIdleTime", 120));
            dataSource.setAcquireIncrement(e.getInt("jdbc.acquireIncrement", 1));
            dataSource.setAcquireRetryAttempts(e.getInt("jdbc.acquireRetryAttempts", 30));
            dataSource.setAcquireRetryDelay(e.getInt("jdbc.acquireRetryDelay", 1000));
            dataSource.setTestConnectionOnCheckin(e.getBoolean("jdbc.testConnectionOnCheckin", false));
            dataSource.setAutomaticTestTable(e.getString("jdbc.automaticTestTable", "c3p0TestTable"));
            dataSource.setCheckoutTimeout(e.getInt("jdbc.checkoutTimeout", 0));
        } catch (PropertyVetoException var1) {
            throw new RuntimeException(var1);
        } catch (ConfigurationException var2) {
            var2.printStackTrace();
        }

    }

    private DBPool() {
    }

    public static final DBPool getInstance() {
        if(dbPool == null) {
            Class var0 = DBPool.class;
            synchronized(DBPool.class) {
                if(dbPool == null) {
                    dbPool = new DBPool();
                }
            }
        }

        return dbPool;
    }

    public final Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException var2) {
            throw new RuntimeException("从数据源获取链接失败! ", var2);
        }
    }

    public final void releaseConnection(Connection conn) {
        try {
            if(conn != null) {
                conn.close();
            }

        } catch (SQLException var3) {
            throw new RuntimeException("释放数据库连接失败! ", var3);
        }
    }
}
