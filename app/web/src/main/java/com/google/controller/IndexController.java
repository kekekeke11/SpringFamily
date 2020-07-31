package com.google.controller;

import com.google.utils.http.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wk
 * @Description:
 * @date 2020/7/31 10:33
 **/
@Controller
public class IndexController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping(value = "/index")
    public String index() {

        return "index";
    }
}
