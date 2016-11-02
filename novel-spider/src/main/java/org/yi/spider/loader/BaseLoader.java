//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.loader;

import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yi.spider.constants.GlobalConfig;
import org.yi.spider.enums.UserAgentEnum;

public class BaseLoader {
    private static Logger logger = LoggerFactory.getLogger(BaseLoader.class);

    public BaseLoader() {
    }

    public static void loadInitParam() {
        logger.debug("开始加载采集参数...");
        GlobalConfig.USER_AGENT = UserAgentEnum.parseEnum(GlobalConfig.collect.getString("userAgent"));
    }

    public void loadCategory() throws ConfigurationException {
        logger.debug("开始加载分类目录...");
    }
}
