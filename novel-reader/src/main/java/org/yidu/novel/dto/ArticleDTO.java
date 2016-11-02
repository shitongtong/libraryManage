//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.dto;

import org.yidu.novel.entity.TArticle;

public class ArticleDTO extends TArticle {
    private static final long serialVersionUID = 1386921210616354787L;

    public ArticleDTO() {
    }

    public String getIntroOmit() {
        return this.getIntro() != null && this.getIntro().length() > 60?this.getIntro().substring(0, 60) + "...":this.getIntro();
    }

    public String getLastchapterOmit() {
        return this.getLastchapter() != null && this.getLastchapter().length() > 10?this.getLastchapter().substring(0, 10):this.getLastchapter();
    }

    public static final class ArticleType {
        public static final int XUANHUAN = 1;
        public static final int WUXIA = 2;
        public static final int YANXIN = 3;
        public static final int JUSHI = 4;
        public static final int ZHENTAN = 5;
        public static final int WANGYOU = 6;
        public static final int KEHUAN = 7;
        public static final int KONGBU = 8;
        public static final int JUBEN = 9;
        public static final int OTHER = 10;

        public ArticleType() {
        }
    }
}
