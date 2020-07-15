package com.google.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author 刘鹏
 * @Description
 * @date 2020-07-13 9:44
 */
@EnableWebMvc
@EnableResourceServer
@Configuration
public class ResourceServerConfigurerImpl extends ResourceServerConfigurerAdapter {

    @Autowired
    @Qualifier("tokenServices")
    private DefaultTokenServices defaultTokenServices;
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .resourceId("*")
                .tokenServices(defaultTokenServices)
                .stateless(true)
                ;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and().requestMatchers().anyRequest()
                .and().anonymous()
                .and().authorizeRequests().antMatchers("/aaa/**").authenticated()//配置访问权限控制，必须认证过后才可以访问
        ;
    }
}
