package com.lym.service.impl;

import com.lym.entity.User;
import com.lym.entity.UserExample;
import com.lym.mapper.UserMapper;
import com.lym.service.UserService;
import com.lym.utils.StringUtil;
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
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public User getUserByPEN(String phoneNum, String email, String name) {
        UserExample userExample = new UserExample();

        UserExample.Criteria criteria = userExample.createCriteria();

        if (StringUtil.nonBlank(phoneNum)) {
            criteria.andPhoneEqualTo(phoneNum);
        }
        if (StringUtil.nonBlank(email)) {
            criteria.andEmailEqualTo(email);
        }
        if (StringUtil.nonBlank(name)) {
            criteria.andNameEqualTo(name);
        }

        List<User> users = userMapper.selectByExample(userExample);
        if (Objects.isNull(users) || users.size() <= 0) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public User getUserOrByPEN(String phoneNum, String email, String name) {
        UserExample userExample = new UserExample();

        UserExample.Criteria criteria = userExample.createCriteria();

        if (StringUtil.nonBlank(phoneNum)) {
            criteria.andPhoneEqualTo(phoneNum);
            userExample.or(criteria);
        }

        if (StringUtil.nonBlank(email)) {
            criteria = userExample.createCriteria();
            criteria.andEmailEqualTo(email);
            userExample.or(criteria);
        }

        if (StringUtil.nonBlank(name)) {
            criteria = userExample.createCriteria();
            criteria.andNameEqualTo(name);
            userExample.or(criteria);
        }


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

    @Override
    public User getUserByName(String name) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(name);
        List<User> users = userMapper.selectByExample(userExample);
        if (Objects.isNull(users) || users.size() <= 0) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateUserById(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<User> selectUsersByExample(UserExample userExample) {
        return userMapper.selectByExample(userExample);
    }

    @Override
    public User selectUserByExample(UserExample userExample) {
        List<User> users = userMapper.selectByExample(userExample);
        if (Objects.isNull(users) || users.size() <= 0) {
            return null;
        }
        return users.get(0);
    }
}
