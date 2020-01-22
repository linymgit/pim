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

    public static Result getSuccess() {
        return new Result(SUCCESS);
    }

    public static Result getError() {
        return new Result(SUCCESS);
    }

}
