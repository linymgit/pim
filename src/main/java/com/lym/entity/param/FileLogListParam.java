package com.lym.entity.param;

import com.lym.entity.Page;

/**
 * @Date 2020/1/30
 * @auth linyimin
 * @Desc
 **/
public class FileLogListParam extends Page {
    private Long userId;
    private String date;
    private String keyWord;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = "%" + keyWord + "%";
    }
}
