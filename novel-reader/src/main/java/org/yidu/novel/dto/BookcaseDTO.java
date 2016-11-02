//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.dto;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TBookcase;

public class BookcaseDTO extends TBookcase {
    private static final long serialVersionUID = 2193825089517868949L;
    private Integer lastchapterno;
    private String lastchapter;
    private Integer chapters;
    private Integer size;
    private Boolean fullflag;
    private Date lastupdate;
    private Integer imgflag;

    public BookcaseDTO() {
    }

    public Integer getLastchapterno() {
        return this.lastchapterno;
    }

    public void setLastchapterno(Integer lastchapterno) {
        this.lastchapterno = lastchapterno;
    }

    public String getLastchapter() {
        return this.lastchapter;
    }

    public void setLastchapter(String lastchapter) {
        this.lastchapter = lastchapter;
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

    public Boolean getFullflag() {
        return this.fullflag;
    }

    public void setFullflag(Boolean fullflag) {
        this.fullflag = fullflag;
    }

    public Date getLastupdate() {
        return this.lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public Integer getImgflag() {
        return this.imgflag;
    }

    public void setImgflag(Integer imgflag) {
        this.imgflag = imgflag;
    }

    public String getImgUrl() {
        String fileName = "";
        if(this.imgflag == null) {
            fileName = "nocover.jpg";
        } else {
            switch(this.imgflag.intValue()) {
                case 1:
                    fileName = this.getArticleno() + "s.jpg";
                    break;
                case 2:
                    fileName = this.getArticleno() + "s.gif";
                    break;
                case 3:
                    fileName = this.getArticleno() + "s.png";
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                default:
                    fileName = "nocover.jpg";
                    break;
                case 10:
                    fileName = this.getArticleno() + "l.jpg";
            }
        }

        String imgUrl = YiDuConstants.yiduConf.getString("relativeIamgePath") + "/";
        if(StringUtils.equals("nocover.jpg", fileName)) {
            imgUrl = imgUrl + fileName;
        } else {
            imgUrl = imgUrl + this.getArticleno().intValue() / 1000 + "/" + this.getArticleno() + "/" + fileName;
        }

        return imgUrl;
    }
}
