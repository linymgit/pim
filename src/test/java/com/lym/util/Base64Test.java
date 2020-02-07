package com.lym.util;

import com.lym.utils.Img2Base64Util;
import org.junit.Test;

/**
 * @Date 2020/2/5
 * @auth linyimin
 * @Desc
 **/
public class Base64Test {

    @Test
    public void test(){
        String s = Img2Base64Util.NetImageToBase64("http://forrily.com/linym.jpg");
        System.out.println(s);
    }
}
