package com.lym.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @Date 2020/1/28
 * @auth linyimin
 * @Desc websocket 配置类
 **/
//@Configuration
//@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private WebsocketMsgHandler msgHandler;

    @Autowired
    private MsgInterceptor msgInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(msgHandler, "/ws").addInterceptors(msgInterceptor);
    }
}
