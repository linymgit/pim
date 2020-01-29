package com.lym.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.lym.entity.Schedule;
import com.lym.entity.param.ScheduleListParam;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @Date 2020/1/28
 * @auth linyimin
 * @Desc
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class TestScheduleService {

    @Autowired
    private ScheduleService scheduleService;

    @Test
    public void testAdd(){
        Schedule schedule = new Schedule();
        schedule.setPlan("测试");
        schedule.setUserId(45L);
        schedule.setRemindCount(0);
        schedule.setRemindPeriod(1);
        schedule.setRemindSum(3);
        schedule.setStartTime(new Date());
        Instant instant = LocalDateTime.now().plusDays(2).atZone(ZoneId.systemDefault()).toInstant();
        schedule.setEndTime(Date.from(instant));
        int add = scheduleService.add(schedule);
        Assert.assertEquals(add, 1);
    }

    @Test
    public void testGet(){
        ScheduleListParam scheduleListParam = new ScheduleListParam();
        scheduleListParam.setUserId(45L);
        scheduleListParam.setPageNum(1);
        scheduleListParam.setPageSize(2);
//        scheduleListParam.setKeyWord("测");
//        scheduleListParam.setDate("2020-02-05");
        scheduleListParam.setDateTime("2020-02-05 14:50");
        PageInfo<Schedule> list = scheduleService.list(scheduleListParam);
        for (Schedule schedule : list.getList()) {
            String s = JSON.toJSONString(schedule);
            System.out.println(s);
        }
    }

    @Test
    public void isRepeat(){

    }
}
