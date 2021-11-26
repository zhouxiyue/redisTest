package com.redisTest;

import redis.clients.jedis.Jedis;

public class TestSet {
    public static void main(String args[]){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.flushDB();
        System.out.println(jedis.sadd("set1","s1","s2","s3","s4","s5"));
        System.out.println(jedis.sadd("set1","s6"));
        System.out.println(jedis.sadd("set1","s6"));
        System.out.println(jedis.smembers("set1"));
        System.out.println(jedis.srem("set1","s0"));
        System.out.println(jedis.smembers("set1"));
        System.out.println(jedis.spop("set1"));
        System.out.println(jedis.spop("set1"));
        System.out.println(jedis.smembers("set1"));
        System.out.println(jedis.scard("set1"));
        System.out.println(jedis.sismember("set1","s3"));
        System.out.println(jedis.sismember("set1","s1"));
        System.out.println(jedis.sismember("set1","s5"));
        System.out.println(jedis.sadd("set2","s1","s2","s4","s3","s0","s8","s7","s5"));
        System.out.println(jedis.sadd("set3","s1","s2","s4","s3","s0","s8"));
        System.out.println(jedis.smove("set2","set3","s7"));
        System.out.println(jedis.smove("set2","set3","s5"));
        System.out.println(jedis.smove("set2","set3","s8"));
        System.out.println(jedis.smembers("set2"));
        System.out.println(jedis.smembers("set3"));
        System.out.println(jedis.sinter("set2","set3"));
        System.out.println(jedis.sunion("set2","set3"));
        System.out.println(jedis.sdiff("set2","set3"));
        jedis.sinterstore("set4","set2","set3");
        System.out.println(jedis.smembers("set4"));

    }
}
