//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.service.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.persister.entity.Joinable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.yidu.novel.service.impl.BaseServiceImpl;

public class HibernateSupportServiceImpl extends BaseServiceImpl {
    protected SessionFactory sessionFactory;

    public HibernateSupportServiceImpl() {
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected final <T> T get(Class<T> entityClass, Serializable id) {
        return (T) this.sessionFactory.getCurrentSession().get(entityClass, id);
    }

    protected final <T> T load(Class<T> entityClass, Serializable id) {
        return (T) this.sessionFactory.getCurrentSession().load(entityClass, id);
    }

    protected final void delete(Object entity) {
        this.sessionFactory.getCurrentSession().delete(entity);
        this.sessionFactory.getCurrentSession().flush();
    }

    protected final void save(Object entity) {
        this.sessionFactory.getCurrentSession().save(entity);
        this.sessionFactory.getCurrentSession().flush();
    }

    protected final void update(Object entity) {
        this.sessionFactory.getCurrentSession().update(entity);
        this.sessionFactory.getCurrentSession().flush();
    }

    protected final void saveOrUpdate(Object entity) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(entity);
        this.sessionFactory.getCurrentSession().flush();
    }

    protected final Query getQuery(String hql, List<?> params) {
        return this.getQuery(hql, params.toArray());
    }

    protected final Query getQuery(String hql, Object... params) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        if(params != null && params.length != 0) {
            for(int i = 0; i < params.length; ++i) {
                query.setParameter(i, params[i]);
            }
        }

        return query;
    }

    protected final Integer getIntResult(String hql, List<?> params) {
        return this.getIntResult(hql, params.toArray());
    }

    protected final Integer getIntResult(String hql, Object... params) {
        Query query = this.getQuery(hql, params);
        return new Integer(((Number)query.uniqueResult()).intValue());
    }

    protected final <T> List<T> find(String hql, List<?> params) {
        return this.find(hql, params.toArray());
    }

    protected final <T> List<T> find(String hql, Object... params) {
        Query query = this.getQuery(hql, params);
        return query.list();
    }

    protected final <T> List<T> findByRange(String hql, int firstResult, int maxResults, List<?> params) {
        return this.findByRange(hql, firstResult, maxResults, params.toArray());
    }

    protected final <T> List<T> findByRange(String hql, int firstResult, int maxResults, Object... params) {
        Query query = this.getQuery(hql, params);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        return query.list();
    }

    protected final void execute(String hql, List<?> params) {
        this.execute(hql, params.toArray());
    }

    protected final void execute(String hql, Object... params) {
        Query query = this.getQuery(hql, params);
        query.executeUpdate();
        this.sessionFactory.getCurrentSession().flush();
    }

    protected final void sqlQuery(String sql, List<?> params) {
        this.sqlQuery(sql, params.toArray());
    }

    protected final void sqlQuery(String sql, Object... params) {
        Query query = this.getQuery(sql, params);
        query.executeUpdate();
        this.sessionFactory.getCurrentSession().clear();
    }

    protected final List<?> getList(String sql, List<?> params) {
        return this.getList(sql, params.toArray());
    }

    protected final List<?> getList(String sql, Object... params) {
        Query query = this.getQuery(sql, params);
        List list = query.list();
        return list;
    }

    protected final List<BigInteger> getIntList(String sql, Object... params) {
        Query query = this.getQuery(sql, params);
        List list = query.list();
        return list;
    }

    protected final void delete(String sql, int... params) {
        Query query = this.getQuery(sql, new Object[]{params});
        query.executeUpdate();
    }

    protected final List<String> getColumnNames(Class<?> entityClass) {
        ArrayList result = new ArrayList();
        AbstractEntityPersister aep = (AbstractEntityPersister)this.sessionFactory.getCurrentSession().getSessionFactory().getClassMetadata(entityClass);
        result.addAll(Arrays.asList(aep.getIdentifierColumnNames()));
        String[] var7;
        int var6 = (var7 = aep.getPropertyNames()).length;

        for(int var5 = 0; var5 < var6; ++var5) {
            String propertyName = var7[var5];
            if(!aep.getPropertyType(propertyName).isCollectionType()) {
                String[] var11;
                int var10 = (var11 = aep.getPropertyColumnNames(propertyName)).length;

                for(int var9 = 0; var9 < var10; ++var9) {
                    String column = var11[var9];
                    result.add(column);
                }
            }
        }

        return result;
    }

    protected final String getColumnStr(Class<?> entityClass, String str) {
        return StringUtils.join(this.getColumnNames(entityClass), str);
    }

    protected final void rollback() {
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }

    protected final String getTableNameByEntity(Class<?> entityClass) {
        ClassMetadata meta = this.sessionFactory.getClassMetadata(entityClass);
        return ((Joinable)Joinable.class.cast(meta)).getTableName();
    }

    protected final String getTableNameByEntity(String entityName) {
        ClassMetadata meta = this.sessionFactory.getClassMetadata(entityName);
        return ((Joinable)Joinable.class.cast(meta)).getTableName();
    }

    protected final void clearSession() {
        this.sessionFactory.getCurrentSession().clear();
    }
}
