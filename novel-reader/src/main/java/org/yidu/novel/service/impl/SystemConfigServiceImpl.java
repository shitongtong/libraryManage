//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.yidu.novel.bean.SystemConfigSearchBean;
import org.yidu.novel.entity.TSystemConfig;
import org.yidu.novel.service.SystemConfigService;
import org.yidu.novel.service.impl.HibernateSupportServiceImpl;

public class SystemConfigServiceImpl extends HibernateSupportServiceImpl implements SystemConfigService {
    public SystemConfigServiceImpl() {
    }

    public List<TSystemConfig> find(SystemConfigSearchBean searchBean) {
        StringBuffer hql = new StringBuffer();
        ArrayList params = new ArrayList();
        hql.append("FROM TSystemBlock where 1=1");
        if(searchBean.getConfigno() != 0) {
            hql.append(" AND configno = ? ");
            params.add(Integer.valueOf(searchBean.getConfigno()));
        }

        if(StringUtils.isNotEmpty(searchBean.getType())) {
            hql.append(" AND type = ? ");
            params.add(searchBean.getType());
        }

        return this.find(hql.toString(), params);
    }

    public TSystemConfig getByNo(int blockno) {
        TSystemConfig systemConfig = (TSystemConfig)this.get(TSystemConfig.class, Integer.valueOf(blockno));
        return systemConfig;
    }

    public void deleteByNo(int blockno) {
        TSystemConfig systemConfig = this.getByNo(blockno);
        this.delete(systemConfig);
    }

    public void save(TSystemConfig systemConfig) {
        this.save(systemConfig);
    }
}
