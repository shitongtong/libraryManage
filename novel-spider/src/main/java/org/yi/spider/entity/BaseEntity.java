//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.entity;

import java.util.Date;

public class BaseEntity {
    private Number novelNo;
    private String novelName;
    private Date postDate;

    public BaseEntity() {
    }

    public Number getNovelNo() {
        return this.novelNo;
    }

    public void setNovelNo(Number novelNo) {
        this.novelNo = novelNo;
    }

    public String getNovelName() {
        return this.novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public Date getPostDate() {
        return this.postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
}
