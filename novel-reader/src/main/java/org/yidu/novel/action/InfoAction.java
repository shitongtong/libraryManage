//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.dto.ArticleDTO;
import org.yidu.novel.entity.TChapter;

public class InfoAction extends AbstractPublicBaseAction {
    private static final long serialVersionUID = -4215796997609788238L;
    private int articleno;
    private ArticleDTO article = new ArticleDTO();
    private List<TChapter> chapterList = new ArrayList();

    public InfoAction() {
    }

    public int getArticleno() {
        return this.articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public int getSubDir() {
        return this.articleno / 1000;
    }

    public void setSubDir(int subDir) {
    }

    public ArticleDTO getArticle() {
        return this.article;
    }

    public void setArticle(ArticleDTO article) {
        this.article = article;
    }

    public List<TChapter> getChapterList() {
        return this.chapterList;
    }

    public void setChapterList(List<TChapter> chapterList) {
        this.chapterList = chapterList;
    }

    public String getTempName() {
        return "info";
    }

    protected void loadData() {
        this.logger.debug("loadData start.");
        this.article = (ArticleDTO)CacheManager.getObject("CacheKey_ARTICLE" + this.articleno);
        if(this.article == null || this.article.getArticleno() == 0) {
            this.article = this.getArticleInfoByNo(this.articleno);
            if(this.article != null && this.article.getArticleno() != 0) {
                CacheManager.putObject("CacheKey_ARTICLE" + this.articleno, this.article);
            }
        }

        if(this.article != null) {
            ChapterSearchBean searchBean = new ChapterSearchBean();
            BeanUtils.copyProperties(this, searchBean);
            this.chapterList = (List)CacheManager.getObject("CacheKey_CHAPTER_LIST" + searchBean.toString());
            if(this.chapterList == null || this.chapterList.size() == 0) {
                this.chapterList = this.chapterService.find(searchBean);
                if(this.chapterList != null && this.chapterList.size() != 0) {
                    CacheManager.putObject("CacheKey_CHAPTER_LIST" + searchBean.toString(), this.chapterList);
                }
            }
        } else {
            this.addActionError(this.getText("errors.not.exsits.article"));
        }

        if(this.articleno != 0) {
            this.articleService.updateVisitStatistic(this.articleno);
        }

        this.logger.debug("loadData normally end.");
    }

    public int getPageType() {
        return 3;
    }
}
