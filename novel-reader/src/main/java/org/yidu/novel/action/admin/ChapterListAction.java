//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractAdminListBaseAction;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.utils.LoginManager;

@Action("chapterList")
public class ChapterListAction extends AbstractAdminListBaseAction {
    private static final long serialVersionUID = 4594082209171084359L;
    public static final String NAME = "chapterList";
    public static final String URL = "/admin/chapterList";
    private int articleno;
    private int chapterno;
    private TArticle article = new TArticle();
    private List<TChapter> chapterList = new ArrayList();

    public ChapterListAction() {
    }

    public int getArticleno() {
        return this.articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public int getChapterno() {
        return this.chapterno;
    }

    public void setChapterno(int chapterno) {
        this.chapterno = chapterno;
    }

    public TArticle getArticle() {
        return this.article;
    }

    public void setArticle(TArticle article) {
        this.article = article;
    }

    public List<TChapter> getChapterList() {
        return this.chapterList;
    }

    public void setChapterList(List<TChapter> chapterList) {
        this.chapterList = chapterList;
    }

    protected void loadData() {
        if(this.articleno == 0) {
            this.addActionError(this.getText("errors.required.input", new String[]{this.getText("label.admin.chapter.list.articleno")}));
        }

        this.article = this.articleService.getByNo(this.articleno);
        ChapterSearchBean searchBean = new ChapterSearchBean();
        BeanUtils.copyProperties(this, searchBean);
        this.chapterList = this.chapterService.find(searchBean);
    }

    public String delete() throws Exception {
        if(this.chapterno == 0) {
            this.addActionError(this.getText("errors.required.input", new String[]{this.getText("label.admin.chapter.list.chapterno")}));
        }

        TChapter chapter = this.chapterService.getByNo(this.chapterno);
        this.articleno = chapter.getArticleno().intValue();
        chapter.setDeleteflag(Boolean.valueOf(true));
        chapter.setModifyuserno(Integer.valueOf(LoginManager.getLoginUser().getUserno()));
        chapter.setModifytime(new Date());
        this.chapterService.save(chapter);
        TArticle article = this.articleService.getByNo(this.articleno);
        article.setSize(this.chapterService.getArticleSize(this.articleno));
        TChapter lastChapter = this.chapterService.getLastChapter(this.articleno);
        if(lastChapter != null) {
            article.setLastchapterno(Integer.valueOf(lastChapter.getChapterno()));
            article.setLastchapter(lastChapter.getChaptername());
            article.setLastupdate(lastChapter.getPostdate());
        }

        article.setSize(this.chapterService.getArticleSize(this.articleno));
        this.articleService.save(article);
        this.loadData();
        return "input";
    }
}
