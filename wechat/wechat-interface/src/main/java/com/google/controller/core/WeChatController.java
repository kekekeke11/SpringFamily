package com.google.controller.core;

import com.google.commons.util.XmlConvertUtils;
import com.google.model.RcvCommonMsg;
import com.google.service.core.MessageHandleService;
import com.google.utils.SignatureUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;


/**
 * @author wk
 * @Description: 微信公众号返回给服务端，处理
 * @date 2020/7/27 13:57
 **/
@RestController
@RequestMapping("/wechat")
public class WeChatController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(WeChatController.class);

    @Autowired
    private MessageHandleService messageHandleService;

    @Autowired
    SignatureUtil signatureUtil;

    @GetMapping("/handler")
    public String validateAccess(String signature, String timestamp, String nonce, String echostr) {
        logger.info("开始测试接入微信...");
        if (!signatureUtil.checkSignature(signature, timestamp, nonce)) {
            logger.error("公众号接入失败");
            return null;
        }
        logger.info("公众号接入成功,echostr:[{}]", echostr);
        return echostr;
    }

    @PostMapping("/handler")
    public String handleMessage(@RequestBody RcvCommonMsg rcvCommonMsg) throws Exception {
        String replyMsg = messageHandleService.handleMessage(rcvCommonMsg);
        // TODO 暂不支持直接bean注解输出
        String rcvMsg = XmlConvertUtils.beanToXml(rcvCommonMsg, RcvCommonMsg.class);
        logger.info("公众号接收消息:[{}}, 回复消息:[{}]", rcvMsg, replyMsg);
        return replyMsg;
    }

}
