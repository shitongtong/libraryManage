//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.constant;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.configuration.PropertiesConfiguration;

public class YiDuConstants {
    public static PropertiesConfiguration yiduConf;
    public static LinkedMap categoryMap;
    public static final String ENCODING_UTF_8 = "UTF-8";
    public static final String ENCODING_GBK = "GBK";
    public static final int SUB_DIR_ARTICLES = 1000;
    private static final String Prefix = "yidu.Novel.";
    public static final String LoginUser = "yidu.Novel.LoginUser";
    public static final String currentPageLocale = "CURRENT_PAGE_LOCALE";
    public static final String[] allowPicTypes = new String[]{"image/jpeg", "image/png", "image/gif"};
    public static final String[] allowSampleTypes = new String[]{"text/plain", "application/kswps"};

    public YiDuConstants() {
    }

    public static final class BlockTarget {
        public static final short ARTICLE_LIST = 1;
        public static final short ARTICLE_DETAIL = 2;
        public static final short CHAPTER_LIST = 3;
        public static final short READER_PAGE = 4;
        public static final short USER_DETAIL = 5;
        public static final short INDEX = 6;

        public BlockTarget() {
        }
    }

    public static final class BlockType {
        public static final short ARTICLE_LIST = 10;
        public static final short CUSTONIZE_ARTICLE_LIST = 20;
        public static final short HTML = 30;

        public BlockType() {
        }
    }

    public static final class ImageType {
        public static final int JPG = 1;
        public static final int GIF = 2;
        public static final int PNG = 3;

        public ImageType() {
        }
    }

    public static final class ImgageMateType {
        public static final String JPG = "image/jpeg";
        public static final String PNG = "image/png";
        public static final String GIF = "image/gif";

        public ImgageMateType() {
        }
    }

    public static final class Pagetype {
        public static final int PAGE_INDEX = 1;
        public static final int PAGE_ARTICLE_LIST = 2;
        public static final int PAGE_ARTICLE_INFO = 3;
        public static final int PAGE_READER = 4;
        public static final int PAGE_SEARCH = 5;
        public static final int PAGE_CATEGORY = 6;
        public static final int PAGE_TOP = 7;
        public static final int PAGE_LOGIN = 11;
        public static final int PAGE_REGEDIT = 12;
        public static final int PAGE_USER_BOOKCASE = 21;
        public static final int PAGE_USER_MESSAGE = 22;
        public static final int PAGE_USER_USEREDIT = 23;
        public static final int PAGE_REGI_AUTHOR = 24;
        public static final int PAGE_USER_CENTER = 25;
        public static final int PAGE_BOOKCASE = 26;
        public static final int PAGE_AUTHER_ARTICLE_LIST = 30;
        public static final int PAGE_AUTHER_ARTICLE_EDIT = 31;
        public static final int PAGE_AUTHER_CHAPTER_LIST = 32;
        public static final int PAGE_AUTHER_CHAPTER_EDIT = 33;
        public static final int PAGE_AUTHER_BILL_DETAIL = 40;
        public static final int PAGE_OTHERS = 99;

        public Pagetype() {
        }
    }

    public static final class Regex {
        public static final String DATE = "^(?:((?!0000)[0-9]{4}/(?:(?:0[1-9]|1[0-2])/(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])/(?:29|30)|(?:0[13578]|1[02])/31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)/02/29)?)$";
        public static final String ALPHANUMERIC = "^[A-Za-z0-9]*$";
        public static final String NUMBER = "^\\d*$";
        public static final String EMAIL = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";

        public Regex() {
        }
    }

    public static final class ResponseStatus {
        public static final int SUCCESS = 200;
        public static final int FAILED = 400;

        public ResponseStatus() {
        }
    }

    public static final class UserGroup {
        public static final int GUEST = 1;
        public static final int ADMIN = 2;
        public static final int NORMARL = 3;
        public static final int AD = 4;
        public static final int VIP = 5;
        public static final int AUTHOR = 6;
        public static final int AUTHOR2 = 7;
        public static final int KONGBU = 8;
        public static final int JUBEN = 9;
        public static final int OTHER = 10;

        public UserGroup() {
        }
    }

    public static final class UserType {
        public static final short NORMAL_USER = 10;
        public static final int AUTHER = 20;
        public static final int VIP = 25;
        public static final int ADMINISTRATOR = 30;
        public static final int EDITOR = 40;

        public UserType() {
        }
    }
}
