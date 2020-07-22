package com.google.controller;

import com.google.utils.http.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


/**
 * @author wk
 * @Description:
 * @date 2020/7/22 10:08
 **/
@Controller
@RequestMapping("ssl/service/")
public class HttpController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "nifa")
    @ResponseBody
    public String getNifaService(String url, String cipherText) {
        //http://localhost:9797/ssl/service/nifa?url=http://www.baidu.com&cipherText=123
        String result = HttpClientUtils.doPostJson(url, cipherText);
        System.out.println(result);
        return result;
    }
}
