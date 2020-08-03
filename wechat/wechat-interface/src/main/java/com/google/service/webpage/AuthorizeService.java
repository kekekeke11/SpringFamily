package com.google.service.webpage;

import com.google.config.redisConfig.AccessTokenConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wk
 * @Description:
 * @date 2020/7/31 17:31
 **/
@Service
public class AuthorizeService {

    //https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf0e81c3bee622d60
    // &redirect_uri=http%3A%2F%2Fnba.bluewebgame.com%2Foauth_response.php
    // &response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect


    @Autowired
    private AccessTokenConfig accessTokenConfig;

}
