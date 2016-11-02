//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.user;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractUserListBaseAction;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TChapter;

@Action("chapterList")
public class ChapterListAction extends AbstractUserListBaseAction {
    private static final long serialVersionUID = 4594082209171084359L;
    public static final String NAME = "chapterList";
    public static final String URL = "/user/chapterList";
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
        this.article = this.articleService.getByNo(this.articleno);
        if(!this.checkRight(this.article)) {
            this.addActionError(this.getText("errors.right"));
        } else {
            ChapterSearchBean searchBean = new ChapterSearchBean();
            BeanUtils.copyProperties(this, searchBean);
            this.chapterList = this.chapterService.find(searchBean);
        }
    }

    public String delete() throws Exception {
        TChapter chapter = this.chapterService.getByNo(this.chapterno);
        if(chapter != null) {
            this.articleno = chapter.getArticleno().intValue();
            TArticle article = this.articleService.getByNo(chapter.getArticleno().intValue());
            if(!this.checkRight(article)) {
                this.addActionError(this.getText("errors.right"));
                return "freemarker_error";
            }

            chapter.setDeleteflag(Boolean.valueOf(true));
            this.chapterService.save(chapter);
        }

        this.loadData();
        return "freemarker";
    }

    public int getPageType() {
        return 32;
    }

    public String getTempName() {
        return "user/chapterList";
    }
}
