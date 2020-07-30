package com.google.service.core.impl;

import com.google.commons.util.DateTimeUtils;
import com.google.commons.util.XmlConvertUtils;
import com.google.constants.MsgType;
import com.google.dao.ReplyMessageDao;
import com.google.entity.ReplyMessage;
import com.google.model.ImgReplyMsg;
import com.google.model.RcvCommonMsg;
import com.google.model.TextReplyMsg;
import com.google.model.VoiceReplyMsg;
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

    /**
     * 对来自微信的消息作出响应(包含消息和事件)
     *
     * @param rcvCommonMsg
     * @return
     * @throws Exception
     */
    @Override
    public String handleMessage(RcvCommonMsg rcvCommonMsg) throws Exception {
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
}
