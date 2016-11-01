//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.service.ArticleService;
import org.yidu.novel.service.impl.HibernateSupportServiceImpl;
import org.yidu.novel.utils.Pagination;

public class ArticleServiceImpl extends HibernateSupportServiceImpl implements ArticleService {
    public ArticleServiceImpl() {
    }

    public List<TArticle> find(ArticleSearchBean searchBean) {
        StringBuffer hql = new StringBuffer();
        hql.append("From TArticle WHERE  deleteflag='false'  ");
        ArrayList params = new ArrayList();
        this.buildCondtion(searchBean, hql, params);
        Pagination pagination = searchBean.getPagination();
        if(pagination != null) {
            hql.append(pagination.getSortInfo());
            return this.findByRange(hql.toString(), pagination.getStart(), pagination.getPageSize(), params);
        } else {
            hql.append("ORDER BY articleno");
            return this.find(hql.toString(), params);
        }
    }

    private void buildCondtion(ArticleSearchBean searchBean, StringBuffer hql, List<Object> params) {
        if(searchBean.getArticleno() != 0) {
            hql.append(" AND articleno = ? ");
            params.add(Integer.valueOf(searchBean.getArticleno()));
        }

        if(StringUtils.isNotEmpty(searchBean.getArticlename())) {
            hql.append(" AND articlename = ? ");
            params.add(searchBean.getArticlename());
        }

        if(StringUtils.isNotEmpty(searchBean.getAuthor())) {
            hql.append(" AND author = ? ");
            params.add(searchBean.getAuthor());
        }

        if(searchBean.getCategory() != null && searchBean.getCategory().intValue() != 0) {
            hql.append(" AND category = ? ");
            params.add(searchBean.getCategory());
        }

        if(searchBean.getFullflag() != null && searchBean.getFullflag().booleanValue()) {
            hql.append(" AND fullflag = TRUE ");
        }

        if(StringUtils.isNotEmpty(searchBean.getArticlenos())) {
            hql.append(" AND articleno in ( " + searchBean.getArticlenos() + " )  ");
        }

        if(StringUtils.isNotEmpty(searchBean.getKey())) {
            hql.append(" AND  (LOWER(articlename) like \'%" + StringEscapeUtils.escapeSql(searchBean.getKey().toLowerCase()) + "%\' OR LOWER(author) like \'%" + StringEscapeUtils.escapeSql(searchBean.getKey().toLowerCase()) + "%\' )");
        }

        if(searchBean.getAuthorid() != null && searchBean.getAuthorid().intValue() > 0) {
            hql.append(" AND authorid = ? ");
            params.add(searchBean.getAuthorid());
        }

        if(searchBean.getPageType() != 2 && searchBean.getPageType() != 3) {
            hql.append(" AND lastupdate is not null ");
            hql.append(" AND lastchapterno is not null ");
        }

    }

    public TArticle getByNo(int articleno) {
        return (TArticle)this.get(TArticle.class, Integer.valueOf(articleno));
    }

    public void delteByNo(int articleno) {
        TArticle article = this.getByNo(articleno);
        this.delete(article);
    }

    public void save(TArticle article) {
        this.saveOrUpdate(article);
    }

    public Integer getCount(ArticleSearchBean searchBean) {
        StringBuffer hql = new StringBuffer();
        ArrayList params = new ArrayList();
        hql.append("SELECT count(*) FROM TArticle where deleteflag = 'false' ");
        this.buildCondtion(searchBean, hql, params);
        return this.getIntResult(hql.toString(), params);
    }

    public void updateVisitStatistic(int articleno) {
        String sql = "update TArticle set   dayvisit  = dayvisit +1 , weekvisit= weekvisit +1 ,monthvisit =monthvisit+1 , allvisit = allvisit +1 where articleno =  ? ";
        this.sqlQuery(sql, new Object[]{Integer.valueOf(articleno)});
    }

    public void updateVoteStatistic(int articleno) {
        String sql = "update TArticle set  dayvote  = dayvote +1 , weekvote= weekvote +1 ,monthvote =monthvote+1 , allvote = allvote +1 where articleno =  ? ";
        this.sqlQuery(sql, new Object[]{Integer.valueOf(articleno)});
    }

    public void cleanStatistics() {
        System.out.println("cleanStatistics start");
        String sql = "update t_article set dayvote = 0 ,dayvisit = 0";
        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(7);
        if(dayOfWeek == 2) {
            sql = sql + ",weekvote = 0,weekvisit=0";
        }

        int dayOfMonth = cal.get(5);
        if(dayOfMonth == 1) {
            sql = sql + ",monthvote = 0,monthvisit=0";
        }

        System.out.println(sql);
        this.yiduJdbcTemplate.update(sql);
        System.out.println("cleanStatistics end");
    }
}
