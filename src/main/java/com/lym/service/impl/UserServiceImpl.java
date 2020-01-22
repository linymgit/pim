package com.lym.service.impl;

import com.lym.entity.User;
import com.lym.mapper.UserMapper;
import com.lym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Cacheable(value = "HelloWorldCache")
    @Override
    public List<User> list() {
        System.out.println("hello world ....");
        return userMapper.selectByExample(null);
    }

    @Override
    public int register(User user) {
        return userMapper.insert(user);
    }
}
