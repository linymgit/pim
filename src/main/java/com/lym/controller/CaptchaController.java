package com.lym.controller;

import com.lym.entity.Captcha;
import com.lym.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @auth linyimin
 * @QQ: 1317113287
 * @desc: 验证码controller
 **/
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    private CaptchaUtil captchaUtil;

    @GetMapping("/arithm")
    public Captcha getArithmCaptcha() {
        return captchaUtil.getArithmeticCaptcha();
    }

    @GetMapping("/char")
    public Captcha getCharCaptcha(HttpServletResponse response) {
        return captchaUtil.getCharCaptcha();
    }
}
