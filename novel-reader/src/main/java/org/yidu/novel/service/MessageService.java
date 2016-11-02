//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.service;

import java.util.List;
import org.yidu.novel.bean.MessageSearchBean;
import org.yidu.novel.entity.TMessage;

public interface MessageService {
    TMessage getByNo(int var1);

    void delteByNo(int var1);

    void save(TMessage var1);

    List<TMessage> find(MessageSearchBean var1);

    int getCount(MessageSearchBean var1);
}
