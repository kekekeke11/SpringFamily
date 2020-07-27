package com.google.service.impl;

import com.google.config.MessagegConstant;
import com.google.dto.BaseMessage;
import com.google.dto.WeChatResult;
import com.google.service.CodeHandleService;
import com.google.service.WechatMsgService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author wk
 * @Description:
 * @date 2020/7/27 14:39
 **/
@Service
public class CodeHandleServiceImpl implements CodeHandleService {


    @Autowired
    private WechatMsgService wechatMsgService;

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
    public WeChatResult handleCode(Map<String, String> params, BaseMessage msgInfo) {

        WeChatResult result = new WeChatResult();

        String msgInfoType = params.get("MsgType");
        if (!StringUtils.isBlank(msgInfoType)) {
            switch (msgInfoType) {
                case MessagegConstant.REQ_MESSAGE_TYPE_TEXT: // 文本消息
                    result = wechatMsgService.textMsgInfo(params, msgInfo);
                    break;
                case MessagegConstant.REQ_MESSAGE_TYPE_IMAGE: // 图片消息
                    result = wechatMsgService.imageMsgInfo(params, msgInfo);
                    break;
                case MessagegConstant.REQ_MESSAGE_TYPE_LINK: // 链接消息
                    result = wechatMsgService.linkMsgInfo(params, msgInfo);
                    break;
                case MessagegConstant.REQ_MESSAGE_TYPE_LOCATION: // 地理位置
                    result = wechatMsgService.locationMsgInfo(params, msgInfo);
                    break;
                case MessagegConstant.REQ_MESSAGE_TYPE_VOICE: // 音频消息
                    result = wechatMsgService.voiceMsgInfo(params, msgInfo);
                    break;
                case MessagegConstant.REQ_MESSAGE_TYPE_SHORTVIDEO: // 短视频消息
                    result = wechatMsgService.shortVideo(params, msgInfo);
                    break;
                case MessagegConstant.REQ_MESSAGE_TYPE_VIDEO: // 视频消息
                    result = wechatMsgService.videoMsgInfo(params, msgInfo);
                    break;
                case MessagegConstant.REQ_MESSAGE_TYPE_EVENT: // 事件消息
                    String eventType = params.get("Event"); //
                    if (eventType != null && !"".equals(eventType)) {
                        switch (eventType) {
                            case MessagegConstant.EVENT_TYPE_SUBSCRIBE:
                                result = wechatMsgService.subscribe(params, msgInfo);
                                break;
                            case MessagegConstant.EVENT_TYPE_UNSUBSCRIBE:
                                result = wechatMsgService.unsubscribe(params, msgInfo);
                                break;
                            case MessagegConstant.EVENT_TYPE_SCAN:
                                result = wechatMsgService.scan(params, msgInfo);
                                break;
                            case MessagegConstant.EVENT_TYPE_LOCATION:
                                result = wechatMsgService.eventLocation(params, msgInfo);
                                break;
                            case MessagegConstant.EVENT_TYPE_CLICK:
                                result = wechatMsgService.eventClick(params, msgInfo);
                                break;
                            case MessagegConstant.EVENT_TYPE_VIEW:
                                result = wechatMsgService.eventView(params, msgInfo);
                                break;
                            case MessagegConstant.KF_CREATE_SESSION:
                                result = wechatMsgService.kfCreateSession(params, msgInfo);
                                break;
                            case MessagegConstant.KF_CLOSE_SESSION:
                                result = wechatMsgService.kfCloseSession(params, msgInfo);
                                break;
                            case MessagegConstant.KF_SWITCH_SESSION:
                                result = wechatMsgService.kfSwitchSession(params, msgInfo);
                                break;
                            default:
                                wechatMsgService.eventDefaultReply(params, msgInfo);
                                break;
                        }
                    }
                    break;
                default:
                    wechatMsgService.defaultMsgInfo(params, msgInfo);
            }
        }
        return result;
    }
}
