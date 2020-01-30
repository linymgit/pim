package com.lym.entity.param;

import com.lym.entity.Page;

/**
 * @Date 2020/1/30
 * @auth linyimin
 * @Desc
 **/
public class IncomeListParam extends Page {
    private String date;
    private Long userId;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
