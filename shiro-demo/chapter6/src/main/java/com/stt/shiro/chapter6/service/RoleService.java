package com.stt.shiro.chapter6.service;

import com.stt.shiro.chapter6.entity.Role;

/**
 * Created by Administrator on 2016-12-28.
 *
 * @author shitongtong
 */
public interface RoleService {
    public Role createRole(Role role);
    public void deleteRole(Long roleId);

    /**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void correlationPermissions(Long roleId, Long... permissionIds);

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
