package com.lym.service;

import com.github.pagehelper.PageInfo;
import com.lym.entity.User;
import com.lym.entity.UserExample;
import com.lym.entity.param.UserListParam;

import java.util.List;

/**
 * @Date 2020/1/14
 * @auth linyimin
 * @Desc
 **/
public interface UserService {
    List<User> list();
    List<User> listByUserIds(List<Long> ids);
    int addUser(User user);
    User getUserByPEN(String phoneNum, String email, String name);
    User getUserOrByPEN(String phoneNum, String email, String name);
    User getUserByNP(String name, String password);
    User getUserByFt(String faceToken);
    User getUserByName(String name);
    User getUserById(Long id);
    int updateUserById(User record);
    List<User> selectUsersByExample(UserExample userExample);
    User selectUserByExample(UserExample userExample);
    PageInfo<User> users(UserListParam userListParam);
}
