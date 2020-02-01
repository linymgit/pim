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
    public static int USER_EMAIL_NOT_VERITFY = -1011;

    public static Result getSuccess() {
        return new Result(SUCCESS,"操作成功");
    }

    public static Result getSuccess(Object o) {
        return new Result(SUCCESS,"操作成功", o);
    }

    public static Result getError() {
        return new Result(ERROR, "操作失败");
    }

    public static Result getError(String msg) {
        return new Result(ERROR, msg);
    }

    public static Result getUserNotExistError(){
        return new Result(ResultUtil.USER_WAS_NOT_EXIST, "用户不存在");
    }

   public static Result getUserEmailNotVeritfy(){
        return new Result(ResultUtil.USER_EMAIL_NOT_VERITFY, "邮箱未认证");
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
        return new Result(INVALIDE_CAPTCHA, "图片验证码不正确或者失效了");
    }

    public static Result getNoAccess() {
        return new Result(NO_ACCESS, "没有权限");
    }

    public static boolean isError(Result result){
        return result.getCode()<0;
    }
}
