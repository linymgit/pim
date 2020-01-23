package com.lym.utils;


import com.lym.entity.Result;

/**
 * @Date 2020/1/21
 * @auth linyimin
 * @Desc
 **/
public class ResultUtil {

    public static int SUCCESS = 0;
    public static int ERROR = -1000;
    public static int INVALIDE_TOKEN = -1001;
    public static int NO_ACCESS = -1002;
    public static int CODE_STILL_EFFECTIVE = -1003;
    public static int INVALIDE_PHONE = -1004;
    public static int INVALIDE_EMAIL = -1005;
    public static int INVALIDE_CAPTCHA = -1006;
    public static int REPEAT_REGISTER = -1007;
    public static int TOKEN_EXPIRED = -1008;
    public static int INVALIDE_NAME_PW = -1009;
    public static int USER_WAS_NOT_EXIST = -1010;

    public static Result getSuccess() {
        return new Result(SUCCESS);
    }

    public static Result getSuccess(Object o) {
        return new Result(SUCCESS, o);
    }

    public static Result getError() {
        return new Result(ERROR);
    }

    public static Result getCodeStillEffectiveError() {
        return new Result(CODE_STILL_EFFECTIVE, "验证码已经发送，请勿重新获取");
    }

    public static Result getInvalidePhoneError() {
        return new Result(INVALIDE_PHONE, "验证码发送失败，请填写正确的手机号");
    }

    public static Result getInvalideEmailError() {
        return new Result(INVALIDE_EMAIL, "验证码发送失败，请填写正确的邮箱号");
    }

    public static Result getInvalideCaptchaError() {
        return new Result(INVALIDE_CAPTCHA, "验证码不正确或者失效了");
    }

    public static boolean isError(Result result){
        return result.getCode()<0;
    }
}
