package com.lym.entity;

/**
 * @Date 2020/1/21
 * @auth linyimin
 * @Desc 验证码
 **/
public class Captcha {

    private int captchaId;
    private int captchaCode;

    public int getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(int captchaId) {
        this.captchaId = captchaId;
    }

    public int getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(int captchaCode) {
        this.captchaCode = captchaCode;
    }
}
