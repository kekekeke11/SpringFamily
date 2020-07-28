package com.google.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.utils.http.HttpClientUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wk
 * @Description:
 * @date 2020/7/28 13:44
 **/
public class MenuService {

    public static Logger log = LoggerFactory.getLogger(MenuService.class);

    /**
     * 菜单创建（POST） 限100（次/天）
     */
    public static String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * 菜单查询
     */
    public static String MENU_GET = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

    /**
     * 删除默认菜单及全部个性化菜单。
     */
    public static String MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    /**
     * 创建菜单
     *
     * @param jsonMenu json格式
     * @return 状态 0 表示成功、其他表示失败
     */
    public static Integer createMenu(String jsonMenu, String access_token) {
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
