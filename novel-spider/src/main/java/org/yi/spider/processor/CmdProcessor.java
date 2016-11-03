//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yi.spider.constants.GlobalConfig;
import org.yi.spider.enums.ParamEnum;
import org.yi.spider.enums.RepairParamEnum;
import org.yi.spider.model.CollectParam;
import org.yi.spider.processor.BaseProcessor;
import org.yi.spider.processor.MainParser;
import org.yi.spider.utils.StringUtils;

public class CmdProcessor extends BaseProcessor {
    private static final Logger logger = LoggerFactory.getLogger(CmdProcessor.class);
    private CommandLine cmd;

    public CmdProcessor() {
    }

    public CmdProcessor(CommandLine cmd) {
        this.cmd = cmd;
    }

    public void run() {
        CollectParam cpm = new CollectParam();
        if(this.cmd.hasOption(ParamEnum.COLLECT_All.getName())) {
            cpm.setCollectType(ParamEnum.COLLECT_All);
        } else if(this.cmd.hasOption(ParamEnum.COLLECT_ASSIGN.getName())) {
            cpm.setCollectType(ParamEnum.COLLECT_ASSIGN);
        } else if(this.cmd.hasOption(ParamEnum.REPAIR_ALL.getName())) {
            cpm.setCollectType(ParamEnum.REPAIR_ALL);
            cpm.setRepairParam(this.getRepairParam());
        } else if(this.cmd.hasOption(ParamEnum.REPAIR_ASSIGN.getName())) {
            cpm.setCollectType(ParamEnum.REPAIR_ASSIGN);
            cpm.setRepairParam(this.getRepairParam());
        } else if(this.cmd.hasOption(ParamEnum.IMPORT.getName())) {
            cpm.setCollectType(ParamEnum.IMPORT);
        } else {
            cpm.setCollectType(ParamEnum.COLLECT_All);
        }

        if(this.cmd.hasOption(ParamEnum.RULE_FILE.getName()) && StringUtils.isNotBlank(this.cmd.getOptionValue(ParamEnum.RULE_FILE.getName()))) {
            cpm.setRuleFile(this.cmd.getOptionValue(ParamEnum.RULE_FILE.getName()));
        }

        MainParser sp = new MainParser(cpm);
        sp.setCmd(this.cmd);
        int interval = GlobalConfig.collect.getInt("interval", 0);

        while(true) {
            logger.debug("进入第一层循环...");
            while(true) {
                logger.debug("进入第二层循环...");
                try {
                    sp.process();
                    interval = Math.max(interval, 0);
                    logger.info("当前线程{}任务已经全部进入执行状态, {}秒后将检查是否有新任务进入...", Thread.currentThread().getName(), Integer.valueOf(interval));
                    Thread.sleep((long)(interval * 1000));
                } catch (InterruptedException var5) {
                    logger.error(var5.getMessage(), var5);
                } catch (Exception var6) {
                    if(logger.isDebugEnabled()) {
                        logger.error("解析异常, 原因：" + var6.getMessage(), var6);
                    } else {
                        logger.error("解析异常, 原因：" + var6.getMessage());
                    }
                }
            }
        }
    }

    private List<String> getRepairParam() {
        Object params = new ArrayList();
        if(this.cmd.hasOption(ParamEnum.REPAIR_PARAMS.getName())) {
            String values = this.cmd.getOptionValue(ParamEnum.REPAIR_PARAMS.getName());
            if(StringUtils.isNotBlank(values)) {
                ((List)params).addAll(Arrays.asList(values.split(",")));
            } else {
                params = RepairParamEnum.toList();
            }
        } else {
            params = RepairParamEnum.toList();
        }

        return (List)params;
    }

    public CommandLine getCmd() {
        return this.cmd;
    }

    public void setCmd(CommandLine cmd) {
        this.cmd = cmd;
    }
}
