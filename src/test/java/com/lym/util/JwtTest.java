package com.lym.util;

import com.lym.entity.Result;
import com.lym.utils.JwtUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

/**
 * @Date 2020/1/23
 * @auth linyimin
 * @Desc
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class JwtTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void Test(){
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("id", "1234");
        objectObjectHashMap.put("test", "hellowrld");
        String tokenHS256 = jwtUtil.createTokenHS256(objectObjectHashMap);

        System.out.println(tokenHS256);

        Result result = jwtUtil.getResult(tokenHS256);
        System.out.println(result.getData());
    }
}
