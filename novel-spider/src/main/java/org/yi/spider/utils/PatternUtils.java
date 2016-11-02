//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.yi.spider.model.Rule;
import org.yi.spider.utils.StringUtils;

public class PatternUtils {
    private static final String FLAG_IGNORECASE = "IgnoreCase";

    public PatternUtils() {
    }

    public static List<String> getValues(String content, Rule rule, boolean replace) {
        if(rule != null && rule.getPattern() != null && !rule.getPattern().isEmpty()) {
            int flag = gePatternFlag(rule);
            Pattern p = Pattern.compile(rule.getPattern(), flag);
            Matcher m = p.matcher(content);
            ArrayList valueList = new ArrayList();

            while(m.find()) {
                if(replace) {
                    valueList.add(replaceDestStr(rule, m));
                } else {
                    valueList.add(m.group(1));
                }
            }

            return valueList;
        } else {
            return null;
        }
    }

    public static List<String> getValues(String content, String pattern) {
        ArrayList valueList = new ArrayList();
        if(StringUtils.isNotBlank(pattern)) {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(content);

            try {
                while(m.find()) {
                    for(int e = 1; e <= m.groupCount(); ++e) {
                        valueList.add(m.group(e));
                    }
                }
            } catch (Exception var6) {
                valueList.add(String.valueOf(m.matches()));
            }
        }

        return valueList;
    }

    public static List<String> getValues(String content, Rule rule) {
        return getValues(content, rule, true);
    }

    public static String getValue(String content, Rule rule, boolean replace) {
        String result = null;
        if(rule != null && StringUtils.isNotEmpty(rule.getPattern())) {
            String pattern = rule.getPattern().replace("(.|\\n)", "[\\s\\S]");
            Pattern p = Pattern.compile(pattern, gePatternFlag(rule));
            Matcher m = p.matcher(content);
            if(m.find()) {
                if(replace) {
                    result = replaceDestStr(rule, m);
                } else {
                    result = m.group(1);
                }
            }
        }

        return result;
    }

    private static int gePatternFlag(Rule rule) {
        int flag = 0;
        if(StringUtils.isNotBlank(rule.getOptions()) && rule.getOptions().indexOf("IgnoreCase") >= 0) {
            flag |= 2;
        }

        return flag;
    }

    public static boolean match(String content, String pattern) {
        if(StringUtils.isNotEmpty(pattern)) {
            pattern = pattern.replace("(.|\\n)", "[\\s\\S]");
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(content);

            try {
                return m.find();
            } catch (Exception var5) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static String getValue(String content, String pattern) {
        String result = null;
        if(StringUtils.isNotEmpty(pattern)) {
            pattern = pattern.replace("(.|\\n)", "[\\s\\S]");
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(content);

            try {
                if(m.find()) {
                    result = m.group(1);
                }
            } catch (Exception var6) {
                result = String.valueOf(m.matches());
            }
        }

        return result;
    }

    public static String getValue(String content, Rule rule) {
        String result = null;
        if(rule != null && StringUtils.isNotEmpty(rule.getPattern())) {
            String pattern = rule.getPattern().replace("(.|\\n)", "[\\s\\S]");
            Pattern p = Pattern.compile(pattern, gePatternFlag(rule));
            Matcher m = p.matcher(content);
            if(m.find()) {
                result = replaceDestStr(rule, m);
            }
        }

        return StringUtils.isNotBlank(result)?result.toLowerCase():result;
    }

    private static String replaceDestStr(Rule rule, Matcher m) {
        String result = null;

        try {
            result = m.group(1);
            String e = rule.getFilterPattern();
            if(e != null && !e.isEmpty()) {
                String[] filter = e.split("\\n");
                String[] var8 = filter;
                int var7 = filter.length;

                for(int var6 = 0; var6 < var7; ++var6) {
                    String f = var8[var6];
                    f = f.replace("\r", "");
                    if(f.indexOf("♂") >= 0 && f.indexOf("♂") != f.length()) {
                        String[] ff = f.split("♂");
                        if(ff.length != 1 && ff[1] != null && !ff[1].isEmpty()) {
                            result = result.toLowerCase().replaceAll(ff[0], ff[1]);
                        } else {
                            result = result.toLowerCase().replaceAll(ff[0], "");
                        }
                    } else {
                        result = result.toLowerCase().replaceAll(f, "");
                    }
                }
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        }

        return result;
    }
}
