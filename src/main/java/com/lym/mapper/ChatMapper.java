package com.lym.mapper;

import com.lym.entity.Chat;
import com.lym.entity.ChatExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChatMapper {
    long countByExample(ChatExample example);

    int deleteByExample(ChatExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Chat record);

    int insertSelective(Chat record);

    List<Chat> selectByExample(ChatExample example);

    Chat selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Chat record, @Param("example") ChatExample example);

    int updateByExample(@Param("record") Chat record, @Param("example") ChatExample example);

    int updateByPrimaryKeySelective(Chat record);

    int updateByPrimaryKey(Chat record);
}