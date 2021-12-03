package com.jedistest;

import com.jedistest.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class Jedis01SpringbootApplicationTests {

    @Autowired
    @Qualifier("redisTemplate1")
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {

        redisTemplate.opsForValue().set("k1","v1");
//        RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
//        redisConnection.flushDb();
//        redisConnection.flushAll();
        System.out.println(redisTemplate.opsForValue().get("k1"));
    }

    @Test
    void test1(){
        User user = new User("n1",3);
        try {
//            String jsonUser = new ObjectMapper().writeValueAsString(user);
            redisTemplate.getConnectionFactory().getConnection().flushDb();
            redisTemplate.opsForValue().set("user",user);
            System.out.println(redisTemplate.opsForValue().get("user"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
