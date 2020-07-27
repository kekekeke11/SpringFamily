package com.google.dto;

/**
 * @author wk
 * @Description:
 * @date 2020/7/27 14:51
 **/
public class BaseMessage {


    private String toUserName;//开发者微信号
    private String fromUserName;//	发送方帐号（一个OpenID）
    private long createTime;//	消息创建时间 （整型）
    private String msgType;//	消息类型，event
    private String event;//	事件类型，subscribe(订阅)、unsubscribe(取消订阅)
    private long msgId;

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
