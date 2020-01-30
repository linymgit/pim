package com.lym.service;

import com.github.pagehelper.PageInfo;
import com.lym.entity.Income;
import com.lym.entity.param.IncomeListParam;

import java.util.List;

/**
 * @Date 2020/1/30
 * @auth linyimin
 * @Desc
 **/
public interface IncomeService {
    /**
     * 添加
     */
    int addIncome(Income income);

    /**
     * 删除
     */
    int removeIncome(Long id);

    /**
     * 编辑
     */
    int editIncome(Income income);

    /**
     * 获取资产信息
     */
    PageInfo<Income> incomes(IncomeListParam incomeListParam);

    /**
     * 获取所有资产信息
     */
    List<Income> incomes(Long userId);
}
