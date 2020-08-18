package com.google.webSocket;

import com.alibaba.fastjson.JSONObject;
import com.google.constants.Constants;
import com.google.entity.CustUac;
import com.google.webSocket.config.GetHttpSessionConfigurator;
import com.google.webSocket.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
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
       /* this.getAllOnlineUser().stream().forEach((toUac) -> {
            WebChatSocketServerEndpoint endpoint = onlineUsers.get(toUac);
            if (endpoint != null && !toUac.equals(custUac.getBid())) {
                try {
                    endpoint.session.getBasicRemote().sendText("您的好友，【" + custUac.getUserName() + "】上线了。。。");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });*/
    }

    /**
     * 客户端关闭
     *
     * @param session session
     */
    @OnClose
    public void onClose(Session session) {
        //从容器中删除下线的用户
        if (this.httpSession != null) {
            CustUac custUac = (CustUac) this.httpSession.getAttribute(Constants.SESSION_UAC);
            log.info("用户【{}】断开了链接。。。。", custUac.getUserName());
            onlineUsers.remove(custUac.getBid());
            //获取推送的消息，系统通知
        }
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
        if (StringUtils.isNotBlank(message)) {
            //将message转换成Message对象
            Message messageObj = JSONObject.parseObject(message, Message.class);
            //消息接收方
            String toUser = messageObj.getToUacId();
            //消息内容
            String msgData = messageObj.getMessage();
            //获取当前登录的用户
            CustUac custUac = (CustUac) this.httpSession.getAttribute(Constants.SESSION_UAC);
            //返回给发送的信息
            Message data = new Message();
            data.setToUacId(toUser);
            data.setMessage(msgData);
            data.setFromUacId(custUac.getBid());
            //获取推送给指定用户的消息格式数据
            String resultMessage = JSONObject.toJSONString(data);
            //发送消息
            try {
                WebChatSocketServerEndpoint endpoint = onlineUsers.get(toUser);
                if (endpoint != null) {
                    endpoint.session.getBasicRemote().sendText(resultMessage);
                } else {
                    //对方不在线情况
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Set<String> getAllOnlineUser() {
        return onlineUsers.keySet();
    }
}
