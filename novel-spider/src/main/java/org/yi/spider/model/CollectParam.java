//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.model;

import java.util.List;
import java.util.Map;
import org.yi.spider.enums.ParamEnum;
import org.yi.spider.model.Rule;
import org.yi.spider.model.Site;

public class CollectParam {
    private ParamEnum collectType;
    private List<String> repairParam;
    private List<String> numList;
    private String ruleFile;
    private Map<String, Rule> ruleMap;
    private Site remoteSite = new Site();

    public CollectParam() {
    }

    public ParamEnum getCollectType() {
        return this.collectType;
    }

    public void setCollectType(ParamEnum collectType) {
        this.collectType = collectType;
    }

    public List<String> getNumList() {
        return this.numList;
    }

    public void setNumList(List<String> numList) {
        this.numList = numList;
    }

    public String getRuleFile() {
        return this.ruleFile;
    }

    public void setRuleFile(String ruleFile) {
        this.ruleFile = ruleFile;
    }

    public Map<String, Rule> getRuleMap() {
        return this.ruleMap;
    }

    public void setRuleMap(Map<String, Rule> ruleMap) {
        this.ruleMap = ruleMap;
    }

    public Site getRemoteSite() {
        return this.remoteSite;
    }

    public void setRemoteSite(Site remoteSite) {
        this.remoteSite = remoteSite;
    }

    public List<String> getRepairParam() {
        return this.repairParam;
    }

    public void setRepairParam(List<String> repairParam) {
        this.repairParam = repairParam;
    }
}
