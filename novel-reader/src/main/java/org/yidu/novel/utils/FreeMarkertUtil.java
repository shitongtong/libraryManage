//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.utils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import javax.servlet.ServletContext;

public class FreeMarkertUtil {
    private static Configuration config = new Configuration();

    public FreeMarkertUtil() {
    }

    public static void processTemplate(String templateName, Map<?, ?> root, Writer out) {
        try {
            Template e = config.getTemplate(templateName, "utf-8");
            e.process(root, out);
            out.flush();
        } catch (IOException var14) {
            var14.printStackTrace();
        } catch (TemplateException var15) {
            var15.printStackTrace();
        } finally {
            try {
                out.close();
                out = null;
            } catch (IOException var13) {
                var13.printStackTrace();
            }

        }

    }

    public static void initConfig(ServletContext servletContext, String templateDir) {
        config.setDefaultEncoding("utf-8");
        config.setServletContextForTemplateLoading(servletContext, templateDir);
        config.setObjectWrapper(new DefaultObjectWrapper());
    }

    public static void initConfig(String templateDir) throws IOException {
        config.setDirectoryForTemplateLoading(new File(templateDir));
    }
}
