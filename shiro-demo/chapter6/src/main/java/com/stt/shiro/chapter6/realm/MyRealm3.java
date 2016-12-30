package com.stt.shiro.chapter6.realm;

import com.stt.shiro.chapter6.entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
 * Created by Administrator on 2016-12-29.
 *
 * @author shitongtong
 */
public class MyRealm3 implements Realm {
    @Override
    public String getName() {
        //realm name 为 “c”
        return "c";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        User user = new User("zhang", "123");
        return new SimpleAuthenticationInfo(
                user, //身份 字符串类型
                "123",   //凭据
                getName() //Realm Name
        );
    }
}
