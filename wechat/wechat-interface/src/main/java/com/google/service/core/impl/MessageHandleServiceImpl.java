package com.google.service.core.impl;

import com.google.commons.util.DateTimeUtils;
import com.google.commons.util.XmlConvertUtils;
import com.google.config.redisConfig.AccessTokenConfig;
import com.google.constants.EventType;
import com.google.constants.MsgType;
import com.google.dao.ReplyMessageDao;
import com.google.entity.CustUac;
import com.google.entity.ReplyMessage;
import com.google.model.ImgReplyMsg;
import com.google.model.RcvCommonMsg;
import com.google.model.TextReplyMsg;
import com.google.model.VoiceReplyMsg;
import com.google.service.CustUacService;
import com.google.service.core.MessageHandleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.text.MessageFormat;
import java.util.Arrays;

/**
 * @author wk
 * @Description:
 * @date 2020/7/27 14:19
 **/
@Service
public class MessageHandleServiceImpl implements MessageHandleService {

    private static final Logger logger = LoggerFactory.getLogger(MessageHandleServiceImpl.class);

    @Autowired
    private ReplyMessageDao replyMessageDao;

    @Autowired
    private CustUacService custUacService;

    @Autowired
    private AccessTokenConfig accessTokenConfig;

    /**
     * 对来自微信的消息作出响应(包含消息和事件)
     *
     * @param rcvCommonMsg
     * @return
     * @throws Exception
     */
    @Override
    public String handleMessage(RcvCommonMsg rcvCommonMsg) {
        String toUser = rcvCommonMsg.getFromUserName();
        String fromUser = rcvCommonMsg.getToUserName();
        Long createTime = DateTimeUtils.getTimeStamp();
        String msgType = rcvCommonMsg.getMsgType();

        String replayMsg = null;
        try {
            switch (msgType) {
                case MsgType.TEXT:
                    replayMsg = handleTextMsg(toUser, fromUser, createTime, rcvCommonMsg.getContent());
                    break;
                case MsgType.IMAGE:
                    replayMsg = handleImageMsg(toUser, fromUser, createTime, rcvCommonMsg.getMediaId());
                    break;
                case MsgType.VOICE:
                    replayMsg = handleVoiceMsg(toUser, fromUser, createTime, rcvCommonMsg.getMediaId());
                    break;
                case MsgType.VIDEO:
                    replayMsg = handleVideoMsg();
                    break;
                case MsgType.SHORT_VIDEO:
                    replayMsg = handleShortVideoMsg();
                    break;
                case MsgType.LOCATION:
                    replayMsg = handleLocationMsg();
                    break;
                case MsgType.LINK:
                    replayMsg = handleLinkMsg();
                    break;
                case MsgType.EVENT:
                    replayMsg = handleEvent(rcvCommonMsg);
                    break;
                default:
                    break;
            }
        } catch (JAXBException e) {
            logger.error("文本转换异常，接收:[{}]", rcvCommonMsg);
            String defaultBusy = replyMessageDao.findByKeyword("default_busy").getText();
            return MessageFormat.format(defaultBusy, toUser, fromUser, createTime);
        }
        return replayMsg;
    }

    // 文本消息回复
    private String handleTextMsg(String toUser, String fromUser, Long createTime, String rcvContent)
            throws JAXBException {
        // 关键字回复，可使用properties或数据库
        ReplyMessage replyMessage = replyMessageDao.findByKeyword(rcvContent);
        if (replyMessage != null && !replyMessage.getText().isEmpty()) {
            TextReplyMsg textReplyMsg = new TextReplyMsg().setToUserName(toUser).setFromUserName(fromUser)
                    .setCreateTime(createTime).setContent(replyMessage.getText());
            return XmlConvertUtils.beanToXml(textReplyMsg, TextReplyMsg.class);
        }

        return null;
    }

    private String handleImageMsg(String toUser, String fromUser, Long createTime, String rcvMediaId)
            throws JAXBException {
        ImgReplyMsg imgReplyMsg = new ImgReplyMsg().setToUserName(toUser).setFromUserName(fromUser)
                .setCreateTime(createTime).setMediaId(Arrays.asList(rcvMediaId));
        return XmlConvertUtils.beanToXml(imgReplyMsg, ImgReplyMsg.class);
    }

    private String handleVoiceMsg(String toUser, String fromUser, Long createTime, String rcvMediaId)
            throws JAXBException {
        VoiceReplyMsg voiceReplyMsg = new VoiceReplyMsg().setToUserName(toUser).setFromUserName(fromUser)
                .setCreateTime(createTime).setMediaId(Arrays.asList(rcvMediaId));
        return XmlConvertUtils.beanToXml(voiceReplyMsg, VoiceReplyMsg.class);
    }

    // 待完善
    private String handleVideoMsg() {
        return null;
    }

    private String handleShortVideoMsg() {
        return null;
    }

    private String handleMusicMsg() {
        return null;
    }

    private String handleLocationMsg() {
        return null;
    }

    private String handleLinkMsg() {
        return null;
    }

    /**
     * 消息类型为：
     * event事件
     *
     * @return
     */
    private String handleEvent(RcvCommonMsg rcvCommonMsg) {
        if (rcvCommonMsg != null && MsgType.EVENT.equals(rcvCommonMsg.getMsgType())) {
            System.out.println("这是什么事件：" + rcvCommonMsg.getEvent());
            System.out.println("扫码人：" + rcvCommonMsg.getFromUserName());
            //该用户是否关注了公众号，查表
            boolean b = false;
            if (b) {
                System.out.println("还没有关注，进行绑定...");
                //EventKey：qrscene_为前缀，后面为二维码的参数值
            } else {
                System.out.println("已关注，进行绑定...");
                //事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
            }
            //扫描带参数二维码关注事件
            if (EventType.subscribe.equals(rcvCommonMsg.getEvent()) && rcvCommonMsg.getEventKey().startsWith("qrscene_")) {
                //去掉前缀
                String key = rcvCommonMsg.getEventKey().replace("qrscene_", "");
                System.out.println("扫描带参数二维码关注  " + key);
                //通过获取的key进行绑定平台企业
                this.bindWeChat(rcvCommonMsg.getFromUserName(), key);
            } else if (EventType.SCAN.equals(rcvCommonMsg.getEvent())) {
                //扫描带参数二维码浏览（已关注）事件
                String eventKey = rcvCommonMsg.getEventKey();
                System.out.println(rcvCommonMsg.getFromUserName() + "扫描带参数二维码已经关注 " + eventKey);
                this.bindWeChat(rcvCommonMsg.getFromUserName(), eventKey);
            }
        }
        return null;
    }

    private void bindWeChat(String fromUserName, String key) {
        //绑定uac
        CustUac custUac = custUacService.getCustUacByBid(key);
        if (custUac != null) {
            custUac.setWechat(fromUserName);
            custUacService.saveCustUac(custUac);
            System.out.println("绑定成功，" + custUac.getUserName());
        } else {
            System.out.println("绑定失败");
        }
    }
}
