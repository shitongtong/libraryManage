//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.entity;

import java.io.Serializable;
import java.util.Date;

public class TSystemBlock implements Serializable {
    private static final long serialVersionUID = 7141718640888562408L;
    private int blockno;
    private String blockid;
    private String blockname;
    private Short type;
    private String sortcol;
    private String isasc;
    private Integer limitnum;
    private String content;
    private Short target;
    private String deleteflag;
    private Integer modifyuserno;
    private Date modifytime;

    public TSystemBlock() {
    }

    public TSystemBlock(int blockno) {
        this.blockno = blockno;
    }

    public int getBlockno() {
        return this.blockno;
    }

    public void setBlockno(int blockno) {
        this.blockno = blockno;
    }

    public String getBlockid() {
        return this.blockid;
    }

    public void setBlockid(String blockid) {
        this.blockid = blockid;
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

    public String getIsasc() {
        return this.isasc;
    }

    public void setIsasc(String isasc) {
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

    public String getDeleteflag() {
        return this.deleteflag;
    }

    public void setDeleteflag(String deleteflag) {
        this.deleteflag = deleteflag;
    }

    public Integer getModifyuserno() {
        return this.modifyuserno;
    }

    public void setModifyuserno(Integer modifyuserno) {
        this.modifyuserno = modifyuserno;
    }

    public Date getModifytime() {
        return this.modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }
}
