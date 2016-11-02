//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.user;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractUserListBaseAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.utils.LoginManager;

@Action("articleList")
public class ArticleListAction extends AbstractUserListBaseAction {
    private static final long serialVersionUID = 6039988916270544079L;
    public static final String NAME = "articleList";
    public static final String URL = "/user/articleList";
    private int articleno;
    private List<TArticle> articleList = new ArrayList();

    public ArticleListAction() {
    }

    public int getArticleno() {
        return this.articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public List<TArticle> getArticleList() {
        return this.articleList;
    }

    public void setArticleList(List<TArticle> articleList) {
        this.articleList = articleList;
    }

    protected void loadData() {
        ArticleSearchBean searchBean = new ArticleSearchBean();
        searchBean.setAuthorid(Integer.valueOf(LoginManager.getLoginUser().getUserno()));
        searchBean.setPageType(2);
        if(StringUtils.isEmpty(this.pagination.getSortColumn())) {
            this.pagination.setSortColumn("articleno");
            this.pagination.setSortOrder("DESC");
        }

        this.articleList = this.articleService.find(searchBean);
    }

    public String delete() throws Exception {
        TArticle article = this.articleService.getByNo(this.articleno);
        if(article == null) {
            this.addActionError(this.getText("errors.not.exsits.article"));
            return "freemarker_error";
        } else if(!this.checkRight(article)) {
            this.addActionError(this.getText("errors.right"));
            return "freemarker_error";
        } else {
            article.setDeleteflag(Boolean.valueOf(true));
            this.articleService.save(article);
            this.loadData();
            return "freemarker";
        }
    }

    public int getPageType() {
        return 30;
    }

    public String getTempName() {
        return "user/articleList";
    }
}
