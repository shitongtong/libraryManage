package com.stt.util;

import org.junit.Test;
import redis.clients.jedis.Tuple;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/4/20.
 */
public class RedisDaoTest {

    private static RedisDao redisDao = new RedisDao();

    @Test
    public void testzadd() {
        String key = "taset1";
        Map<String, Double> scoreMembers = new HashMap<>();
        scoreMembers.put("stt1", 1d);
        scoreMembers.put("stt2", 2d);
        scoreMembers.put("stt3", 3d);
        long cont = redisDao.zadd(key, scoreMembers);
        System.out.println(cont);
    }

    @Test
    public void testzrank() {
        System.out.println(redisDao.zrank("taset", "stt3"));
    }

    @Test
    public void testzcard() {
        System.out.println(redisDao.zcard("taset"));
    }

    @Test
    public void testzincrby() {
        System.out.println(redisDao.zincrby("taset", 11, "stt2"));
    }

    @Test
    public void testzrange() {
        Set<String> taset11 = redisDao.zrange("taset11", 0, 0);
        System.out.println(taset11);
        System.out.println(taset11.size());
    }

    @Test
    public void testzrangeWithScores() {
        Set<Tuple> tupleSet = redisDao.zrangeWithScores("taset2", 0, 0);
        Optional<Tuple> tupleOptional = tupleSet.stream().findFirst();
        if (!tupleOptional.isPresent()){
            System.out.println("ssss");
        }else{
            Tuple tuple1 = tupleOptional.get();
            System.out.println(tuple1);
        }
        for (Tuple tuple : tupleSet) {
            double score = tuple.getScore();
            String element = tuple.getElement();
            System.out.println(score);
            System.out.println(element);
        }
        System.out.println();
    }

    @Test
    public void testzrem(){
        System.out.println(redisDao.zrem("taset1","stt1"));
    }

}
