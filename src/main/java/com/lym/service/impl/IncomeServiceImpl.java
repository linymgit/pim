package com.lym.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lym.entity.Income;
import com.lym.entity.IncomeExample;
import com.lym.entity.param.IncomeListParam;
import com.lym.mapper.IncomeMapper;
import com.lym.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Date 2020/1/30
 * @auth linyimin
 * @Desc
 **/
@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private IncomeMapper incomeMapper;

    @Override
    public int addIncome(Income income) {
        return incomeMapper.insertSelective(income);
    }

    @Override
    public int removeIncome(Long id) {
        return incomeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int editIncome(Income income) {
        return incomeMapper.updateByPrimaryKeySelective(income);
    }

    @Override
    public PageInfo<Income> incomes(IncomeListParam incomeListParam) {
        PageHelper.startPage(incomeListParam.getPageNum(), incomeListParam.getPageSize());
        IncomeExample incomeExample = new IncomeExample();
        IncomeExample.Criteria criteria = incomeExample.createCriteria().andUseridEqualTo(incomeListParam.getUserId());
        if (Objects.nonNull(incomeListParam.getDate()) && !incomeListParam.getDate().equals("")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = sdf.parse(incomeListParam.getDate());
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.DAY_OF_MONTH, 1);
                Date date2 = c.getTime();
                criteria.andIncomeTimeBetween(date, date2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
//        incomeExample.setOrderByClause("id DESC");
        List<Income> incomes = incomeMapper.selectByExample(incomeExample);
        return new PageInfo(incomes);
    }

    @Override
    public List<Income> incomes(Long userId) {
        IncomeExample incomeExample = new IncomeExample();
        IncomeExample.Criteria criteria = incomeExample.createCriteria();
        criteria.andUseridEqualTo(userId);
        return incomeMapper.selectByExample(incomeExample);
    }
}
