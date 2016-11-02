//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.service.jieqi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yi.spider.constants.GlobalConfig;
import org.yi.spider.db.DBPool;
import org.yi.spider.db.YiQueryRunner;
import org.yi.spider.entity.NovelEntity;
import org.yi.spider.model.User;
import org.yi.spider.service.BaseService;
import org.yi.spider.service.INovelService;
import org.yi.spider.utils.ObjectUtils;
import org.yi.spider.utils.StringUtils;

public class NovelServiceImpl extends BaseService implements INovelService {
    private static final Logger logger = LoggerFactory.getLogger(NovelServiceImpl.class);

    public NovelServiceImpl() {
        try {
            this.getAdmin();
        } catch (SQLException var2) {
            logger.error(var2.getMessage(), var2);
        }

    }

    protected User loadAdmin() throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        return (User)queryRunner.query(conn, "SELECT uid, uname FROM jieqi_system_users ORDER BY uid LIMIT 0,1", new ResultSetHandler() {
            public User handle(ResultSet rs) throws SQLException {
                User user = null;
                if(rs != null && rs.next()) {
                    user = new User();
                    user.setUserId(rs.getString("uid"));
                    user.setUserName(rs.getString("uname"));
                }

                return user;
            }
        });
    }

    public int update(NovelEntity novel) throws SQLException {
        String sql = "update jieqi_article_article set lastupdate=?, lastvolumeid=?,lastvolume=?,lastchapterid=?,lastchapter=?,chapters=?,size=?  where articleid = ?";
        return this.update(sql, new Object[]{this.getJieQiTimeStamp(), Integer.valueOf(0), "", novel.getLastChapterno(), novel.getLastChapterName(), novel.getChapters(), novel.getSize(), novel.getNovelNo()});
    }

    public void repair(NovelEntity novel, NovelEntity newNovel) throws SQLException {
        String sqlPre = "update jieqi_article_article set lastupdate=? ";
        ArrayList params = new ArrayList();
        params.add(this.getJieQiTimeStamp());
        StringBuffer sql = new StringBuffer();
        if(StringUtils.isNotBlank(newNovel.getIntro())) {
            sql.append(" ,intro = ?");
            params.add(newNovel.getIntro());
        }

        if(newNovel.getTopCategory() != null) {
            sql.append(" ,sortid = ?");
            params.add(newNovel.getTopCategory());
        }

        if(newNovel.getSubCategory() != null) {
            sql.append(" ,typeid = ?");
            params.add(newNovel.getSubCategory());
        }

        if(newNovel.getFullFlag() != null) {
            sql.append(" ,fullflag = ?");
            params.add(newNovel.getFullFlag());
        }

        if(StringUtils.isNotBlank(newNovel.getKeywords())) {
            sql.append(" ,keywords = ?");
            params.add(newNovel.getKeywords());
        }

        sql.append(" where articleid = ?");
        params.add(novel.getNovelNo());
        if(sql.length() > 0) {
            this.update(sqlPre + sql.toString(), params.toArray());
        }

    }

    public boolean exist(String name) throws SQLException {
        String sql = "select count(*) from jieqi_article_article where articlename=?";
        Object count = this.query(sql, new Object[]{name});
        return ObjectUtils.obj2Int(count).intValue() > 0;
    }

    public Number saveNovel(final NovelEntity novel) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        ArrayList params = new ArrayList() {
            private static final long serialVersionUID = 1L;

            {
                this.add(Integer.valueOf(0));
                this.add(NovelServiceImpl.this.getJieQiTimeStamp());
                this.add(NovelServiceImpl.this.getJieQiTimeStamp());
                this.add(novel.getNovelName());
                this.add(novel.getKeywords());
                this.add(novel.getInitial());
                this.add(Integer.valueOf(0));
                this.add(novel.getAuthor());
                this.add(NovelServiceImpl.admin.getUserId());
                this.add(NovelServiceImpl.admin.getUserName());
                this.add(novel.getTopCategory());
                this.add(novel.getSubCategory());
                this.add(novel.getIntro());
                this.add("");
                this.add("");
                this.add(novel.getFullFlag());
            }
        };
        String ziduan = "";
        String zhi = "";
        if(GlobalConfig.localSite.getUsePinyin().intValue() == 1) {
            ziduan = ", pyh, zysoft_pinyin";
            zhi = ",?,?";
            params.add(novel.getPinyin());
            params.add(novel.getPinyin());
        }

        String sql = "insert into jieqi_article_article (siteid,postdate,lastupdate,articlename,keywords,`initial`,authorid,author,posterid,poster,sortid,typeid,intro,notice,setting,fullflag " + ziduan + ")" + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? " + zhi + ")";
        return (Number)queryRunner.save(conn, sql, params.toArray());
    }

    public NovelEntity find(String novelName) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String ziduan = "";
        if(GlobalConfig.localSite.getUsePinyin().intValue() == 1) {
            ziduan = ", pyh";
        }

        String sql = "select articleid,articlename,author,sortid,typeid,intro" + ziduan + " from jieqi_article_article where articlename=?";
        return (NovelEntity)queryRunner.query(conn, sql, new ResultSetHandler() {
            public NovelEntity handle(ResultSet rs) throws SQLException {
                NovelEntity novel = null;
                if(rs != null && rs.next()) {
                    novel = new NovelEntity();
                    novel.setNovelNo(Integer.valueOf(rs.getInt("articleid")));
                    novel.setNovelName(rs.getString("articlename"));
                    novel.setAuthor(rs.getString("author"));
                    novel.setTopCategory(Integer.valueOf(rs.getInt("sortid")));
                    novel.setSubCategory(Integer.valueOf(rs.getInt("typeid")));
                    novel.setIntro(rs.getString("intro"));
                    if(GlobalConfig.localSite.getUsePinyin().intValue() == 1) {
                        novel.setPinyin(rs.getString("pyh"));
                    }
                }

                return novel;
            }
        }, new Object[]{novelName});
    }

    public Map<String, Object> loadSystemParam() throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "SELECT cname, cvalue FROM jieqi_system_configs where modname=\'article\'";
        return (Map)queryRunner.query(conn, sql, new MapHandler());
    }

    public Number getMaxPinyin(String pinyin) throws SQLException {
        if(GlobalConfig.localSite.getUsePinyin().intValue() == 1) {
            Connection conn = DBPool.getInstance().getConnection();
            YiQueryRunner queryRunner = new YiQueryRunner(true);
            String sql = "SELECT count(*) FROM jieqi_article_article WHERE pyh REGEXP \'" + pinyin + "[0-9]*\' ";
            return (Number)queryRunner.query(conn, sql, new ScalarHandler());
        } else {
            return Integer.valueOf(0);
        }
    }
}
