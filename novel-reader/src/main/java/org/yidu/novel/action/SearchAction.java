//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractPublicListBaseAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.entity.TArticle;

public class SearchAction extends AbstractPublicListBaseAction {
    private static final long serialVersionUID = -4215796997609788238L;
    private String key;
    private int page;
    List<TArticle> articleList = new ArrayList();

    public SearchAction() {
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = StringUtils.trim(key);
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<TArticle> getArticleList() {
        return this.articleList;
    }

    public void setArticleList(List<TArticle> articleList) {
        this.articleList = articleList;
    }

    public String getTempName() {
        return "search";
    }

    protected void loadData() {
        this.logger.debug("loadData start.");
        ArticleSearchBean searchBean = new ArticleSearchBean();
        BeanUtils.copyProperties(this, searchBean);
        searchBean.setPageType(1);
        this.pagination.setPageNumber(this.page == 0?1:this.page);
        this.pagination.setSortColumn("lastupdate");
        this.pagination.setSortOrder("ASC");
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
        return 5;
    }
}
