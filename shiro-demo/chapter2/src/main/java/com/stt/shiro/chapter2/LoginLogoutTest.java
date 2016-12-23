package com.stt.shiro.chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2016-12-19.
 *
 * @author shitongtong
 */
public class LoginLogoutTest {

    @Test
    public void testHelloWorld(){
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2、得到SecurityManager实例 并绑定给    SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        System.out.println("token=="+token.toString());

        try{
            //4、登录，即身份验证
            subject.login(token);
        }catch (AuthenticationException e){
            //5、身份验证失败
            //TODO
            e.printStackTrace();
        }

        //断言用户已经登录
        Assert.assertEquals(true,subject.isAuthenticated());

        //6、退出
        subject.logout();

    }

    @Test
    public void testCustomRealm(){
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            //4、登录，即身份验证
            subject.login(token);
        }catch (AuthenticationException e){
            //5、身份验证失败
            System.err.println("身份验证失败");
            e.printStackTrace();
        }

        //断言用户已经登录
        Assert.assertEquals(true,subject.isAuthenticated());
        //6、退出
        subject.logout();
    }

    @Test
    public void testCustomMultiRealm(){
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");
        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wang", "123");
        try {
            //4、登录，即身份验证
            subject.login(token);
        }catch (AuthenticationException e){
            //5、身份验证失败
            System.err.println("身份验证失败");
            e.printStackTrace();
        }

        //断言用户已经登录
        Assert.assertEquals(true,subject.isAuthenticated());
        //6、退出
        subject.logout();
    }

    @Test
    public void testJDBCRealm(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            subject.login(token);
        }catch (AuthenticationException e){
            System.err.println("身份认证失败");
            e.printStackTrace();
        }
        Assert.assertEquals(true,subject.isAuthenticated());
        subject.logout();
    }

    @After
    public void tearDown(){
        //退出时请解除绑定Subject到线程 否则对下次测试造成影响
        ThreadContext.unbindSubject();
    }
}
