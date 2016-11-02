//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.enums;

import org.yi.spider.enums.BaseEnum;

public enum RuleDetailEnum implements BaseEnum {
    SITE_URL("站点地址", "GetSiteUrl", "采集目标站点地址"),
    SITE_NAME("站点名称", "GetSiteName", "采集目标站点名称"),
    SITE_CHARSET("站点编码", "GetSiteCharset", "采集目标站点编码"),
    LIST_URL("列表地址", "NovelListUrl", "目标站点列表地址， 通常是最近更新页面地址"),
    NOVEL_KEY("小说编号", "NovelList_GetNovelKey", "从最新列表中获得小说编号,此规则中可以同时获得书名以方便手动时查看,此获得结果存入{NovelKey}变量"),
    NOVEL_URL("小说信息页地址", "NovelUrl", "小说信息页地址 可调用{NovelKey}变,{NovelKey}一般情况表示小说编号"),
    NOVEL_NAME("小说名称", "NovelName", "小说名称"),
    NOVEL_AUTHOR("小说作者", "NovelAuthor", "小说作者"),
    LAGER_SORT("小说大类", "LagerSort", "小说大类"),
    SMALL_SORT("小说小类", "SmallSort", "小说小类"),
    NOVEL_INTRO("小说简介", "NovelIntro", "小说简介"),
    NOVEL_KEYWORD("小说关键字", "NovelKeyword", "小说关键字"),
    NOVEL_DEGREE("写作进程", "NovelDegree", "写作进程"),
    NOVEL_COVER("小说封面", "NovelCover", "小说封面"),
    Default_Cover("默认封面地址", "NovelDefaultCoverUrl", "默认封面地址"),
    MENU_URL("小说目录页地址", "PubIndexUrl", "小说目录页地址"),
    CHAPTER_NAME("小说章节名", "PubChapterName", "小说章节名"),
    CHAPTER_Key("章节编号", "PubChapter_GetChapterKey", "获得章节地址(章节编号)，所获得的数量必须和章节名相同。记录变量{ChapterKey}"),
    CONTENT_URL("章节内容页地址", "PubContentUrl", "章节内容页地址 可调用{ChapterKey} {NovelKey}变量"),
    CONTENT_TEXT("章节内容", "PubContentText", "章节内容， 目前只支持文本内容， 如果目标站的章节内容是图片可以使用修复功能从其他站对该章节进行修复采集");

    private String ruleName;
    private String ruleKey;
    private String ruleDesc;

    private RuleDetailEnum(String ruleName, String ruleKey, String ruleDesc) {
        this.ruleName = ruleName;
        this.ruleKey = ruleKey;
        this.ruleDesc = ruleDesc;
    }

    public static RuleDetailEnum parseEnum(String vlaue) {
        RuleDetailEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            RuleDetailEnum e = var4[var2];
            if(vlaue.equals(e.getRuleKey())) {
                return e;
            }
        }

        return null;
    }

    public String toString() {
        return this.ruleName;
    }

    public String getRuleName() {
        return this.ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleKey() {
        return this.ruleKey;
    }

    public void setRuleKey(String ruleKey) {
        this.ruleKey = ruleKey;
    }

    public String getRuleDesc() {
        return this.ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }
}
