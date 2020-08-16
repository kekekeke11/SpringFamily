package com.google.webSocket;

import com.alibaba.fastjson.JSONObject;
import com.google.config.MyApplicationContextAware;
import com.google.webSocket.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wk
 * @Description:
 * @date 2020/7/31 10:46
 **/
@ServerEndpoint(value = "/test")
@Component
@Slf4j
public class MyWebsocketServer {

    private RedisTemplate<String, String> redisTemplate;

    {
        redisTemplate = (RedisTemplate<String, String>) MyApplicationContextAware.getApplicationContext().getBean("redisTemplate");
    }

    /**
     * 存放所有在线的客户端
     */
    private static Map<String, Session> clients = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        log.info("有新的客户端连接了: {}", session.getId());
        //将新用户存入在线的组
        clients.put(session.getId(), session);
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
        clients.remove(session.getId());
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
    public void onMessage(String message) {
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
     * 群发消息
     *
     * @param message 消息内容
     */
    private void sendAll(String message) {
        for (Map.Entry<String, Session> sessionEntry : clients.entrySet()) {
            sessionEntry.getValue().getAsyncRemote().sendText(message);
        }
    }

    /**
     * 单发消息
     *
     * @param message
     */
    private void sendToOne(Message message) {
        Session s = clients.get(message.getToUacId());
        if (s != null) {
            try {
                s.getBasicRemote().sendText(message.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
