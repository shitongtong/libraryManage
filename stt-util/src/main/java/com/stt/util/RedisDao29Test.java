package com.stt.util;

import org.junit.Test;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2018/4/3.
 */
public class RedisDao29Test {
    RedisDao29 redisDao29 = new RedisDao29();
    String key = "person-of-course_courseUuidTest";
    String member1 = "STUDENT";
    String member2 = "TEACHER";

    @Test
    public void testsadd() {
        System.out.println(redisDao29.sadd(key, member1));
        System.out.println(redisDao29.sadd(key, member2));
    }

    @Test
    public void testsismember() {
        System.out.println(redisDao29.sismember(key, member1));
    }

    @Test
    public void testsrem() {
        System.out.println(redisDao29.srem(key, member1));
    }
}
