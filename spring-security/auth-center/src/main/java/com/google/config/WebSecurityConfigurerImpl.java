package com.google.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author 刘鹏
 * @Description     配置用户权限|拦截保护的请求
 * @date 2020-07-09 14:38
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfigurerImpl extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();
        return passwordEncoder;
    }

    @Bean
    @Primary
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
