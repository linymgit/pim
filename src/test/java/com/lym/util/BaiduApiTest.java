package com.lym.util;

import com.lym.utils.BaiduApiUtil;
import com.lym.utils.Img2Base64Util;
import org.json.JSONArray;
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
    public void test() {
//        JSONObject groups = baiduApiUtil.getGroups(0, 50);
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

//        JSONObject search = baiduApiUtil.search();
//        System.out.println(search.toString(2));
    }


    @Test
    public void testRegist() {
        JSONObject register = baiduApiUtil.register("helloworld", "http://forrily.com/linym.jpg", BaiduApiUtil.IMG_TYPE_URL, "userId001", "user");
        System.out.println(register.toString(2));
        System.out.println(register.get("error_code"));
        System.out.println(register.get("error_msg").toString());
    }

    @Test
    public void testRegist2() {
        String img = Img2Base64Util.NetImageToBase64("http://forrily.com/linym.jpg");
        JSONObject register = baiduApiUtil.register("iamtest", img, BaiduApiUtil.IMG_TYPE_BASE64, "userId002", "user");
        System.out.println(register.toString(2));
    }

    @Test
    public void testSearch() {
        JSONObject result = baiduApiUtil.search("http://forrily.com/linym.jpg");
        System.out.println(result.toString(2));
        JSONObject resultResult = (JSONObject) result.get("result");
        System.out.println(resultResult.get("face_token"));
        JSONArray userList = (JSONArray) resultResult.get("user_list");
        JSONObject o = (JSONObject)userList.get(0);
        System.out.println(o.get("user_id"));
        result = baiduApiUtil.search("http://forrily.com/18a39a4d-46eb-40ee-8cb4-45e19b6eaefd");
        System.out.println(result.toString(2));
    }

    @Test
    public void testSearchBase64() {
        String img = Img2Base64Util.NetImageToBase64("http://forrily.com/linym.jpg");
        JSONObject result = baiduApiUtil.searchBase64(img);
        JSONObject resultResult = (JSONObject) result.get("result");
        System.out.println(resultResult.get("face_token"));
    }
}
