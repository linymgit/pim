package com.lym.util;

import com.lym.utils.BaiduApiUtil;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Date 2020/1/24
 * @auth linyimin
 * @Desc
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class BaiduApiTest {

    @Autowired
    private BaiduApiUtil baiduApiUtil;

    @Test
    public void test(){
//        JSONObject groups = baiduApiUtil.getGroups(1, 50);
//        System.out.println(groups.toString(2));

//        JSONObject user = baiduApiUtil.addGroup("user");
//        System.out.println(user.toString(2));
//
//        groups = baiduApiUtil.getGroups(1, 50);
//        System.out.println(groups.toString(2));

//        JSONObject register = baiduApiUtil.getUsers(1, 50, "user2");
////        System.out.println(register.toString(2));

//        JSONObject userInfo = baiduApiUtil.getUserInfo("", "");
//        System.out.println(userInfo.toString(2));

        JSONObject search = baiduApiUtil.search();
        System.out.println(search.toString(2));
    }
}
