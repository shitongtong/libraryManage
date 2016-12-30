package com.stt.shiro.chapter6.service.impl;

import com.stt.shiro.chapter6.dao.PermissionDao;
import com.stt.shiro.chapter6.dao.impl.PermissionDaoImpl;
import com.stt.shiro.chapter6.entity.Permission;
import com.stt.shiro.chapter6.service.PermissionService;

/**
 * Created by Administrator on 2016-12-28.
 *
 * @author shitongtong
 */
public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao = new PermissionDaoImpl();

    @Override
    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    @Override
    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
