package com.google.dto.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author wk
 * @Description:
 * @date 2020/7/30 10:38
 **/
@XmlRootElement(name = "xml") // 根节点
@XmlAccessorType(XmlAccessType.FIELD) // 映射类中的所有字段到XML
public class MsgSendDto extends MsgSendEntity {

    // 文本消息内容
    @XmlElement(name = "Content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
