package com.google.utils.accessToken;

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

/**
 * @author wk
 * @Description:定时任务获取Token
 * @date 2020/7/28 12:41
 **/
@Service
public class TokenThread {

    public static Logger log = LoggerFactory.getLogger(MenuService.class);

    /**
     * 获取token url
     */
    @Value("${wechat.TOKEN_URL}")
    private String TOKEN_URL;

    /**
     * 测试号配置信息
     */
    @Value("${wechat.APP_ID}")
    public String APP_ID;

    @Value("${wechat.APP_SECRET}")
    public String APP_SECRET;

    @Autowired
    AccessTokenConfig accessTokenConfig;

    public static AccessToken accesstoken = null;

    public void getAccessToken() {
        try {
            accesstoken = this.getInterfaceToken(APP_ID, APP_SECRET);
            if (null != accesstoken) {
                String access_token = accesstoken.getAccess_token();
                // 有效期，单位秒
                Long expiresIn = accesstoken.getExpires_in();
                //提前5分钟过期
                accessTokenConfig.setAccessToken(access_token, 5000000);
                log.info("获取accesstoken成功，accesstoken：" + access_token + " 有效时间为"
                        + accesstoken.getExpires_in());
            } else {
                log.info("获取accesstoken失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取accesstoken失败：{}", e.getMessage());
        }
    }


    /**
     * 微信公众号获取Token
     *
     * @param appId
     * @param appSecret
     * @return
     */
    public AccessToken getInterfaceToken(String appId, String appSecret) {
        AccessToken accessToken = null;
        StringBuffer reqUrl = new StringBuffer(TOKEN_URL).append("&appid=").append(appId).append("&secret=").append(appSecret);
        String resp = HttpClientUtils.doGet(reqUrl.toString());
        if (StringUtils.isNotBlank(resp)) {
            accessToken = JSONObject.parseObject(resp, AccessToken.class);
        }
        System.out.println(resp);
        return accessToken;
    }
}
