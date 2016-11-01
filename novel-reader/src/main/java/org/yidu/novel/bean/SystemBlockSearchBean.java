//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.bean;

import org.yidu.novel.bean.BaseSearchBean;

public class SystemBlockSearchBean extends BaseSearchBean {
    private int blockno;
    private String blockname;
    private Short type;
    private String sortcol;
    private Boolean isasc;
    private Integer limitnum;
    private String content;
    private Short target;

    public SystemBlockSearchBean() {
    }

    public int getBlockno() {
        return this.blockno;
    }

    public void setBlockno(int blockno) {
        this.blockno = blockno;
    }

    public String getBlockname() {
        return this.blockname;
    }

    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }

    public Short getType() {
        return this.type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getSortcol() {
        return this.sortcol;
    }

    public void setSortcol(String sortcol) {
        this.sortcol = sortcol;
    }

    public Boolean getIsasc() {
        return this.isasc;
    }

    public void setIsasc(Boolean isasc) {
        this.isasc = isasc;
    }

    public Integer getLimitnum() {
        return this.limitnum;
    }

    public void setLimitnum(Integer limitnum) {
        this.limitnum = limitnum;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Short getTarget() {
        return this.target;
    }

    public void setTarget(Short target) {
        this.target = target;
    }
}
