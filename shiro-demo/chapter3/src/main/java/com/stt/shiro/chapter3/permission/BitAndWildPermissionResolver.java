package com.stt.shiro.chapter3.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 *
 * BitAndWildPermissionResolver实现了PermissionResolver接口，
 * 并根据权限字符串是否以“+”开头来解析权限字符串为BitPermission或WildcardPermission。
 *
 * Created by Administrator on 2016-12-21.
 *
 * @author shitongtong
 */
public class BitAndWildPermissionResolver implements PermissionResolver {
    @Override
    public Permission resolvePermission(String permissionString) {
        if (permissionString.startsWith("+")){
            return new BitPermission(permissionString);
        }
        return new WildcardPermission(permissionString);
    }
}
