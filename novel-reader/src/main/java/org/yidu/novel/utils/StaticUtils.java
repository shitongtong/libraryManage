//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletContext;

public class StaticUtils {
    public StaticUtils() {
    }

    public static void crateHTML(ServletContext context, Map<String, Object> data, String templatePath, String targetHtmlPath) {
        Configuration freemarkerCfg = new Configuration();
        freemarkerCfg.setServletContextForTemplateLoading(context, "/");
        freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");

        try {
            Template e = freemarkerCfg.getTemplate(templatePath, "UTF-8");
            e.setEncoding("UTF-8");
            String htmlPath = context.getRealPath("/html") + "/" + targetHtmlPath;
            File htmlFile = new File(htmlPath);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
            e.process(data, out);
            out.flush();
            out.close();
        } catch (Exception var9) {
            var9.printStackTrace();
        }

    }
}
