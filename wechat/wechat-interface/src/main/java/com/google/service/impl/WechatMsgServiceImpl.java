package com.google.service.impl;

import com.google.controller.WeChatController;
import com.google.dto.BaseMessage;
import com.google.dto.TextMessage;
import com.google.dto.WeChatResult;
import com.google.service.CustomService;
import com.google.service.WechatMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author wk
 * @Description:
 * @date 2020/7/27 14:41
 **/
@Service
public class WechatMsgServiceImpl implements WechatMsgService {

    @Autowired
    private CustomService customService;

    private static final Logger logger = LoggerFactory.getLogger(WeChatController.class);

    /*
     * TODO
     *
     * @param params
     *
     * @param msgInfo
     *
     * @return
     */
    @Override
    public WeChatResult textMsgInfo(Map<String, String> params, BaseMessage msgInfo) {

        logger.info("文本消息");
        WeChatResult result = new WeChatResult();
        TextMessage text = new TextMessage();
        text.setContent(params.get("Content").trim());// 自动回复
        text.setCreateTime(new Date().getTime());
        text.setToUserName(msgInfo.getFromUserName());
        text.setFromUserName(msgInfo.getToUserName());
        text.setMsgId(msgInfo.getMsgId());
        text.setMsgType("text");
        result.setObject(text);
        return result;
    }

    /*
     * TODO
     *
     * @param params
     *
     * @param msgInfo
     *
     * @return
     */
    @Override
    public WeChatResult imageMsgInfo(Map<String, String> params, BaseMessage msgInfo) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * TODO
     *
     * @param params
     *
     * @param msgInfo
     *
     * @return
     */
    @Override
    public WeChatResult linkMsgInfo(Map<String, String> params, BaseMessage msgInfo) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * TODO
     *
     * @param params
     *
     * @param msgInfo
     *
     * @return
     */
    @Override
    public WeChatResult locationMsgInfo(Map<String, String> params, BaseMessage msgInfo) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * TODO
     *
     * @param params
     *
     * @param msgInfo
     *
     * @return
     */
    @Override
    public WeChatResult voiceMsgInfo(Map<String, String> params, BaseMessage msgInfo) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * TODO
     *
     * @param params
     *
     * @param msgInfo
     *
     * @return
     */
    @Override
    public WeChatResult shortVideo(Map<String, String> params, BaseMessage msgInfo) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * TODO
     *
     * @param params
     *
     * @param msgInfo
     *
     * @return
     */
    @Override
    public WeChatResult videoMsgInfo(Map<String, String> params, BaseMessage msgInfo) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * TODO
     *
     * @param params
     *
     * @param msgInfo
     *
     * @return
     */
    @Override
    public WeChatResult subscribe(Map<String, String> params, BaseMessage msgInfo) {

        logger.info("开始调用关注回复服务");
        // 关注回复，使用客服接口
        //customService.handlerCustomerMessage_subscribe(params);
        // 发送小程序卡片
        //customService.handlerCustomerMessage_miniprogrampage(params);
        return null;
    }

    /*
     * TODO
     *
     * @param params
     *
     * @param msgInfo
     *
     * @return
     */
    @Override
    public WeChatResult unsubscribe(Map<String, String> params, BaseMessage msgInfo) {

        return null;
    }

    /*
     * TODO
     *
     * @param params
     *
     * @param msgInfo
     *
     * @return
     */
    @Override
    public WeChatResult scan(Map<String, String> params, BaseMessage msgInfo) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * TODO
     *
     * @param params
     *
     * @param msgInfo
     *
     * @return
     */
    @Override
    public WeChatResult eventLocation(Map<String, String> params, BaseMessage msgInfo) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * TODO
     *
     * @param params
     *
     * @param msgInfo
     *
     * @return
     */
    @Override
    public WeChatResult eventClick(Map<String, String> params, BaseMessage msgInfo) {

        logger.info("CLICK_RGKF事件");
  /*      if (params.get("EventKey").equals("CLICK_BUG")) {
            String text = JsonConfig.getJsonResource("datas/BUG").toString();

            JSON json = JSONObject.fromObject(text);
            json.put("touser", params.get("FromUserName"));
            customService.handleKefuMessage(json);
        }
        if (params.get("EventKey").equals("CLICK_RGKF")) {
            String text = JsonConfig.getJsonResource("datas/rgkf").toString();

            JSONObject json = JSONObject.fromObject(text);
            json.put("touser", params.get("FromUserName"));
            customService.handleKefuMessage(json);
        }*/
        return null;
    }

    /*
     * TODO
     *
     * @param params
     *
     * @param msgInfo
     *
     * @return
     */
    @Override
    public WeChatResult eventView(Map<String, String> params, BaseMessage msgInfo) {

        logger.info("view事件，人工客服");

//      String content = "欢迎关注我的微信公众号";
//      WeChatResult result = new WeChatResult();
//      String text = JsonConfig.getJsonResource("datas/rgkf").toString();

//      TextMessage text = new TextMessage();
//      text.setContent(content);// 自动回复
//      text.setCreateTime(DateTimeUtil.currentTime());
//      text.setToUserName(msgInfo.getFromUserName());
//      text.setFromUserName(msgInfo.getToUserName());
//      text.setMsgId(msgInfo.getMsgId());
//      text.setMsgType("text");
//      result.setObject(text);
        return null;

    }

    /*
     * TODO
     *
     * @param params
     *
     * @param msgInfo
     *
     * @return
     */
    @Override
    public WeChatResult kfCreateSession(Map<String, String> params, BaseMessage msgInfo) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * TODO
     *
     * @param params
     *
     * @param msgInfo
     *
     * @return
     */
    @Override
    public WeChatResult kfCloseSession(Map<String, String> params, BaseMessage msgInfo) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * TODO
     *
     * @param params
     *
     * @param msgInfo
     *
     * @return
     */
    @Override
    public WeChatResult kfSwitchSession(Map<String, String> params, BaseMessage msgInfo) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * TODO
     *
     * @param params
     *
     * @param msgInfo
     */
    @Override
    public void eventDefaultReply(Map<String, String> params, BaseMessage msgInfo) {
        // TODO Auto-generated method stub

    }

    /*
     * TODO
     *
     * @param params
     *
     * @param msgInfo
     */
    @Override
    public void defaultMsgInfo(Map<String, String> params, BaseMessage msgInfo) {
        // TODO Auto-generated method stub

    }
}
