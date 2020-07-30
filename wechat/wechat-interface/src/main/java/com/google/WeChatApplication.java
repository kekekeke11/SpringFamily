package com.google;

import com.google.utils.accessToken.TokenThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

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
    }

    @Autowired
    TokenThread tokenThread;

    /**
     * 定时任务获取token，每1.5时
     */
    @Scheduled(fixedRate = 5400000)
    public void cronGetToken() {
        tokenThread.getAccessToken();
    }
}
