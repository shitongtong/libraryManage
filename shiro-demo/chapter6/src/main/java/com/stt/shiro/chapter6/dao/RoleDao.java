package com.stt.shiro.chapter6.dao;

import com.stt.shiro.chapter6.entity.Role;

/**
 * Created by Administrator on 2016-12-28.
 *
 * @author shitongtong
 */
public interface RoleDao {

    public Role createRole(Role role);

    public void deleteRole(Long roleId);

    public void correlationPermissions(Long roleId, Long... permissionIds);

    public void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
