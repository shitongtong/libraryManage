//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.script.ScriptException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yi.spider.constants.Constants;
import org.yi.spider.constants.GlobalConfig;
import org.yi.spider.entity.NovelEntity;
import org.yi.spider.enums.CategoryGradeEnum;
import org.yi.spider.exception.BaseException;
import org.yi.spider.helper.FileHelper;
import org.yi.spider.helper.HttpHelper;
import org.yi.spider.helper.StringHelper;
import org.yi.spider.model.Category;
import org.yi.spider.model.CollectParam;
import org.yi.spider.model.Rule;
import org.yi.spider.utils.HttpUtils;
import org.yi.spider.utils.PatternUtils;
import org.yi.spider.utils.ScriptUtils;
import org.yi.spider.utils.StringUtils;

public class ParseHelper {
    private static final Logger logger = LoggerFactory.getLogger(ParseHelper.class);

    public ParseHelper() {
    }

    public static String get(String source, Rule pattern) {
        return StringUtils.trimToEmpty(PatternUtils.getValue(source, pattern));
    }

    public static String getSource(CollectParam cpm, String destURL) throws IOException {
        CloseableHttpClient httpClient = HttpUtils.buildClient(Constants.TEST_TIMEOUT.intValue());
        String source = getSource(httpClient, cpm, destURL);
        httpClient.close();
        return source;
    }

    public static String getSource(CloseableHttpClient httpClient, CollectParam cpm, String destURL) {
        logger.debug("获取源文件， 目标地址： " + destURL);
        String content = "";

        try {
            content = HttpHelper.getContent(httpClient, destURL, cpm.getRemoteSite().getCharset());
        } catch (Exception var5) {
            throw new BaseException("小说名为空, 目标链接：" + destURL);
        }

        if(StringUtils.isBlank(content)) {
            throw new BaseException("小说名为空, 目标链接：" + destURL);
        } else {
            return content;
        }
    }

    public static String getRuleVersion(CollectParam cpm) {
        return ((Rule)cpm.getRuleMap().get("RuleVersion")).getPattern();
    }

    public static String getSiteName(CollectParam cpm) {
        return ((Rule)cpm.getRuleMap().get("GetSiteName")).getPattern();
    }

    public static String getSiteCharset(CollectParam cpm) {
        return ((Rule)cpm.getRuleMap().get("GetSiteCharset")).getPattern();
    }

    public static String getSiteUrl(CollectParam cpm) {
        return ((Rule)cpm.getRuleMap().get("GetSiteUrl")).getPattern();
    }

    public static String getNovelName(String infoSource, CollectParam cpm) {
        return StringUtils.trimToEmpty(PatternUtils.getValue(infoSource, (Rule)cpm.getRuleMap().get("NovelName")));
    }

    public static String getNovelAuthor(String infoSource, CollectParam cpm) {
        return get(infoSource, (Rule)cpm.getRuleMap().get("NovelAuthor"));
    }

    public static String getAssignURL(String assignURL, String novelNo) throws ScriptException {
        String result = assignURL.replace("{NovelKey}", novelNo).replace("{NovelPubKey}", novelNo).replace("NovelKey", novelNo);
        if(result.indexOf("{") > 0) {
            String express = result.substring(result.indexOf("{") + 1, result.indexOf("}"));
            String novelKey = String.valueOf(ScriptUtils.evalInt(express, (Map)null));
            result = result.replaceAll("\\{.*\\}", novelKey);
        }

        return result;
    }

    private static Category getCategoryObj(String catStr, CategoryGradeEnum top) {
        Category category = null;
        if(StringUtils.isNotBlank(catStr)) {
            new ArrayList();
            List cats;
            if(top == CategoryGradeEnum.TOP) {
                cats = GlobalConfig.TOP_CATEGORY;
            } else {
                cats = GlobalConfig.SUB_CATEGORY;
            }

            Iterator var5 = cats.iterator();

            while(var5.hasNext()) {
                Category c = (Category)var5.next();
                if(c.getWords().contains(catStr)) {
                    category = c;
                    break;
                }
            }
        }

        return category;
    }

    public static Integer getCategory(String catStr, CategoryGradeEnum top) {
        Category c = getCategoryObj(catStr, top);
        return c == null?Integer.valueOf(GlobalConfig.collect.getInt("default_category", 10)):Integer.valueOf(Integer.parseInt(c.getId()));
    }

    public static Integer getNovelCover(NovelEntity novel, String infoSource, CollectParam cpm) throws Exception {
        String novelCover = getNovelCoverURL(infoSource, cpm);
        Integer imgFlag;
        if(novelCover != null && !novelCover.isEmpty()) {
            String suffix = novelCover.substring(novelCover.lastIndexOf("."), novelCover.length());
            novelCover = StringUtils.getFullUrl(getSiteUrl(cpm), novelCover);
            FileHelper.downImage(novelCover, novel, suffix);
            imgFlag = StringHelper.getImgFlag(novelCover);
        } else {
            imgFlag = Integer.valueOf(0);
        }

        return imgFlag;
    }

    public static String getNovelCoverURL(String infoSource, CollectParam cpm) {
        String novelCover = PatternUtils.getValue(infoSource, (Rule)cpm.getRuleMap().get("NovelCover"));
        if(StringUtils.isBlank(novelCover)) {
            String novelDefaultCoverUrl = ((Rule)cpm.getRuleMap().get("NovelDefaultCoverUrl")).getPattern();
            if(novelDefaultCoverUrl != null && !novelDefaultCoverUrl.isEmpty()) {
                novelCover = novelDefaultCoverUrl;
            }
        }

        return novelCover;
    }

    public static String getInfoRUL(CollectParam cpm, String novelNo) throws ScriptException {
        String infoURL = "";
        String assignURL = ((Rule)cpm.getRuleMap().get("NovelUrl")).getPattern();
        if(StringUtils.isNotBlank(assignURL)) {
            infoURL = getAssignURL(assignURL, novelNo);
        }

        return infoURL;
    }

    public static String getTopCategory(String infoSource, CollectParam cpm) {
        return get(infoSource, (Rule)cpm.getRuleMap().get("LagerSort"));
    }

    public static String getSubCategory(String infoSource, CollectParam cpm) {
        return get(infoSource, (Rule)cpm.getRuleMap().get("SmallSort"));
    }

    public static String getNovelIntro(String infoSource, CollectParam cpm) {
        String novelIntro = get(infoSource, (Rule)cpm.getRuleMap().get("NovelIntro"));
        if(StringUtils.isNotBlank(novelIntro)) {
            novelIntro = StringUtils.replaceHtml(novelIntro);
            novelIntro = StringUtils.removeBlankLine(novelIntro);
        }

        return novelIntro;
    }

    public static String getNovelKeywrods(String infoSource, CollectParam cpm) {
        return get(infoSource, (Rule)cpm.getRuleMap().get("NovelKeyword"));
    }

    public static String getNovelDegree(String infoSource, CollectParam cpm) {
        return get(infoSource, (Rule)cpm.getRuleMap().get("NovelDegree"));
    }

    public static String getNovelMenuURL(String infoSource, String novelNo, CollectParam cpm) throws Exception {
        String novelPubKey = get(infoSource, (Rule)cpm.getRuleMap().get("NovelInfo_GetNovelPubKey"));
        if(StringUtils.isBlank(novelPubKey)) {
            novelPubKey = ((Rule)cpm.getRuleMap().get("PubIndexUrl")).getPattern();
            if(StringUtils.isBlank(novelPubKey)) {
                throw new BaseException("无法从页面获取目录页地址， 需要在规则中PubIndexUrl项指定目录页地址");
            }

            novelPubKey = getAssignURL(novelPubKey, novelNo);
        }

        return StringUtils.isBlank(novelPubKey)?novelPubKey:StringUtils.getFullUrl(cpm.getRemoteSite().getSiteUrl(), novelPubKey);
    }

    public static String getNovelInfoExtra(String infoSource, CollectParam cpm) {
        return get(infoSource, (Rule)cpm.getRuleMap().get("NovelInfoExtra"));
    }

    public static String getChapterListSource(String novelPubKeyURL, CollectParam cpm) throws Exception {
        CloseableHttpClient httpClient = HttpUtils.buildClient(Constants.TEST_TIMEOUT.intValue());
        String menuSource = HttpHelper.getContent(httpClient, novelPubKeyURL, cpm.getRemoteSite().getCharset());
        Rule pubIndexContentRule = (Rule)cpm.getRuleMap().get("PubMenuContent");
        if(pubIndexContentRule != null) {
            menuSource = get(menuSource, pubIndexContentRule);
        }

        httpClient.close();
        return menuSource;
    }

    public static List<String> getChapterNameList(String menuSource, CollectParam cpm) {
        return PatternUtils.getValues(menuSource, (Rule)cpm.getRuleMap().get("PubChapterName"));
    }

    public static List<String> getChapterNoList(String menuSource, CollectParam cpm) {
        return PatternUtils.getValues(menuSource, (Rule)cpm.getRuleMap().get("PubChapter_GetChapterKey"));
    }

    public static String getChapterURL(String novelPubKeyURL, String novelNo, String cno, CollectParam cpm) throws Exception {
        String chapterURL = ((Rule)cpm.getRuleMap().get("PubContentUrl")).getPattern();
        return StringHelper.getRemoteChapterUrl(chapterURL, novelPubKeyURL, novelNo, cno, cpm);
    }

    public static String getChapterSource(String chapterURL, CollectParam cpm) throws Exception {
        CloseableHttpClient httpClient = HttpUtils.buildClient(Constants.TEST_TIMEOUT.intValue());
        String source = HttpHelper.getContent(httpClient, chapterURL, cpm.getRemoteSite().getCharset());
        httpClient.close();
        return source;
    }

    public static String getChapterContent(String chapterSource, CollectParam cpm) {
        String chapterContent = get(chapterSource, (Rule)cpm.getRuleMap().get("PubContentText"));
        chapterContent = StringUtils.removeBlankLine(chapterContent);
        chapterContent = StringUtils.replaceHtml(chapterContent);
        return chapterContent;
    }
}
