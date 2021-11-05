package com.redisTest;

import redis.clients.jedis.Jedis;

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
    }
}
