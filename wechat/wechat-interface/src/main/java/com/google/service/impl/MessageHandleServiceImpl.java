package com.google.service.impl;

import com.google.dto.BaseMessage;
import com.google.dto.WeChatResult;
import com.google.service.CodeHandleService;
import com.google.service.MessageHandleService;
import com.google.utils.JaxbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author wk
 * @Description:
 * @date 2020/7/27 14:19
 **/
@Service
public class MessageHandleServiceImpl implements MessageHandleService {

    private static final Logger logger = LoggerFactory.getLogger(MessageHandleServiceImpl.class);

    @Autowired
    private CodeHandleService codeHandleService;

    /**
     * 对来自微信的消息作出响应(包含消息和事件)
     *
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public String handleMessage(Map<String, String> params) throws Exception {

        logger.info("开始处理【message】信息");

        String result = null;

        if (params != null && params.size() > 0) {
            BaseMessage msgInfo = new BaseMessage();
            String createTime = params.get("CreateTime");
            String msgId = params.get("MsgId");
            msgInfo.setCreateTime((createTime != null && !"".equals(createTime)) ? Integer.parseInt(createTime) : 0);
            msgInfo.setFromUserName(params.get("FromUserName"));
            msgInfo.setMsgId((msgId != null && !"".equals(msgId)) ? Long.parseLong(msgId) : 0);
            msgInfo.setToUserName(params.get("ToUserName"));
            WeChatResult resp = codeHandleService.handleCode(params, msgInfo);
            if (null == resp) {
                return null;
            }
            logger.info("响应成功的消息: {}", resp.getObject());
            //对象转xml
            String xml = JaxbUtil.convertToXml(resp.getObject(), "UTF-8", false);
            result = xml;
     /*       boolean success = resp.isSuccess(); // 如果 为true,则表示返回xml文件, 直接转换即可,否则按类型
            if (success) {
                result = resp.getObject().toString();
            } else {
                int type = resp.getType(); // 这里规定 1 图文消息 否则直接转换
                if (type == WeChatResult.NEWSMSG) {
                    NewsMessage message = (NewsMessage) resp.getObject();
                    result = MessageUtil.newsMessagegToXml(message);
                    return result;
                } else {
                    BaseMessage baseMessage = (BaseMessage) resp.getObject();
                    result = MessageUtil.baseMessageToXml(baseMessage);
                    return result;
                }
            }*/
        } else {
            result = "msg is wrong";

        }
        return result;
    }
}
