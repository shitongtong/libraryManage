//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.yi.spider.entity.ChapterEntity;
import org.yi.spider.entity.NovelEntity;

public interface IChapterService {
    Number save(ChapterEntity var1) throws SQLException;

    Map<String, Object> getTotalInfo(Number var1) throws SQLException;

    int getChapterOrder(ChapterEntity var1) throws SQLException;

    boolean exist(ChapterEntity var1) throws SQLException;

    ChapterEntity get(ChapterEntity var1, int var2) throws SQLException;

    List<ChapterEntity> getChapterList(NovelEntity var1) throws SQLException;

    ChapterEntity get(Number var1) throws SQLException;

    ChapterEntity getLasChapter(ChapterEntity var1) throws SQLException;

    List<ChapterEntity> getDuplicateChapter() throws SQLException;

    ChapterEntity getChapterByChapterNameAndNovelNo(ChapterEntity var1) throws SQLException;

    void delete(List<ChapterEntity> var1) throws SQLException;

    int updateSize(ChapterEntity var1) throws SQLException;
}
