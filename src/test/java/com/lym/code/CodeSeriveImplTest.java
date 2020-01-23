package com.lym.code;

import com.lym.entity.Result;
import com.lym.service.CodeService;
import com.lym.utils.ResultUtil;
import org.springframework.stereotype.Service;

/**
 * @Date 2020/1/22
 * @auth linyimin
 * @Desc
 **/
@Service("smsSeriveImplTest")
public class CodeSeriveImplTest extends CodeService {
    @Override
    public Result doSentCode(String phone, String code) {
        return ResultUtil.getSuccess("test");
    }
}
