//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.service;

import java.util.List;
import org.yidu.novel.bean.BookcaseSearchBean;
import org.yidu.novel.dto.BookcaseDTO;
import org.yidu.novel.entity.TBookcase;

public interface BookcaseService {
    List<TBookcase> find(BookcaseSearchBean var1);

    List<BookcaseDTO> findWithArticleInfo(BookcaseSearchBean var1);

    Integer getCount(BookcaseSearchBean var1);

    TBookcase getByNo(int var1);

    void delteByNo(int var1);

    void batchdeleteByNo(String var1, int var2);

    void save(TBookcase var1);

    TBookcase getByArticleno(int var1, int var2);

    void deleteByArticleno(int var1, int var2);
}
