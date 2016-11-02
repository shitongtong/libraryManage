//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.service.yidu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.yi.spider.db.DBPool;
import org.yi.spider.db.YiQueryRunner;
import org.yi.spider.entity.NovelEntity;
import org.yi.spider.model.User;
import org.yi.spider.service.BaseService;
import org.yi.spider.service.INovelService;
import org.yi.spider.utils.ObjectUtils;
import org.yi.spider.utils.StringUtils;

public class NovelServiceImpl extends BaseService implements INovelService {
    public NovelServiceImpl() {
    }

    protected User loadAdmin() {
        return null;
    }

    public void repair(NovelEntity novel, NovelEntity newNovel) throws SQLException {
        String sqlPre = "update t_article set lastupdate=? ";
        ArrayList params = new ArrayList();
        params.add(new Timestamp(System.currentTimeMillis()));
        StringBuffer sql = new StringBuffer();
        if(StringUtils.isNotBlank(newNovel.getIntro())) {
            sql.append(" ,intro = ?");
            params.add(newNovel.getIntro());
        }

        if(newNovel.getTopCategory() != null) {
            sql.append(" ,category = ?");
            params.add(newNovel.getTopCategory());
        }

        if(newNovel.getSubCategory() != null) {
            sql.append(" ,subcategory = ?");
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

        sql.append(" where articleno = ?");
        params.add(novel.getNovelNo());
        if(sql.length() > 0) {
            this.update(sqlPre + sql.toString(), params.toArray());
        }

    }

    public int update(NovelEntity novel) throws SQLException {
        String sql = "update t_article set lastupdate=?,lastchapterno=?,lastchapter=?,chapters=?,size=? ";
        ArrayList params = new ArrayList();
        params.add(new Timestamp(System.currentTimeMillis()));
        params.add(novel.getLastChapterno());
        params.add(novel.getLastChapterName());
        params.add(novel.getChapters());
        params.add(novel.getSize());
        if(novel != null && novel.getImgFlag() != null) {
            sql = sql + ",imgflag=? ";
            params.add(novel.getImgFlag());
        }

        sql = sql + " where articleno = ?";
        params.add(novel.getNovelNo());
        return this.update(sql, params.toArray());
    }

    public boolean exist(String name) throws SQLException {
        String sql = "select count(*) from t_article where articlename=?";
        Object count = this.query(sql, new Object[]{name});
        return ObjectUtils.obj2Int(count).intValue() > 0;
    }

    public Integer saveNovel(NovelEntity novel) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "INSERT INTO t_article(articlename, pinyin, initial ,keywords ,authorid ,author ,category ,subcategory, intro ,fullflag ,postdate,dayvisit, weekvisit, monthvisit,  allvisit, dayvote, weekvote, monthvote, allvote ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        Object[] params = new Object[]{novel.getNovelName(), novel.getPinyin(), novel.getInitial(), StringUtils.trimToEmpty(novel.getKeywords()), Integer.valueOf(0), novel.getAuthor(), novel.getTopCategory(), novel.getSubCategory(), novel.getIntro(), novel.getFullFlag(), new Timestamp(System.currentTimeMillis()), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)};
        return (Integer)queryRunner.save(conn, sql, params);
    }

    public NovelEntity find(String novelName) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "select * from t_article where deleteflag=false and articlename=?";
        return (NovelEntity)queryRunner.query(conn, sql, new ResultSetHandler() {
            public NovelEntity handle(ResultSet rs) throws SQLException {
                NovelEntity novel = null;
                if(rs != null && rs.next()) {
                    novel = new NovelEntity();
                    novel.setNovelNo(Integer.valueOf(rs.getInt("articleno")));
                    novel.setNovelName(rs.getString("articlename"));
                    novel.setAuthor(rs.getString("author"));
                    novel.setTopCategory(Integer.valueOf(rs.getInt("category")));
                    novel.setSubCategory(Integer.valueOf(rs.getInt("subcategory")));
                    novel.setIntro(rs.getString("intro"));
                    novel.setInitial(rs.getString("initial"));
                    novel.setKeywords(rs.getString("keywords"));
                    novel.setPinyin(rs.getString("pinyin"));
                }

                return novel;
            }
        }, new Object[]{novelName});
    }

    public Map<String, Object> loadSystemParam() {
        return null;
    }

    public Number getMaxPinyin(String pinyin) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "SELECT count(*) FROM t_article WHERE pinyin ~ \'^" + pinyin + "\\d*\' ";
//        String sql = "SELECT count(*) FROM t_article ";
        return (Number)queryRunner.query(conn, sql, new ScalarHandler());
    }
}
