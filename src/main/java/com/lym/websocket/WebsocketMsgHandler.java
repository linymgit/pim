package com.lym.websocket;

import com.lym.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Date 2020/1/28
 * @auth linyimin
 * @Desc
 **/
@Component
public class WebsocketMsgHandler extends TextWebSocketHandler {

    private Map<Long, WebSocketSession> id2SessionMap = new ConcurrentHashMap<>();

    // 建立连接时候触发
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        Long userId = getUserIdFromSession(session);
        id2SessionMap.putIfAbsent(userId, session);
    }


    // 关闭连接时候触发
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        Long userId = getUserIdFromSession(session);
        id2SessionMap.remove(userId);
    }

    // 处理消息
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 防止中文乱码
        String msg = URLDecoder.decode(message.getPayload(), "utf-8");
        Long userId = getUserIdFromSession(session);
        // 简单模拟群发消息
        TextMessage reply = new TextMessage(userId + " : " + msg);
        id2SessionMap.forEach((s, webSocketSession)
            -> {
            try {
                webSocketSession.sendMessage(reply);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Long getUserIdFromSession(WebSocketSession session) {
        return (Long) session.getAttributes().get(JwtUtil.ID_KEY);
    }

    public Map<Long, WebSocketSession> getId2SessionMap() {
        return id2SessionMap;
    }
}
