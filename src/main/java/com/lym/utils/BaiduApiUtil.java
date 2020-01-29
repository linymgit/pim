package com.lym.utils;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * @Date 2020/1/24
 * @auth linyimin
 * @Desc
 **/
@Component
public class BaiduApiUtil {

    @Value("${baiduAppId}")
    public String appId;

    @Value("${baiduAppKey}")
    public String appKey;

    @Value("${baiduSecretKey}")
    public String secretKey;

    private AipFace client;

    @PostConstruct
    public void init() {
        client = new AipFace(appId, appKey, secretKey);
    }

    public JSONObject addGroup(String groupId) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        // 创建用户组
        return client.groupAdd(groupId, options);
    }

    public JSONObject getGroups(Integer start, Integer length) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        options.put("start", start.toString());
        options.put("length", length.toString());
        // 组列表查询
        return client.getGroupList(options);
    }

    public JSONObject register(String userInfo, String imageUrl, String userId, String groupId) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        options.put("user_info", userInfo);
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        options.put("action_type", "REPLACE");
        String image = imageUrl;
        String imageType = "URL";
        // 人脸注册
        return client.addUser(image, imageType, groupId, userId, options);
    }

    public JSONObject getUsers(Integer start, Integer length, String groupId) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        options.put("start", start.toString());
        options.put("length", length.toString());
        // 获取用户列表
        return client.getGroupUsers(groupId, options);
    }

    public JSONObject getUserInfo(String userId, String groupId) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        // 用户信息查询
        return client.getUser(userId, groupId, options);
    }

    public JSONObject search() {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        options.put("max_face_num", "3");
        options.put("match_threshold", "70");
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        options.put("user_id", "12345678");
//        options.put("max_user_num", "1");

        String image = "http://forrily.com/linym.jpg";
        String imageType = "URL";
        String groupIdList = "user";

        // 人脸搜索
        return client.search(image, imageType, groupIdList, options);

    }
}
