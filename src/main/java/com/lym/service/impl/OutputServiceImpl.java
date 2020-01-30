package com.lym.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lym.entity.Output;
import com.lym.entity.OutputExample;
import com.lym.entity.param.OutputListParam;
import com.lym.mapper.OutputMapper;
import com.lym.service.OutputService;
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
public class OutputServiceImpl implements OutputService {

    @Autowired
    private OutputMapper outputMapper;

    @Override
    public int addOutput(Output output) {
        return outputMapper.insertSelective(output);
    }

    @Override
    public int removeOutput(Long id) {
        return outputMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int editOutput(Output output) {
        return outputMapper.updateByPrimaryKeySelective(output);
    }

    @Override
    public PageInfo<Output> outputs(OutputListParam outputListParam) {
        PageHelper.startPage(outputListParam.getPageNum(), outputListParam.getPageSize());
        OutputExample outputExample = new OutputExample();
        OutputExample.Criteria criteria = outputExample.createCriteria().andUseridEqualTo(outputListParam.getUserId());
        if (Objects.nonNull(outputListParam.getDate()) && !outputListParam.getDate().equals("")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = sdf.parse(outputListParam.getDate());
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.DAY_OF_MONTH, 1);
                Date date2 = c.getTime();
                criteria.andOutputTimeBetween(date, date2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
//        OutputExample.setOrderByClause("id DESC");
        List<Output> Outputs = outputMapper.selectByExample(outputExample);
        return new PageInfo(Outputs);
    }
}
