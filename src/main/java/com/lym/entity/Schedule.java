package com.lym.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Schedule {
    private Long id;

    private Long userId;

    private Integer remindSum;

    private Integer remindCount;

    private Integer remindPeriod;

    private String plan;

    @JSONField(format="yyyy-MM-dd HH:mm")
    private Date startTime;

    private Date endTime;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRemindSum() {
        return remindSum;
    }

    public void setRemindSum(Integer remindSum) {
        this.remindSum = remindSum;
    }

    public Integer getRemindCount() {
        return remindCount;
    }

    public void setRemindCount(Integer remindCount) {
        this.remindCount = remindCount;
    }

    public Integer getRemindPeriod() {
        return remindPeriod;
    }

    public void setRemindPeriod(Integer remindPeriod) {
        this.remindPeriod = remindPeriod;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan == null ? null : plan.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}