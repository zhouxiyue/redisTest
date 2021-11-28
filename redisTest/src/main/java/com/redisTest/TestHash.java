package com.redisTest;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class TestHash {
    public static void main(String rags[]){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.flushDB();
        Map<String,String> map = new HashMap<>();
        map.put("k1","v1");
        map.put("k2","v2");
        map.put("k3","v3");
        map.put("k4","v4");
        jedis.hmset("hash",map);
        jedis.hset("hash","k5","v5");
        System.out.println(jedis.hgetAll("hash"));
        System.out.println(jedis.hkeys("hash"));
        System.out.println(jedis.hvals("hash"));
        System.out.println(jedis.hsetnx("hash","k6","6"));
        System.out.println(jedis.hgetAll("hash"));
        System.out.println(jedis.hincrBy("hash","k6",6));
        System.out.println(jedis.hgetAll("hash"));
        System.out.println(jedis.hdel("hash","k6"));
        System.out.println(jedis.hgetAll("hash"));
        System.out.println(jedis.hlen("hash"));
        System.out.println(jedis.hexists("hash","k5"));
        System.out.println(jedis.hexists("hash","k4"));
        System.out.println(jedis.hexists("hash","k6"));
        System.out.println(jedis.hget("hash","k3"));
        System.out.println(jedis.hmget("hash","k1","k4","k6"));
    }
}
