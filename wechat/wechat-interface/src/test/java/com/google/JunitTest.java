package com.google;

import com.google.config.JsonConfig;
import com.google.config.redisConfig.AccessTokenConfig;
import com.google.service.menuManagement.MenuService;
import com.google.service.messageManage.MessageManageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wk
 * @Description:
 * @date 2020/7/30 13:20
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class JunitTest {

    /**
     * redis获取token
     */
    @Autowired
    AccessTokenConfig accessTokenConfig;

    /**
     * 消息管理
     */
    @Autowired
    private MessageManageService messageManageService;

    /**
     * 自定义菜单
     */
    @Autowired
    private MenuService menuService;

    @Test
    public void menuTest() {
        /**
         * 创建自定义菜单
         */
        String menu = JsonConfig.getJsonResource("datas/menu/menu").toString();
        menuService.createMenu(menu, accessTokenConfig.getAccessToken());
    }

    @Test
    public void messageManageTest() {
        //发送模板消息
        messageManageService.sendTemplateManage(null);
    }
}
