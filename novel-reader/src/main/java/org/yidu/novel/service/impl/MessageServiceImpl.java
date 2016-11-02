//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.yidu.novel.bean.MessageSearchBean;
import org.yidu.novel.entity.TMessage;
import org.yidu.novel.service.MessageService;
import org.yidu.novel.service.impl.HibernateSupportServiceImpl;
import org.yidu.novel.utils.Pagination;

public class MessageServiceImpl extends HibernateSupportServiceImpl implements MessageService {
    public MessageServiceImpl() {
    }

    public TMessage getByNo(int messageno) {
        return (TMessage)this.get(TMessage.class, Integer.valueOf(messageno));
    }

    public void delteByNo(int messageno) {
        TMessage message = this.getByNo(messageno);
        this.delete(message);
    }

    public void save(TMessage message) {
        this.saveOrUpdate(message);
    }

    public int getCount(MessageSearchBean searchBean) {
        StringBuffer hql = new StringBuffer();
        ArrayList params = new ArrayList();
        hql.append("SELECT count(*) FROM TMessage where  deleteflag=false ");
        return this.getIntResult(hql.toString(), params).intValue();
    }

    public List<TMessage> find(MessageSearchBean searchBean) {
        StringBuffer hql = new StringBuffer();
        ArrayList params = new ArrayList();
        hql.append("From TMessage WHERE  deleteflag=false  ");
        if(searchBean.getUserno().intValue() != 0) {
            hql.append(" AND (userno = ? OR touserno = ? )");
            params.add(searchBean.getUserno());
            params.add(searchBean.getUserno());
        }

        Pagination pagination = searchBean.getPagination();
        if(pagination != null) {
            hql.append(pagination.getSortInfo());
            return this.findByRange(hql.toString(), pagination.getStart(), pagination.getEnd(), params);
        } else {
            hql.append(" ORDER BY messageno");
            return this.find(hql.toString(), params);
        }
    }
}
