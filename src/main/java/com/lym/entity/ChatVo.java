package com.lym.entity;

/**
 * @Date 2020/2/2
 * @auth linyimin
 * @Desc
 **/
public class ChatVo extends Chat {

    private String name;
    private String avatar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
