//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractAdminListBaseAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.utils.LoginManager;

@Action("articleList")
public class ArticleListAction extends AbstractAdminListBaseAction {
    private static final long serialVersionUID = 6039988916270544079L;
    public static final String NAME = "articleList";
    public static final String URL = "/admin/articleList";
    private int articleno;
    private String key;
    private int page;
    private int category;
    private List<TArticle> articleList = new ArrayList();

    public ArticleListAction() {
    }

    public int getCategory() {
        return this.category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getArticleno() {
        return this.articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
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

    protected void loadData() {
        ArticleSearchBean searchBean = new ArticleSearchBean();
        BeanUtils.copyProperties(this, searchBean, new String[]{"articleno"});
        searchBean.setPageType(3);
        if(StringUtils.isEmpty(this.pagination.getSortColumn())) {
            this.pagination.setSortColumn("lastupdate");
            this.pagination.setSortOrder("DESC");
        }

        this.pagination.setPreperties(this.articleService.getCount(searchBean).intValue());
        searchBean.setPagination(this.pagination);
        this.articleList = this.articleService.find(searchBean);
        this.pagination.setPageRecords(this.articleList.size());
    }

    public String delete() throws Exception {
        this.initCollections(new String[]{"collectionProperties.article.category"});
        TArticle article = this.articleService.getByNo(this.articleno);
        article.setDeleteflag(Boolean.valueOf(true));
        article.setModifyuserno(Integer.valueOf(LoginManager.getLoginUser().getUserno()));
        article.setModifytime(new Date());
        this.articleService.save(article);
        this.loadData();
        return "input";
    }
}
