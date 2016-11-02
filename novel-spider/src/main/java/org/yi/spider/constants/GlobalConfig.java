//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.constants;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.yi.spider.enums.UserAgentEnum;
import org.yi.spider.model.Category;
import org.yi.spider.model.DuoYinZi;
import org.yi.spider.model.Site;
import org.yi.spider.model.User;

public class GlobalConfig {
    public static PropertiesConfiguration site;
    public static PropertiesConfiguration collect;
    public static PropertiesConfiguration config;
    public static List<DuoYinZi> duoyin = new ArrayList();
    public static UserAgentEnum USER_AGENT;
    public static User ADMIN;
    public static Site localSite;
    public static List<Category> TOP_CATEGORY;
    public static List<Category> SUB_CATEGORY;

    static {
        USER_AGENT = UserAgentEnum.DEFAULT;
        ADMIN = null;
        localSite = new Site();
        TOP_CATEGORY = new ArrayList();
        SUB_CATEGORY = new ArrayList();
    }

    public GlobalConfig() {
    }
}
