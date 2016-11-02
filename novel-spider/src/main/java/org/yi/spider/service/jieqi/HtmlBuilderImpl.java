//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.service.jieqi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yi.spider.constants.GlobalConfig;
import org.yi.spider.entity.ChapterEntity;
import org.yi.spider.entity.NovelEntity;
import org.yi.spider.enums.CategoryGradeEnum;
import org.yi.spider.helper.FileHelper;
import org.yi.spider.model.Category;
import org.yi.spider.model.PreNextChapter;
import org.yi.spider.service.IHtmlBuilder;
import org.yi.spider.utils.FileUtils;
import org.yi.spider.utils.PatternUtils;
import org.yi.spider.utils.ScriptUtils;
import org.yi.spider.utils.StringUtils;

public class HtmlBuilderImpl implements IHtmlBuilder {
    private static final Logger logger = LoggerFactory.getLogger(HtmlBuilderImpl.class);
    private static final String LOOP_START = "{?section";
    private static final String LOOP_END = "{?/section?}";
    private static final String PARSE_CNT_START = "{?else?}";
    private static final String PARSE_CNT_END = "{?/if?}";
    private static final String pattern = "[\\s\\S]+(<if[\\s\\S]+<else[\\s\\S]+</if)[\\s\\S]+";
    private static final String conditionPattern = "<if(.+?)>";
    private static final String ifPattern = "<if.+?>([\\s\\S]+)<else";
    private static final String elsePattern = "<else([\\s\\S]+)</if";

    public HtmlBuilderImpl() {
    }

    public void buildChapterListHtml(NovelEntity novel, List<ChapterEntity> chapterList) {
        String localPath = FileHelper.getHtmlFilePath(novel, (ChapterEntity)null);
        String dir = localPath.substring(0, localPath.lastIndexOf("/"));
        if(!(new File(dir)).exists()) {
            (new File(dir)).mkdirs();
        }

        String template = GlobalConfig.localSite.getTemplate().getChapter();

        try {
            FileInputStream e = new FileInputStream(template);
            int lenght = e.available();
            byte[] bytes = new byte[lenght];
            e.read(bytes);
            e.close();
            String templateContent = new String(bytes, GlobalConfig.localSite.getCharset());
            if(templateContent.indexOf("{?section") > 0) {
                int modulesArticleUrl = templateContent.indexOf("{?section");
                int fos = templateContent.indexOf("{?/section?}") + "{?/section?}".length();
                String tag_bytes = templateContent.substring(modulesArticleUrl, fos);
                templateContent = templateContent.substring(0, modulesArticleUrl) + this.getArticleIndexLoop(tag_bytes, novel, chapterList) + templateContent.substring(fos, templateContent.length());
            }

            templateContent = templateContent.replace("{?$articleid?}", String.valueOf(novel.getNovelNo()));
            templateContent = templateContent.replace("{?$article_id?}", String.valueOf(novel.getNovelNo()));
            templateContent = templateContent.replace("{?$intro?}", String.valueOf(novel.getIntro()));
            templateContent = templateContent.replace("{?$article_title?}", novel.getNovelName());
            templateContent = templateContent.replace("{?$jieqi_sitename?}", GlobalConfig.localSite.getSiteName());
            templateContent = templateContent.replace("{?$jieqi_url?}", GlobalConfig.localSite.getSiteUrl());
            templateContent = templateContent.replace("{?$jieqi_main_url?}", GlobalConfig.localSite.getSiteUrl());
            templateContent = templateContent.replace("{?$jieqi_local_url?}", GlobalConfig.localSite.getSiteUrl());
            templateContent = templateContent.replace("{?$jieqi_charset?}", GlobalConfig.localSite.getCharset());
            String modulesArticleUrl1 = GlobalConfig.localSite.getSiteUrl() + "/modules/article/";
            templateContent = templateContent.replace("{?$jieqi_modules[\'article\'][\'url\']?}", modulesArticleUrl1);
            templateContent = templateContent.replace("{?$url_bookroom?}", modulesArticleUrl1);
            templateContent = templateContent.replace("{?$dynamic_url?}", modulesArticleUrl1);
            templateContent = templateContent.replace("{?$sortid?}", String.valueOf(novel.getTopCategory()));
            templateContent = templateContent.replace("{?$sortname?}", this.getCategoryById(String.valueOf(novel.getTopCategory()), CategoryGradeEnum.TOP).getName());
            templateContent = templateContent.replace("{?$author?}", novel.getAuthor());
            templateContent = templateContent.replace("{?$meta_author?}", novel.getAuthor());
            templateContent = templateContent.replace("{pinyin}", StringUtils.isBlank(novel.getPinyin())?"":novel.getPinyin());
            templateContent = templateContent.replace("{?$3ey_pyh?}", StringUtils.isBlank(novel.getPinyin())?"":novel.getPinyin());
            templateContent = templateContent.replace("{?$url_articleinfo?}", this.getTrueURL(GlobalConfig.localSite.getTemplate().getInfoURL(), novel, (ChapterEntity)null));
            FileOutputStream fos1 = new FileOutputStream(localPath);
            byte[] tag_bytes1 = templateContent.getBytes(GlobalConfig.localSite.getCharset());
            fos1.write(tag_bytes1);
            fos1.close();
        } catch (Exception var13) {
            logger.error("生成[{}]目录页异常： ", novel.getNovelName(), var13.getMessage());
            var13.printStackTrace();
        }

    }

    private String getArticleIndexLoop(String loopContent, NovelEntity novel, List<ChapterEntity> chapterList) {
        int startIndex = loopContent.indexOf("cname") + 1;
        String temp = loopContent.substring(0, startIndex);
        startIndex = temp.lastIndexOf("{?else?}") + "{?else?}".length();
        int endIndex = loopContent.lastIndexOf("{?/if?}");
        String content = loopContent.substring(startIndex, endIndex);
        Integer rowSize = GlobalConfig.localSite.getTemplate().getRowSize();
        int loopSize = chapterList.size() % rowSize.intValue() == 0?chapterList.size() / rowSize.intValue():chapterList.size() / rowSize.intValue() + 1;
        StringBuffer loopBuffer = new StringBuffer();
        content = content.replace("{?$", "#").replace("?}\"", "\"").replace("$", "#").replace("{?", "<").replace("\"?}", "\">").replace("?}", "");

        for(int k = 0; k < loopSize; ++k) {
            int _rowSize = rowSize.intValue();
            if(_rowSize > chapterList.size() - k * rowSize.intValue()) {
                _rowSize = chapterList.size() - k * rowSize.intValue();
            }

            String newContent = content;

            int i;
            for(i = 0; i < _rowSize; ++i) {
                ChapterEntity chapter = (ChapterEntity)chapterList.get(k * rowSize.intValue() + i);
                String cName = chapter.getChapterName();
                String cUrl = GlobalConfig.localSite.getTemplate().getReaderURL().replace("#subDir#", String.valueOf(chapter.getNovelNo().intValue() / 1000)).replace("#articleNo#", String.valueOf(chapter.getNovelNo())).replace("#chapterNo#", String.valueOf(chapter.getChapterNo()));
                if(GlobalConfig.localSite.getUsePinyin().intValue() == 1) {
                    cUrl = cUrl.replace("#pinyin#", StringUtils.isBlank(novel.getPinyin())?"":novel.getPinyin());
                }

                newContent = newContent.replace("#indexrows[i].cname" + (i + 1), "#0#" + cName + "#0#").replace("#indexrows[i].curl" + (i + 1), cUrl);
            }

            for(i = _rowSize; i < rowSize.intValue(); ++i) {
                newContent = newContent.replace("#indexrows[i].cname" + (i + 1), "#0##0#").replace("#indexrows[i].curl" + (i + 1), "");
            }

            while(PatternUtils.match(newContent, "[\\s\\S]+(<if[\\s\\S]+<else[\\s\\S]+</if)[\\s\\S]+")) {
                String ifelseStr = PatternUtils.getValue(newContent, "[\\s\\S]+(<if[\\s\\S]+<else[\\s\\S]+</if)[\\s\\S]+");
                String condStr = PatternUtils.getValue(ifelseStr, "<if(.+?)>").replace("#0#", "\"");
                String ifStr = PatternUtils.getValue(ifelseStr, "<if.+?>([\\s\\S]+)<else");
                ifStr = StringUtils.isBlank(ifStr)?"":ifStr;
                String elseStr = PatternUtils.getValue(ifelseStr, "<else([\\s\\S]+)</if");
                elseStr = StringUtils.isBlank(elseStr)?"":elseStr;
                if(ScriptUtils.evalBoolean(condStr, (Map)null).booleanValue()) {
                    newContent = newContent.replace(ifelseStr, ifStr.replace("#0#", "\""));
                } else {
                    newContent = newContent.replace(ifelseStr, elseStr.replace("#0#", ""));
                }
            }

            loopBuffer.append(newContent);
        }

        return loopBuffer.toString();
    }

    public void buildChapterCntHtml(NovelEntity novel, ChapterEntity chapter, String content, PreNextChapter preNext) {
        int novelNo = novel.getNovelNo().intValue();
        String localPath = FileHelper.getHtmlFilePath(novel, chapter);
        String dir = localPath.substring(0, localPath.lastIndexOf("/"));
        if(!(new File(dir)).exists()) {
            (new File(dir)).mkdirs();
        }

        String template = GlobalConfig.localSite.getTemplate().getReader();

        try {
            FileInputStream e = new FileInputStream(template);
            int lenght = e.available();
            byte[] bytes = new byte[lenght];
            e.read(bytes);
            e.close();
            String templateContent = new String(bytes, GlobalConfig.localSite.getCharset());
            templateContent = templateContent.replace("{?$articleid?}", String.valueOf(novelNo));
            templateContent = templateContent.replace("{?$chapterid?}", String.valueOf(chapter.getChapterNo()));
            templateContent = templateContent.replace("{?$article_id?}", String.valueOf(novelNo));
            templateContent = templateContent.replace("{?$chapter_id?}", String.valueOf(chapter.getChapterNo()));
            templateContent = templateContent.replace("{?$jieqi_title?}", chapter.getChapterName());
            templateContent = templateContent.replace("{?$jieqi_chapter?}", chapter.getChapterName());
            templateContent = templateContent.replace("{?$jieqi_volume?}", "正文");
            templateContent = templateContent.replace("{?$article_title?}", novel.getNovelName());
            templateContent = templateContent.replace("{?$jieqi_sitename?}", GlobalConfig.localSite.getSiteName());
            templateContent = templateContent.replace("{?$jieqi_url?}", GlobalConfig.localSite.getSiteUrl());
            templateContent = templateContent.replace("{?$jieqi_main_url?}", GlobalConfig.localSite.getSiteUrl());
            templateContent = templateContent.replace("{?$jieqi_local_url?}", GlobalConfig.localSite.getSiteUrl());
            templateContent = templateContent.replace("{?$jieqi_charset?}", GlobalConfig.localSite.getCharset());
            String modulesArticleUrl = GlobalConfig.localSite.getSiteUrl() + "/modules/article/";
            templateContent = templateContent.replace("{?$jieqi_modules[\'article\'][\'url\']?}", modulesArticleUrl);
            templateContent = templateContent.replace("{?$url_bookroom?}", modulesArticleUrl);
            templateContent = templateContent.replace("{?$dynamic_url?}", modulesArticleUrl);
            templateContent = templateContent.replace("{?$sortid?}", String.valueOf(novel.getTopCategory()));
            templateContent = templateContent.replace("{?$sortname?}", this.getCategoryById(String.valueOf(novel.getTopCategory()), CategoryGradeEnum.TOP).getName());
            content = StringUtils.str2Html(content);
            templateContent = templateContent.replace("{?$jieqi_content?}", content);
            templateContent = templateContent.replace("{?$author?}", novel.getAuthor());
            templateContent = templateContent.replace("{?$meta_author?}", novel.getAuthor());
            templateContent = templateContent.replace("{pinyin}", StringUtils.isBlank(novel.getPinyin())?"":novel.getPinyin());
            templateContent = templateContent.replace("{?$3ey_pyh?}", StringUtils.isBlank(novel.getPinyin())?"":novel.getPinyin());
            templateContent = templateContent.replace("{?$preview_page?}", preNext.getPreURL());
            templateContent = templateContent.replace("{?$next_page?}", preNext.getNextURL());
            templateContent = templateContent.replace("{?$index_page?}", preNext.getChapterListURL());
            templateContent = templateContent.replace("{?$url_articleinfo?}", this.getTrueURL(GlobalConfig.localSite.getTemplate().getInfoURL(), novel, (ChapterEntity)null));
            FileOutputStream fos = new FileOutputStream(localPath);
            byte[] tag_bytes = templateContent.getBytes(GlobalConfig.localSite.getCharset());
            fos.write(tag_bytes);
            fos.close();
        } catch (Exception var16) {
            logger.error("生成[{}][{}]章节内容异常！", novel.getNovelName(), chapter.getChapterName());
        }

    }

    public String loadChapterContent(ChapterEntity chapter) {
        String localPath = FileHelper.getTxtFilePath(chapter);
        return FileUtils.readFile(localPath, GlobalConfig.localSite.getCharset());
    }

    public Category getCategoryById(String id, CategoryGradeEnum grade) {
        List list = null;
        if(grade == CategoryGradeEnum.TOP) {
            list = GlobalConfig.TOP_CATEGORY;
        } else {
            list = GlobalConfig.SUB_CATEGORY;
        }

        Iterator var5 = list.iterator();

        while(var5.hasNext()) {
            Category c = (Category)var5.next();
            if(id.equalsIgnoreCase(c.getId())) {
                return c;
            }
        }

        return new Category();
    }

    private String getTrueURL(String psedoURL, NovelEntity novel, ChapterEntity chapter) {
        String trueURL = "";
        if(StringUtils.isNotBlank(psedoURL)) {
            trueURL = psedoURL.replace("#subDir#", String.valueOf(novel.getNovelNo().intValue() / 1000)).replace("#articleNo#", String.valueOf(novel.getNovelNo()));
            if(chapter != null) {
                trueURL = trueURL.replace("#chapterNo#", String.valueOf(chapter.getChapterNo()));
            }

            trueURL = trueURL.replace("#pinyin#", StringUtils.isBlank(novel.getPinyin())?"":novel.getPinyin());
        }

        return trueURL;
    }
}
