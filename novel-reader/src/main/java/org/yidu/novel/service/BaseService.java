//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.service;

import java.util.List;

public interface BaseService {
    <T> List<T> find(T var1);

    <T> Integer getCount(T var1);

    <T> T getByNo(int var1);

    void delteByNo(int var1);

    <T> void saveEntity(T var1);
}
