package com.stt.shiro.chapter6.service.impl;

import com.stt.shiro.chapter6.dao.UserDao;
import com.stt.shiro.chapter6.dao.impl.UserDaoImpl;
import com.stt.shiro.chapter6.entity.User;
import com.stt.shiro.chapter6.service.UserService;
import com.stt.shiro.chapter6.utils.PasswordHelper;

import java.util.Set;

/**
 * Created by Administrator on 2016-12-28.
 *
 * @author shitongtong
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    private PasswordHelper passwordHelper = new PasswordHelper();

    @Override
    public User createUser(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        return userDao.createUser(user);
    }

    @Override
    public void changePassword(Long userId, String newPassword) {
        User user = userDao.findOne(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.updateUser(user);
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        userDao.correlationRoles(userId,roleIds);
    }

    @Override
    public void uncorrelationRoles(Long userId, Long... roleIds) {
        userDao.uncorrelationRoles(userId,roleIds);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Set<String> findRoles(String username) {
        return userDao.findRoles(username);
    }

    @Override
    public Set<String> findPermissions(String username) {
        return userDao.findPermissions(username);
    }
}
