package com.lym.mapper;

import com.lym.entity.Sensitive;
import com.lym.entity.SensitiveExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SensitiveMapper {
    long countByExample(SensitiveExample example);

    int deleteByExample(SensitiveExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Sensitive record);

    int insertSelective(Sensitive record);

    List<Sensitive> selectByExample(SensitiveExample example);

    Sensitive selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Sensitive record, @Param("example") SensitiveExample example);

    int updateByExample(@Param("record") Sensitive record, @Param("example") SensitiveExample example);

    int updateByPrimaryKeySelective(Sensitive record);

    int updateByPrimaryKey(Sensitive record);
}