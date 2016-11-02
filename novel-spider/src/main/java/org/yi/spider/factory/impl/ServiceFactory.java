//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.factory.impl;

import org.yi.spider.enums.ProgramEnum;
import org.yi.spider.factory.IServiceFactory;
import org.yi.spider.service.IChapterService;
import org.yi.spider.service.IHtmlBuilder;
import org.yi.spider.service.INovelService;
import org.yi.spider.service.jieqi.ChapterServiceImpl;
import org.yi.spider.service.jieqi.HtmlBuilderImpl;
import org.yi.spider.service.jieqi.NovelServiceImpl;

public class ServiceFactory implements IServiceFactory {
    public ServiceFactory() {
    }

    public INovelService createNovelService(String key) {
        Object novelService = null;
        if(key.equalsIgnoreCase(ProgramEnum.JIEQI.getName())) {
            novelService = new NovelServiceImpl();
        } else {
            novelService = new org.yi.spider.service.yidu.NovelServiceImpl();
        }

        return (INovelService)novelService;
    }

    public IChapterService createChapterService(String key) {
        Object chapterService = null;
        if(key.equalsIgnoreCase(ProgramEnum.JIEQI.getName())) {
            chapterService = new ChapterServiceImpl();
        } else {
            chapterService = new org.yi.spider.service.yidu.ChapterServiceImpl();
        }

        return (IChapterService)chapterService;
    }

    public IHtmlBuilder createHtmlBuilder(String key) {
        HtmlBuilderImpl htmlBuilder = null;
        if(key.equalsIgnoreCase(ProgramEnum.JIEQI.getName())) {
            htmlBuilder = new HtmlBuilderImpl();
        }

        return htmlBuilder;
    }
}
