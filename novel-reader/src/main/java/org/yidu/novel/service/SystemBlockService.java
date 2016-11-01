//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.service;

import java.util.List;
import org.yidu.novel.bean.SystemBlockSearchBean;
import org.yidu.novel.entity.TSystemBlock;

public interface SystemBlockService {
    List<TSystemBlock> find(SystemBlockSearchBean var1);

    Integer getCount(SystemBlockSearchBean var1);

    TSystemBlock getByNo(int var1);

    void delteByNo(int var1);

    void save(TSystemBlock var1);
}
