//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.yidu.novel.bean.SystemBlockSearchBean;
import org.yidu.novel.entity.TSystemBlock;
import org.yidu.novel.service.SystemBlockService;
import org.yidu.novel.service.impl.HibernateSupportServiceImpl;
import org.yidu.novel.utils.Pagination;

public class SystemBlockServiceImpl extends HibernateSupportServiceImpl implements SystemBlockService {
    public SystemBlockServiceImpl() {
    }

    public List<TSystemBlock> find(SystemBlockSearchBean searchBean) {
        StringBuffer hql = new StringBuffer();
        ArrayList params = new ArrayList();
        hql.append("FROM TSystemBlock where deleteflag='false' ");
        if(searchBean.getBlockno() != 0) {
            hql.append(" AND blockno = ? ");
            params.add(Integer.valueOf(searchBean.getBlockno()));
        }

        if(StringUtils.isNotEmpty(searchBean.getBlockname())) {
            hql.append(" AND blockname = ? ");
            params.add(searchBean.getBlockname());
        }

        if(searchBean.getType() != null && searchBean.getType().shortValue() != 0) {
            hql.append(" AND type = ? ");
            params.add(searchBean.getType());
        }

        Pagination pagination = searchBean.getPagination();
        if(pagination != null) {
            hql.append(pagination.getSortInfo());
            return this.findByRange(hql.toString(), pagination.getStart(), pagination.getEnd(), params);
        } else {
            return this.find(hql.toString(), params);
        }
    }

    public TSystemBlock getByNo(int blockno) {
        TSystemBlock systemBlock = (TSystemBlock)this.get(TSystemBlock.class, Integer.valueOf(blockno));
        return systemBlock;
    }

    public void delteByNo(int blockno) {
        TSystemBlock systemBlock = this.getByNo(blockno);
        this.delete(systemBlock);
    }

    public void save(TSystemBlock systemBlock) {
        this.saveOrUpdate(systemBlock);
    }

    public Integer getCount(SystemBlockSearchBean searchBean) {
        StringBuffer hql = new StringBuffer();
        ArrayList params = new ArrayList();
        hql.append("Select count(*) FROM TSystemBlock where  deleteflag='false' ");
        if(searchBean.getBlockno() != 0) {
            hql.append(" AND blockno = ? ");
            params.add(String.valueOf(searchBean.getBlockno()));
        }

        if(StringUtils.isNotEmpty(searchBean.getBlockname())) {
            hql.append(" AND blockname = ? ");
            params.add(searchBean.getBlockname());
        }

        if(searchBean.getType() != null && searchBean.getType().shortValue() != 0) {
            hql.append(" AND type = ? ");
            params.add(searchBean.getType());
        }

        return this.getIntResult(hql.toString(), params);
    }
}
