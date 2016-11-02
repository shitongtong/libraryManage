//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.service;

import java.util.List;
import org.yi.spider.entity.ChapterEntity;
import org.yi.spider.entity.NovelEntity;
import org.yi.spider.enums.CategoryGradeEnum;
import org.yi.spider.model.Category;
import org.yi.spider.model.PreNextChapter;

public interface IHtmlBuilder {
    void buildChapterListHtml(NovelEntity var1, List<ChapterEntity> var2);

    void buildChapterCntHtml(NovelEntity var1, ChapterEntity var2, String var3, PreNextChapter var4);

    String loadChapterContent(ChapterEntity var1);

    Category getCategoryById(String var1, CategoryGradeEnum var2);
}
