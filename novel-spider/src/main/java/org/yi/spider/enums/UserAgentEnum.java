//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.enums;

import org.yi.spider.enums.BaseEnum;

public enum UserAgentEnum implements BaseEnum {
    DEFAULT("baidu", "Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html)"),
    BAIDU("baidu", "Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html)"),
    GOOGLE("google", "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)"),
    SOGOU("sogou", "Sogou web spider/4.0(+http://www.sogou.com/docs/help/webmasters.htm#07)"),
    YAHOO("yahoo", "Mozilla/5.0 (compatible; Yahoo! Slurp/3.0; http://help.yahoo.com/help/us/ysearch/slurp)"),
    MSN("msn", "msnbot/1.0 (+http://search.msn.com/msnbot.htm)"),
    YOUDAO("youdao", "Mozilla/5.0 (compatible; YoudaoBot/1.0; http://www.youdao.com/help/webmaster/spider/;)"),
    JIKE("jike", "Mozilla/5.0 (compatible; JikeSpider; +http://shoulu.jike.com/spider.html)"),
    USER_IE8_X64("user_ie_x64", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Win64; x64; Trident/4.0)"),
    USER_IE8_X86("user_ie_x86", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)");

    private String name;
    private String value;

    private UserAgentEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static UserAgentEnum parseEnum(String vlaue) {
        UserAgentEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            UserAgentEnum e = var4[var2];
            if(vlaue.equals(e.getValue())) {
                return e;
            }
        }

        return DEFAULT;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return this.name + ":" + this.value;
    }
}
