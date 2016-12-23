package com.stt.shiro.chapter3;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2016-12-20.
 *
 * @author shitongtong
 */
public class PermissionTest extends BaseTest {

    @Test
    public void testIsPermitted(){
        login("classpath:shiro-permission.ini", "zhang", "123");
        //判断拥有权限：user:create
        Assert.assertTrue(subject().isPermitted("user:create"));
        //判断拥有权限：user:update and user:delete
        Assert.assertTrue(subject().isPermittedAll("user:update","user:delete"));
        //判断没有权限：user:view
        Assert.assertFalse(subject().isPermitted("user:view"));
        //判断拥有权限：user:update user:delete and !user:view
        boolean[] permitted = subject().isPermitted("user:update", "user:delete", "user:view");
        Assert.assertTrue(permitted[0]);
        Assert.assertTrue(permitted[1]);
        Assert.assertFalse(permitted[2]);
    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckPermission(){
        login("classpath:shiro-permission.ini", "zhang", "123");
        //断言拥有权限：user:create
        subject().checkPermission("user:create");
        //断言拥有权限：user:delete and user:update
        subject().checkPermissions("user:delete", "user:update");
        //断言拥有权限：user:view 失败抛出异常UnauthorizedException
        subject().checkPermissions("user:view");
    }
}
