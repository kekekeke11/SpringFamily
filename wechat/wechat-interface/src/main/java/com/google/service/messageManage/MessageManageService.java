package com.google.service.messageManage;

import java.util.Map;

/**
 * @author wk
 * @Description:消息管理
 * @date 2020/7/28 16:26
 **/
public interface MessageManageService {

    /**
     * 模板消息接口
     *
     * @param params
     */
    void sendTemplateManage(Map<String, Object> params);

}
