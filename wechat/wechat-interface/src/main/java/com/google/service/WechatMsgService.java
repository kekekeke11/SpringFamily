package com.google.service;

import com.google.dto.BaseMessage;
import com.google.dto.WeChatResult;

import java.util.Map;

public interface WechatMsgService {

    WeChatResult textMsgInfo(Map<String, String> params, BaseMessage msgInfo);

    WeChatResult imageMsgInfo(Map<String, String> params, BaseMessage msgInfo);

    WeChatResult linkMsgInfo(Map<String, String> params, BaseMessage msgInfo);

    WeChatResult locationMsgInfo(Map<String, String> params, BaseMessage msgInfo);

    WeChatResult voiceMsgInfo(Map<String, String> params, BaseMessage msgInfo);

    WeChatResult shortVideo(Map<String, String> params, BaseMessage msgInfo);

    WeChatResult videoMsgInfo(Map<String, String> params, BaseMessage msgInfo);

    WeChatResult subscribe(Map<String, String> params, BaseMessage msgInfo);

    WeChatResult unsubscribe(Map<String, String> params, BaseMessage msgInfo);

    WeChatResult scan(Map<String, String> params, BaseMessage msgInfo);

    WeChatResult eventLocation(Map<String, String> params, BaseMessage msgInfo);

    WeChatResult eventClick(Map<String, String> params, BaseMessage msgInfo);

    WeChatResult eventView(Map<String, String> params, BaseMessage msgInfo);

    WeChatResult kfCreateSession(Map<String, String> params, BaseMessage msgInfo);

    WeChatResult kfCloseSession(Map<String, String> params, BaseMessage msgInfo);

    WeChatResult kfSwitchSession(Map<String, String> params, BaseMessage msgInfo);

    void eventDefaultReply(Map<String, String> params, BaseMessage msgInfo);

    void defaultMsgInfo(Map<String, String> params, BaseMessage msgInfo);
}
