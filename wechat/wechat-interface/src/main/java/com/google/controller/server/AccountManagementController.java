package com.google.controller.server;

import com.google.service.accountManagement.AccountManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wk
 * @Description:帐号管理
 * @date 2020/7/29 16:53
 **/
@RequestMapping("qrcode/")
@Controller
public class AccountManagementController {

    @Autowired
    private AccountManagementService accountManagementService;

    /**
     * 生成临时带参二维码
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "create", method = {RequestMethod.GET, RequestMethod.POST})
    public String sendTemplateManage(String bid) {
        Map<String, Object> map = new HashMap<>();
        map.put("bid", bid);
        return accountManagementService.productTempQrcode(map);
    }
}
