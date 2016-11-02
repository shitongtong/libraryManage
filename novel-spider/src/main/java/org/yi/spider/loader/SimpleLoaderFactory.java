//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yi.spider.enums.ProgramEnum;
import org.yi.spider.exception.BaseException;
import org.yi.spider.loader.ILoader;
import org.yi.spider.loader.impl.JieQiConfigLoader;
import org.yi.spider.loader.impl.YiDuConfigLoader;

public class SimpleLoaderFactory {
    private static final Logger logger = LoggerFactory.getLogger(SimpleLoaderFactory.class);

    public SimpleLoaderFactory() {
    }

    public static ILoader create(ProgramEnum e) {
        if(e == null) {
            throw new BaseException("获取本地站点使用的程序异常！");
        } else {
            Object loader = null;
            if(e == ProgramEnum.YIDU) {
                loader = new YiDuConfigLoader();
            } else if(e == ProgramEnum.JIEQI) {
                loader = new JieQiConfigLoader();
            }

            logger.debug("产生加载策略： " + e.getName());
            return (ILoader)loader;
        }
    }
}
