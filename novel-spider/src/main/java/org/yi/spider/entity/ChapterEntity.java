//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.entity;

import org.yi.spider.entity.BaseEntity;

public class ChapterEntity extends BaseEntity implements Cloneable {
    private Integer chapterNo;
    private String chapterName;
    private Short chapterType;
    private Integer size;
    private Integer chapterOrder;

    public ChapterEntity() {
    }

    public ChapterEntity clone() throws CloneNotSupportedException {
        ChapterEntity cloned = (ChapterEntity)super.clone();
        return cloned;
    }

    public Integer getChapterNo() {
        return this.chapterNo;
    }

    public void setChapterNo(Integer chapterNo) {
        this.chapterNo = chapterNo;
    }

    public String getChapterName() {
        return this.chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public Short getChapterType() {
        return this.chapterType;
    }

    public void setChapterType(Short chapterType) {
        this.chapterType = chapterType;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getChapterOrder() {
        return this.chapterOrder;
    }

    public void setChapterOrder(Integer chapterOrder) {
        this.chapterOrder = chapterOrder;
    }
}
