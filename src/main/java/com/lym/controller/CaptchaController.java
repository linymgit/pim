package com.lym.controller;

import com.lym.utils.CaptchaUtil;
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
        CaptchaUtil.getArithmeticCode(response);
    }

    @GetMapping("/char")
    public void getCharCaptcha(HttpServletResponse response) {
        CaptchaUtil.getCharCode(response);
    }
}
