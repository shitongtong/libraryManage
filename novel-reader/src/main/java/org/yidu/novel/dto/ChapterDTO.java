//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.dto;

import org.yidu.novel.entity.TChapter;

public class ChapterDTO extends TChapter {
    private static final long serialVersionUID = -9171385880720383954L;
    private int nextChapterno;
    private int preChapterno;
    private String content;

    public ChapterDTO() {
    }

    public int getNextChapterno() {
        return this.nextChapterno;
    }

    public void setNextChapterno(int nextChapterno) {
        this.nextChapterno = nextChapterno;
    }

    public int getPreChapterno() {
        return this.preChapterno;
    }

    public void setPreChapterno(int preChapterno) {
        this.preChapterno = preChapterno;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
