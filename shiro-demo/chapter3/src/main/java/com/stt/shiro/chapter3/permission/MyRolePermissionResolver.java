package com.stt.shiro.chapter3.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import java.util.Arrays;
import java.util.Collection;

/**
 *
 * RolePermissionResolver用于根据角色字符串来解析得到权限集合。
 * 此处的实现很简单，如果用户拥有role1，那么就返回一个“menu:*”的权限。
 *
 * Created by Administrator on 2016-12-21.
 *
 * @author shitongtong
 */
public class MyRolePermissionResolver implements RolePermissionResolver{
    @Override
    public Collection<Permission> resolvePermissionsInRole(String roleString) {
        if ("role1".equals(roleString)){
            return Arrays.asList((Permission) new WildcardPermission("menu:*"));
        }
        return null;
    }
}
