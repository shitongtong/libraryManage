package com.stt.shiro.chapter6.utils;

import com.stt.shiro.chapter6.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by Administrator on 2016-12-29.
 *
 * @author shitongtong
 *         <p>
 *         密码生成器
 *         在创建账户及修改密码时直接把生成密码操作委托给PasswordHelper。
 */
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private String algorithmName = "MD5";

    private final int hashIterations = 2;

    /**
     * 重新生成用户密码
     *
     * @param user
     */
    public void encryptPassword(User user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(algorithmName, user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()), hashIterations).toHex();

        user.setPassword(newPassword);
    }

}
