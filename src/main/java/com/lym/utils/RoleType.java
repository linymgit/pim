package com.lym.utils;

/**
 * @Date 2020/1/15
 * @auth linyimin
 * @Desc 用户类型
 **/
public enum RoleType {
    Admin(1),
    User(2),
    ;
    private int type;

    RoleType(int type) {
        this.type = type;
    }
}
