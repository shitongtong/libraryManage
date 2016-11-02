//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.admin;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import java.util.Date;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractAdminEditBaseAction;
import org.yidu.novel.entity.TSystemBlock;
import org.yidu.novel.utils.LoginManager;

@Action("blockEdit")
@Result(
        name = "redirect",
        type = "redirect",
        location = "/admin/blockList"
)
public class BlockEditAction extends AbstractAdminEditBaseAction {
    private static final long serialVersionUID = -94899397547008502L;
    private int blockno;
    private String blockname;
    private String blockid;
    private Short type;
    private String sortcol;
    private Boolean isasc;
    private Integer limitnum;
    private String content;
    private Short target;

    public BlockEditAction() {
    }

    public int getBlockno() {
        return this.blockno;
    }

    public void setBlockno(int blockno) {
        this.blockno = blockno;
    }

    public String getBlockid() {
        return this.blockid;
    }

    public void setBlockid(String blockid) {
        this.blockid = blockid;
    }

    public String getBlockname() {
        return this.blockname;
    }

    @Validations(
            requiredStrings = {            @RequiredStringValidator(
                    message = "${getText(\"errors.required.input\", {getText(\"label.admin.block.edit.blockname\")})}"
            )}
    )
    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }

    public Short getType() {
        return this.type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getSortcol() {
        return this.sortcol;
    }

    public void setSortcol(String sortcol) {
        this.sortcol = sortcol;
    }

    public Boolean getIsasc() {
        return this.isasc;
    }

    public void setIsasc(Boolean isasc) {
        this.isasc = isasc;
    }

    public Integer getLimitnum() {
        return this.limitnum;
    }

    public void setLimitnum(Integer limitnum) {
        this.limitnum = limitnum;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Short getTarget() {
        return this.target;
    }

    public void setTarget(Short target) {
        this.target = target;
    }

    protected void loadData() {
        this.logger.debug("loadData start.");
        this.initCollections(new String[]{"collectionProperties.block.type", "collectionProperties.block.target"});
        if(this.blockno != 0) {
            TSystemBlock systemBlock = this.systemBlockService.getByNo(this.blockno);
            BeanUtils.copyProperties(systemBlock, this);
        }

        this.logger.debug("loadData normally end.");
    }

    public String save() {
        this.logger.debug("save start.");
        TSystemBlock systemBlock = new TSystemBlock();
        if(this.blockno != 0) {
            systemBlock = this.systemBlockService.getByNo(this.blockno);
        } else {
//            systemBlock.setDeleteflag(Boolean.valueOf(false));
            systemBlock.setDeleteflag("false");
        }

        BeanUtils.copyProperties(this, systemBlock);
        systemBlock.setModifytime(new Date());
        systemBlock.setModifyuserno(Integer.valueOf(LoginManager.getLoginUser().getUserno()));
        this.systemBlockService.save(systemBlock);
        this.logger.debug("save normally end.");
        return "redirect";
    }
}
