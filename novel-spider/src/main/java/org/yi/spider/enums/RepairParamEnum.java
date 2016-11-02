//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.enums;

import java.util.ArrayList;
import java.util.List;
import org.yi.spider.enums.BaseEnum;

public enum RepairParamEnum implements BaseEnum {
    COVER("cover", "修复封面"),
    INTRO("intro", "简介"),
    TOP("top", "大类"),
    SUB("sub", "小类"),
    KEYWORDS("keywords", "关键词"),
    DEGREE("degree", "写作进度"),
    ETXT("etxt", "空章节内容"),
    TXT("txt", "章节内容");

    private String value;
    private String desc;

    private RepairParamEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static RepairParamEnum parseEnum(String vlaue) {
        RepairParamEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            RepairParamEnum e = var4[var2];
            if(vlaue.equals(e.getValue())) {
                return e;
            }
        }

        return null;
    }

    public static List<String> toList() {
        ArrayList list = new ArrayList();
        RepairParamEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            RepairParamEnum e = var4[var2];
            list.add(e.getValue());
        }

        return list;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
