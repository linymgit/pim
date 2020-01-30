package com.lym.entity;

import java.util.List;

/**
 * @Date 2020/1/30
 * @auth linyimin
 * @Desc 资产饼状图
 **/
public class IncomePieData {
    private List<String> types;
    private List<IncomeVN> values;

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<IncomeVN> getValues() {
        return values;
    }

    public void setValues(List<IncomeVN> values) {
        this.values = values;
    }
}
