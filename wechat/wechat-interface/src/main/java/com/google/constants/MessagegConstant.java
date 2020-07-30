package com.google.constants;

/**
 * @author wk
 * @Description:微信公众号开发常量类
 * @date 2020/7/27 15:07
 **/
public class MessagegConstant {

    /**
     * 微信响应给公众号服务端的MsgType（消息类型）
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    public static final String REQ_MESSAGE_TYPE_LINK = "link";
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    public static final String REQ_MESSAGE_TYPE_MUSIC= "music";
    public static final String REQ_MESSAGE_TYPE_NEWS= "news";
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /**
     * Event 事件类型
     */
    //订阅
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    //取消订阅
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    //扫描
    public static final String EVENT_TYPE_SCAN = "SCAN";
    //上报地理位置
    public static final String EVENT_TYPE_LOCATION = "LOCATION";
    //自定义菜单事件（点击菜单弹出子菜单，不会产生上报。）
    public static final String EVENT_TYPE_CLICK = "CLICK";
    public static final String EVENT_TYPE_VIEW = "VIEW";
    public static final String KF_CREATE_SESSION = "KF_CREATE_SESSION";
    public static final String KF_CLOSE_SESSION = "KF_CLOSE_SESSION";
    public static final String KF_SWITCH_SESSION = "KF_SWITCH_SESSION";
}
