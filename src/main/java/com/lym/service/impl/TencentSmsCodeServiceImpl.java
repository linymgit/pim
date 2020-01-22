package com.lym.service.impl;

import com.lym.service.SmsCodeService;
import com.lym.utils.TencentSendSmsUtil;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20190711.models.SendStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Date 2020/1/22
 * @auth linyimin
 * @Desc
 **/
@Service("tencentSmsCodeServiceImpl")
public class TencentSmsCodeServiceImpl extends SmsCodeService {

    @Autowired
    private TencentSendSmsUtil tencentSendSmsUtil;

    public String doSentCode(String phone, String code) {
        SendSmsResponse sendSmsResponse = tencentSendSmsUtil.sendSmsCode(phone, genCode());
        if (Objects.isNull(sendSmsResponse)) {
            return null;
        }
        SendStatus[] sendStatusSet = sendSmsResponse.getSendStatusSet();
        if (sendStatusSet.length <= 0) {
            return null;
        }
        return sendStatusSet[0].getSessionContext();
    }
}
