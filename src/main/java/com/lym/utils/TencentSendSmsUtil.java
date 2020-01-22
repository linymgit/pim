package com.lym.utils;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


// 导入要请求接口对应的request response类

/**
 * @Date 2020/1/22
 * @auth linyimin
 * @Desc 腾讯云手机短信发送工具类
 **/
@Component
public class TencentSendSmsUtil {

    static Logger log = LoggerFactory.getLogger(TencentSendSmsUtil.class);

    @Value("${tencentSecretId}")
    private String tencentSecretId;

    @Value("${tencentsecretKey}")
    private String tencentsecretKey;

    @Value("${tencentSmsTemplateId}")
    private String tencentSmsTemplateId;

    @Value("${tencentSmsSign}")
    private String tencentSmsSign;

    @Value("${tencentSmsSdkAppid}")
    private String tencentSmsSdkAppid;

    private Credential cred;

    public TencentSendSmsUtil() {
        cred = new Credential(tencentSecretId, tencentsecretKey);
    }

    /**
     * 发送手机验证码
     */
    public SendSmsResponse sendSmsCode(String toPhoneNum, String code) {
        try {
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            SmsClient client = new SmsClient(cred, "", clientProfile);

            SendSmsRequest sendSmsRequest = new SendSmsRequest();
            sendSmsRequest.setPhoneNumberSet(new String[]{toPhoneNum});
            sendSmsRequest.setTemplateID(tencentSmsTemplateId);
            sendSmsRequest.setSign(tencentSmsSign);
            //模板自定义参数
            sendSmsRequest.setTemplateParamSet(new String[]{code});
            sendSmsRequest.setSessionContext(code);
            sendSmsRequest.setSmsSdkAppid(tencentSmsSdkAppid);

            return client.SendSms(sendSmsRequest);
        } catch (TencentCloudSDKException e) {
            log.error("fail to send sms code because " + e.toString());
        }
        return null;
    }

}
