package com.google.model;

import com.google.constants.MessageConstant;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @Author YC
 * @create 2020/3/6
 * 被动回复的消息实体 图片
 */
@Data
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@Accessors(chain = true)
public class ImgReplyMsg {

    @XmlElement(name = "ToUserName")
    private String toUserName;

    @XmlElement(name = "FromUserName")

    private String fromUserName;

    @XmlElement(name = "CreateTime")
    private Long createTime;

    @XmlElement(name = "MsgType")
    private final String msgType = MessageConstant.REQ_MESSAGE_TYPE_IMAGE;

    // 通过素材管理中的接口上传多媒体文件，得到的id
    @XmlElementWrapper(name = "Image")
    @XmlElement(name = "MediaId")
    private List<String> mediaId;

}

