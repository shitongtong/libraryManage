//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.factory;

import org.yi.spider.service.IChapterService;
import org.yi.spider.service.IHtmlBuilder;
import org.yi.spider.service.INovelService;

public interface IServiceFactory {
    INovelService createNovelService(String var1);

    IChapterService createChapterService(String var1);

    IHtmlBuilder createHtmlBuilder(String var1);
}
