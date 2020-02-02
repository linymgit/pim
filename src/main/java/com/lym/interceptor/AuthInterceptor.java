package com.lym.interceptor;

import com.alibaba.fastjson.JSON;
import com.lym.anno.Auth;
import com.lym.entity.Income;
import com.lym.entity.Result;
import com.lym.utils.JwtUtil;
import com.lym.utils.ResultUtil;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Date 2020/1/15
 * @auth linyimin
 * @Desc 权限管理拦截器
 **/
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HandlerMethod myhandler = (HandlerMethod) handler;
        Auth authAnnotation = myhandler.getMethodAnnotation(Auth.class);
        int flag = -1;
        // 1.优先执行方法的权限检验
        if (Objects.nonNull(authAnnotation)) {
            flag = authAnnotation.flag();
        } else {
            // 2.方法没有限制用类的，类的没有说明不能做权限校验
            authAnnotation = AnnotationUtils.findAnnotation(myhandler.getBeanType(), Auth.class);
            if (Objects.nonNull(authAnnotation)) {
                flag = authAnnotation.flag();
            }
        }
        if (flag >= 0) {
            Result result;
            String token = request.getHeader("x-token");
            if (Objects.isNull(token) || token.equals("")) {
                String qs = request.getQueryString();
                if (Objects.nonNull(qs) && qs.contains("x-token=")) {
                    token = qs.substring(qs.indexOf("x-token") + "x-token=".length());
                    result = jwtUtil.getResult(token);
                } else {
                    result = ResultUtil.getNoAccess();
                }
            } else {
                result = jwtUtil.getResult(token);
            }
            if (ResultUtil.isError(result)) {
                if (authAnnotation.isRedirect()) {
                    if (flag == 1) {
                        response.sendRedirect("/admin2020/login");
                    }else{
                        response.sendRedirect("/user/login");
                    }
                } else {
                    response.setStatus(HttpStatus.OK.value());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.getOutputStream().write(JSON.toJSONBytes(result));
                    response.flushBuffer();
                }
                return false;
            }
            JSONObject data = (JSONObject) result.getData();
            //判断是不是管理员
            if (flag == 1){
                Long role = (Long)data.get(JwtUtil.ROLE_KEY);
                if (Objects.isNull(role) || role<=0) {
                    response.sendRedirect("/admin2020/login");
                    return false;
                }
            }
            Long id = (Long) data.get(JwtUtil.ID_KEY);
            request.setAttribute(JwtUtil.ID_KEY, id);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
