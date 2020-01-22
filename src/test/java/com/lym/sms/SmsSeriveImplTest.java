package com.lym.sms;

import com.lym.service.SmsCodeService;
import org.springframework.stereotype.Service;

/**
 * @Date 2020/1/22
 * @auth linyimin
 * @Desc
 **/
@Service("smsSeriveImplTest")
public class SmsSeriveImplTest extends SmsCodeService {
    @Override
    public String doSentCode(String phone, String code) {
        return "test";
    }
}
