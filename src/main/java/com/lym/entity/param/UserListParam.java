package com.lym.entity.param;

import com.lym.entity.Page;
import com.lym.utils.StringUtil;

/**
 * @Date 2020/2/1
 * @auth linyimin
 * @Desc
 **/
public class UserListParam extends Page {

    private String keyWord;

    private Long id;

    public String getKeyWord() {
        if (StringUtil.isBlank(keyWord)) {
            return null;
        }
        return "%"+keyWord+"%";
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
