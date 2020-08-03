package com.google.utils;

import com.swetake.util.Qrcode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author wk
 * @Description:
 * @date 2020/8/1 13:28
 **/
public class WeiXinCode {
    //程序入口
    public static void main(String[] args) {
        //生成二维码的地址
        String s = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx50d4e7bbd034f1de&redirect_uri=" +
                "http://f4zx4x.natappfree.cc/bind" +
                "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        //二维码存储地址
        getQrcodeImage(s, "C:/Users/wk/Desktop/b.png");
    }

    /**
     * 生成方法
     *
     * @param content 二维码扫描地址
     * @param imgPath 图片存储地址
     */
    public static void getQrcodeImage(String content, String imgPath) {
        //图片宽度
        int width = 235;
        //图片高度
        int height = 235;
        //实例化一个对象
        Qrcode qrcode = new Qrcode();
        //编码方式
        qrcode.setQrcodeEncodeMode('B');
        //设置拍错率
        qrcode.setQrcodeErrorCorrect('M');
        //二维码的版本
        qrcode.setQrcodeVersion(15);
        //绘制二维码
        //画板
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        //画笔
        Graphics2D gs = image.createGraphics();
        //设置背景颜色
        gs.setBackground(Color.white);
        //设置二维码的颜色
        gs.setColor(Color.red);
        //创建一个二维码的绘制区域
        gs.clearRect(0, 0, width, height);
        byte[] codeOut;
        try {
            codeOut = content.getBytes("utf-8");
            boolean[][] code = qrcode.calQrcode(codeOut);
            for (int i = 0; i < code.length; i++) {
                for (int j = 0; j < code.length; j++) {
                    if (code[j][i]) {
                        gs.fillRect(j * 3 + 2, i * 3 + 2, 3, 3);
                    }
                }
            }
            //加载logo（C:/Users/admin/Desktop/a.png是logo地址，需要修改成自己的）
/*            File file = new File("C:/Users/wk/Desktop/favicon.png");
            Image srcImage = ImageIO.read(file);
            int imgWidth = srcImage.getWidth(null);
            int imgHeith = srcImage.getHeight(null);
            gs.drawImage(srcImage, 50, 90, imgWidth, imgHeith, null);*/
            //释放资源
            gs.dispose();
            image.flush();
            //写入指定路径
            ImageIO.write(image, "png", new File(imgPath));
            System.out.println("生成成功！");
        } catch (Exception e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

        }
    }
}
