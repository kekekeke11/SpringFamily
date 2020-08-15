package com.google.controller;

import com.google.constants.Constants;
import com.google.entity.CustUac;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wk
 * @Description:
 * @date 2020/8/16 3:03
 **/
@RequestMapping(value = "/webChat/")
@Controller
public class WebChatController {

    /**
     * 进入聊天室首页
     *
     * @return
     */
    @RequestMapping(value = "webChatIndex.htm")
    public String webChatIndex() {
        return "webChat/index";
    }

    /**
     * 加载基本信息
     *
     * @return
     */
    @RequestMapping(value = "loadInfo.htm")
    @ResponseBody
    public Map<String, Object> loadInfo(HttpSession httpSession) {
        Map<String, Object> outputMap = new HashMap<>();
        CustUac custUac = (CustUac) httpSession.getAttribute(Constants.SESSION_UAC);
        outputMap.put("custUac", custUac);
        return outputMap;
    }
}
