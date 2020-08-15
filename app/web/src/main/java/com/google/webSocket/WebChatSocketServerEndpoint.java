package com.google.webSocket;

import com.alibaba.fastjson.JSONObject;
import com.google.config.MyApplicationContextAware;
import com.google.constants.Constants;
import com.google.entity.CustUac;
import com.google.webSocket.config.GetHttpSessionConfigurator;
import com.google.webSocket.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wk
 * @Description:
 * @date 2020/8/16 10:46
 **/
@ServerEndpoint(value = "/webChat", configurator = GetHttpSessionConfigurator.class)
@Component
@Slf4j
public class WebChatSocketServerEndpoint {

    private static RedisTemplate<String, String> redisTemplate;

    static {
        redisTemplate = (RedisTemplate<String, String>) MyApplicationContextAware.getApplicationContext().getBean("redisTemplate");
    }

    private Session session;

    private HttpSession httpSession;

    /**
     * 存放所有在线的客户端
     */
    private static Map<String, WebChatSocketServerEndpoint> onlineUsers = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        this.session = session;
        this.httpSession = (HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
        //获取当前
        CustUac custUac = (CustUac) this.httpSession.getAttribute(Constants.SESSION_UAC);
        log.info("有新的客户端连接了: {}", custUac.getUserName());
        //将登录的用户存入在线的组
        if (custUac != null) {
            onlineUsers.put(custUac.getBid(), this);
        }
        //推送系统消息
        this.getAllOnlineUser().stream().forEach((toUac) -> {
            WebChatSocketServerEndpoint endpoint = onlineUsers.get(toUac);
            if (endpoint != null) {
                try {
                    endpoint.session.getBasicRemote().sendText("这是一个系统消息，" + new Date().getTime());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 客户端关闭
     *
     * @param session session
     */
    @OnClose
    public void onClose(Session session) {
        log.info("有用户断开了, id为:{}", session.getId());
        //将掉线的用户移除在线的组里
        onlineUsers.remove(session.getId());
    }

    /**
     * 发生错误
     *
     * @param throwable e
     */
    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * 收到客户端发来消息
     *
     * @param message 消息对象
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("服务端收到客户端发来的消息: {}", message);
        String flag = redisTemplate.opsForValue().get("uacBid_1c64e3952f544d4da0d0907dfb407f1e3006");
        //this.sendAll(flag);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", message);
        jsonObject.put("message", flag);
        //{"userId":"1c64e3952f544d4da0d0907dfb407f1e3006","message":flag}
        this.sendToOne(JSONObject.parseObject(jsonObject.toJSONString(), Message.class));
    }

    /**
     * 单发消息
     *
     * @param message
     */
    private void sendToOne(Message message) {
        WebChatSocketServerEndpoint endpoint = onlineUsers.get(message.getUacId());
        if (endpoint != null) {
            try {
                endpoint.session.getBasicRemote().sendText(message.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Set<String> getAllOnlineUser() {
        return onlineUsers.keySet();
    }
}
