package com.google;

import com.google.utils.accessToken.TokenThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


/**
 * @author wk
 * @Description:
 * @date 2020/7/27 13:51
 **/
@SpringBootApplication
@EnableScheduling//定时任务
public class WeChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeChatApplication.class, args);
        init();
    }

    public static void init() {
        // 启动线程定时获取access_token
        new Thread(new TokenThread()).start();
    }

}
