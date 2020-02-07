package com.lym.service;

import com.github.pagehelper.PageInfo;
import com.lym.entity.Page;
import com.lym.entity.Sensitive;

/**
 * @Date 2020/2/7
 * @auth linyimin
 * @Desc
 **/
public interface SensitiveService {
    /**
     * 添加
     */
    int addSensitive(Sensitive sensitive);

    /**
     * 删除
     */
    int removeRelation(Integer id);


    /**
     * 获取联系人信息
     */
    PageInfo<Sensitive> sensitives(Page page);

    /**
     * 是否包含敏感信息
     */
    boolean isOk(String sentence);
}
