//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.entity;

import java.util.Date;
import org.yi.spider.entity.BaseEntity;

public class NovelEntity extends BaseEntity {
    private String keywords;
    private String initial;
    private String author;
    private Integer topCategory;
    private Integer subCategory;
    private String intro;
    private Integer lastChapterno;
    private String lastChapterName;
    private Integer chapters;
    private Integer size;
    private Boolean fullFlag;
    private Integer imgFlag;
    private Date lastUpdate;
    private String pinyin;

    public NovelEntity() {
    }

    public String getKeywords() {
        return this.keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getInitial() {
        return this.initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getTopCategory() {
        return this.topCategory;
    }

    public void setTopCategory(Integer topCategory) {
        this.topCategory = topCategory;
    }

    public Integer getSubCategory() {
        return this.subCategory;
    }

    public void setSubCategory(Integer subCategory) {
        this.subCategory = subCategory;
    }

    public String getIntro() {
        return this.intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getLastChapterno() {
        return this.lastChapterno;
    }

    public void setLastChapterno(Integer lastChapterno) {
        this.lastChapterno = lastChapterno;
    }

    public String getLastChapterName() {
        return this.lastChapterName;
    }

    public void setLastChapterName(String lastChapterName) {
        this.lastChapterName = lastChapterName;
    }

    public Integer getChapters() {
        return this.chapters;
    }

    public void setChapters(Integer chapters) {
        this.chapters = chapters;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getFullFlag() {
        return this.fullFlag;
    }

    public void setFullFlag(Boolean fullFlag) {
        this.fullFlag = fullFlag;
    }

    public Integer getImgFlag() {
        return this.imgFlag;
    }

    public void setImgFlag(Integer imgFlag) {
        this.imgFlag = imgFlag;
    }

    public Date getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getPinyin() {
        return this.pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
