package com.stt.shiro.chapter6.dao;

import com.stt.shiro.chapter6.entity.Permission;

/**
 * Created by Administrator on 2016-12-28.
 *
 * @author shitongtong
 */
public interface PermissionDao {

    public Permission createPermission(Permission permission);

    public void deletePermission(Long permissionId);

}
