package com.lym.service;

import com.lym.entity.User;

import java.util.List;

/**
 * @Date 2020/1/14
 * @auth linyimin
 * @Desc
 **/
public interface UserService {
    List<User> list();
    int register(User user);
    User getUserByPEN(String phoneNum, String email, String name);
    User getUserByNP(String name, String password);
}
