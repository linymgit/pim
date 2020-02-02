package com.lym.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lym.entity.Admin;
import com.lym.entity.AdminExample;
import com.lym.entity.Page;
import com.lym.mapper.AdminMapper;
import com.lym.service.AdminService;
import com.lym.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Date 2020/2/1
 * @auth linyimin
 * @Desc
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public PageInfo<Admin> admins(Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Admin> admins = adminMapper.selectByExample(new AdminExample());
        return new PageInfo(admins);
    }

    @Override
    public Admin getAdminByPhone(String phone) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andPhoneEqualTo(phone);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (Objects.isNull(admins) || admins.size()<=0) {
            return null;
        }
        return admins.get(0);
    }

    @Override
    public Admin getAdminByEmail(String email) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andEmailEqualTo(email);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (Objects.isNull(admins) || admins.size()<=0) {
            return null;
        }
        return admins.get(0);
    }

    @Override
    public Admin getAdminById(Long id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public Admin getAdminByNameAndPassword(String name, String password) {
        if (StringUtil.isBlank(name) || StringUtil.isBlank(password)) {
            return null;
        }
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (Objects.isNull(admins) || admins.size()<=0) {
            return null;
        }
        return admins.get(0);
    }
}
