package com.lym.utils;

import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

/**
 * @Date 2020/2/5
 * @auth linyimin
 * @Desc
 **/
public class Img2Base64Util{


    /**
     * 网络图片转base64
     *
     * @param netImagePath
     */
    public static String NetImageToBase64(String netImagePath) {
        final ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            // 创建URL
            URL url = new URL(netImagePath);
            final byte[] by = new byte[1024];
            // 创建链接
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            try (
                    InputStream is = conn.getInputStream();
            ) {

                // 将内容读取内存中
                int len = -1;
                while ((len = is.read(by)) != -1) {
                    data.write(by, 0, len);
                }
                // 对字节数组Base64编码
                BASE64Encoder encoder = new BASE64Encoder();
                return encoder.encode(data.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 本地图片转换Base64的方法
     *
     * @param imgPath     
     */
    public static String ImageToBase64(String imgPath) {
        byte[] data = null;
        // 读取图片字节数组
        try (InputStream in = new FileInputStream(imgPath)) {
            data = new byte[in.available()];
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        return new BASE64Encoder().encode(Objects.requireNonNull(data));
    }
}
