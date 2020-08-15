package com.google.service;

import com.google.constants.Constants;
import com.google.entity.CustUac;
import com.google.exceptions.BusinessException;
import com.google.utils.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author wk
 * @Description:
 * @date 2020/8/16 0:53
 **/
@Service
public class LoginService {

    @Autowired
    private CustUacService custUacService;

    /**
     * 登录方法
     *
     * @param httpSession
     * @param mobile
     * @param pwd
     */
    public void login(HttpSession httpSession, String mobile, String pwd) {
        if (StringUtils.isBlank(pwd)) {
            throw new BusinessException("密码不能为空");
        }
        String digest = MD5Util.digest(pwd);
        CustUac custUac = custUacService.getCustUacByMobileAndPwd(mobile, digest);
        if (custUac == null) {
            throw new BusinessException("帐号或密码错误");
        }
        httpSession.setAttribute(Constants.SESSION_UAC, custUac);
        httpSession.setAttribute(Constants.SESSION_LOGINTIME, new Date().getTime());
    }
}
