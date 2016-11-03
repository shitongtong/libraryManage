//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.processor;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yi.spider.constants.GlobalConfig;
import org.yi.spider.entity.ChapterEntity;
import org.yi.spider.entity.NovelEntity;
import org.yi.spider.enums.CategoryGradeEnum;
import org.yi.spider.enums.ParamEnum;
import org.yi.spider.enums.ProgramEnum;
import org.yi.spider.enums.RepairParamEnum;
import org.yi.spider.exception.BaseException;
import org.yi.spider.factory.impl.ServiceFactory;
import org.yi.spider.helper.FileHelper;
import org.yi.spider.helper.ParseHelper;
import org.yi.spider.model.CollectParam;
import org.yi.spider.model.DuoYinZi;
import org.yi.spider.model.PreNextChapter;
import org.yi.spider.model.Rule;
import org.yi.spider.processor.BaseProcessor;
import org.yi.spider.service.IChapterService;
import org.yi.spider.service.IHtmlBuilder;
import org.yi.spider.service.INovelService;
import org.yi.spider.utils.HttpUtils;
import org.yi.spider.utils.ObjectUtils;
import org.yi.spider.utils.PinYinUtils;
import org.yi.spider.utils.StringUtils;

public class NovelParser extends BaseProcessor {
    private static final Logger logger = LoggerFactory.getLogger(NovelParser.class);
    private CollectParam cpm;
    private String novelNo;
    private INovelService novelService;
    private IChapterService chapterService;
    private IHtmlBuilder htmlBuilder;

    public NovelParser(CollectParam cpm) throws Exception {
        this.cpm = cpm;
        this.init();
    }

    public NovelParser(CollectParam cpm, String novelNo) throws Exception {
        this.cpm = cpm;
        this.novelNo = novelNo;
        this.init();
    }

    private void init() throws Exception {
        this.novelService = (new ServiceFactory()).createNovelService(GlobalConfig.localSite.getProgram().getName());
        this.chapterService = (new ServiceFactory()).createChapterService(GlobalConfig.localSite.getProgram().getName());
        if(GlobalConfig.collect.getBoolean("create_html", false)) {
            this.htmlBuilder = (new ServiceFactory()).createHtmlBuilder(GlobalConfig.localSite.getProgram().getName());
        }

    }

    public void run() {
        this.prase();
    }

    public void prase() {
        int timeOut = GlobalConfig.site.getInt("connection_timeout", 60);
        CloseableHttpClient httpClient = HttpUtils.buildClient(timeOut * 1000);

        try {
            String e = ParseHelper.getInfoRUL(this.cpm, this.novelNo);
            String infoSource = ParseHelper.getSource(httpClient, this.cpm, e);
            String novelName = ParseHelper.getNovelName(infoSource, this.cpm);
            if(novelName == null || novelName.isEmpty()) {
                throw new BaseException("小说名为空, 目标链接：" + e);
            }

            NovelEntity novel = this.novelService.find(novelName);
            if(novel != null) {
                if(this.cpm.getCollectType() == ParamEnum.REPAIR_ALL || this.cpm.getCollectType() == ParamEnum.REPAIR_ASSIGN) {
                    NovelEntity newNovel = this.getNovelInfo(infoSource, novelName);
                    this.novelService.repair(novel, newNovel);
                    if(this.cpm.getRepairParam() != null && this.cpm.getRepairParam().contains(RepairParamEnum.COVER.getValue())) {
                        this.getCover(infoSource, novel);
                    }
                }
            } else if((this.cpm.getCollectType() == ParamEnum.COLLECT_All || this.cpm.getCollectType() == ParamEnum.COLLECT_ASSIGN) && GlobalConfig.collect.getBoolean("add_new_book", false)) {
                novel = this.addNovel(infoSource, novelName);
            }

            if(this.cpm.getCollectType() == ParamEnum.IMPORT) {
                if(novel == null) {
                    this.addNovel(infoSource, novelName);
                }
            } else if(novel != null) {
                this.parse(this.novelNo, novel, infoSource);
            }
        } catch (Exception var16) {
            if(logger.isDebugEnabled()) {
                logger.error("解析异常, 原因：" + var16.getMessage(), var16);
            } else {
                logger.error("解析异常, 原因：" + var16.getMessage());
            }
        } finally {
            try {
                if(httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException var15) {
                logger.error("关闭连接出错,原因：{}", var15.getMessage());
            }

        }

    }

    private synchronized NovelEntity addNovel(String infoSource, String novelName) throws Exception {
        NovelEntity novel = this.getNovelInfo(infoSource, novelName);
        String pinyin = novelName;

        DuoYinZi count;
        for(Iterator var6 = GlobalConfig.duoyin.iterator(); var6.hasNext(); pinyin = pinyin.replace(count.getName(), count.getPinyin())) {
            count = (DuoYinZi)var6.next();
        }

        pinyin = PinYinUtils.getFullSpell(pinyin).trim();
        Integer count1 = Integer.valueOf(this.novelService.getMaxPinyin(pinyin).intValue());
        if(count1.intValue() > 0) {
            pinyin = pinyin + (count1.intValue() + 1);
        }

        novel.setPinyin(pinyin);
        novel.setInitial(PinYinUtils.getPinyinShouZiMu(pinyin));
        novel.setNovelNo(this.novelService.saveNovel(novel));
        this.getCover(infoSource, novel);
        return novel;
    }

    private NovelEntity getNovelInfo(String infoSource, String novelName) {
        NovelEntity novel = new NovelEntity();
        novel.setNovelName(novelName);
        String author = ParseHelper.getNovelAuthor(infoSource, this.cpm);
        novel.setAuthor(author);
        String topCat = "";
        Integer cat = Integer.valueOf(0);
        if(this.willParse(RepairParamEnum.TOP.getValue())) {
            topCat = ParseHelper.get(infoSource, (Rule)this.cpm.getRuleMap().get("LagerSort"));
            cat = ParseHelper.getCategory(topCat, CategoryGradeEnum.TOP);
            novel.setTopCategory(cat);
        }

        String novelDegree;
        if(this.willParse(RepairParamEnum.SUB.getValue())) {
            novelDegree = ParseHelper.get(infoSource, (Rule)this.cpm.getRuleMap().get("SmallSort"));
            cat = ParseHelper.getCategory(novelDegree, CategoryGradeEnum.SUB);
            novel.setSubCategory(cat);
        }

        if(this.willParse(RepairParamEnum.INTRO.getValue())) {
            novelDegree = ParseHelper.getNovelIntro(infoSource, this.cpm);
            novel.setIntro(StringUtils.isBlank(novelDegree)?"":novelDegree);
        }

        if(this.willParse(RepairParamEnum.KEYWORDS.getValue())) {
            novelDegree = ParseHelper.getNovelKeywrods(infoSource, this.cpm);
            novel.setKeywords(StringUtils.isBlank(novelDegree)?"":novelDegree);
        }

        if(this.willParse(RepairParamEnum.DEGREE.getValue())) {
            novelDegree = ParseHelper.get(infoSource, (Rule)this.cpm.getRuleMap().get("NovelDegree"));
            String fullFlagStr = GlobalConfig.collect.getString("full_flag", "已完结");
            boolean fullFlag = fullFlagStr.equals(novelDegree);
            novel.setFullFlag(Boolean.valueOf(fullFlag));
        }

        return novel;
    }

    private boolean willParse(String param) {
        return this.cpm.getCollectType() == ParamEnum.COLLECT_All || this.cpm.getCollectType() == ParamEnum.COLLECT_ASSIGN || this.cpm.getCollectType() == ParamEnum.IMPORT || (this.cpm.getCollectType() == ParamEnum.REPAIR_ALL || this.cpm.getCollectType() == ParamEnum.REPAIR_ASSIGN) && this.cpm.getRepairParam() != null && this.cpm.getRepairParam().contains(param);
    }

    private void getCover(String infoSource, NovelEntity novel) throws Exception {
        Integer imgFlag = ParseHelper.getNovelCover(novel, infoSource, this.cpm);
        novel.setImgFlag(imgFlag);
    }

    private void parse(String novelNo, NovelEntity novel, String infoSource) throws Exception {
        String novelPubKeyURL = ParseHelper.getNovelMenuURL(infoSource, novelNo, this.cpm);
        String novelInfoExtra = ParseHelper.getNovelInfoExtra(infoSource, this.cpm);
        if(StringUtils.isNotBlank(novelInfoExtra)) {
            FileHelper.writeLastTxtFile(FileHelper.getLastTxtFilePath(novel), novelInfoExtra);
        }

        String menuSource = ParseHelper.getChapterListSource(novelPubKeyURL, this.cpm);
        List chapterNameList = ParseHelper.getChapterNameList(menuSource, this.cpm);
        List chapterKeyList = ParseHelper.getChapterNoList(menuSource, this.cpm);
        if(chapterNameList.size() != chapterKeyList.size()) {
            logger.warn("规则：{}, 小说[{}]章节名称数和章节地址数不一致， 可能导致采集结果混乱！", ((Rule)this.cpm.getRuleMap().get("GetSiteName")).getPattern(), novel.getNovelName());
        }

        ChapterEntity chapter = new ChapterEntity();
        chapter.setNovelNo(novel.getNovelNo());
        chapter.setNovelName(novel.getNovelName());
        if(this.cpm.getCollectType() != ParamEnum.REPAIR_ALL && this.cpm.getCollectType() != ParamEnum.REPAIR_ASSIGN) {
            this.normalCollect(novelNo, novel, chapter, novelPubKeyURL, chapterNameList, chapterKeyList);
        } else {
            this.repaireChapter(novelNo, novel, novelPubKeyURL, chapterNameList, chapterKeyList);
        }

    }

    private void normalCollect(String novelNo, NovelEntity novel, ChapterEntity chapter, String novelPubKeyURL, List<String> chapterNameList, List<String> chapterKeyList) throws Exception {
        List chapterListDB = this.chapterService.getChapterList(novel);

        for(int i = 0; i < chapterNameList.size(); ++i) {
            String cname = ((String)chapterNameList.get(i)).trim();
            boolean needCollect = true;
            Iterator var12 = chapterListDB.iterator();

            while(var12.hasNext()) {
                ChapterEntity cno = (ChapterEntity)var12.next();
                if(cname.equalsIgnoreCase(cno.getChapterName().trim())) {
                    needCollect = false;
                    break;
                }
            }

            if(needCollect) {
                String var13 = (String)chapterKeyList.get(i);
                chapter.setChapterName(cname);
                logger.info("采集小说: {}，章节：{}， 规则：{}", new Object[]{novel.getNovelName(), cname, ((Rule)this.cpm.getRuleMap().get("GetSiteName")).getPattern()});
                this.collectChapter(novelNo, var13, novelPubKeyURL, novel, chapter);
            }
        }

    }

    private void repaireChapter(String novelNo, NovelEntity novel, String novelPubKeyURL, List<String> chapterNameList, List<String> chapterKeyList) throws Exception {
        ChapterEntity chapter = null;
        List chapterListDB = this.chapterService.getChapterList(novel);

        for(int i = 0; i < chapterNameList.size(); ++i) {
            String cname = (String)chapterNameList.get(i);
            Iterator var11 = chapterListDB.iterator();

            while(var11.hasNext()) {
                ChapterEntity tc = (ChapterEntity)var11.next();
                if(cname.equalsIgnoreCase(tc.getChapterName())) {
                    chapter = this.chapterService.getChapterByChapterNameAndNovelNo(tc);
                    if(chapter == null) {
                        break;
                    }

                    if(this.cpm.getRepairParam() != null && this.cpm.getRepairParam().contains(RepairParamEnum.ETXT.getValue())) {
                        String txtFile = FileHelper.getTxtFilePath(chapter);
                        if(!(new File(txtFile)).exists()) {
                            logger.debug("修复小说: {}，规则:{}，修复空章节：{}", new Object[]{novel.getNovelName(), ((Rule)this.cpm.getRuleMap().get("GetSiteName")).getPattern(), cname});
                            this.collectChapter(novelNo, (String)chapterKeyList.get(i), novelPubKeyURL, novel, chapter);
                        }
                        break;
                    }

                    if(this.cpm.getRepairParam() != null && this.cpm.getRepairParam().contains(RepairParamEnum.TXT.getValue())) {
                        logger.debug("修复小说: {}，规则：{}，重新采集章节：{}", new Object[]{novel.getNovelName(), ((Rule)this.cpm.getRuleMap().get("GetSiteName")).getPattern(), cname});
                        this.collectChapter(novelNo, (String)chapterKeyList.get(i), novelPubKeyURL, novel, chapter);
                    }
                    break;
                }
            }
        }

    }

    private void collectChapter(String novelNo, String cno, String novelPubKeyURL, NovelEntity novel, ChapterEntity tc) throws Exception {
        ChapterEntity chapter = tc.clone();
        this.setChapterOrder(chapter);
        Integer chapterNo = Integer.valueOf(0);
        if(chapter.getChapterNo() != null && chapter.getChapterNo().intValue() != 0) {
            chapterNo = chapter.getChapterNo();
        } else {
            boolean chapterURL = this.chapterService.exist(chapter);
            if(chapterURL) {
                return;
            }

            chapter.setSize(Integer.valueOf(0));
            chapterNo = Integer.valueOf(this.chapterService.save(chapter).intValue());
            chapter.setChapterNo(chapterNo);
            Map chapterSource = this.chapterService.getTotalInfo(novel.getNovelNo());
            novel.setChapters(ObjectUtils.obj2Int(chapterSource.get("count")));
            novel.setLastChapterName(chapter.getChapterName());
            novel.setLastChapterno(chapterNo);
                novel.setSize(ObjectUtils.obj2Int(chapterSource.get("size")));
            this.novelService.update(novel);
        }

        String chapterURL1 = ParseHelper.getChapterURL(novelPubKeyURL, novelNo, cno, this.cpm);
        if(StringUtils.isNotBlank(chapterURL1)) {
            String chapterSource1 = ParseHelper.getChapterSource(chapterURL1, this.cpm);
            String chapterContent = ParseHelper.getChapterContent(chapterSource1, this.cpm);
            if(StringUtils.isBlank(chapterContent)) {
                logger.error("章节内容采集出错， 目标地址：{}， 本站小说号：{}， 章节号：{}", new Object[]{chapterURL1, novel.getNovelNo(), chapterNo});
            }

            if(StringUtils.isBlank(chapterContent)) {
                logger.error("采集到空章节， 规则：{}， 小说名：{}， 章节名：{}", new Object[]{((Rule)this.cpm.getRuleMap().get("GetSiteName")).getPattern(), novel.getNovelName(), chapter.getChapterName()});
            } else {
                FileHelper.writeTxtFile(novel, chapter, chapterContent);
            }

            chapter.setSize(Integer.valueOf(chapterContent.length()));
            this.chapterService.updateSize(chapter);
            if(GlobalConfig.collect.getBoolean("create_html", false)) {
                ChapterEntity nextChapter = this.chapterService.get(chapter, 1);
                ChapterEntity preChapter = this.chapterService.get(chapter, -1);
                PreNextChapter preNext = null;
                if(preChapter != null) {
                    ChapterEntity pre2Chapter = this.chapterService.get(chapter, -2);
                    preNext = this.getPreNext(pre2Chapter, chapter, novel);
                    String preChapterContent = this.htmlBuilder.loadChapterContent(preChapter);
                    this.htmlBuilder.buildChapterCntHtml(novel, preChapter, preChapterContent, preNext);
                }

                preNext = this.getPreNext(preChapter, nextChapter, novel);
                this.htmlBuilder.buildChapterCntHtml(novel, chapter, chapterContent, preNext);
                this.htmlBuilder.buildChapterListHtml(novel, this.chapterService.getChapterList(novel));
            }
        }

    }

    private void setChapterOrder(ChapterEntity chapter) throws SQLException {
        if(GlobalConfig.localSite.getProgram() == ProgramEnum.JIEQI) {
            int chapterOrder = this.chapterService.getChapterOrder(chapter);
            chapter.setChapterOrder(Integer.valueOf(chapterOrder + 1));
        }

    }

    private PreNextChapter getPreNext(ChapterEntity pre, ChapterEntity next, NovelEntity novel) throws Exception {
        PreNextChapter pn = new PreNextChapter();
        String novelPubKeyURL = GlobalConfig.localSite.getTemplate().getChapterURL();
        int novelNo = novel.getNovelNo().intValue();
        novelPubKeyURL = novelPubKeyURL.replace("#subDir#", String.valueOf(novelNo / 1000)).replace("#articleNo#", String.valueOf(novelNo));
        if(GlobalConfig.localSite.getUsePinyin().intValue() == 1) {
            novelPubKeyURL = novelPubKeyURL.replace("#pinyin#", novel.getPinyin());
        }

        novelPubKeyURL = StringUtils.getFullUrl(GlobalConfig.localSite.getSiteUrl(), novelPubKeyURL);
        if(pre == null) {
            pn.setPreURL(novelPubKeyURL);
        } else {
            pn.setPreURL(this.getLocalChapterUrl(GlobalConfig.localSite.getTemplate().getReaderURL(), novel, pre.getChapterNo()));
        }

        if(next == null) {
            pn.setNextURL(novelPubKeyURL);
        } else {
            pn.setNextURL(this.getLocalChapterUrl(GlobalConfig.localSite.getTemplate().getReaderURL(), novel, next.getChapterNo()));
        }

        pn.setChapterListURL(novelPubKeyURL);
        return pn;
    }

    private String getLocalChapterUrl(String url, NovelEntity novel, Integer chapterNo) throws Exception {
        int novelNo = novel.getNovelNo().intValue();
        url = url.replace("#subDir#", String.valueOf(novelNo / 1000)).replace("#articleNo#", String.valueOf(novelNo)).replace("#chapterNo#", String.valueOf(chapterNo));
        if(GlobalConfig.localSite.getUsePinyin().intValue() == 1) {
            url = url.replace("#pinyin#", novel.getPinyin());
        }

        url = StringUtils.getFullUrl(GlobalConfig.localSite.getSiteUrl(), url);
        return url;
    }
}
