package com.google.utils.accessToken;

import com.alibaba.fastjson.JSONObject;
import com.google.config.JsonConfig;
import com.google.config.WechatConstant;
import com.google.service.impl.MenuService;
import com.google.utils.http.HttpClientUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author wk
 * @Description:
 * @date 2020/7/28 12:41
 **/
@Service
public class TokenThread implements Runnable {

    public static AccessToken accesstoken = null;

    @Autowired
    private RestTemplate restTemplate;

    public void run() {

        while (true) {
            try {
                accesstoken = this.getInterfaceToken(WechatConstant.APP_ID, WechatConstant.APP_SECRET);
                if (null != accesstoken) {
                    WechatConstant.ACCESS_TOKEN = accesstoken.getAccess_token();
                    System.out.println("获取accesstoken成功，accesstoken：" + accesstoken.getAccess_token() + " 有效时间为"
                            + accesstoken.getExpires_in());
                    menu(accesstoken.getAccess_token());
                    Thread.sleep((accesstoken.getExpires_in() - 200) * 1000);// 休眠7000秒
                } else {
                    Thread.sleep(60 * 1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(60 * 1000);
                } catch (Exception e2) {
                    e.printStackTrace();
                    System.out.println(e2.getMessage());
                }
            }
        }
    }


    private static String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

    /**
     * 微信公众号
     *
     * @param appId
     * @param appSecret
     * @return
     */
    public AccessToken getInterfaceToken(String appId, String appSecret) {
        AccessToken accessToken = null;
        StringBuffer reqUrl = new StringBuffer(url).append("&appid=").append(appId).append("&secret=").append(appSecret);
        String resp = HttpClientUtils.doGet(reqUrl.toString());
        if (StringUtils.isNotBlank(resp)) {
            accessToken = JSONObject.parseObject(resp, AccessToken.class);
        }
        System.out.println(resp);
        return accessToken;
    }

    /**
     * 自定义菜单
     */
    public static void menu(String token) {
        String access_token = token;
        String menu = JsonConfig.getJsonResource("datas/menu/menu").toString();
        MenuService.createMenu(menu, access_token);
    }
}
