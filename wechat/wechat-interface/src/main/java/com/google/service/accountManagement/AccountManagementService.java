package com.google.service.accountManagement;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.config.redisConfig.AccessTokenConfig;
import com.google.service.menuManagement.MenuService;
import com.google.utils.http.HttpClientUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author wk
 * @Description:帐号管理
 * @date 2020/7/29 16:15
 **/
@Service
public class AccountManagementService {

    @Value("${wechat.QRCODE_URL}")
    private String QRCODE_URL;

    @Autowired
    private AccessTokenConfig accessTokenConfig;

    public static Logger log = LoggerFactory.getLogger(MenuService.class);

    public String productTempQrcode(Map<String, Object> params) {
        //{"expire_seconds": 604800, "action_name": "QR_SCENE", "action_info": {"scene": {"scene_id": 123}}}
        //{"expire_seconds": 604800, "action_name": "QR_STR_SCENE", "action_info": {"scene": {"scene_str": "test"}}}
        JSONObject jsonObject = new JSONObject();
        //该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
        jsonObject.put("expire_seconds", 2592000);
        //二维码类型，QR_SCENE为临时的整型参数值，
        // QR_STR_SCENE为临时的字符串参数值，
        // QR_LIMIT_SCENE为永久的整型参数值，
        // QR_LIMIT_STR_SCENE为永久的字符串参数值
        jsonObject.put("action_name", "QR_STR_SCENE");
        //二维码详细信息
        JSONObject action_info = new JSONObject();
        JSONObject scene = new JSONObject();
        scene.put("scene_str", params.get("bid"));
        action_info.put("scene", scene);
        jsonObject.put("action_info", action_info);
        String ticket = this.send(jsonObject.toJSONString(), accessTokenConfig.getAccessToken(), "创建临时二维码");
        if (StringUtils.isNotBlank(ticket)) {
            JSONObject resp = JSON.parseObject(ticket);
//            {"ticket":"gQH47joAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2taZ2Z3TVRtNzJXV1Brb3ZhYmJJAAIEZ23sUwMEmm
//                3sUw==","expire_seconds":60,"url":"http://weixin.qq.com/q/kZgfwMTm72WWPkovabbI"}
//            https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET
            String ticketUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
            return ticketUrl.replace("TICKET", resp.getString("ticket"));
        }
        return ticket;
    }

    public String send(String jsonParam, String access_token, String remark) {
        String resp = "";
        if (access_token != null) {
            JSONObject jsonObject = null;
            try {
                String url = QRCODE_URL.replace("ACCESS_TOKEN", access_token);
                resp = HttpClientUtils.doPostJson(url, jsonParam);
                System.out.println(remark + "接口：" + resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (null != jsonObject) {
                if (0 != jsonObject.getIntValue("errcode")) {
                    log.error(remark + "失败 errcode:" + jsonObject.getIntValue("errcode")
                            + "，errmsg:" + jsonObject.getString("errmsg"));
                }
            }
        }
        return resp;
    }


}
