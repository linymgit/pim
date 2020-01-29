package com.lym.entity.param;

import com.lym.entity.Schedule;
import com.lym.entity.User;

import java.util.List;

/**
 * @Date 2020/1/29
 * @auth linyimin
 * @Desc
 **/
public class IndexResult {
    private User user;
    private List<Schedule> schedules;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
