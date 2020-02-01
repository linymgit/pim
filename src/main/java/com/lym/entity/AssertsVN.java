package com.lym.entity;

import java.math.BigDecimal;

/**
 * @Date 2020/1/30
 * @auth linyimin
 * @Desc
 **/
public class AssertsVN {
    private BigDecimal value;
    private String name;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
