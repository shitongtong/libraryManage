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
        String USERNAME = "zhangsan";
        String PASSWORD = "123";
        String passwd = new SimpleHash("SHA-1", USERNAME, PASSWORD).toString();	//密码加密
        System.out.println(passwd);

    }
}
