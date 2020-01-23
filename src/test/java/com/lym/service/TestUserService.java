package com.lym.service;

import com.lym.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

/**
 * @Date 2020/1/23
 * @auth linyimin
 * @Desc
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class TestUserService {

    @Autowired
    private UserService userService;

    @Test
    public void test(){
        User hh = userService.getUserByPEN("193", "", "hh");
        System.out.println(hh);
    }

}
