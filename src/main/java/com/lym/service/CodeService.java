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
 * @Desc 验证码
 **/
public abstract class CodeService {

    @Autowired
    private CacheManager cacheManager;

    @Value("${toCaptchaCache}")
    private String toCaptchaCache;

    public abstract Result doSentCode(String to, String code);

    public Result sendCode(String to) {
//        if (hasCode(to)) {
//            return ResultUtil.getCodeStillEffectiveError();
//        }
        Result result = doSentCode(to, genCode());
        if (ResultUtil.isError(result)) {
            return result;
        }
        saveCode(to, (String) result.getData());
        return ResultUtil.getSuccess();
    }

    public String genCode() {
        return StringUtil.getRandomNumBylen(4);
    }

    public boolean hasCode(String to) {
        Cache cache = cacheManager.getCache(toCaptchaCache);
        Cache.ValueWrapper valueWrapper = cache.get(to);
        return Objects.nonNull(valueWrapper);
    }

    public void saveCode(String to, String code) {
        Cache cache = cacheManager.getCache(toCaptchaCache);
        cache.put(to, code);
    }

    public boolean isOk(String to, String code) {
        Cache cache = cacheManager.getCache(toCaptchaCache);
        Cache.ValueWrapper valueWrapper = cache.get(to);
        if (Objects.isNull(valueWrapper)) {
            return false;
        }
        if (valueWrapper.get().equals(code)) {
            cache.evict(to);
            return true;
        }
        return false;
    }
}
