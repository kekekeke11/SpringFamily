package com.google.controller.biz;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author wk
 * @Description:
 * @date 2020/7/30 18:09
 **/
@Controller
public class IndexController {

    @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String sendTemplateManage() {
        return "/index";
    }
}
