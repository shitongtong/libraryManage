package com.stt.shiro.chapter10.session.dao;

import com.stt.shiro.chapter10.JdbcTemplateUtils;
import com.stt.shiro.chapter10.SerializableUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义实现SessionDAO，继承CachingSessionDAO即可
 *
 * Created by Administrator on 2017-01-03.
 *
 * @author shitongtong
 */
public class MySessionDAO extends CachingSessionDAO {

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

    //更新会话；如更新会话最后访问时间/停止会话/设置超时时间/设置移除属性等会调用
    @Override
    protected void doUpdate(Session session) {
        if (session instanceof ValidatingSession && !((ValidatingSession)session).isValid()){
            return;//如果会话过期/停止 没必要再更新了
        }
        String sql = "update sessions set session=? where id=?";
        jdbcTemplate.update(sql,SerializableUtils.serialize(session),session.getId());
    }

    //删除会话；当会话过期/会话停止（如用户退出时）会调用
    @Override
    protected void doDelete(Session session) {
        String sql = "delete from session where id=?";
        jdbcTemplate.update(sql,session.getId());
    }

    //如DefaultSessionManager在创建完session后会调用该方法；如保存到关系数据库/文件系统/NoSQL数据库；
    // 即可以实现会话的持久化；返回会话ID；主要此处返回的ID.equals(session.getId())；
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session,sessionId);
        String sql = "insert into sessions(id,session) values(?,?)";
        jdbcTemplate.update(sql,sessionId, SerializableUtils.serialize(session));
        return session.getId();
    }

    //根据会话ID获取会话
    @Override
    protected Session doReadSession(Serializable sessionId) {
        String sql = "select session from sessions where id=?";
        List<String> sessionStrList = jdbcTemplate.queryForList(sql, String.class, sessionId);
        if (sessionStrList.size() == 0) return null;
        return SerializableUtils.deserialize(sessionStrList.get(0));
    }
}
