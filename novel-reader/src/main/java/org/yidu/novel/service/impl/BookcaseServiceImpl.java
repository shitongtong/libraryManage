//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.yidu.novel.bean.BookcaseSearchBean;
import org.yidu.novel.dto.BookcaseDTO;
import org.yidu.novel.entity.TBookcase;
import org.yidu.novel.service.BookcaseService;
import org.yidu.novel.service.impl.HibernateSupportServiceImpl;

public class BookcaseServiceImpl extends HibernateSupportServiceImpl implements BookcaseService {
    public BookcaseServiceImpl() {
    }

    public List<TBookcase> find(BookcaseSearchBean searchBean) {
        StringBuffer hql = new StringBuffer();
        hql.append("From TBookcase WHERE  deleteflag=false  ");
        ArrayList params = new ArrayList();
        this.buildCondtion(searchBean, hql, params);
        hql.append("ORDER BY createtime DESC");
        return this.find(hql.toString(), params);
    }

    private void buildCondtion(BookcaseSearchBean searchBean, StringBuffer hql, List<Object> params) {
        if(searchBean.getUserno() != null && searchBean.getUserno().intValue() != 0) {
            hql.append("  AND userno=?");
            params.add(searchBean.getUserno());
        }

        if(searchBean.getArticleno() != null && searchBean.getArticleno().intValue() != 0) {
            hql.append("  AND articleno=?");
            params.add(searchBean.getArticleno());
        }

    }

    public Integer getCount(BookcaseSearchBean searchBean) {
        StringBuffer hql = new StringBuffer();
        hql.append("SELECT count(*)  From TBookcase WHERE  deleteflag=false  ");
        ArrayList params = new ArrayList();
        this.buildCondtion(searchBean, hql, params);
        return this.getIntResult(hql.toString(), params);
    }

    public TBookcase getByNo(int bookcaseno) {
        return (TBookcase)this.get(TBookcase.class, Integer.valueOf(bookcaseno));
    }

    public void delteByNo(int bookcaseno) {
        TBookcase bookcase = this.getByNo(bookcaseno);
        this.delete(bookcase);
    }

    public void save(TBookcase bookcase) {
        this.saveOrUpdate(bookcase);
    }

    public List<BookcaseDTO> findWithArticleInfo(BookcaseSearchBean searchBean) {
        StringBuffer sql = new StringBuffer();
        sql.append("Select tb.*,ta.lastchapterno,ta.lastchapter,ta.chapters,ta.size,ta.fullflag,ta.lastupdate  ,ta.imgflag       FROM t_bookcase tb                                                                           LEFT JOIN t_article ta ON tb.articleno = ta.articleno                                  WHERE tb.userno= ");
        sql.append(searchBean.getUserno());
        if(searchBean.getPagination() != null) {
            sql.append(searchBean.getPagination().getSortInfo());
        } else {
            sql.append("ORDER BY ta.lastupdate DESC");
        }

        return this.yiduJdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(BookcaseDTO.class));
    }

    public TBookcase getByArticleno(int userno, int articleno) {
        BookcaseSearchBean searchBean = new BookcaseSearchBean();
        searchBean.setArticleno(Integer.valueOf(articleno));
        searchBean.setUserno(Integer.valueOf(userno));
        List list = this.find(searchBean);
        return list != null && list.size() > 0?(TBookcase)list.get(0):null;
    }

    public void batchdeleteByNo(String bookcasenos, int userno) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from  t_bookcase   WHERE userno= ");
        sql.append(userno);
        sql.append("  AND bookcaseno in (");
        sql.append(bookcasenos);
        sql.append("  )");
        this.yiduJdbcTemplate.execute(sql.toString());
    }

    public void deleteByArticleno(int articleno, int userno) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from  t_bookcase   WHERE userno= ");
        sql.append(userno);
        sql.append("  AND articleno =  ");
        sql.append(articleno);
        this.yiduJdbcTemplate.execute(sql.toString());
    }
}
