package com.redisTest;

import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

public class TestString {
    public static void main(String args[]){
        Jedis j = new Jedis("127.0.0.1",6379);
        j.flushDB();
        System.out.println(j.set("k1","v1"));
        System.out.println(j.set("k2","v2"));
        System.out.println(j.set("k3","v3"));
        System.out.println(j.del("k2"));
        System.out.println(j.get("k2"));
        System.out.println(j.set("k1","v11"));
        System.out.println(j.get("k1"));
        System.out.println(j.append("k3","123123123"));
        System.out.println(j.get("k3"));
        System.out.println(j.mset("k11","v11","k22","v22","k33","v33"));
        System.out.println(j.mget("k11","k22","k33"));
        System.out.println(j.mget("k11","k22","k33","k44"));
        System.out.println(j.del("k11","k22"));
        System.out.println(j.mget("k11","k22"));
        j.flushDB();
        System.out.println(j.setnx("k1","v1"));
        System.out.println(j.setnx("k2","v2"));
        System.out.println(j.setnx("k2","v22"));
        System.out.println(j.get("k1"));
        System.out.println(j.get("k2"));
        System.out.println(j.setex("k3",2,"v3"));
        System.out.println(j.get("k3"));
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(j.get("k3"));
        System.out.println(j.getSet("k2","k2new"));
        System.out.println(j.get("k2"));
        System.out.println(j.getrange("k2",2,4));
    }
}
