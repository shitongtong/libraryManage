package com.stt.shiro.chapter6.realm;

import com.stt.shiro.chapter6.BaseTest;
import com.stt.shiro.chapter6.entity.User;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Set;

/**
 * Created by Administrator on 2016-12-29.
 *
 * @author shitongtong
 */
public class PrincialCollectionTest extends BaseTest {

    @Test
    public void test(){
        //因为Realm里没有进行验证，所以相当于每个Realm都身份验证成功了
        login("classpath:shiro-multirealm.ini", "zhang", "123");
        Subject subject = subject();
        //获取Primary Principal（即第一个）
        Object principal = subject.getPrincipal();
        PrincipalCollection princialCollection = subject.getPrincipals();
        Object primaryPrincipal = princialCollection.getPrimaryPrincipal();
        //但是因为多个Realm都返回了Principal，所以此处到底是哪个是不确定的
        Assert.assertEquals(principal,primaryPrincipal);

        //返回 a b c
        Set<String> realmNames = princialCollection.getRealmNames();
        System.out.println(realmNames);

        //因为MyRealm1和MyRealm2返回的凭据都是zhang，所以排重了
        Set<Object> principals = princialCollection.asSet(); //asList和asSet的结果一样
        System.out.println(principals);

        //根据Realm名字获取
        Collection<User> users = princialCollection.fromRealm("c");
        System.out.println(users);

    }
}
