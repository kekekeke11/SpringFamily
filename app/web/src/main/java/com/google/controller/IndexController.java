package com.google.controller;

import com.google.service.LoginService;
import com.google.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author wk
 * @Description:
 * @date 2020/7/31 10:33
 **/
@Controller
public class IndexController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/")
    public String indexDefault() {
        return "login";
    }


    /**
     * 登录action
     *
     * @param httpSession
     * @param mobile
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/login.htm")
    @ResponseBody
    public Map<String, Object> login(HttpSession httpSession, String mobile, String pwd) {
        try {
            loginService.login(httpSession, mobile, pwd);
        } catch (Exception e) {
            return R.fail(e);
        }
        return R.succ();
    }

    /**
     * 退出action
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout.htm")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
