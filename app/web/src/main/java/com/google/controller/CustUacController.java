package com.google.controller;

import com.google.entity.CustUac;
import com.google.service.CustUacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wk
 * @Description:
 * @date 2020/7/28 10:53
 **/
@RequestMapping("ssl/uac/")
@RestController
public class CustUacController {

    @Autowired
    private CustUacService custUacService;

    @RequestMapping("listCustUac")
    @ResponseBody
    public List<CustUac> listCustUac() {
        return custUacService.listCustUac();
    }
}
