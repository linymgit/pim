package com.lym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Date 2020/1/14
 * @auth linyimin
 * @Desc
 **/
@Controller
public class IndexController {
    @RequestMapping("/index")
    public  String index(){
        return "index";
    }
}
