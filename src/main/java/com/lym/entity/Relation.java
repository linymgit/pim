package com.lym.entity;

import java.util.Date;

public class Relation {
    private Long id;

    private Long userid;

    private Long friendid;

    private String name;

    private String pone;

    private String email;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getFriendid() {
        return friendid;
    }

    public void setFriendid(Long friendid) {
        this.friendid = friendid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPone() {
        return pone;
    }

    public void setPone(String pone) {
        this.pone = pone == null ? null : pone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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