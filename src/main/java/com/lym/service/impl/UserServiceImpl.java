package com.lym.service.impl;

import com.lym.entity.User;
import com.lym.entity.UserExample;
import com.lym.mapper.UserMapper;
import com.lym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Date 2020/1/14
 * @auth linyimin
 * @Desc
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EhCacheCacheManager ehCacheCacheManager;

    @Override
    public List<User> list() {
        return userMapper.selectByExample(null);
    }

    @Override
    public int register(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User getUserByPEN(String phoneNum, String email, String name) {
        UserExample userExample = new UserExample();

        UserExample.Criteria criteria = userExample.createCriteria();

        criteria.andPhoneEqualTo(phoneNum);

        criteria = userExample.createCriteria();
        criteria.andEmailEqualTo(email);
        userExample.or(criteria);

        criteria = userExample.createCriteria();
        criteria.andNameEqualTo(name);
        userExample.or(criteria);

        List<User> users = userMapper.selectByExample(userExample);
        if (Objects.isNull(users) || users.size() <= 0) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public User getUserByNP(String name, String password) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(name).andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(userExample);
        if (Objects.isNull(users) || users.size() <= 0) {
            return null;
        }
        return users.get(0);
    }
}
