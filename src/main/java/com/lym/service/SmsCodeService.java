package com.lym.service;

import com.lym.entity.Result;
import com.lym.utils.ResultUtil;
import com.lym.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Objects;

/**
 * @Date 2020/1/22
 * @auth linyimin
 * @Desc 短信验证码
 **/
public abstract class SmsCodeService {

    @Autowired
    private CacheManager cacheManager;

    @Value("${phoneCaptchaCache}")
    private String phoneCaptchaCache;

    public abstract String doSentCode(String phone, String code);

    public Result sendCode(String phone) {
        if (hasCode(phone)) {
            return ResultUtil.getError();
        }
        String s = doSentCode(phone, genCode());
        if (Objects.isNull(s) || s.equals("")) {
            return ResultUtil.getError();
        }
        saveCode(phone, s);
        return ResultUtil.getSuccess();
    }

    public String genCode() {
        return StringUtil.getRandomNumBylen(4);
    }

    public boolean hasCode(String phone) {
        Cache cache = cacheManager.getCache(phoneCaptchaCache);
        Cache.ValueWrapper valueWrapper = cache.get(phone);
        return Objects.nonNull(valueWrapper);
    }

    public void saveCode(String phone, String code) {
        Cache cache = cacheManager.getCache(phoneCaptchaCache);
        cache.put(phone, code);
    }

    public boolean isOk(String phone, String code) {
        Cache cache = cacheManager.getCache(phoneCaptchaCache);
        Cache.ValueWrapper valueWrapper = cache.get(phone);
        if (Objects.isNull(valueWrapper)) {
            return false;
        }
        if (valueWrapper.get().equals(code)) {
            cache.evict(phone);
            return true;
        }
        return false;
    }
}
