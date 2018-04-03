package com.stt.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Tuple;
import redis.clients.util.SafeEncoder;

import java.util.Map;
import java.util.Set;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/4/20.
 */
public class RedisDao29 {

    private JedisPool jedisPool;

    public RedisDao29() {
        if (jedisPool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
//            config.setMaxActive(JRedisPoolConfig.MAX_ACTIVE);
            config.setMaxTotal(100);
            config.setMaxIdle(300);
//            config.setMaxWait(JRedisPoolConfig.MAX_WAIT);
            config.setMaxWaitMillis(100 * 1000);
            config.setTestOnBorrow(true);
//            config.setTestOnReturn(true);
            jedisPool = new JedisPool(config, "172.16.21.253", 6379, 2000, null, 5);//内测环境
//            jedisPool = new JedisPool(config, "106.14.46.51", 6300,2000,"onlyhi.cn",5);//公网测试换
        }
    }

    /**
     * 过期时间：默认7天
     */
    private final int expire = 3600 * 24 * 7;


    /**
     * 从jedis连接池中获取获取jedis对象
     *
     * @return
     */
    private Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 关闭连接
     */
    private void closeJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param seconds
     */
    public void expire(String key, int seconds) {
        if (seconds <= 0) {
            return;
        }
        Jedis jedis = getJedis();
        jedis.expire(key, seconds);
        closeJedis(jedis);
    }

    /**
     * 设置默认过期时间
     *
     * @param key
     */
    public void expire(String key) {
        expire(key, expire);
    }

    /**
     * 将key对应的value加1，只有value可以转为数字时该方法才可用
     *
     * @param key
     * @return long 加1后的值
     */
    public long incr(String key) {
        Jedis jedis = getJedis();
        long len = jedis.incr(key);
        closeJedis(jedis);
        return len;
    }

    /**
     * 将key对应的value减1，只有value可以转为数字时该方法才可用
     *
     * @param key
     * @return long 减1后的值
     */
    public long decr(String key) {
        Jedis jedis = getJedis();
        long len = jedis.decr(key);
        closeJedis(jedis);
        return len;
    }

    /**
     * 添加记录,如果记录已存在将覆盖原有的value
     *
     * @param key
     * @param value
     * @return 状态码
     */
    public String set(byte[] key, byte[] value) {
        Jedis jedis = getJedis();
        String status = jedis.set(key, value);
        closeJedis(jedis);
        return status;
    }

    /**
     * 添加记录,如果记录已存在将覆盖原有的value
     * 默认过期时间7天
     *
     * @param key
     * @param value
     * @return 状态码
     */
    public String set(String key, String value) {
        String status = set(SafeEncoder.encode(key), SafeEncoder.encode(value));
        expire(key);
        return status;
    }

    /**
     * 添加记录并设置过期时间,如果记录已存在将覆盖原有的value
     *
     * @param key
     * @param value
     * @param second 过期时间
     * @return 状态码
     */
    public String set(String key, String value, int second) {
        String status = set(SafeEncoder.encode(key), SafeEncoder.encode(value));
        expire(key, second);
        return status;
    }

    /**
     * 根据key获取记录
     *
     * @param key
     * @return 值
     */
    public String get(String key) {
        Jedis jedis = getJedis();
        String value = jedis.get(key);
        closeJedis(jedis);
        return value;
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return boolean
     */
    public boolean exists(String key) {
        Jedis jedis = getJedis();
        boolean exis = jedis.exists(key);
        closeJedis(jedis);
        return exis;
    }

    /**
     * 更改key
     *
     * @param oldkey
     * @param newkey
     * @return 状态码
     */
    private String rename(byte[] oldkey, byte[] newkey) {
        Jedis jedis = getJedis();
        String status = jedis.rename(oldkey, newkey);
        closeJedis(jedis);
        return status;
    }

    /**
     * 更改key
     *
     * @param oldkey
     * @param newkey
     * @return 状态码
     */
    public String rename(String oldkey, String newkey) {
        return rename(SafeEncoder.encode(oldkey),
                SafeEncoder.encode(newkey));
    }

    /**
     * 删除keys对应的记录,可以是多个key
     *
     * @param keys
     * @return 删除的记录数
     */
    public long del(String... keys) {
        Jedis jedis = getJedis();
        long count = jedis.del(keys);
        closeJedis(jedis);
        return count;
    }

    /**
     * 添加一个或多个成员到有序集合，或者如果它已经存在更新其分数
     *
     * @param key
     * @param scoreMembers
     * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员。
     */
    public long zadd(String key, Map<String, Double> scoreMembers) {
        Jedis jedis = getJedis();
        Long count = jedis.zadd(key, scoreMembers);
        closeJedis(jedis);
        return count;
    }

    /**
     * 添加一个或多个成员到有序集合，或者如果它已经存在更新其分数
     *
     * @param key
     * @param score
     * @param members
     * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员。
     */
    public long zadd(String key, double score, String members) {
        Jedis jedis = getJedis();
        Long count = jedis.zadd(key, score, members);
        closeJedis(jedis);
        return count;
    }

    /**
     * 根据key和集合成员返回其索引值，若其不存在则返回null
     *
     * @param key
     * @param members
     * @return
     */
    public Long zrank(String key, String members) {
        Jedis jedis = getJedis();
        Long zrank = jedis.zrank(key, members);
        closeJedis(jedis);
        return zrank;
    }

    /**
     * key的集合数量
     *
     * @param key
     * @return
     */
    public Long zcard(String key) {
        Jedis jedis = getJedis();
        Long zrank = jedis.zcard(key);
        closeJedis(jedis);
        return zrank;
    }

    /**
     * 根据key和member增加分数score，若元素不存在则创建并返回新的分数
     *
     * @param key
     * @param score
     * @param member
     * @return
     */
    public Double zincrby(String key, double score, String member) {
        Jedis jedis = getJedis();
        Double zincrby = jedis.zincrby(key, score, member);
        closeJedis(jedis);
        return zincrby;
    }

    /**
     * 根据key和索引值查询成员集合
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zrange(String key, long start, long end) {
        Jedis jedis = getJedis();
        Set<String> zrevrange = jedis.zrange(key, start, end);
        closeJedis(jedis);
        return zrevrange;
    }

    /**
     * 根据key和索引值查询成员集合对象(包括分数)
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        Jedis jedis = getJedis();
        Set<Tuple> tupleSet = jedis.zrangeWithScores(key, start, end);
        closeJedis(jedis);
        return tupleSet;
    }

    /**
     * 删除指定成员，返回删除的数量
     *
     * @param key
     * @param members
     * @return
     */
    public long zrem(String key, String... members) {
        Jedis jedis = getJedis();
        Long zrem = jedis.zrem(key, members);
        closeJedis(jedis);
        return zrem;
    }


    /**
     * 向集合添加一个或多个成员
     *
     * @param key
     * @param members
     * @return
     */
    public long sadd(String key, String... members) {
        Jedis jedis = getJedis();
        Long sadd = jedis.sadd(key, members);
        closeJedis(jedis);
        return sadd;
    }

    /**
     * 移除集合中一个或多个成员
     *
     * @param key
     * @param members
     * @return
     */
    public long srem(String key, String... members) {
        Jedis jedis = getJedis();
        Long srem = jedis.srem(key, members);
        closeJedis(jedis);
        return srem;
    }

    /**
     * 判断 member 元素是否是集合 key 的成员
     *
     * @param key
     * @param member
     * @return
     */
    public boolean sismember(String key, String member) {
        Jedis jedis = getJedis();
        Boolean sismember = jedis.sismember(key, member);
        closeJedis(jedis);
        return sismember;
    }

}
