package com.lym.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lym.entity.Schedule;
import com.lym.entity.ScheduleExample;
import com.lym.entity.param.ScheduleListParam;
import com.lym.mapper.ScheduleMapper;
import com.lym.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Date 2020/1/28
 * @auth linyimin
 * @Desc
 **/
@Service
public class ScheduleServiceImpl implements ScheduleService {

    static Logger log = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public int add(Schedule s) {
        return scheduleMapper.insertSelective(s);
    }

    @Override
    public int del(Long id) {
        return scheduleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<Schedule> list(ScheduleListParam param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        ScheduleExample scheduleExample = new ScheduleExample();
        ScheduleExample.Criteria criteria = scheduleExample.createCriteria().andUserIdEqualTo(param.getUserId());
        if (Objects.nonNull(param.getKeyWord()) && !param.getKeyWord().equals("")) {
            criteria.andPlanLike(param.getKeyWord());
        }
        if (Objects.nonNull(param.getDate()) && !param.getDate().equals("")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = sdf.parse(param.getDate());
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                Date date2 = c.getTime();
                criteria.andStartTimeBetween(date, date2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (Objects.nonNull(param.getDateTime()) && !param.getDateTime().equals("")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                Date date = sdf.parse(param.getDateTime());
                criteria.andStartTimeEqualTo(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        scheduleExample.setOrderByClause("id DESC");
        List<Schedule> schedules = scheduleMapper.selectByExample(scheduleExample);
        return new PageInfo(schedules);
    }

    @Override
    public Schedule get(Long id) {
        return scheduleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int edit(Schedule s) {
        return scheduleMapper.updateByPrimaryKeySelective(s);
    }

    @Override
    public List<Schedule> userSchedules(Long userId) {
        ScheduleExample scheduleExample = new ScheduleExample();
        ScheduleExample.Criteria criteria = scheduleExample.createCriteria();
        criteria.andUserIdEqualTo(userId).andStartTimeGreaterThan(new Date());
        return scheduleMapper.selectByExample(scheduleExample);
    }

}
