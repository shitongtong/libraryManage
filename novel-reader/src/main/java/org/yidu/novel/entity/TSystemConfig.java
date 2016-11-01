//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.entity;

import java.io.Serializable;
import java.util.Date;

public class TSystemConfig implements Serializable {
    private static final long serialVersionUID = 1896384275550616445L;
    private int configno;
    private String name;
    private String title;
    private String description;
    private Short type;
    private String options;
    private String catname;
    private Boolean deleteflag;
    private Integer modifyuserno;
    private Date modifytime;

    public TSystemConfig() {
    }

    public TSystemConfig(int configno) {
        this.configno = configno;
    }

    public int getConfigno() {
        return this.configno;
    }

    public void setConfigno(int configno) {
        this.configno = configno;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getType() {
        return this.type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getOptions() {
        return this.options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getCatname() {
        return this.catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public Boolean getDeleteflag() {
        return this.deleteflag;
    }

    public void setDeleteflag(Boolean deleteflag) {
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
