//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.service.impl;

import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class BaseServiceImpl {
    protected Log logger = LogFactory.getLog(this.getClass());
    protected JdbcTemplate yiduJdbcTemplate;

    public BaseServiceImpl() {
    }

    @Autowired
    public void setYiduDataSource(DataSource yiduDataSource) {
        this.yiduJdbcTemplate = new JdbcTemplate(yiduDataSource);
    }
}
