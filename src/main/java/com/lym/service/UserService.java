package com.lym.service;

import com.lym.entity.User;
import com.lym.entity.UserExample;

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
    User getUserByName(String name);
    User getUserById(Long id);
    int updateUserById(User record);
    List<User> selectUsersByExample(UserExample userExample);
    User selectUserByExample(UserExample userExample);
}
