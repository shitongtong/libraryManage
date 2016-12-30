package com.stt.shiro.chapter6;

import com.stt.shiro.chapter6.entity.Permission;
import com.stt.shiro.chapter6.entity.Role;
import com.stt.shiro.chapter6.entity.User;
import com.stt.shiro.chapter6.service.PermissionService;
import com.stt.shiro.chapter6.service.RoleService;
import com.stt.shiro.chapter6.service.UserService;
import com.stt.shiro.chapter6.service.impl.PermissionServiceImpl;
import com.stt.shiro.chapter6.service.impl.RoleServiceImpl;
import com.stt.shiro.chapter6.service.impl.UserServiceImpl;
import com.stt.shiro.chapter6.utils.JdbcTemplateUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Before;

/**
 * Created by Administrator on 2016-12-29.
 *
 * @author shitongtong
 */
public abstract class BaseTest {

    protected UserService userService = new UserServiceImpl();
    protected RoleService roleService = new RoleServiceImpl();
    protected PermissionService permissionService = new PermissionServiceImpl();

    protected String password = "123";

    protected User user1;
    protected User user2;
    protected User user3;
    protected User user4;
    protected Role role1;
    protected Role role2;
    protected Permission permission1;
    protected Permission permission2;
    protected Permission permission3;

    //初始化应该执行一次就好了??需要每次执行，对象是实时的
    @Before
    public void setUp(){
        //清除数据
        JdbcTemplateUtil.jdbcTemplate().update("delete from sys_users");
        JdbcTemplateUtil.jdbcTemplate().update("delete from sys_roles");
        JdbcTemplateUtil.jdbcTemplate().update("delete from sys_permissions");
        JdbcTemplateUtil.jdbcTemplate().update("delete from sys_users_roles");
        JdbcTemplateUtil.jdbcTemplate().update("delete from sys_roles_permissions");

        //1、新增权限
        permission1 = new Permission("user:create","用户模块新增",Boolean.TRUE);
        permission2 = new Permission("user:update","用户模块修改",Boolean.TRUE);
        permission3 = new Permission("menu:create","菜单模块新增",Boolean.TRUE);
        permissionService.createPermission(permission1);
        permissionService.createPermission(permission2);
        permissionService.createPermission(permission3);

        //2、新增角色
        role1 = new Role("admin","管理员",Boolean.TRUE);
        role2 = new Role("user","用户管理员",Boolean.TRUE);
        roleService.createRole(role1);
        roleService.createRole(role2);

        //3、关联角色-权限
        roleService.correlationPermissions(role1.getId(),permission1.getId());
        roleService.correlationPermissions(role1.getId(),permission2.getId());
        roleService.correlationPermissions(role1.getId(),permission3.getId());

        roleService.correlationPermissions(role2.getId(),permission1.getId());
        roleService.correlationPermissions(role2.getId(),permission2.getId());

        //4、新增用户
        user1 = new User("zhang",password);
        user2 = new User("li",password);
        user3 = new User("wu",password);
        user4 = new User("wang",password);
        user4.setLocked(Boolean.TRUE);
        userService.createUser(user1);
        userService.createUser(user2);
        userService.createUser(user3);
        userService.createUser(user4);

        //5、关联用户-角色
        userService.correlationRoles(user1.getId(),role1.getId());
    }

    @After
    public void tearDown() throws Exception{
        //退出时请解除绑定Subject到线程 否则对下次测试造成影响
        ThreadContext.unbindSubject();
    }

    protected void login(String configFile,String username,String password){

        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
//        Subject subject = SecurityUtils.getSubject();
        Subject subject = subject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        subject.login(token);

    }

    protected Subject subject(){
        return SecurityUtils.getSubject();
    }
}
