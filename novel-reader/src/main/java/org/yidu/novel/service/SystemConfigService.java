//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.service;

import java.util.List;
import org.yidu.novel.bean.SystemConfigSearchBean;
import org.yidu.novel.entity.TSystemConfig;

public interface SystemConfigService {
    List<TSystemConfig> find(SystemConfigSearchBean var1);

    TSystemConfig getByNo(int var1);

    void deleteByNo(int var1);

    void save(TSystemConfig var1);
}
