package com.lym.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Date 2020/1/15
 * @auth linyimin
 * @Desc 用户登录注册、个人信息controller
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public ModelAndView login(ModelAndView mv){
        mv.setViewName("/user/login");
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView loginHandler(ModelAndView mv){
        mv.setViewName("/user/index");
        return mv;
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView mv){
        mv.setViewName("/user/register");
        return mv;
    }
}
