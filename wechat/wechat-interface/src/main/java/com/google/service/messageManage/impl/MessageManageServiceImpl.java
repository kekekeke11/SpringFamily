package com.google.service.messageManage.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.config.WechatConstant;
import com.google.service.impl.MenuService;
import com.google.service.messageManage.MessageManageService;
import com.google.utils.http.HttpClientUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author wk
 * @Description:消息管理
 * @date 2020/7/28 16:28
 **/
@Service
public class MessageManageServiceImpl implements MessageManageService {

    public static Logger log = LoggerFactory.getLogger(MenuService.class);

    public static String TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    @Override
    public void sendTemplateManage(Map<String, Object> params) {
        //{{compName1.DATA}}向{{compName2.DATA}}背书了票据，金额为{{amt.DATA}}元，请您及时复核。
        JSONObject jsonObject = new JSONObject();
        //基本信息
        jsonObject.put("touser", "o5Kzr089Ri4kFotGQ-cfXf6YPnsg");
        jsonObject.put("template_id", "xuqHyVJTLHD-U0Q3LB8tvHhLUjrlj7jD1X36Y6f4BfQ");
        jsonObject.put("url", "");
        jsonObject.put("miniprogram", "");
        //模板数据信息
        JSONObject data = new JSONObject();
        JSONObject param1 = new JSONObject();
        param1.put("value", "腾讯公司");
        param1.put("color", "#173177");
        JSONObject param2 = new JSONObject();
        param2.put("value", "阿里公司");
        param2.put("color", "");
        JSONObject param3 = new JSONObject();
        param3.put("value", "10000.00");
        param3.put("color", "#173177");
        data.put("compName1", param1);
        data.put("compName2", param2);
        data.put("amt", param3);
        jsonObject.put("data", data);
        this.send(jsonObject.toJSONString(), WechatConstant.ACCESS_TOKEN);
    }

    public static Integer send(String jsonParam, String access_token) {
        int result = 0;
        if (access_token != null) {

            // 调用接口创建菜单
            JSONObject jsonObject = null;
            try {
                String url = TEMPLATE_URL.replace("ACCESS_TOKEN", access_token);
                String resp = HttpClientUtils.doPostJson(url, jsonParam);
                System.out.println("调用发送模板消息接口：" + resp);
                if (StringUtils.isNotBlank(resp)) {
                    jsonObject = JSON.parseObject(resp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (null != jsonObject) {
                if (0 != jsonObject.getIntValue("errcode")) {
                    result = jsonObject.getIntValue("errcode");
                    log.error("发送模板消息失败 errcode:" + jsonObject.getIntValue("errcode")
                            + "，errmsg:" + jsonObject.getString("errmsg"));
                }
            }
        }
        return result;
    }
}
