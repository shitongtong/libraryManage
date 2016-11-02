//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.helper;

import java.io.PrintWriter;
import org.apache.commons.cli.HelpFormatter;
import org.yi.spider.constants.GlobalConfig;
import org.yi.spider.helper.CliHelper;

public class CmdHelper {
    public CmdHelper() {
    }

    public static void showHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("yispider", CliHelper.getOptions());
    }

    public static void showVersion() {
        PrintWriter pw = new PrintWriter(System.out);
        StringBuffer sb = new StringBuffer("yispider: ");
        sb.append(GlobalConfig.config.getString("version"));
        sb.append(System.getProperty("line.separator"));
        pw.write(sb.toString());
        pw.flush();
        pw.close();
    }
}
