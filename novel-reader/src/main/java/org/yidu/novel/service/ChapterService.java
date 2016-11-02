//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.service;

import java.util.List;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.entity.TChapter;

public interface ChapterService {
    List<TChapter> find(ChapterSearchBean var1);

    Integer getCount(ChapterSearchBean var1);

    TChapter getLastChapter(int var1);

    Integer getArticleSize(int var1);

    TChapter getNextChapter(int var1, int var2, boolean var3);

    TChapter getByNo(int var1);

    void save(TChapter var1);

    void delteByNo(int var1);
}
