//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.helper;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.yi.spider.enums.ParamEnum;
import org.yi.spider.utils.StringUtils;

public class CliHelper {
    private static Options options = new Options();

    static {
        ParamEnum[] var3;
        int var2 = (var3 = ParamEnum.values()).length;

        for(int var1 = 0; var1 < var2; ++var1) {
            ParamEnum e = var3[var1];
            if(StringUtils.isEmpty(e.getValueName())) {
                Option option = new Option(e.getName(), e.isHasArgs(), e.getDesc());
                options.addOption(option);
            } else {
                OptionBuilder.withArgName(e.getValueName());
                OptionBuilder.hasArg();
                OptionBuilder.withDescription(e.getDesc());
                options.addOption(OptionBuilder.create(e.getName()));
            }
        }

    }

    public CliHelper() {
    }

    public static Options getOptions() {
        return options;
    }

    public static CommandLine parse(String[] args) throws ParseException {
        PosixParser parser = new PosixParser();
        CommandLine cmd = parser.parse(options, args);
        return cmd;
    }
}
