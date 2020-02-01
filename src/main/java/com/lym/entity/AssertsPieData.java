package com.lym.entity;

import java.util.List;

/**
 * @Date 2020/1/30
 * @auth linyimin
 * @Desc 资产饼状图
 **/
public class AssertsPieData {
    private List<String> types;
    private List<AssertsVN> values;

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<AssertsVN> getValues() {
        return values;
    }

    public void setValues(List<AssertsVN> values) {
        this.values = values;
    }
}
