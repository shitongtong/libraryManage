package com.stt.shiro.chapter2.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;


/**
 * Created by Administrator on 2016-12-19.
 */
public class MyRealm2 implements Realm {
    //返回一个唯一的Realm名字
    @Override
    public String getName() {
        return "myrealm2";
    }

    //判断此Realm是否支持此Token
    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        //仅支持UsernamePasswordToken类型的Token
        return authenticationToken instanceof UsernamePasswordToken;
    }

    //根据Token获取认证信息
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println(getName()+"认证开始...");
        //得到用户名
        String username = (String) authenticationToken.getPrincipal();
        //得到密码
        String password = new String((char[]) authenticationToken.getCredentials());
        //如果用户名错误
        if (!"wang".equals(username)) {
            throw new UnknownAccountException();
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException();
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());
        System.out.println(getName()+"认证结束...");
        return info;
    }
}
