package com.google;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wk
 * @Description:
 * @date 2020/7/22 10:08
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test01 {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test01(){
        System.out.println("redisTemplate = " + redisTemplate);
    }
}
