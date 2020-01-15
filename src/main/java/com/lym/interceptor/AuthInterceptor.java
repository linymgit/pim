package com.lym.interceptor;

import com.lym.anno.Auth;
import com.lym.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod myhandler = (HandlerMethod) handler;
        Auth authAnnotation = myhandler.getMethodAnnotation(Auth.class);
        // 1.优先执行方法的权限检验
        if (Objects.nonNull(authAnnotation)) {
            //todo
            System.out.println(jwtUtil);
            System.out.println(authAnnotation.flag()+"");
            return false;
        }
        // 2.方法没有限制用类的，类的没有说明不能做权限校验
        authAnnotation = AnnotationUtils.findAnnotation(myhandler.getBeanType(), Auth.class);
        if (Objects.nonNull(authAnnotation)) {
            //todo
            System.out.println(authAnnotation.flag()+"");
            return false;
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
