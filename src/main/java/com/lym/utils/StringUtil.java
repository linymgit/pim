package com.lym.utils;

import java.util.Random;

/**
 * @Date 2020/1/22
 * @auth linyimin
 * @Desc
 **/
public class StringUtil {

    public static final String NUM_STR = "1234567890";

    public static String getRandomNumBylen(int lenght){
        Random random = new Random(System.currentTimeMillis());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < lenght; i++) {
            stringBuilder.append(NUM_STR.charAt(random.nextInt(NUM_STR.length())));
        }
        return stringBuilder.toString();
    }
}
