package com.google.controller;

import com.google.service.MessageHandleService;
import com.google.utils.JaxbUtil;
import com.google.utils.SignatureUtil;
import com.google.utils.StreamToString;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

/**
 * @author wk
 * @Description:
 * @date 2020/7/27 13:57
 **/
@RestController
@RequestMapping("/wechat")
public class WeChatController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(WeChatController.class);

    @Autowired
    private MessageHandleService messageHandleService;

    /**
     * 校验信息是否是从微信服务器发出，处理消息
     *
     * @throws Exception
     */
/*    @RequestMapping(value = "/handler", method = {RequestMethod.GET, RequestMethod.POST})
    public void processPost() throws Exception {

        this.getRequest().setCharacterEncoding("UTF-8");
        this.getResponse().setCharacterEncoding("UTF-8");

        logger.info("开始校验信息是否是从微信服务器发出");

        // 签名
        String signature = this.getRequest().getParameter("signature");
        // 时间戳
        String timestamp = this.getRequest().getParameter("timestamp");
        // 随机数
        String nonce = this.getRequest().getParameter("nonce");
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败

        if (SignatureUtil.checkSignature(signature, timestamp, nonce)) {
            // 随机字符串
            String echostr = this.getRequest().getParameter("echostr");
            logger.debug("接入成功，echostr {}", echostr);
            this.getResponse().getWriter().write(echostr);
        }
    }*/
    @RequestMapping(value = "/handler", method = {RequestMethod.GET, RequestMethod.POST})
    public void processPost() throws Exception {

        this.getRequest().setCharacterEncoding("UTF-8");
        this.getResponse().setCharacterEncoding("UTF-8");

        logger.info("开始校验信息是否是从微信服务器发出");
        boolean isPost = Objects.equals("POST", this.getRequest().getMethod().toUpperCase());
        if (isPost) {
            logger.info("接入成功，正在处理逻辑");
            InputStream inputStream = this.getRequest().getInputStream();
            String xml = StreamToString.getString(inputStream, "UTF-8");
            logger.info("inputStream :{}。{}", inputStream, xml);
            Map<String, String> params = JaxbUtil.xmlToMap(xml);
            String respXml = messageHandleService.handleMessage(params);
            if (StringUtils.isNotBlank(respXml)) {
                // 输出流
                this.getResponse().getWriter().write(respXml);
            }
        } else {
            // 签名
            String signature = this.getRequest().getParameter("signature");
            // 时间戳
            String timestamp = this.getRequest().getParameter("timestamp");
            // 随机数
            String nonce = this.getRequest().getParameter("nonce");
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (SignatureUtil.checkSignature(signature, timestamp, nonce)) {
                // 随机字符串
                String echostr = this.getRequest().getParameter("echostr");
                logger.info("接入成功，echostr {}", echostr);
                this.getResponse().getWriter().write(echostr);
            }
        }
    }
}
