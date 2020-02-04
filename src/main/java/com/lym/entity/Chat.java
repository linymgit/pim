package com.lym.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Chat {
    private Long id;

    private Long fromUserId;

    private Long toUserId;

    private String msg;

    private Byte readState;

    @JSONField(format="yyyy-MM-dd HH:mm")
    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public Byte getReadState() {
        return readState;
    }

    public void setReadState(Byte readState) {
        this.readState = readState;
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