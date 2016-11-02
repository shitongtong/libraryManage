//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.yidu.novel.bean.UserSearchBean;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.service.UserService;
import org.yidu.novel.service.impl.HibernateSupportServiceImpl;
import org.yidu.novel.utils.Pagination;

public class UserServiceImpl extends HibernateSupportServiceImpl implements UserService {
    public UserServiceImpl() {
    }

    public TUser findByLoginInfo(String loginid, String password) {
        String sql = "FROM TUser where loginid = ? AND password = ? AND deleteflag=false";
        ArrayList params = new ArrayList();
        params.add(loginid);
        params.add(password);
        List userinfoList = this.find(sql, params);
        return userinfoList.size() > 0?(TUser)userinfoList.get(0):null;
    }

    public TUser findByLoginInfoByJDBC(String loginid, String password) {
        String sql = "select * from t_user where loginid = ? AND password = ? AND deleteflag=false";
        ArrayList params = new ArrayList();
        params.add(loginid);
        params.add(password);
        List userinfoList = this.yiduJdbcTemplate.query(sql, params.toArray(), new BeanPropertyRowMapper(TUser.class));
        return userinfoList.size() > 0?(TUser)userinfoList.get(0):null;
    }

    public List<TUser> find(UserSearchBean searchBean) {
        StringBuilder hql = new StringBuilder();
        ArrayList params = new ArrayList();
        hql.append("FROM TUser WHERE deleteflag=false ");
        this.buildCondition(searchBean, hql, params);
        Pagination pagination = searchBean.getPagination();
        if(pagination != null) {
            hql.append(pagination.getSortInfo());
            return this.findByRange(hql.toString(), pagination.getStart(), pagination.getEnd(), params);
        } else {
            hql.append("ORDER BY userno");
            return this.find(hql.toString(), params);
        }
    }

    public void delteByNo(int userno) {
        TUser user = (TUser)this.get(TUser.class, Integer.valueOf(userno));
        this.delete(user);
    }

    public void save(TUser user) {
        this.saveOrUpdate(user);
    }

    public TUser getByNo(int userno) {
        return (TUser)this.get(TUser.class, Integer.valueOf(userno));
    }

    public int getCount(UserSearchBean searchBean) {
        StringBuilder hql = new StringBuilder();
        ArrayList params = new ArrayList();
        hql.append("SELECT count(*) FROM TUser WHERE deleteflag=false ");
        this.buildCondition(searchBean, hql, params);
        return this.getIntResult(hql.toString(), params).intValue();
    }

    private void buildCondition(UserSearchBean searchBean, StringBuilder hql, List<Object> params) {
        if(searchBean.getUserno() != 0) {
            hql.append(" AND userno = ? ");
            params.add(Integer.valueOf(searchBean.getUserno()));
        }

        if(StringUtils.isNotBlank(searchBean.getLoginid())) {
            hql.append(" AND loginid = ? ");
            params.add(searchBean.getLoginid());
        }

        if(StringUtils.isNotBlank(searchBean.getUsername())) {
            hql.append(" AND username = ? ");
            params.add(searchBean.getUsername());
        }

        if(searchBean.getDeleteflag() != null) {
            hql.append(" AND deleteflag = ? ");
            params.add(searchBean.getDeleteflag());
        }

    }

    public void updateLastLoginDate(int userno, Date lastLoginDate) {
        String sql = "update t_user set lastlogin = ? where userno = ? ";
        ArrayList params = new ArrayList();
        params.add(lastLoginDate);
        params.add(Integer.valueOf(userno));
        this.yiduJdbcTemplate.update(sql, params.toArray());
    }
}
