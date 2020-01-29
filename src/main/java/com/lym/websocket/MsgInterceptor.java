package com.lym.websocket;

import com.lym.entity.Result;
import com.lym.utils.JwtUtil;
import com.lym.utils.ResultUtil;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @Date 2020/1/28
 * @auth linyimin
 * @Desc
 **/
@Component
public class MsgInterceptor implements HandshakeInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        String qs = serverHttpRequest.getURI().getQuery();
        String token = qs.substring(qs.indexOf("x-token") + "x-token=".length());
        Result result = jwtUtil.getResult(token);
        if (ResultUtil.isError(result)) {
            return false;
        }
        JSONObject data = (JSONObject) result.getData();
        Long id = (Long) data.get(JwtUtil.ID_KEY);
        map.put(JwtUtil.ID_KEY, id);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
    }
}
