package com.lym.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lym.entity.Page;
import com.lym.entity.Sensitive;
import com.lym.entity.SensitiveExample;
import com.lym.mapper.SensitiveMapper;
import com.lym.service.SensitiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date 2020/2/7
 * @auth linyimin
 * @Desc
 **/
@Service
public class SensitiveServiceImpl implements SensitiveService {

    @Autowired
    private SensitiveMapper sensitiveMapper;

    @Override
    public int addSensitive(Sensitive sensitive) {
        return sensitiveMapper.insertSelective(sensitive);
    }

    @Override
    public int removeRelation(Integer id) {
        return sensitiveMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<Sensitive> sensitives(Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return new PageInfo(sensitiveMapper.selectByExample(new SensitiveExample()));
    }

    @Override
    public boolean isOk(String sentence) {
        final List<Sensitive> sensitives = sensitiveMapper.selectByExample(new SensitiveExample());
        for (Sensitive sensitive : sensitives) {
            if (sentence.contains(sensitive.getWords())) {
                return false;
            }
        }
        return true;
    }
}
