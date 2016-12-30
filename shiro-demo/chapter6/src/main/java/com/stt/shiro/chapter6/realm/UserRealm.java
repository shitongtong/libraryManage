package com.stt.shiro.chapter6.realm;

import com.stt.shiro.chapter6.entity.User;
import com.stt.shiro.chapter6.service.UserService;
import com.stt.shiro.chapter6.service.impl.UserServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * Created by Administrator on 2016-12-29.
 *
 * @author shitongtong
 */
public class UserRealm extends AuthorizingRealm {

    private UserService userService = new UserServiceImpl();

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(userService.findRoles(username));
        simpleAuthorizationInfo.setStringPermissions(userService.findPermissions(username));
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        User user = userService.findByUsername(username);
        if (user == null) {//未找到账号
            throw new UnknownAccountException();
        }
        if (Boolean.TRUE.equals(user.getLocked())) {//账号已锁定
            throw new LockedAccountException();
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(),//用户名
                user.getPassword(),//密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//盐：salt=username+salt
                getName()//realm name
        );
        return simpleAuthenticationInfo;
    }
}
