package com.lym.service.impl;

import com.lym.entity.User;
import com.lym.mapper.UserMapper;
import com.lym.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date 2020/1/14
 * @auth linyimin
 * @Desc
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.list();
    }
}
