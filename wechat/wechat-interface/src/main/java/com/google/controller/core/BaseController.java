package com.google.controller.core;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wk
 * @Description:
 * @date 2020/7/27 13:56
 **/
public class BaseController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;


    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}
