package com.redisTest;

import redis.clients.jedis.Jedis;

public class TestList {
    public static void main(String args[]){
        Jedis j = new Jedis("127.0.0.1",6379);
        j.flushDB();
        j.lpush("list1","l1","l2","l3","l1","l4","l5","l6");
        j.lpush("list1","l7");
        j.lpush("list1","l8");
        j.lpush("list1","l9");
        j.lpush("list1","l10");
        System.out.println(j.lrange("list1",0,-1));
        System.out.println(j.lrange("list1",0,3));
        System.out.println(j.lrem("list1",2,"l1"));
        System.out.println(j.lrange("list1",0,-1));
        System.out.println(j.ltrim("list1",0,3));
        System.out.println(j.lrange("list1",0,-1));
        System.out.println(j.lpop("list1"));
        System.out.println(j.lrange("list1",0,-1));
        System.out.println(j.rpush("list1","ll1","ll2","ll3"));
        System.out.println(j.lrange("list1",0,-1));
        System.out.println(j.rpop("list1"));
        System.out.println(j.lrange("list1",0,-1));
        System.out.println(j.lset("list1",1,"llll1"));
        System.out.println(j.lrange("list1",0,-1));
        System.out.println(j.llen("list1"));
        System.out.println(j.lindex("list1",1));
        System.out.println(j.lpush("list2","6","5","4","3","1","2"));
        System.out.println(j.lrange("list2",0,-1));
        System.out.println(j.sort("list2"));
    }
}
