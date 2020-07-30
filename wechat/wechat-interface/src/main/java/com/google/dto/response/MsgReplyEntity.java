package com.google.dto.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author wk
 * @Description:
 * @date 2020/7/30 10:34
 **/
@XmlAccessorType(XmlAccessType.FIELD)
public class MsgReplyEntity {

    //  用户的OpenID
    @XmlElement(name = "ToUserName")
    private String toUserName;

    // 测试号的微信号
    @XmlElement(name = "FromUserName")
    private String fromUserName;

    // 消息创建时间 （整型）
    @XmlElement(name = "CreateTime")
    private Long createTime;

    // 消息类型
    @XmlElement(name = "MsgType")
    private String msgType;

    // 文本消息内容
    @XmlElement(name = "Content")
    private String content;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
