package com.lym.controller;

import com.lym.anno.Auth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Date 2020/1/14
 * @auth linyimin
 * @Desc
 **/
@Auth
@Controller
public class IndexController {

    @RequestMapping("/index")
    public  String index(){
        return "/user/index";
    }

}
