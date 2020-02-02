package com.lym.service;

import com.github.pagehelper.PageInfo;
import com.lym.entity.Admin;
import com.lym.entity.Page;
import com.lym.mapper.AdminMapper;
import com.lym.utils.SnowFlakeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

/**
 * @Date 2020/2/1
 * @auth linyimin
 * @Desc
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class TestAdminService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void test(){
        Page page = new Page();
        page.setPageSize(10);
        page.setPageNum(2);
        PageInfo<Admin> admins = adminService.admins(page);
        System.out.println(admins);

//        page.setPageSize(10);
//        page.setPageNum(2);
//        admins = adminService.admins(page);
//        System.out.println(admins);
    }

    @Test
    public void testAdd(){
        Admin admin = new Admin();
        admin.setId(SnowFlakeUtil.nextId());
        admin.setEmail("18316471919@139.com");
        admin.setPhone("18316471919");
        admin.setName("admin");
        admin.setRole(0);
//        admin.setResource("*");
        String md5Password = DigestUtils.md5DigestAsHex("admin".getBytes());
        admin.setPassword(md5Password);
        int insert = adminMapper.insertSelective(admin);
        System.out.println(insert);
    }
}
