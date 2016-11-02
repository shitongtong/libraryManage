//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractPublicListBaseAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.entity.TArticle;

@Action("articleList")
public class ArticleListAction extends AbstractPublicListBaseAction {
    private static final long serialVersionUID = -4215796997609788238L;
    private Integer category;
    private int page;
    private String author;
    private Boolean fullflag;
    List<TArticle> articleList = new ArrayList();

    public ArticleListAction() {
    }

    public Integer getCategory() {
        return this.category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getFullflag() {
        return this.fullflag;
    }

    public void setFullflag(Boolean fullflag) {
        this.fullflag = fullflag;
    }

    public List<TArticle> getArticleList() {
        return this.articleList;
    }

    public void setArticleList(List<TArticle> articleList) {
        this.articleList = articleList;
    }

    public String getTempName() {
        return "articleList";
    }

    protected void loadData() {
        this.logger.debug("loadData start.");
        ArticleSearchBean searchBean = new ArticleSearchBean();
        BeanUtils.copyProperties(this, searchBean);
        searchBean.setPageType(1);
        this.pagination.setPageNumber(this.page == 0?1:this.page);
        this.pagination.setSortColumn("lastupdate");
        this.pagination.setSortOrder("DESC");
        Object countInfo = CacheManager.getObject("CacheKey_ARTICLE_LIST_COUNT" + searchBean.toString());
        boolean count = false;
        int count1;
        if(countInfo == null) {
            count1 = this.articleService.getCount(searchBean).intValue();
            CacheManager.putObject("CacheKey_ARTICLE_LIST_COUNT" + searchBean.toString(), Integer.valueOf(count1));
        } else {
            count1 = Integer.parseInt(countInfo.toString());
        }

        this.pagination.setPreperties(count1);
        searchBean.setPagination(this.pagination);
        this.articleList = (List)CacheManager.getObject("CacheKey_ARTICLE_LIST" + searchBean.toString());
        if(this.articleList == null || this.articleList.size() == 0) {
            this.articleList = this.articleService.find(searchBean);
            CacheManager.putObject("CacheKey_ARTICLE_LIST" + searchBean.toString(), this.articleList);
        }

        this.logger.debug("normally end.");
    }

    public int getPageType() {
        return 2;
    }
}
