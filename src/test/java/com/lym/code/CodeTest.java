package com.lym.code;

import com.lym.entity.Result;
import com.lym.service.CodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Date 2020/1/22
 * @auth linyimin
 * @Desc
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class CodeTest {

    @Autowired
    @Qualifier("smsSeriveImplTest")
    private CodeService smsCodeService;

    @Autowired
    @Qualifier("mail163CodeServiceImpl")
    private CodeService smsCodeService2;

    @Autowired
    @Qualifier("tencentSmsCodeServiceImpl")
    private CodeService smsCodeService3;

    @Test
    public void test(){
        Result result = smsCodeService.sendCode("18316471919");
        System.out.println(result.getCode());

        boolean test = smsCodeService.isOk("18316471919", "test");
        System.out.println(test);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        test = smsCodeService.isOk("18316471919", "test");
        System.out.println(test);
    }


    @Test
    public void test2(){
        Result result = smsCodeService2.sendCode("18316471919");
        System.out.println(result.getCode());

        result = smsCodeService2.sendCode("18316471919@139.com");
        System.out.println(result.getCode());

        result = smsCodeService2.sendCode("18316471919@139.com");
        System.out.println(result.getCode());

        boolean test = smsCodeService.isOk("18316471919", "test");
        System.out.println(test);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        test = smsCodeService.isOk("18316471919", "test");
        System.out.println(test);
    }

    @Test
    public void test3(){
        Result result = smsCodeService3.sendCode("18316471919");
        System.out.println(result.getCode());

        boolean test = smsCodeService.isOk("18316471919", "test");
        System.out.println(test);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        test = smsCodeService.isOk("18316471919", "test");
        System.out.println(test);
    }
}
