package com.google.controller.server;

import com.google.service.messageManage.MessageManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wk
 * @Description:消息管理
 * @date 2020/7/28 17:16
 **/
@RequestMapping("message/")
@Controller
public class MessageManageController {

    @Autowired
    private MessageManageService messageManageService;

    @ResponseBody
    @RequestMapping(value = "template", method = {RequestMethod.GET, RequestMethod.POST})
    public String sendTemplateManage() {
        messageManageService.sendTemplateManage(null);
        return "调用成功";
    }
}
