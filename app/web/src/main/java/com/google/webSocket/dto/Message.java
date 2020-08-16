package com.google.webSocket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wk
 * @Description: WebSocket消息发送对象
 * @date 2020/7/31 15:26
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    /**
     * 消息接收方
     */
    private String toUacId;

    /**
     * 消息来源
     */
    private String fromUacId;

    /**
     * 消息
     */
    private String message;

    /**
     * 消息发送时间
     */
    private String sendTime;
}
