//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.action.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractAdminListBaseAction;
import org.yidu.novel.bean.SystemBlockSearchBean;
import org.yidu.novel.entity.TSystemBlock;
import org.yidu.novel.utils.LoginManager;

@Action("blockList")
public class BlockListAction extends AbstractAdminListBaseAction {
    private static final long serialVersionUID = -4110412379794700028L;
    public static final String NAME = "blockList";
    public static final String URL = "/admin/blockList";
    private int blockno;
    List<TSystemBlock> blockList = new ArrayList();

    public BlockListAction() {
    }

    public int getBlockno() {
        return this.blockno;
    }

    public void setBlockno(int blockno) {
        this.blockno = blockno;
    }

    public List<TSystemBlock> getBlockList() {
        return this.blockList;
    }

    protected void loadData() {
        this.logger.debug("loadData start.");
        this.initCollections(new String[]{"collectionProperties.block.type"});
        SystemBlockSearchBean searchBean = new SystemBlockSearchBean();
        if(StringUtils.isEmpty(this.pagination.getSortColumn())) {
            this.pagination.setSortColumn("blockno");
        }

        searchBean.setPagination(this.pagination);
        this.pagination.setPreperties(this.systemBlockService.getCount(searchBean).intValue());
        this.blockList = this.systemBlockService.find(searchBean);
        this.pagination.setPageRecords(this.blockList.size());
        this.logger.debug("loadData normally end.");
    }

    public String delete() throws Exception {
        this.logger.debug("del start.");
        TSystemBlock systemBlock = this.systemBlockService.getByNo(this.blockno);
//        systemBlock.setDeleteflag(Boolean.valueOf(true));
        systemBlock.setDeleteflag("true");
        systemBlock.setModifyuserno(Integer.valueOf(LoginManager.getLoginUser().getUserno()));
        systemBlock.setModifytime(new Date());
        this.systemBlockService.save(systemBlock);
        this.loadData();
        this.logger.debug("del normally end.");
        return "success";
    }
}
