package com.google.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author wk
 * @Description:
 * @date 2020/7/27 15:42
 **/
public class StreamToString {


    public static String getString(InputStream stream, String encoding) {
        String str = null;
        try {
            //转换
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, encoding));
            StringBuilder string = new StringBuilder();
            //中间值
            String len;
            while ((len = reader.readLine()) != null) {
                //追加
                string.append(len);
            }
            //转换
            str = string.toString();
            //关流
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回值
        return str;
    }
}
