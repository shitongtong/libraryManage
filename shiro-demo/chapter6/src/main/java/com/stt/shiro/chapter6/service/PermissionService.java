package com.stt.shiro.chapter6.service;

import com.stt.shiro.chapter6.entity.Permission;

/**
 * Created by Administrator on 2016-12-28.
 *
 * @author shitongtong
 *
 * 实现基本的创建/删除权限。
 *
 */
public interface PermissionService {

    public Permission createPermission(Permission permission);
    public void deletePermission(Long permissionId);
}
