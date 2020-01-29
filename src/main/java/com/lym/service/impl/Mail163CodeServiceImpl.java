package com.lym.service.impl;

import com.lym.entity.Result;
import com.lym.service.CodeService;
import com.lym.utils.MailUtil;
import com.lym.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Date 2020/1/23
 * @auth linyimin
 * @Desc 邮箱验证码实现类
 **/
@Service("mail163CodeServiceImpl")
public class Mail163CodeServiceImpl extends CodeService {

    @Autowired
    private MailUtil mailUtil;

    @Override
    public Result doSentCode(String to, String code) {
        if (Objects.isNull(to) || code.equals("")) {
            return ResultUtil.getError("邮箱地址不正确");
        }
        boolean ok = mailUtil.sendEmail(to, "xxx个人信息管理系统", String.format("验证码为%s,你正在登录,若非本人操作，请勿泄露", code));
        return ok ? ResultUtil.getSuccess(code) : ResultUtil.getInvalideEmailError();
    }
}
