package com.lym.entity;

/**
 * @Date 2020/1/21
 * @auth linyimin
 * @Desc 验证码
 **/
public class Captcha {

    private Long captchaId;
    private String captchaCode;
    private String pic;

    public Long getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(Long captchaId) {
        this.captchaId = captchaId;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
