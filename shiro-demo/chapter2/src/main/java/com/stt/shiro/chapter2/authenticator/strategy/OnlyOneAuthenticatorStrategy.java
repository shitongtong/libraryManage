package com.stt.shiro.chapter2.authenticator.strategy;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.realm.Realm;

import java.util.Collection;

/**
 * Created by Administrator on 2016-12-20.
 *
 * @author shitongtong
 */
public class OnlyOneAuthenticatorStrategy extends AbstractAuthenticationStrategy {

    @Override
    public AuthenticationInfo beforeAllAttempts(Collection<? extends Realm> realms, AuthenticationToken token) throws AuthenticationException {
        return super.beforeAllAttempts(realms, token);//返回一个权限的认证信息
    }

    @Override
    public AuthenticationInfo beforeAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo aggregate) throws AuthenticationException {
        return super.beforeAttempt(realm, token, aggregate);//返回之前合并的
    }

    @Override
    public AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo singleRealmInfo, AuthenticationInfo aggregateInfo, Throwable t) throws AuthenticationException {
        AuthenticationInfo info = super.afterAttempt(realm, token, singleRealmInfo, aggregateInfo, t);
        if (info.getPrincipals().getRealmNames().size() > 1){
            System.out.println(info.getPrincipals().getRealmNames());
            throw new AuthenticationException("Authentication token of type [" + token.getClass() + "] " +
                    "could not be authenticated by any configured realms.  Please ensure that only one realm can " +
                    "authenticate these tokens.");
        }
        return info;
    }

    @Override
    public AuthenticationInfo afterAllAttempts(AuthenticationToken token, AuthenticationInfo aggregate) throws AuthenticationException {
        AuthenticationInfo info = super.afterAllAttempts(token, aggregate);
        /*if (info == null || CollectionUtils.isEmpty(info.getPrincipals()) || info.getPrincipals().getRealmNames().size() > 1){
            System.out.println(info.getPrincipals().getRealmNames());
            throw new AuthenticationException("Authentication token of type [" + token.getClass() + "] " +
                    "could not be authenticated by any configured realms.  Please ensure that only one realm can " +
                    "authenticate these tokens.");
        }*/
        return info;
    }
}
