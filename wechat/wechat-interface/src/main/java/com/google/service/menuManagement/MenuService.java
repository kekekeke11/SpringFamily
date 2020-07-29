package com.google.service.menuManagement;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.utils.http.HttpClientUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author wk
 * @Description:自定义菜单
 * @date 2020/7/28 13:44
 **/
@Service
public class MenuService {

    public static Logger log = LoggerFactory.getLogger(MenuService.class);

    /**
     * 菜单创建（POST） 限100（次/天）
     */
    @Value("${wechat.MENU_CREATE}")
    public String MENU_CREATE;

    /**
     * 菜单查询
     */
    @Value("${wechat.MENU_GET}")
    public String MENU_GET;

    /**
     * 删除默认菜单及全部个性化菜单。
     */
    @Value("${wechat.MENU_DELETE}")
    public String MENU_DELETE;

    /**
     * 创建菜单
     *
     * @param jsonMenu json格式
     * @return 状态 0 表示成功、其他表示失败
     */
    public Integer createMenu(String jsonMenu, String access_token) {
        int result = 0;
        if (access_token != null) {

            String url3 = MENU_DELETE.replace("ACCESS_TOKEN", access_token);
            String resp3 = HttpClientUtils.doPostJson(url3, jsonMenu);
            System.out.println("调用菜单删除接口：" + resp3);

            // 拼装创建菜单的url
            String url = MENU_CREATE.replace("ACCESS_TOKEN", access_token);
            // 调用接口创建菜单
            JSONObject jsonObject = null;
            try {
                String resp = HttpClientUtils.doPostJson(url, jsonMenu);
                System.out.println("调用菜单创建接口：" + resp);
                if (StringUtils.isNotBlank(resp)) {
                    jsonObject = JSON.parseObject(resp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (null != jsonObject) {
                if (0 != jsonObject.getIntValue("errcode")) {
                    result = jsonObject.getIntValue("errcode");
                    log.error("创建菜单失败 errcode:" + jsonObject.getIntValue("errcode")
                            + "，errmsg:" + jsonObject.getString("errmsg"));
                }
            }
        }
        return result;
    }
}
