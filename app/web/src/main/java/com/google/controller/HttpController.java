package com.google.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wk
 * @Description:
 * @date 2020/7/22 10:08
 **/
@Controller
@RequestMapping("ssl/service/")
public class HttpController {

    @RequestMapping(value = "")
    @ResponseBody
    public String getNifaService() {
        return "测试";
    }
}
