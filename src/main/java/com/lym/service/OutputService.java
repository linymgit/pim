package com.lym.service;

import com.github.pagehelper.PageInfo;
import com.lym.entity.Income;
import com.lym.entity.Output;
import com.lym.entity.param.OutputListParam;

import java.util.List;

/**
 * @Date 2020/1/30
 * @auth linyimin
 * @Desc
 **/
public interface OutputService {
    /**
     * 添加
     */
    int addOutput(Output output);

    /**
     * 删除
     */
    int removeOutput(Long id);

    /**
     * 编辑
     */
    int editOutput(Output output);

    /**
     * 获取资产信息
     */
    PageInfo<Output> outputs(OutputListParam outputListParam);

    /**
     * 获取所有资产信息
     */
    List<Output> outputs(Long userId);

}
