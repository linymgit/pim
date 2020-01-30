package com.lym.mapper;

import com.lym.entity.FileLog;
import com.lym.entity.FileLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileLogMapper {
    long countByExample(FileLogExample example);

    int deleteByExample(FileLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FileLog record);

    int insertSelective(FileLog record);

    List<FileLog> selectByExample(FileLogExample example);

    FileLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FileLog record, @Param("example") FileLogExample example);

    int updateByExample(@Param("record") FileLog record, @Param("example") FileLogExample example);

    int updateByPrimaryKeySelective(FileLog record);

    int updateByPrimaryKey(FileLog record);
}