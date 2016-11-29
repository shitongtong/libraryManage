//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.helper;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.yi.spider.model.CollectParam;
import org.yi.spider.model.Rule;
import org.yi.spider.utils.FileUtils;

public class RuleHelper {
    private static final String REGEX_NAME = "RegexName";
    private static final String FILTER_PATTERN = "FilterPattern";
    private static final String PATTERN = "Pattern";
    private static final String METHOD = "Method";
    private static final String OPTIONS = "Options";
    private static final String RULE_DIR = "rules/";

    public RuleHelper() {
    }

    public static Map<String, Rule> parseXml(String fileName) throws DocumentException {
        //打成jar包后不能读取file文件
//        return parseXml(FileUtils.locateAbsolutePathFromClasspath("rules/" + fileName));
        //返回读取指定资源的输入流
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream is = loader.getResourceAsStream("rules/" + fileName);
        return parseXml(is);
    }

    public static Map<String, Rule> parseXml(InputStream is) throws DocumentException {
        SAXReader reader = new SAXReader();
        HashMap ruleMap = new HashMap();

        try {
            Document e = reader.read(is);
            Element root = e.getRootElement();
            List elementList = root.elements();
            Iterator var7 = elementList.iterator();

            while(var7.hasNext()) {
                Element element = (Element)var7.next();
                Rule rule = new Rule();
                Iterator i = element.nodeIterator();

                while(i.hasNext()) {
                    Node node = (Node)i.next();
                    if(StringUtils.equals(node.getName(), "Pattern")) {
                        rule.setPattern(node.getStringValue());
                    } else if(StringUtils.equals(node.getName(), "FilterPattern")) {
                        rule.setFilterPattern(node.getStringValue());
                    } else if(StringUtils.equals(node.getName(), "RegexName")) {
                        rule.setRegexName(node.getStringValue());
                    } else if(StringUtils.equals(node.getName(), "Method")) {
                        rule.setMethod(node.getStringValue());
                    } else if(StringUtils.equals(node.getName(), "Options")) {
                        rule.setOptions(node.getStringValue());
                    }
                }

                ruleMap.put(element.getName(), rule);
            }

            return ruleMap;
        } catch (DocumentException var11) {
            throw new DocumentException("解析规则出错！");
        }
    }

    public static Map<String, Rule> parseXml(File file) throws DocumentException {
        SAXReader reader = new SAXReader();
        HashMap ruleMap = new HashMap();

        try {
            Document e = reader.read(file);
            Element root = e.getRootElement();
            List elementList = root.elements();
            Iterator var7 = elementList.iterator();

            while(var7.hasNext()) {
                Element element = (Element)var7.next();
                Rule rule = new Rule();
                Iterator i = element.nodeIterator();

                while(i.hasNext()) {
                    Node node = (Node)i.next();
                    if(StringUtils.equals(node.getName(), "Pattern")) {
                        rule.setPattern(node.getStringValue());
                    } else if(StringUtils.equals(node.getName(), "FilterPattern")) {
                        rule.setFilterPattern(node.getStringValue());
                    } else if(StringUtils.equals(node.getName(), "RegexName")) {
                        rule.setRegexName(node.getStringValue());
                    } else if(StringUtils.equals(node.getName(), "Method")) {
                        rule.setMethod(node.getStringValue());
                    } else if(StringUtils.equals(node.getName(), "Options")) {
                        rule.setOptions(node.getStringValue());
                    }
                }

                ruleMap.put(element.getName(), rule);
            }

            return ruleMap;
        } catch (DocumentException var11) {
            throw new DocumentException("解析规则出错！");
        }
    }

    public static String getPattern(CollectParam cpm, String regexName) {
        String regexValue = "";
        Map rules = cpm.getRuleMap();
        if(rules.get(regexName) != null) {
            regexValue = ((Rule)rules.get(regexName)).getPattern();
        }

        return regexValue;
    }
}
