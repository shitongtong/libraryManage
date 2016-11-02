//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.service.ChapterService;
import org.yidu.novel.service.impl.HibernateSupportServiceImpl;
import org.yidu.novel.utils.Pagination;

public class ChapterServiceImpl extends HibernateSupportServiceImpl implements ChapterService {
    public ChapterServiceImpl() {
    }

    public List<TChapter> find(ChapterSearchBean searchBean) {
        StringBuffer hql = new StringBuffer();
        hql.append("FROM TChapter WHERE  deleteflag=false  ");
        ArrayList params = new ArrayList();
        if(searchBean.getArticleno() != 0) {
            hql.append(" AND articleno = ? ");
            params.add(Integer.valueOf(searchBean.getArticleno()));
        }

        if(StringUtils.isNotEmpty(searchBean.getChapternos())) {
            hql.append(" AND chapterno in ( " + searchBean.getChapternos() + ") ");
        }

        Pagination pagination = searchBean.getPagination();
        if(pagination != null) {
            hql.append(pagination.getSortInfo());
            return this.findByRange(hql.toString(), pagination.getStart(), pagination.getPageSize(), params);
        } else {
            hql.append("ORDER BY chapterno ASC ");
            return this.find(hql.toString(), params);
        }
    }

    public TChapter getByNo(int chapterno) {
        return (TChapter)this.get(TChapter.class, Integer.valueOf(chapterno));
    }

    public TChapter getNextChapter(int articleno, int chapterno, boolean isNext) {
        StringBuffer hql = new StringBuffer();
        ArrayList params = new ArrayList();
        hql.append("FROM TChapter WHERE deleteflag=false and articleno = ? ");
        params.add(Integer.valueOf(articleno));
        if(isNext) {
            hql.append(" AND chapterno > ?  AND chaptertype = 0 ORDER BY  chapterno ASC ");
        } else {
            hql.append(" AND chapterno < ?  AND chaptertype = 0 ORDER BY  chapterno DESC ");
        }

        params.add(Integer.valueOf(chapterno));
        List chapterList = this.findByRange(hql.toString(), 0, 1, params);
        return chapterList != null && chapterList.size() > 0?(TChapter)chapterList.get(0):null;
    }

    public void save(TChapter chapter) {
        this.saveOrUpdate(chapter);
    }

    public void delteByNo(int chapterno) {
        TChapter chapter = this.getByNo(chapterno);
        this.delete(chapter);
    }

    public Integer getCount(ChapterSearchBean searchBean) {
        StringBuffer sql = new StringBuffer();
        ArrayList params = new ArrayList();
        sql.append("SELECT count(*) FROM TChapter where  deleteflag=false ");
        if(searchBean.getArticleno() != 0) {
            sql.append(" AND articleno = ? ");
            params.add(Integer.valueOf(searchBean.getArticleno()));
        }

        return this.getIntResult(sql.toString(), params);
    }

    public Integer getArticleSize(int articleno) {
        StringBuffer sql = new StringBuffer();
        ArrayList params = new ArrayList();
        sql.append("SELECT sum(size) FROM TChapter where  deleteflag=false and chaptertype = 0 ");
        sql.append(" AND articleno = ? ");
        params.add(Integer.valueOf(articleno));
        return this.getIntResult(sql.toString(), params);
    }

    public TChapter getLastChapter(int articleno) {
        StringBuffer hql = new StringBuffer();
        ArrayList params = new ArrayList();
        hql.append("FROM TChapter WHERE deleteflag=false and articleno = ? order by chapterno desc");
        params.add(Integer.valueOf(articleno));
        List chapterList = this.findByRange(hql.toString(), 0, 1, params);
        return chapterList != null && chapterList.size() > 0?(TChapter)chapterList.get(0):null;
    }
}
