//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.processor;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.commons.cli.CommandLine;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yi.spider.constants.GlobalConfig;
import org.yi.spider.exception.BaseException;
import org.yi.spider.helper.RuleHelper;
import org.yi.spider.helper.SpiderHelper;
import org.yi.spider.model.CollectParam;
import org.yi.spider.model.Rule;
import org.yi.spider.processor.NovelParser;
import org.yi.spider.utils.StringUtils;

public class MainParser {
    private static final Logger logger = LoggerFactory.getLogger(MainParser.class);
    private CollectParam cpm;
    private CommandLine cmd;

    public MainParser(CollectParam cpm) {
        this.cpm = cpm;
    }

    public void process() throws Exception {
        try {
            this.cpm.setRuleMap(this.parseRule(this.cpm));
            this.initRemoteSite(this.cpm);
            List e = SpiderHelper.getArticleNo(this.cmd, this.cpm);
            int concurrent = GlobalConfig.collect.getInt("concurrent_novel_task", 1);
            if(concurrent > 1) {
                ExecutorService novelNo = Executors.newFixedThreadPool(GlobalConfig.collect.getInt("concurrent_novel_task", 1));
                Iterator var5 = e.iterator();

                while(var5.hasNext()) {
                    String novelNo1 = (String)var5.next();
                    logger.trace("多线程，开始采集： " + ((Rule)this.cpm.getRuleMap().get("GetSiteName")).getPattern());
                    novelNo.execute(new NovelParser(this.cpm, novelNo1));
                }

                novelNo.shutdown();
                novelNo.awaitTermination(9223372036854775807L, TimeUnit.DAYS);
            } else {
                logger.trace("单线程，开始采集： " + ((Rule)this.cpm.getRuleMap().get("GetSiteName")).getPattern());
                Iterator novelNo3 = e.iterator();

                while(novelNo3.hasNext()) {
                    String novelNo2 = (String)novelNo3.next();
                    (new NovelParser(this.cpm, novelNo2)).prase();
                }
            }

        } catch (DocumentException var6) {
            var6.printStackTrace();
            throw new DocumentException(var6.getMessage());
        } catch (BaseException var7) {
            var7.printStackTrace();
            throw new BaseException(var7.getMessage());
        }
    }

    private void initRemoteSite(CollectParam cpm) throws BaseException {
        String destUrl = RuleHelper.getPattern(cpm, "GetSiteUrl");
        if(destUrl.isEmpty()) {
            throw new BaseException("规则文件错误， 错误发生位置: GetSiteUrl");
        } else {
            logger.debug("目标站URL: " + destUrl);
            cpm.getRemoteSite().setSiteUrl(destUrl);
            String siteName = RuleHelper.getPattern(cpm, "GetSiteName");
            logger.debug("目标站名称: " + siteName);
            cpm.getRemoteSite().setSiteName(siteName);
            String charset = RuleHelper.getPattern(cpm, "GetSiteCharset");
            logger.debug("目标站编码: " + charset);
            cpm.getRemoteSite().setCharset(charset);
        }
    }

    private Map<String, Rule> parseRule(CollectParam cpm) throws DocumentException {
        String ruleFile = cpm.getRuleFile();
        if(StringUtils.isBlank(ruleFile)) {
            ruleFile = GlobalConfig.collect.getString("rule_name");
        }

        if(StringUtils.isBlank(ruleFile)) {
            throw new BaseException("全局规则和采集命令中必须至少有一个指定采集规则文件！");
        } else {
            return RuleHelper.parseXml(ruleFile);
        }
    }

    public CollectParam getCpm() {
        return this.cpm;
    }

    public void setCpm(CollectParam cpm) {
        this.cpm = cpm;
    }

    public CommandLine getCmd() {
        return this.cmd;
    }

    public void setCmd(CommandLine cmd) {
        this.cmd = cmd;
    }
}
