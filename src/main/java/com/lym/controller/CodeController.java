package com.lym.controller;

import com.lym.entity.Result;
import com.lym.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date 2020/1/23
 * @auth linyimin
 * @Desc
 **/
@RestController
@RequestMapping("/code")
public class CodeController {

    @Autowired
    @Qualifier("tencentSmsCodeServiceImpl")
    private CodeService smsCodeService;

    @Autowired
    @Qualifier("mail163CodeServiceImpl")
    private CodeService mailCodeService;

    @PostMapping("/phone")
    public Result sendPhoneCode(String phone){
        return smsCodeService.sendCode(phone);
    }

    @PostMapping("/email")
    public Result sendEmailCode(String email){
        return mailCodeService.sendCode(email);
    }

}
