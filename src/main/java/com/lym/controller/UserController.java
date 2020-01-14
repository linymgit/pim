package com.lym.controller;

import com.lym.entity.User;
import com.lym.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Date 2020/1/14
 * @auth linyimin
 * @Desc
 **/
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    public List<User> list(){
        return userService.list();
    }
}
