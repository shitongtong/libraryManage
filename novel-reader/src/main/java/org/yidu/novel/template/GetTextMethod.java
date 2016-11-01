//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.template;

import com.opensymphony.xwork2.ActionSupport;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;
import java.util.List;

public class GetTextMethod implements TemplateMethodModel {
    private ActionSupport action;

    public GetTextMethod(ActionSupport action) {
        this.action = action;
    }

    public Object exec(List argList) throws TemplateModelException {
        if(argList.size() != 1) {
            throw new TemplateModelException("Wrong arguments!");
        } else {
            return this.action.getText((String)argList.get(0));
        }
    }
}
