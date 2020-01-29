package com.lym.service;

import com.github.pagehelper.PageInfo;
import com.lym.entity.Schedule;
import com.lym.entity.param.ScheduleListParam;

import java.util.List;

/**
 * @Date 2020/1/28
 * @auth linyimin
 * @Desc
 **/
public interface ScheduleService {
    int add(Schedule s);
    int del(Long id);
    PageInfo<Schedule> list(ScheduleListParam param);
    Schedule get(Long id);
    int edit(Schedule s);
    List<Schedule> userSchedules(Long userId);
}
