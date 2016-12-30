package com.stt.shiro.chapter6.dao.impl;

import com.stt.shiro.chapter6.dao.PermissionDao;
import com.stt.shiro.chapter6.entity.Permission;
import com.stt.shiro.chapter6.service.PermissionService;
import com.stt.shiro.chapter6.utils.JdbcTemplateUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016-12-28.
 *
 * @author shitongtong
 */
public class PermissionDaoImpl implements PermissionDao {

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtil.jdbcTemplate();

    @Override
    public Permission createPermission(final Permission permission) {
        final String sql = "insert into sys_permissions (permission,description,available) values(?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
                preparedStatement.setString(1,permission.getPermission());
                preparedStatement.setString(2,permission.getDescription());
                preparedStatement.setBoolean(3,permission.getAvailable());
                return preparedStatement;
            }
        }, keyHolder);

        permission.setId((Long) keyHolder.getKey());

        return permission;
    }

    @Override
    public void deletePermission(Long permissionId) {
        //1，首先把与权限关联的表(角色权限表)数据删除
        String sql = "delete from sys_roles_permissions where permission_id=?";
        jdbcTemplate.update(sql,permissionId);

        //2，删除权限表数据
        sql = "delete from sys_permissions where id=?";
        jdbcTemplate.update(sql,permissionId);
    }
}
