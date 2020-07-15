package com.google.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘鹏
 * @Description
 * @date 2020-07-13 0:47
 */
@RestController
public class UserController {

    //需要Token才能访问的资源
    @RequestMapping("/aaa/get1")
    public Object get1_a(){
        return "aaa---get1---Other service resources";
    }

    //不需要Token就能访问的资源
    @RequestMapping("/bbb/get1")
    public Object get1_b(){
        return "bbb---get1---Other service resources";
    }

}
