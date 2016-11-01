package org.yidu.novel.init;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.constant.YiDuConstants;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Administrator on 2016-10-31.
 */
public class InitializerListener implements ServletContextListener {

    protected Log logger = LogFactory.getLog(this.getClass());

    public InitializerListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            PropertiesConfiguration e = new PropertiesConfiguration("yidu.properties");
            FileChangedReloadingStrategy reloadStrategy = new FileChangedReloadingStrategy();
            e.setReloadingStrategy(reloadStrategy);
            YiDuConstants.yiduConf = e;
            CacheManager.initCacheManager();
            this.logger.info("Initialize successfully.");
        } catch (Exception var4) {
            this.logger.error(var4.getMessage(), var4);
            System.exit(0);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
