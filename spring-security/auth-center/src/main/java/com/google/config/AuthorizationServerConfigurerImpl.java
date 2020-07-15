package com.google.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * @author 刘鹏
 * @Description     配置授权认证服务
 * @date 2020-07-09 13:52
 */
@EnableWebMvc
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfigurerImpl extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //使用存在内存中配置
        clients
                .jdbc(dataSource)
        ;
    }

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;
    @Autowired
    @Qualifier("tokenServices")
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeServices)
                .tokenServices(authorizationServerTokenServices)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PUT, HttpMethod.OPTIONS);
        ;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        // 授权认证服务需要把 /oauth/check_toke 暴露出来
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();//允许表单认证
    }

    /**
     * 授权码模式
     * http://localhost:8084/Project12_AuthCenter_SpringMVC/oauth/authorize?client_id=client_1&response_type=code&scope=all&redirect_uri=https://www.baidu.com
     *
     * http://localhost:8084/Project12_AuthCenter_SpringMVC/oauth/token?client_id=client_1&client_secret=123456&grant_type=authorization_code&code=MpYWoO&redirect_uri=https://www.baidu.com
     *
     * 客户端模式
     *      获取Token
     * http://localhost:8084/Project12_AuthCenter_SpringMVC/oauth/token?client_id=client_1&client_secret=123456&grant_type=client_credentials
     *      携带Token访问资源
     * http://localhost:8084/Project12_AuthCenter_SpringMVC/aaa/get1?access_token=TnEH1qUNMeVXhxGDKchVP1c7I24
     */
}
