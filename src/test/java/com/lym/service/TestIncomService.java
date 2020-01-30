package com.lym.service;

import com.lym.entity.Income;
import com.lym.utils.SnowFlakeUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Date 2020/1/30
 * @auth linyimin
 * @Desc
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class TestIncomService {

    @Autowired
    private IncomeService incomeService;

    @Test
    public void test(){
        Income income = new Income();
        income.setId(SnowFlakeUtil.nextId());
        income.setUserid(45L);
        income.setMemo("123");
        income.setIncomeTime(new Date());
        income.setValue(BigDecimal.valueOf(123));
        income.setType("test");
        int i = incomeService.addIncome(income);
        Assert.assertEquals(1, i);
    }
}
