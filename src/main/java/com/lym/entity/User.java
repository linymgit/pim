package com.lym.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class User {
    private Long id;

    private String name;

    private String relname;

    private String email;

    private Byte emailVertify;

    private String phone;

    private Byte phoneVertify;

    private String password;

    private String address;

    private String avatar;

    private Byte sex;

    private String job;

    @JSONField(format="yyyy-MM-dd")
    private Date banTime;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRelname() {
        return relname;
    }

    public void setRelname(String relname) {
        this.relname = relname == null ? null : relname.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Byte getEmailVertify() {
        return emailVertify;
    }

    public void setEmailVertify(Byte emailVertify) {
        this.emailVertify = emailVertify;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Byte getPhoneVertify() {
        return phoneVertify;
    }

    public void setPhoneVertify(Byte phoneVertify) {
        this.phoneVertify = phoneVertify;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public Date getBanTime() {
        return banTime;
    }

    public void setBanTime(Date banTime) {
        this.banTime = banTime;
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