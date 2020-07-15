package com.google.test;

import com.google.entity.TokenBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author 刘鹏
 * @Description
 * @date 2020-07-13 14:17
 */
@ContextConfiguration(locations = {"classpath:applicationContext-oauth2.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class Test01 {

    @Test
    public void test01(){
        RestTemplate restTemplate = new RestTemplate();
        //获取Token
        TokenBean tokenBean = restTemplate.getForObject("http://localhost:8084/Project12_AuthCenter_SpringMVC/oauth/token?client_id=client_1&client_secret=123456&grant_type=client_credentials", TokenBean.class);
        System.out.println("tokenBean = " + tokenBean);
        String access_token = tokenBean.getAccess_token();
        System.out.println("access_token = " + access_token);

        //此时客户系统将Token响应给浏览器并存到localStorage中，这样每次请求就能携带Token请求
    }

    @Test
    public void test02(){
        //携带Token请求资源
        //http://localhost:8084/Project12_AuthCenter_SpringMVC/aaa/get1?access_token=kegowtPAjv1I9r07sIOClVNpyVI
    }

    @Test
    public void test03(){
        RestTemplate restTemplate = new RestTemplate();
        //获取Token
        Map<String, Object> resultMap = restTemplate.getForObject("http://localhost:8084/Project12_AuthCenter_SpringMVC/oauth/token?client_id=client_1&client_secret=123456&grant_type=client_credentials", Map.class);
        Object access_token = resultMap.get("access_token");
    }
}
