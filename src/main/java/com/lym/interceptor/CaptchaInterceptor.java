package com.lym.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lym.entity.Captcha;
import com.lym.entity.Result;
import com.lym.utils.CaptchaUtil;
import com.lym.utils.JwtUtil;
import com.lym.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * @Date 2020/1/15
 * @auth linyimin
 * @Desc 验证码拦截器
 **/
public class CaptchaInterceptor implements HandlerInterceptor {

    @Autowired
    private CaptchaUtil captchaUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String captchaId = request.getHeader("captchaId");
        String captchaCode = request.getHeader("captchaCode");
        if (Objects.isNull(captchaId) || Objects.isNull(captchaCode) || captchaId.equals("") || captchaCode.equals("")) {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            Result r = ResultUtil.getInvalideCaptchaError();
            String s = JSONObject.toJSONString(r);
            response.getOutputStream().write(s.getBytes());
            return false;
        }
        Captcha captcha = new Captcha();
        captcha.setCaptchaId(Long.parseLong(captchaId));
        captcha.setCaptchaCode(captchaCode);
        if (!captchaUtil.isOk(captcha)) {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            Result r = ResultUtil.getInvalideCaptchaError();
            String s = JSONObject.toJSONString(r);
            response.getOutputStream().write(s.getBytes());
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
