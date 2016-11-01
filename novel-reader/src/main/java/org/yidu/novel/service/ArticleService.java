//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.service;

import java.util.List;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.entity.TArticle;

public interface ArticleService {
    List<TArticle> find(ArticleSearchBean var1);

    Integer getCount(ArticleSearchBean var1);

    TArticle getByNo(int var1);

    void delteByNo(int var1);

    void save(TArticle var1);

    void updateVisitStatistic(int var1);

    void updateVoteStatistic(int var1);

    void cleanStatistics();
}
