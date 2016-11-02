//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

public class YiQueryRunner extends QueryRunner {
    public YiQueryRunner() {
    }

    public YiQueryRunner(boolean pmdKnownBroken) {
        super(pmdKnownBroken);
    }

    public YiQueryRunner(DataSource ds, boolean pmdKnownBroken) {
        super(ds, pmdKnownBroken);
    }

    public YiQueryRunner(DataSource ds) {
        super(ds);
    }

    public <T> T save(Connection conn, boolean closeConn, String sql, Object... params) throws SQLException {
        if(conn == null) {
            throw new SQLException("Null connection");
        } else if(sql == null) {
            if(closeConn) {
                this.close(conn);
            }

            throw new SQLException("Null SQL statement");
        } else {
            PreparedStatement stmt = null;

            Object var8;
            try {
                stmt = this.prepareStatement(conn, sql, 1);
                this.fillStatement(stmt, params);
                stmt.execute();
                ResultSet e = stmt.getGeneratedKeys();
                if(!e.next()) {
                    return null;
                }

                var8 = e.getObject(1);
            } catch (SQLException var11) {
                this.rethrow(var11, sql, params);
                return null;
            } finally {
                this.close(stmt);
                if(closeConn) {
                    this.close(conn);
                }

            }

            return (T) var8;
        }
    }

    protected PreparedStatement prepareStatement(Connection conn, String sql, int generatedKeys) throws SQLException {
        PreparedStatement ps = null;
        if(1 == generatedKeys) {
            ps = conn.prepareStatement(sql, 1);
        } else {
            ps = conn.prepareStatement(sql);
        }

        return ps;
    }

    public <T> T save(Connection conn, String sql, Object... params) throws SQLException {
        return this.save(conn, true, sql, params);
    }

    public int[] batch(Connection conn, String sql, Object[][] params) throws SQLException {
        return this.batch(conn, true, sql, params);
    }

    public int[] batch(Connection conn, String sql, int batchSize, Object[][] params) throws SQLException {
        return this.batch(conn, true, Integer.valueOf(batchSize), sql, params);
    }

    public int[] batch(String sql, Object[][] params) throws SQLException {
        Connection conn = this.prepareConnection();
        return this.batch(conn, true, sql, params);
    }

    public int[] batch(String sql, int batchSize, Object[][] params) throws SQLException {
        Connection conn = this.prepareConnection();
        return this.batch(conn, true, Integer.valueOf(batchSize), sql, params);
    }

    private int[] batch(Connection conn, boolean closeConn, String sql, Object[][] params) throws SQLException {
        return this.batch(conn, closeConn, Integer.valueOf(-1), sql, params);
    }

    private int[] batch(Connection conn, boolean closeConn, Integer batchSize, String sql, Object[][] params) throws SQLException {
        if(conn == null) {
            throw new SQLException("Null connection");
        } else if(sql == null) {
            if(closeConn) {
                this.close(conn);
            }

            throw new SQLException("Null SQL statement");
        } else if(params == null) {
            if(closeConn) {
                this.close(conn);
            }

            throw new SQLException("Null parameters. If parameters aren\'t need, pass an empty array.");
        } else {
            PreparedStatement stmt = null;
            int[] rows = null;

            try {
                stmt = this.prepareStatement(conn, sql);
                if(batchSize == null || batchSize.intValue() < 1) {
                    batchSize = Integer.valueOf(params.length);
                }

                for(int e = 0; e < batchSize.intValue(); ++e) {
                    this.fillStatement(stmt, params[e]);
                    stmt.addBatch();
                }

                rows = stmt.executeBatch();
            } catch (SQLException var12) {
                this.rethrow(var12, sql, params);
            } finally {
                this.close(stmt);
                if(closeConn) {
                    this.close(conn);
                }

            }

            return rows;
        }
    }

    /** @deprecated */
    @Deprecated
    public <T> T query(Connection conn, String sql, Object param, ResultSetHandler<T> rsh) throws SQLException {
        return this.query(conn, true, sql, rsh, new Object[]{param});
    }

    /** @deprecated */
    @Deprecated
    public <T> T query(Connection conn, String sql, Object[] params, ResultSetHandler<T> rsh) throws SQLException {
        return this.query(conn, true, sql, rsh, params);
    }

    public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
        return this.query(conn, true, sql, rsh, params);
    }

    public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh) throws SQLException {
        return this.query(conn, true, sql, rsh, (Object[])null);
    }

    /** @deprecated */
    @Deprecated
    public <T> T query(String sql, Object param, ResultSetHandler<T> rsh) throws SQLException {
        Connection conn = this.prepareConnection();
        return this.query(conn, true, sql, rsh, new Object[]{param});
    }

    /** @deprecated */
    @Deprecated
    public <T> T query(String sql, Object[] params, ResultSetHandler<T> rsh) throws SQLException {
        Connection conn = this.prepareConnection();
        return this.query(conn, true, sql, rsh, params);
    }

    public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
        Connection conn = this.prepareConnection();
        return this.query(conn, true, sql, rsh, params);
    }

    public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
        Connection conn = this.prepareConnection();
        return this.query(conn, true, sql, rsh, (Object[])null);
    }

    private <T> T query(Connection conn, boolean closeConn, String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
        if(conn == null) {
            throw new SQLException("Null connection");
        } else if(sql == null) {
            if(closeConn) {
                this.close(conn);
            }

            throw new SQLException("Null SQL statement");
        } else if(rsh == null) {
            if(closeConn) {
                this.close(conn);
            }

            throw new SQLException("Null ResultSetHandler");
        } else {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Object result = null;

            try {
                stmt = this.prepareStatement(conn, sql);
                this.fillStatement(stmt, params);
                rs = this.wrap(stmt.executeQuery());
                result = rsh.handle(rs);
            } catch (SQLException var32) {
                this.rethrow(var32, sql, params);
            } finally {
                try {
                    this.close(rs);
                } finally {
                    this.close(stmt);
                    if(closeConn) {
                        this.close(conn);
                    }

                }
            }

            return (T) result;
        }
    }

    public int update(Connection conn, String sql) throws SQLException {
        return this.update(conn, true, sql, (Object[])null);
    }

    public int update(Connection conn, String sql, Object param) throws SQLException {
        return this.update(conn, true, sql, new Object[]{param});
    }

    public int update(Connection conn, String sql, Object... params) throws SQLException {
        return this.update(conn, true, sql, params);
    }

    public int update(String sql) throws SQLException {
        Connection conn = this.prepareConnection();
        return this.update(conn, true, sql, (Object[])null);
    }

    public int update(String sql, Object param) throws SQLException {
        Connection conn = this.prepareConnection();
        return this.update(conn, true, sql, new Object[]{param});
    }

    public int update(String sql, Object... params) throws SQLException {
        Connection conn = this.prepareConnection();
        return this.update(conn, true, sql, params);
    }

    private int update(Connection conn, boolean closeConn, String sql, Object... params) throws SQLException {
        if(conn == null) {
            throw new SQLException("Null connection");
        } else if(sql == null) {
            if(closeConn) {
                this.close(conn);
            }

            throw new SQLException("Null SQL statement");
        } else {
            PreparedStatement stmt = null;
            int rows = 0;

            try {
                stmt = this.prepareStatement(conn, sql);
                this.fillStatement(stmt, params);
                rows = stmt.executeUpdate();
            } catch (SQLException var11) {
                this.rethrow(var11, sql, params);
            } finally {
                this.close(stmt);
                if(closeConn) {
                    this.close(conn);
                }

            }

            return rows;
        }
    }
}
