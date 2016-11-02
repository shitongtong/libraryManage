//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractPublicListBaseAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.entity.TArticle;

@Action("siteMap")
public class SiteMapAction extends AbstractPublicListBaseAction {
    private static final long serialVersionUID = -3069730816500421029L;
    public static final String NAME = "siteMap";
    public static final String URL = "/siteMap";
    List<TArticle> articleList = new ArrayList();

    public SiteMapAction() {
    }

    public List<TArticle> getArticleList() {
        return this.articleList;
    }

    public void setArticleList(List<TArticle> articleList) {
        this.articleList = articleList;
    }

    public int getPageType() {
        return 0;
    }

    public String getTempName() {
        return "siteMap";
    }

    protected void loadData() {
        this.articleList = this.articleService.find(new ArticleSearchBean());
    }
}
