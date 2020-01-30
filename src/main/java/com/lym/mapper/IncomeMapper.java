package com.lym.mapper;

import com.lym.entity.Income;
import com.lym.entity.IncomeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IncomeMapper {
    long countByExample(IncomeExample example);

    int deleteByExample(IncomeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Income record);

    int insertSelective(Income record);

    List<Income> selectByExample(IncomeExample example);

    Income selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Income record, @Param("example") IncomeExample example);

    int updateByExample(@Param("record") Income record, @Param("example") IncomeExample example);

    int updateByPrimaryKeySelective(Income record);

    int updateByPrimaryKey(Income record);
}