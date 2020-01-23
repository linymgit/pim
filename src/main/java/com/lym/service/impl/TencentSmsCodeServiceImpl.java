package com.lym.service.impl;

import com.lym.entity.Result;
import com.lym.service.CodeService;
import com.lym.utils.ResultUtil;
import com.lym.utils.TencentSendSmsUtil;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20190711.models.SendStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Date 2020/1/22
 * @auth linyimin
 * @Desc
 **/
@Service("tencentSmsCodeServiceImpl")
public class TencentSmsCodeServiceImpl extends CodeService {

    static Logger log = LoggerFactory.getLogger(TencentSmsCodeServiceImpl.class);

    @Autowired
    private TencentSendSmsUtil tencentSendSmsUtil;

    public Result doSentCode(String to, String code) {
        SendSmsResponse sendSmsResponse = tencentSendSmsUtil.sendSmsCode(to, genCode());
        if (Objects.isNull(sendSmsResponse)) {
            return ResultUtil.getInvalidePhoneError();
        }
        SendStatus[] sendStatusSet = sendSmsResponse.getSendStatusSet();
        if (sendStatusSet.length <= 0) {
            return ResultUtil.getInvalidePhoneError();
        }
        String respCode = sendStatusSet[0].getCode();
        if (Objects.isNull(respCode) || !respCode.equals("Ok")) {
            log.warn("send code was not normal " + sendStatusSet[0].getCode() + " " + sendStatusSet[0].getMessage());
            return ResultUtil.getInvalidePhoneError();
        }
        return ResultUtil.getSuccess(sendStatusSet[0].getSessionContext());
    }
}
