//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.service;

import java.util.Date;
import java.util.List;
import org.yidu.novel.bean.UserSearchBean;
import org.yidu.novel.entity.TUser;

public interface UserService {
    TUser findByLoginInfo(String var1, String var2);

    TUser findByLoginInfoByJDBC(String var1, String var2);

    void updateLastLoginDate(int var1, Date var2);

    List<TUser> find(UserSearchBean var1);

    TUser getByNo(int var1);

    void delteByNo(int var1);

    void save(TUser var1);

    int getCount(UserSearchBean var1);
}
