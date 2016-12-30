package com.stt.shiro.chapter6.service.impl;

import com.stt.shiro.chapter6.dao.RoleDao;
import com.stt.shiro.chapter6.dao.impl.RoleDaoImpl;
import com.stt.shiro.chapter6.entity.Role;
import com.stt.shiro.chapter6.service.RoleService;

/**
 * Created by Administrator on 2016-12-28.
 *
 * @author shitongtong
 */
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao = new RoleDaoImpl();

    @Override
    public Role createRole(Role role) {
        return roleDao.createRole(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        roleDao.correlationPermissions(roleId,permissionIds);
    }

    @Override
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        roleDao.uncorrelationPermissions(roleId,permissionIds);
    }
}
