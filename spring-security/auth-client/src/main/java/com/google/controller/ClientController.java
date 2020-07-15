package com.google.controller;

import com.google.entity.TokenBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 刘鹏
 * @Description
 * @date 2020-07-13 14:29
 */
@RestController
public class ClientController {

    @Value("client_1")
    private String client_id;
    @Value("123456")
    private String client_secret;

    @Value("${authCenterTokenUrl}")
    private String authCenterTokenUrl;

    @RequestMapping("/getToken")
    public Object getToken() {
        RestTemplate restTemplate = new RestTemplate();
        //获取Token
        TokenBean tokenBean = null;
        try {
            tokenBean = restTemplate.getForObject(authCenterTokenUrl + "?client_id=" + client_id + "&client_secret=" + client_secret + "&grant_type=client_credentials", TokenBean.class);
            return tokenBean;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
