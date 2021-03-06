//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.loader;

import ch.qos.logback.core.joran.spi.JoranException;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yi.spider.constants.GlobalConfig;
import org.yi.spider.enums.ProgramEnum;
import org.yi.spider.model.Category;
import org.yi.spider.model.DuoYinZi;
import org.yi.spider.model.Template;
import org.yi.spider.utils.FileUtils;
import org.yi.spider.utils.LogUtils;
import org.yi.spider.utils.PropertiesUtils;

public class InitCfgLoader {

    private static Logger logger = LoggerFactory.getLogger(InitCfgLoader.class);

    public InitCfgLoader() {
    }

    public static void load() throws Exception {
//        loadLogback();
        loadCollectConfig();
        loadCategories();
        loadSiteConfig();
        loadDuoYinZi();
    }

    private static void loadDuoYinZi() throws ConfigurationException {
        List list = FileUtils.readFile2List("duoyinzi", "utf-8");
        Iterator var2 = list.iterator();

        while(var2.hasNext()) {
            String ss = (String)var2.next();
            GlobalConfig.duoyin.add(new DuoYinZi(ss.split("=")[0], ss.split("=")[1]));
        }

    }

    private static void loadLogback() throws JoranException, IOException {
        try {
            File file = FileUtils.locateAbsolutePathFromClasspath("logback.xml");
            String absolutePath = file.getAbsolutePath();
            String filePath = file.getPath();
            String name = file.getName();
            logger.info("absolutePath:"+absolutePath);
            logger.info("filePath:"+filePath);
            logger.info("name:"+name);
            LogUtils.load(absolutePath);
//            LogUtils.load(filePath);
//            LogUtils.load(name);
        } catch (IOException var1) {
            logger.error(var1.getMessage(),var1);
            throw new IOException("查找logback.xml失败！");
        } catch (JoranException var2) {
            throw new JoranException(var2.getMessage());
        }
    }

    public static void loadCollectConfig() throws ConfigurationException {
        try {
            GlobalConfig.collect = PropertiesUtils.load("collect.ini", "utf-8");
        } catch (ConfigurationException var1) {
            throw new ConfigurationException("读取配置文件出错，" + var1.getMessage());
        }
    }

    private static void loadCategories() throws Exception {
        BufferedReader reader = null;

        try {
//            File file = FileUtils.locateAbsolutePathFromClasspath("category.ini");
//            String filePath = FileUtils.locateFromClasspath("category.ini").getFile();
//            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));

            //返回读取指定资源的输入流
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream is = loader.getResourceAsStream("category.ini");
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String e = null;
            boolean grade = true;

            while((e = reader.readLine()) != null) {
                if("[small]".equalsIgnoreCase(e)) {
                    grade = false;
                } else {
                    grade = true;
                }

                String[] s = e.split("\\|");
                if(s.length == 3) {
                    Category c = new Category();
                    c.setId(s[0]);
                    c.setName(s[1]);
                    c.setWords(Arrays.asList(s[2].split(",")));
                    if(!grade) {
                        GlobalConfig.SUB_CATEGORY.add(c);
                    } else {
                        GlobalConfig.TOP_CATEGORY.add(c);
                    }
                }
            }

            reader.close();
        } catch (Exception var12) {
            logger.debug(var12.getMessage(),var12);
            throw new Exception("加载分类异常！");
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException var11) {
                    logger.debug(var11.getMessage(),var11);
                    throw new IOException("IO异常！");
                }
            }

        }

    }

    private static void loadSiteConfig() throws ConfigurationException {
        try {
            GlobalConfig.site = PropertiesUtils.load("site.ini", "utf-8");
        } catch (ConfigurationException var1) {
            throw new ConfigurationException("读取配置文件出错，" + var1.getMessage());
        }

        GlobalConfig.localSite.setSiteUrl(GlobalConfig.site.getString("local_site_url"));
        GlobalConfig.localSite.setSiteName(GlobalConfig.site.getString("local_site_name"));
        GlobalConfig.localSite.setProgram(ProgramEnum.parseEnum(GlobalConfig.site.getString("local_program")));
        GlobalConfig.localSite.setBasePath(GlobalConfig.site.getString("base_path"));
        GlobalConfig.localSite.setCharset(GlobalConfig.site.getString("charset"));
        GlobalConfig.localSite.setTxtFile(GlobalConfig.site.getString("txt_file"));
        GlobalConfig.localSite.setHtmlFile(GlobalConfig.site.getString("html_file"));
        GlobalConfig.localSite.setCoverDir(GlobalConfig.site.getString("cover_dir"));
        GlobalConfig.localSite.setUsePinyin(GlobalConfig.site.getInteger("use_pinyin", Integer.valueOf(0)));
        Template tp = new Template();
        tp.setIndex(GlobalConfig.site.getString("template_index"));
        tp.setList(GlobalConfig.site.getString("template_list"));
        tp.setTop(GlobalConfig.site.getString("template_top"));
        tp.setInfo(GlobalConfig.site.getString("template_info"));
        tp.setInfoURL(GlobalConfig.site.getString("url_info"));
        tp.setChapter(GlobalConfig.site.getString("template_chapter"));
        tp.setRowSize(Integer.valueOf(GlobalConfig.site.getInt("chapter_row_size", 4)));
        tp.setChapterURL(GlobalConfig.site.getString("url_chapter"));
        tp.setReader(GlobalConfig.site.getString("template_reader"));
        tp.setReaderURL(GlobalConfig.site.getString("url_reader"));
        GlobalConfig.localSite.setTemplate(tp);
    }
}
