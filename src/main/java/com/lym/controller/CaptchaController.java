package com.lym.controller;

import com.lym.utils.Captcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @auth linyimin
 * @QQ: 1317113287
 * @desc: 验证码controller
 **/
@Controller
@RequestMapping("/captcha")
public class CaptchaController {

    @GetMapping("/arithm")
    public void getArithmCaptcha(HttpServletResponse response) {
        Captcha.getArithmeticCode(response);
    }

    @GetMapping("/char")
    public void getCharCaptcha(HttpServletResponse response) {
        Captcha.getCharCode(response);
    }
}
