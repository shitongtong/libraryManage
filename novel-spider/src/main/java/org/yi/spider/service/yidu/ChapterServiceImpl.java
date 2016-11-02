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
    protected static final String DEFAULT_STATICURL = "/reader/#subDir#/#articleNo#/#chapterNo#.html";

    public ChapterServiceImpl() {
    }

    public Number save(ChapterEntity chapter) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "INSERT INTO t_chapter( articleno, articlename, chaptertype, chaptername,         size, isvip, postdate) VALUES ( ?, ?, ?, ?, ?, ?, ?) ;";
        Object[] params = new Object[]{chapter.getNovelNo(), chapter.getNovelName(), Integer.valueOf(0), chapter.getChapterName(), chapter.getSize(), Boolean.FALSE, new Timestamp(System.currentTimeMillis())};
        return (Number)queryRunner.save(conn, sql, params);
    }

    public Map<String, Object> getTotalInfo(Number novelno) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "SELECT SUM(size) as size, count(*) as count FROM t_chapter WHERE articleno = ?";
        return (Map)queryRunner.query(conn, sql, new MapHandler(), new Object[]{novelno});
    }

    public int getChapterOrder(ChapterEntity chapter) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        boolean order = false;
        String sql = "select max(chapterno) from t_chapter WHERE articleno = ?";
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
        String sql = "select count(*) from t_chapter where articleno = ? and chaptername=?";
        Object count = queryRunner.query(conn, sql, new ScalarHandler(), new Object[]{chapter.getNovelNo(), chapter.getChapterName()});
        return ObjectUtils.obj2Int(count).intValue() > 0;
    }

    public ChapterEntity get(ChapterEntity chapter, int i) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "select * from t_chapter where 1=1";
        if(i == -1) {
            sql = sql + " and chapterno < ? order by chapterno desc limit 1";
        } else if(i == 1) {
            sql = sql + " and chapterno > ? order by chapterno asc limit 1";
        }

        return (ChapterEntity)queryRunner.query(conn, sql, new ChapterServiceImpl.ChapterResultSetHandler());
    }

    public List<ChapterEntity> getChapterList(NovelEntity novel) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "select * from t_chapter where articleno = ? order by chapterno asc";
        List linedMap = (List)queryRunner.query(conn, sql, new MapListHandler(), new Object[]{novel.getNovelNo()});
        ArrayList result = new ArrayList();

        for(int i = 0; i < linedMap.size(); ++i) {
            Map map = (Map)linedMap.get(i);
            ChapterEntity chapter = new ChapterEntity();
            chapter.setNovelNo(novel.getNovelNo());
            chapter.setNovelName(novel.getNovelName());
            chapter.setChapterName(String.valueOf(map.get("chaptername")));
            chapter.setChapterNo(Integer.valueOf(Integer.parseInt(String.valueOf(map.get("chapterno")))));
            chapter.setChapterOrder(chapter.getChapterNo());
            result.add(chapter);
        }

        return result;
    }

    public ChapterEntity get(Number chapterNo) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "SELECT articleno,articlename,chapterno,chaptername,size FROM t_chapter WHERE articleno=?";
        return (ChapterEntity)queryRunner.query(conn, sql, new ChapterServiceImpl.ChapterResultSetHandler(), new Object[]{chapterNo});
    }

    public ChapterEntity getLasChapter(ChapterEntity chapter) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "SELECT articleno,articlename,chapterno,chaptername,size FROM t_chapter WHERE articleno=? order by chapterno desc limit 1 offset 0";
        return (ChapterEntity)queryRunner.query(conn, sql, new ChapterServiceImpl.ChapterResultSetHandler(), new Object[]{chapter.getNovelNo()});
    }

    public List<ChapterEntity> getDuplicateChapter() throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "select articleno,chapterno from t_chapter where chapterno in (\tselect min(chapterno) from t_chapter tc inner join ( \t\tselect articleno ,chaptername from t_chapter \t\tgroup by articleno,chaptername having count(1)>1 \t) tc1 \ton tc.chaptername = tc1.chaptername and tc.articleno = tc1.articleno \tgroup by tc.chaptername );";
        ArrayList result = new ArrayList();
        List chapterList = (List)queryRunner.query(conn, sql, new MapListHandler());

        for(int i = 0; i < chapterList.size(); ++i) {
            Map map = (Map)chapterList.get(i);
            ChapterEntity chapter = new ChapterEntity();
            chapter.setNovelNo(Integer.valueOf(Integer.parseInt(String.valueOf(map.get("articleno")))));
            chapter.setNovelName(String.valueOf(map.get("chaptername")));
            chapter.setChapterNo(Integer.valueOf(Integer.parseInt(String.valueOf(map.get("chapterno")))));
            result.add(chapter);
        }

        return result;
    }

    public ChapterEntity getChapterByChapterNameAndNovelNo(ChapterEntity chapter) throws SQLException {
        Connection conn = DBPool.getInstance().getConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "SELECT articleno,articlename,chapterno,chaptername,size FROM t_chapter WHERE articleno=? and chaptername=? limit 1 offset 0";
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
        String sql1 = "delete from t_chapter where chapterno in (" + ids.toString() + ")";
        queryRunner.update(conn, sql1);
    }

    public int updateSize(ChapterEntity chapter) throws SQLException {
        String sql = "update t_chapter set size=?  where chapterno = ?";
        return this.update(sql, new Object[]{chapter.getSize(), chapter.getNovelNo()});
    }

    protected User loadAdmin() throws SQLException {
        return null;
    }

    private final class ChapterResultSetHandler implements ResultSetHandler<ChapterEntity> {
        private ChapterResultSetHandler() {
        }

        public ChapterEntity handle(ResultSet rs) throws SQLException {
            ChapterEntity chapter = null;
            if(rs != null && rs.next()) {
                chapter = new ChapterEntity();
            }

            chapter.setNovelNo(Integer.valueOf(rs.getInt("articleno")));
            chapter.setNovelName(rs.getString("articlename"));
            chapter.setChapterNo(Integer.valueOf(rs.getInt("chapterno")));
            chapter.setChapterName(rs.getString("chaptername"));
            chapter.setSize(Integer.valueOf(rs.getInt("size")));
            return chapter;
        }
    }
}
