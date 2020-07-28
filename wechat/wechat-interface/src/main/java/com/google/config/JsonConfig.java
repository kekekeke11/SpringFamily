package com.google.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.io.Resources;
import org.apache.commons.codec.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * @author wk
 * @Description:
 * @date 2020/7/28 13:42
 **/
public class JsonConfig {

    private static final Logger logger = LoggerFactory.getLogger(JsonConfig.class);

    /**
     * 根据json文件名称获取json配置文件数据
     *
     * @param fileName json文件名称前缀，如果在resource下直接写文件名，如果有路径，请在前面添加路径如："com/xxx/abc"
     */
    public static JSONObject getJsonResource(String fileName) {

        fileName += ".json";
        ClassLoader classLoader = getClassLoader();

        Enumeration<URL> resources;
        JSONObject jsonObject = new JSONObject();
        try {
            resources = classLoader.getResources(fileName);
        } catch (IOException e) {
            logger.warn("getJsonResource fail {}", fileName, e);
            return jsonObject;
        }

        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            try {
                String json = Resources.toString(url, Charsets.UTF_8);
                jsonObject.putAll(JSON.parseObject(json)); // 有多个的时候，后面的覆盖前面的
            } catch (IOException e) {
                logger.warn("addJsonFile fail url:{}", url, e);
            }
        }
        return jsonObject;
    }

    private static ClassLoader getClassLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader != null) {
            return classLoader;
        }
        return JsonConfig.class.getClassLoader();
    }

    /**
     * 私有构造方法，防止工具类被new
     */
    private JsonConfig() {
        throw new IllegalAccessError();
    }
}
