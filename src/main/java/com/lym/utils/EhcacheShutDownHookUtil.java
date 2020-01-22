package com.lym.utils;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Date 2020/1/19
 * @auth linyimin
 * @Desc
 **/
@Component
public class EhcacheShutDownHookUtil {

    @PostConstruct
    private void init(){
        System.setProperty(net.sf.ehcache.CacheManager.ENABLE_SHUTDOWN_HOOK_PROPERTY,"true");
    }
}
