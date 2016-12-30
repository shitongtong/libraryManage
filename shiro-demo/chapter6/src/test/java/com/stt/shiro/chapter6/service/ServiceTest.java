package com.stt.shiro.chapter6.service;

import com.stt.shiro.chapter6.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Created by Administrator on 2016-12-29.
 *
 * @author shitongtong
 */
public class ServiceTest extends BaseTest {

    @Test
    public void testUserRolePermissionRelation(){

        //zhang
        Set<String> roles = userService.findRoles(user1.getUsername());
        for (String role : roles){
            System.out.println(role);
        }
        Assert.assertEquals(1, roles.size());
        Assert.assertTrue(roles.contains(role1.getRole()));
        Set<String> permissions = userService.findPermissions(user1.getUsername());
        for (String permission : permissions){
            System.out.println(permission);
        }
        Assert.assertEquals(3, permissions.size());
        Assert.assertTrue(permissions.contains(permission3.getPermission()));

        //li
        roles = userService.findRoles(user2.getUsername());
        Assert.assertEquals(0, roles.size());
        permissions = userService.findPermissions(user2.getUsername());
        Assert.assertEquals(0, permissions.size());


        //解除 admin-menu:update关联
        roleService.uncorrelationPermissions(role1.getId(), permission3.getId());
        permissions = userService.findPermissions(user1.getUsername());
        Assert.assertEquals(2, permissions.size());
        Assert.assertFalse(permissions.contains(permission3.getPermission()));


        //删除一个permission
        permissionService.deletePermission(permission2.getId());
        permissions = userService.findPermissions(user1.getUsername());
        Assert.assertEquals(1, permissions.size());

        //解除 zhang-admin关联
        userService.uncorrelationRoles(user1.getId(), role1.getId());
        roles = userService.findRoles(user1.getUsername());
        Assert.assertEquals(0, roles.size());
    }

}
