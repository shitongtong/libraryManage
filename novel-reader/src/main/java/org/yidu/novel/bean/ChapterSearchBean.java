//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.bean;

import org.yidu.novel.bean.BaseSearchBean;

public class ChapterSearchBean extends BaseSearchBean {
    private int articleno;
    private int chapterno;
    private String chapternos;

    public ChapterSearchBean() {
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

    public String getChapternos() {
        return this.chapternos;
    }

    public void setChapternos(String chapternos) {
        this.chapternos = chapternos;
    }
}
