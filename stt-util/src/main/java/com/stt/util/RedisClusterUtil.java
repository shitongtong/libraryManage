package com.stt.util;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName RedisUtil
 * @Description 通过jedis连接redis集群操作工具类
 * @Author shitt7
 * @Date 2019/1/22 8:43
 * @Version 1.0
 */
public class RedisClusterUtil {
    private JedisCluster jedisCluster;

    public RedisClusterUtil() {
        init();
    }

    private void init() {
        String redisString = "redisCluster=172.25.241.235:6379,172.25.241.236:6379,172.25.241.237:6379,172.25.241.239:6379,172.25.241.240:6379,172.25.241.241:6379";
        String[] hostArray = redisString.split(",");
        Set<HostAndPort> nodes = new HashSet<>();

        //配置redis集群
        for (String host : hostArray) {
            String[] detail = host.split(":");
            nodes.add(new HostAndPort(detail[0], Integer.parseInt(detail[1])));
        }

        jedisCluster = new JedisCluster(nodes);
    }

    public void closeConnect() {
        try {
            jedisCluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加key的超时时间
     *
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(final String key, final int seconds) {
        return jedisCluster.expire(key, seconds);
    }

    /**
     * 添加key-value,key存在则覆盖
     *
     * @param key
     * @param value
     * @return 成功返回OK
     */
    public String set(final String key, final String value) throws IOException {
        return jedisCluster.set(key, value);
    }

    /**
     * 获取redis中指定key的值，value类型为String的使用此方法
     *
     * @param key
     * @return value
     */
    public String get(final String key) {
        return jedisCluster.get(key);
    }

    /**
     * 添加key-value并添加超时时间,key存在则覆盖
     *
     * @param key
     * @param seconds 超时时间，单位s
     * @param value
     * @return 成功返回OK
     */
    public String setex(final String key, final int seconds, final String value) {
        return jedisCluster.setex(key, seconds, value);
    }

    /**
     * 删除指定key
     *
     * @param key
     * @return 删除的数量
     */
    public Long del(final String key) {
        return jedisCluster.del(key);
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public Boolean exists(final String key) {
        return jedisCluster.exists(key);
    }

    /**
     * 添加哈希，field存在则覆盖
     *
     * @param key
     * @param field
     * @param value
     * @return 成功数
     */
    public Long hset(final String key, final String field, final String value) {
        return jedisCluster.hset(key, field, value);
    }

    /**
     * 添加哈希，field不存在则创建，存在则添加失败
     *
     * @param key
     * @param field
     * @param value
     * @return 成功数
     */
    public Long hsetnx(final String key, final String field, final String value) {
        return jedisCluster.hsetnx(key, field, value);
    }

    /**
     * 批量添加hash
     *
     * @param key
     * @param hash
     * @return OK
     */
    public String hmset(final String key, final Map<String, String> hash) {
        return jedisCluster.hmset(key, hash);
    }

    /**
     * 获取hash值
     *
     * @param key
     * @param field
     * @return
     */
    public String hmget(final String key, final String field) {
        return jedisCluster.hget(key, field);
    }

    /**
     * 获取hash值列表
     *
     * @param key
     * @param fields
     * @return
     */
    public List<String> hmget(final String key, final String... fields) {
        return jedisCluster.hmget(key, fields);
    }

    /**
     * 获取hash的fields和values
     *
     * @param key
     * @return
     */
    public Map<String, String> hgetAll(final String key) {
        return jedisCluster.hgetAll(key);
    }

    /**
     * 添加列表
     *
     * @param key
     * @param string
     * @return
     */
    public Long lpush(final String key, final String... string) {
        return jedisCluster.lpush(key, string);
    }

    /**
     * 获取列表数据
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> lrange(final String key, final long start, final long end) {
        return jedisCluster.lrange(key, start, end);
    }

    /**
     * 添加集合
     *
     * @param key
     * @param member
     * @return
     */
    public Long sadd(final String key, final String... member) {
        return jedisCluster.sadd(key, member);
    }

    /**
     * 获取集合
     *
     * @param key
     * @return
     */
    public Set<String> members(final String key) {
        return jedisCluster.smembers(key);
    }

    /**
     * 添加有序集合
     *
     * @param key
     * @param score
     * @param member
     * @return
     */
    public Long zadd(final String key, final double score, final String member) {
        return jedisCluster.zadd(key, score, member);
    }

    /**
     * 批量添加有序集合
     *
     * @param key
     * @param scoreMembers
     * @return
     */
    public Long zadd(final String key, final Map<String, Double> scoreMembers) {
        return jedisCluster.zadd(key, scoreMembers);
    }

    /**
     * 获取有序集合
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zrange(final String key, final long start, final long end) {
        return jedisCluster.zrange(key, start, end);
    }

    /**
     * 增量计数
     *
     * @param key
     * @return
     */
    public Long incr(final String key) {
        return jedisCluster.incr(key);
    }

    /**
     * 减量计数
     *
     * @param key
     * @return
     */
    public Long decr(final String key) {
        return jedisCluster.decr(key);
    }
}
