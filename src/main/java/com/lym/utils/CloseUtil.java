package com.lym.utils;

import javax.annotation.PreDestroy;

/**
 * @Date 2020/1/29
 * @auth linyimin
 * @Desc
 **/
public class CloseUtil {

    @PreDestroy
    public void close(){
        System.out.println("close");
    }
}
