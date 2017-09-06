package com.stt;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

/**
 * Created by Administrator on 2017-01-12.
 *
 * @author shitongtong
 */
public class SimpleTest {

    @Test
    public void testPassword(){
        String USERNAME = "admin";
        String PASSWORD = "admin";
        String passwd = new SimpleHash("SHA-1", USERNAME, PASSWORD).toString();	//密码加密
        System.out.println(passwd);//dd94709528bb1c83d08f3088d4043f4742891f4f

    }
}
