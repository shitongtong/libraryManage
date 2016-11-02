//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.service.jieqi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.yi.spider.db.DBPool;
import org.yi.spider.db.YiQueryRunner;
import org.yi.spider.entity.ChapterEntity;
import org.yi.spider.entity.NovelEntity;
import org.yi.spider.model.User;
import org.yi.spider.service.BaseService;
import org.yi.spider.service.IChapterService;
import org.yi.spider.utils.ObjectUtils;

public class ChapterServiceImpl extends BaseService implements IChapterService {
    public ChapterServiceImpl() {
    }

    public Number save(ChapterEntity chapter) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "insert into jieqi_article_chapter (siteid,articleid,articlename,volumeid,posterid,poster,postdate,lastupdate,chaptername,size,attachment,chapterorder) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params = new Object[]{Integer.valueOf(0), chapter.getNovelNo(), chapter.getNovelName(), Integer.valueOf(0), admin.getUserId(), admin.getUserName(), this.getJieQiTimeStamp(), this.getJieQiTimeStamp(), chapter.getChapterName(), chapter.getSize(), "", Integer.valueOf(this.getChapterOrder(chapter))};
        return (Number)queryRunner.save(conn, sql, params);
    }

    public Map<String, Object> getTotalInfo(Number novelno) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "SELECT SUM(size) as size, count(*) as count FROM jieqi_article_chapter WHERE articleid = ?";
        return (Map)queryRunner.query(conn, sql, new MapHandler(), new Object[]{novelno});
    }

    public int getChapterOrder(ChapterEntity chapter) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        boolean order = false;
        String sql = "select max(chapterorder) from jieqi_article_chapter WHERE articleid = ?";
        Object obj = queryRunner.query(conn, sql, new ScalarHandler(), new Object[]{chapter.getNovelNo()});
        int order1;
        if(obj == null) {
            order1 = 1;
        } else {
            order1 = ObjectUtils.obj2Int(obj).intValue();
        }

        return order1;
    }

    public boolean exist(ChapterEntity chapter) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "select count(*) from jieqi_article_chapter where chaptername=?";
        Object count = queryRunner.query(conn, sql, new ScalarHandler(), new Object[]{chapter.getChapterName()});
        return ObjectUtils.obj2Int(count).intValue() > 0;
    }

    public ChapterEntity get(ChapterEntity chapter, int i) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "select * from jieqi_article_chapter where articleid = ?";
        if(i < 0) {
            sql = sql + " and chapterid < ? order by chapterid desc limit " + (Math.abs(i) - 1) + ", 1";
        } else if(i > 0) {
            sql = sql + " and chapterid > ? order by chapterid asc limit " + (i - 1) + ", 1";
        }

//        return (ChapterEntity)queryRunner.query(conn, sql, new ChapterServiceImpl.ChapterResultSetHandler((ChapterServiceImpl.ChapterResultSetHandler)null), new Object[]{chapter.getNovelNo(), chapter.getChapterNo()});
        return (ChapterEntity)queryRunner.query(conn, sql, new ChapterServiceImpl.ChapterResultSetHandler(), new Object[]{chapter.getNovelNo(), chapter.getChapterNo()});
    }

    public List<ChapterEntity> getChapterList(NovelEntity novel) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "select * from jieqi_article_chapter where articleid = ? order by chapterid asc";
        List linedMap = (List)queryRunner.query(conn, sql, new MapListHandler(), new Object[]{novel.getNovelNo()});
        ArrayList result = new ArrayList();

        for(int i = 0; i < linedMap.size(); ++i) {
            Map map = (Map)linedMap.get(i);
            ChapterEntity chapter = new ChapterEntity();
            chapter.setNovelNo(novel.getNovelNo());
            chapter.setNovelName(novel.getNovelName());
            chapter.setChapterName(String.valueOf(map.get("chaptername")));
            chapter.setChapterNo(Integer.valueOf(Integer.parseInt(String.valueOf(map.get("chapterid")))));
            chapter.setChapterOrder(chapter.getChapterNo());
            result.add(chapter);
        }

        return result;
    }

    public ChapterEntity get(Number chapterid) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "SELECT articleid,articlename,chapterid,chaptername,size FROM jieqi_article_chapter WHERE articleid=?";
        return (ChapterEntity)queryRunner.query(conn, sql, new ChapterServiceImpl.ChapterResultSetHandler(), new Object[]{chapterid});
    }

    public ChapterEntity getLasChapter(ChapterEntity chapter) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "SELECT articleid,articlename,chapterid,chaptername,size FROM jieqi_article_chapter WHERE articleid=? order by chapterid desc limit 1 offset 0";
        return (ChapterEntity)queryRunner.query(conn, sql, new ChapterServiceImpl.ChapterResultSetHandler(), new Object[]{chapter.getNovelNo()});
    }

    public List<ChapterEntity> getDuplicateChapter() throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "select articleid,chapterid from jieqi_article_chapter where chapterid in (\tselect min(chapterid) from jieqi_article_chapter tc inner join ( \t\tselect articleid ,chaptername from jieqi_article_chapter \t\tgroup by articleid,chaptername having count(1)>1 \t) tc1 \ton tc.chaptername = tc1.chaptername and tc.articleid = tc1.articleid \tgroup by tc.chaptername );";
        ArrayList result = new ArrayList();
        List chapterList = (List)queryRunner.query(conn, sql, new MapListHandler());

        for(int i = 0; i < chapterList.size(); ++i) {
            Map map = (Map)chapterList.get(i);
            ChapterEntity chapter = new ChapterEntity();
            chapter.setNovelNo(Integer.valueOf(Integer.parseInt(String.valueOf(map.get("articleid")))));
            chapter.setNovelName(String.valueOf(map.get("chaptername")));
            chapter.setChapterNo(Integer.valueOf(Integer.parseInt(String.valueOf(map.get("chapterid")))));
            result.add(chapter);
        }

        return result;
    }

    public ChapterEntity getChapterByChapterNameAndNovelNo(ChapterEntity chapter) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "SELECT articleid,articlename,chapterid,chaptername,size FROM jieqi_article_chapter WHERE articleid=? and chaptername=? limit 1 offset 0";
        return (ChapterEntity)queryRunner.query(conn, sql, new ChapterServiceImpl.ChapterResultSetHandler(), new Object[]{chapter.getNovelNo(), chapter.getChapterName()});
    }

    public void delete(List<ChapterEntity> chapters) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        StringBuffer ids = new StringBuffer("\'");
        Iterator var6 = chapters.iterator();

        while(var6.hasNext()) {
            ChapterEntity sql = (ChapterEntity)var6.next();
            ids.append(sql.getChapterNo() + "\',");
        }

        ids.deleteCharAt(ids.length() - 1);
        String sql1 = "delete from jieqi_article_chapter where chapterid in (" + ids.toString() + ")";
        queryRunner.update(conn, sql1);
    }

    protected User loadAdmin() throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        return (User)queryRunner.query(conn, "SELECT uid, uname FROM jieqi_system_users ORDER BY uid LIMIT 0,1", new ResultSetHandler() {
            public User handle(ResultSet rs) throws SQLException {
                User user = null;
                if(rs != null && rs.next()) {
                    user = new User();
                }

                user.setUserId(rs.getString("uid"));
                user.setUserName(rs.getString("uname"));
                return user;
            }
        });
    }

    public int updateSize(ChapterEntity chapter) throws SQLException {
        String sql = "update jieqi_article_chapter set size=?  where chapterid = ?";
        return this.update(sql, new Object[]{chapter.getSize(), chapter.getNovelNo()});
    }

    private final class ChapterResultSetHandler implements ResultSetHandler<ChapterEntity> {
        private ChapterResultSetHandler() {
        }

        public ChapterEntity handle(ResultSet rs) throws SQLException {
            ChapterEntity chapter = null;
            if(rs != null && rs.next()) {
                chapter = new ChapterEntity();
                chapter.setNovelNo(Integer.valueOf(rs.getInt("articleid")));
                chapter.setNovelName(rs.getString("articlename"));
                chapter.setChapterNo(Integer.valueOf(rs.getInt("chapterid")));
                chapter.setChapterName(rs.getString("chaptername"));
                chapter.setSize(Integer.valueOf(rs.getInt("size")));
            }

            return chapter;
        }
    }
}
