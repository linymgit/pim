package com.lym.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Date 2020/1/30
 * @auth linyimin
 * @Desc
 **/
public class IncomeBarData {
    private List<String> dataAxis;
    private List<BigDecimal> data;
    private BigDecimal yMax;

    public List<String> getDataAxis() {
        return dataAxis;
    }

    public void setDataAxis(List<String> dataAxis) {
        this.dataAxis = dataAxis;
    }

    public List<BigDecimal> getData() {
        return data;
    }

    public void setData(List<BigDecimal> data) {
        this.data = data;
    }

    public BigDecimal getyMax() {
        return yMax;
    }

    public void setyMax(BigDecimal yMax) {
        this.yMax = yMax;
    }
}
