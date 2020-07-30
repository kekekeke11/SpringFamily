package com.google.config.redisConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * @author wk
 * @Description:Token存储、获取
 * @date 2020/7/29 14:43
 **/
@Configuration
public class AccessTokenConfig {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * token存入redis
     *
     * @param accessToken
     */
    public void setAccessToken(String accessToken, long timeout) {
        redisTemplate.opsForValue().set("ACCESS_TOKEN", accessToken);
        redisTemplate.expire("ACCESS_TOKEN", timeout, TimeUnit.MILLISECONDS);
    }

    public void setAccessToken(String accessToken) {
        redisTemplate.opsForValue().set("ACCESS_TOKEN", accessToken);
    }

    /**
     * redis取token
     *
     * @return
     */
    public String getAccessToken() {
        return redisTemplate.opsForValue().get("ACCESS_TOKEN");
    }
}
