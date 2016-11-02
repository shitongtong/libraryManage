//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.enums;

import org.yi.spider.enums.BaseEnum;

public enum ParamEnum implements BaseEnum {
    HELP("help", false, "获取帮助信息"),
    VERSION("version", false, "获取软件版本信息"),
    MULTI("m", false, "同时采集指定多个配置文件进行采集"),
    ADD_NEWBOOK("a", false, "允许新书入库， 若命令中带此参数， 则配置文件中对应项无效"),
    COLLECT_All("ca", false, "采集所有规则中指定的小说"),
    COLLECT_ASSIGN("c", true, "采集指定目标站小说, 例如 -c 1,234,5678 或 -c 1-5"),
    REPAIR_ALL("ra", false, "修复所有目标站和本站均存在的小说"),
    REPAIR_ASSIGN("r", true, "修复指定小说中目标站和本站均存在的小说,例如 -r 1,234,5678 或 -r 1-5"),
    REPAIR_PARAMS("rp", true, "指定小说需要修复的部分，必须指定修复项，修复项包括：intro(简介)、degree(写作进度)、cover(封面图片)、top(小说大类)、sub(小说细类)， 必须和ra或r共用。 如 -ra -rp cover,top,sub,intro,degree,etxt,txt"),
    IMPORT("i", false, "导入小说，即只入库小说， 不采集章节"),
    RULE_FILE("rule", "file", "指定采集使用的规则文件");

    private String name;
    private String valueName;
    private boolean hasArgs;
    private String desc;

    private ParamEnum(String name, boolean hasArgs, String desc) {
        this.name = name;
        this.hasArgs = hasArgs;
        this.desc = desc;
    }

    private ParamEnum(String name, String valueName, String desc) {
        this.name = name;
        this.valueName = valueName;
        this.desc = desc;
    }

    public static ParamEnum parseEnum(String vlaue) {
        ParamEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            ParamEnum e = var4[var2];
            if(vlaue.equals(e.getName())) {
                return e;
            }
        }

        return null;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasArgs() {
        return this.hasArgs;
    }

    public void setHasArgs(boolean hasArgs) {
        this.hasArgs = hasArgs;
    }

    public String getValueName() {
        return this.valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
