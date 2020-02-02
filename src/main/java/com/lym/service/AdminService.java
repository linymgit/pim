package com.lym.service;

import com.github.pagehelper.PageInfo;
import com.lym.entity.Admin;
import com.lym.entity.Page;

/**
 * @Date 2020/2/1
 * @auth linyimin
 * @Desc
 **/
public interface AdminService {

    /**
     * 获取管理员信息
     */
    PageInfo<Admin> admins(Page page);

    Admin getAdminByPhone(String phone);

    Admin getAdminByEmail(String email);

    Admin getAdminById(Long id);

    Admin getAdminByNameAndPassword(String name, String password);
}
