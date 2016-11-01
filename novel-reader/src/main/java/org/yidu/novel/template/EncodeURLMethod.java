//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.template;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public class EncodeURLMethod implements TemplateMethodModel {
    private HttpServletResponse response;

    public EncodeURLMethod(HttpServletResponse response) {
        this.response = response;
    }

    public Object exec(List argList) throws TemplateModelException {
        if(argList.size() != 1) {
            throw new TemplateModelException("Wrong arguments!");
        } else {
            return this.response.encodeURL((String)argList.get(0));
        }
    }
}
