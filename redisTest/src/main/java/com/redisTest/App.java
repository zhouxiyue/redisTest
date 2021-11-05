package com.redisTest;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        System.out.println(jedis.ping());
        System.out.println(jedis.exists("username"));
        System.out.println(jedis.set("username","user1"));
        System.out.println(jedis.set("password","user1"));
        Set<String> set = jedis.keys("*");
        System.out.println(set);
        System.out.println(jedis.del("username"));
        System.out.println(jedis.exists("password"));
        System.out.println(jedis.type("password"));
        System.out.println(jedis.randomKey());
        System.out.println(jedis.rename("password","pwd"));
        System.out.println(jedis.get("pwd"));
        System.out.println(jedis.select(0));
        System.out.println(jedis.flushDB());
        System.out.println(jedis.dbSize());
        System.out.println(jedis.flushAll());
    }
}
