//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.service;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.yi.spider.db.DBPool;
import org.yi.spider.db.YiQueryRunner;
import org.yi.spider.model.User;

public abstract class BaseService {
    protected static final String EMPTY = "";
    protected static User admin;

    public BaseService() {
    }

    public User getAdmin() throws SQLException {
        if(admin == null) {
            admin = this.loadAdmin();
        }

        return admin;
    }

    protected Integer getJieQiTimeStamp() {
        return Integer.valueOf(String.valueOf(System.currentTimeMillis() / 1000L));
    }

    protected abstract User loadAdmin() throws SQLException;

    public int update(String sql, Object[] params) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        return queryRunner.update(conn, sql, params);
    }

    public Object query(String sql, Object[] params) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        return queryRunner.query(conn, sql, new ScalarHandler(), params);
    }
}
