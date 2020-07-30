package com.google.dto.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author wk
 * @Description:映射类中的所有字段到XML 基础消息实体类
 * @date 2020/7/30 10:31
 **/
@XmlAccessorType(XmlAccessType.FIELD)
public class MsgSendEntity {

    /**
     * 公有部分
     */
    //开发者微信号
    @XmlElement(name = "ToUserName") // 指定名称映射
    private String toUserName;

    // 发送方帐号（一个OpenID）
    @XmlElement(name = "FromUserName")
    private String fromUserName;

    // 消息创建时间 （整型）
    @XmlElement(name = "CreateTime")
    private Long createTime;

    // 消息类型
    @XmlElement(name = "MsgType")
    private String msgType;

    // 消息id，64位整型
    @XmlElement(name = "MsgId")
    private Long msgId;

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

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }
}
