package com.redisTest;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTx {
    public static void main(String args[]){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.flushDB();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("t1","v1");
        jsonObject.put("t2","v2");
        Transaction transaction = jedis.multi();//开启事务
        String result = jsonObject.toJSONString();
        transaction.watch("user1");
        try {
            transaction.set("user1",result);
            transaction.set("user2",result);
//            int i = 1/0;
            transaction.exec();
        } catch (Exception e) {
            transaction.discard();
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();
        }
    }
}
