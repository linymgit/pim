package com.lym.cron;

import java.time.LocalDateTime;

/**
 * @Date 2020/1/28
 * @auth linyimin
 * @Desc
 **/
public class ScheduleRemindJob {

    public void execute() {
        System.out.println("Quartz的任务调度！！！" + LocalDateTime.now());
    }

}
