//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider;

import org.yi.spider.MainThread;
import org.yi.spider.constants.GlobalConfig;
import org.yi.spider.loader.InitCfgLoader;
import org.yi.spider.loader.SimpleLoaderFactory;

public class StartApp {
    public StartApp() {
    }

    public static void main(String[] args) {
        try {
            InitCfgLoader.load();
            SimpleLoaderFactory.create(GlobalConfig.localSite.getProgram()).load();
        } catch (Exception var2) {
            var2.printStackTrace();
            System.exit(-1);
        }

        (new MainThread(args)).run();
    }
}
