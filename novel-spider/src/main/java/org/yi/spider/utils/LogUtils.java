//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.utils;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import java.io.File;
import java.io.IOException;
import org.slf4j.LoggerFactory;

public class LogUtils {
    public LogUtils() {
    }

    public static void load(String configFile) throws IOException, JoranException {
        LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
        File externalConfigFile = new File(configFile);
        if(!externalConfigFile.exists()) {
            throw new IOException("查找Logback配置文件失败!");
        } else if(!externalConfigFile.isFile()) {
            throw new IOException("Logback配置文件不能指向目录！ ");
        } else if(!externalConfigFile.canRead()) {
            throw new IOException("Logback配置文件不可读！");
        } else {
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(lc);
            lc.reset();
            configurator.doConfigure(configFile);
            StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
        }
    }
}
