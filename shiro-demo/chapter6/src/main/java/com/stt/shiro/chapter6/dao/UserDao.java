package com.stt.shiro.chapter6.dao;

import com.stt.shiro.chapter6.entity.User;

import java.util.Set;

/**
 * Created by Administrator on 2016-12-28.
 *
 * @author shitongtong
 */
public interface UserDao {
    public User createUser(User user);
    public void updateUser(User user);
    public void deleteUser(Long userId);

    public void correlationRoles(Long userId, Long... roleIds);
    public void uncorrelationRoles(Long userId, Long... roleIds);

    User findOne(Long userId);

    User findByUsername(String username);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);
}
