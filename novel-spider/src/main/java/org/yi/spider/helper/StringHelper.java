//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.helper;

import org.yi.spider.helper.ParseHelper;
import org.yi.spider.model.CollectParam;
import org.yi.spider.utils.StringUtils;

public class StringHelper {
    private static final String REGEX_DOMAIN = ".+\\..+/.+";

    public StringHelper() {
    }

    public static String getRemoteChapterUrl(String chapterUrl, String novelPubKeyURL, String novelNo, String cno, CollectParam cpm) throws Exception {
        if(StringUtils.isBlank(chapterUrl)) {
            return "";
        } else {
            chapterUrl = chapterUrl.replace("{ChapterKey}", cno);
            chapterUrl = ParseHelper.getAssignURL(chapterUrl, novelNo);
            if(!chapterUrl.startsWith("/") && !"http".equalsIgnoreCase(chapterUrl.substring(0, 4)) && !chapterUrl.matches(".+\\..+/.+")) {
                chapterUrl = novelPubKeyURL.substring(0, novelPubKeyURL.lastIndexOf("/") + 1) + chapterUrl;
            }

            chapterUrl = StringUtils.getFullUrl(cpm.getRemoteSite().getSiteUrl(), chapterUrl);
            return chapterUrl;
        }
    }

    public static String getCoverSuffix(int flag) {
        String suffix = "";
        switch(flag) {
            case 0:
            default:
                break;
            case 1:
                suffix = ".jpg";
                break;
            case 2:
                suffix = ".gif";
                break;
            case 3:
                suffix = ".png";
        }

        return suffix;
    }

    public static Integer getImgFlag(String novelCover) {
        Integer flag = Integer.valueOf(0);
        if(novelCover != null && !novelCover.isEmpty()) {
            int index = novelCover.lastIndexOf(".");
            if(index > 0) {
                String suffix = novelCover.substring(index, novelCover.length());
                if(".JPG".equalsIgnoreCase(suffix)) {
                    flag = Integer.valueOf(1);
                } else if(".GIF".equalsIgnoreCase(suffix)) {
                    flag = Integer.valueOf(2);
                } else if(".PNG".equalsIgnoreCase(suffix)) {
                    flag = Integer.valueOf(3);
                } else {
                    flag = Integer.valueOf(0);
                }
            }
        }

        return flag;
    }
}
