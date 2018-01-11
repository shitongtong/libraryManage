package com.stt.util2;

import com.stt.util.RedisUtil;

import java.util.Iterator;
import java.util.Set;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/11/18.
 */
public class DeleteRedisData {
    private static RedisUtil redisUtil = new RedisUtil("106.14.46.51", 6300, "onlyhi.cn");

    public static void main(String[] args) {
        //删除学生数据
        deleteToken("TEACHER");
        //删除老师数据
        deleteToken("TEACHER");
        //删除家长数据
        deleteToken("PATRIARCH");

    }

    private static void deleteToken(String userType){
        Set<String> keys = redisUtil.keys("*" + userType);
        Iterator<String> it = keys.iterator();
        while(it.hasNext()){
            String key = it.next();
            String token = redisUtil.get(key);
            redisUtil.del(token);
            redisUtil.del(key);
        }
    }
}
