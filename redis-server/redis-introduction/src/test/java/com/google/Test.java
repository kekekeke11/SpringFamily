package com.google;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wk
 * @Description:
 * @date 2020/7/29 13:30
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @org.junit.Test
    public void set() {
        redisTemplate.opsForValue().set("myKey", "myValue");
        System.out.println("redis设置值："+redisTemplate.opsForValue().get("myKey"));
    }
}
