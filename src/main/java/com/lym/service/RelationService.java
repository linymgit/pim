package com.lym.service;

import com.github.pagehelper.PageInfo;
import com.lym.entity.Relation;
import com.lym.entity.param.RelationListParam;

/**
 * @Date 2020/1/30
 * @auth linyimin
 * @Desc
 **/
public interface RelationService {
    byte SYS_VISITOR = 0;
    byte SYS_USER = 1;
    byte SYS_FRIEND = 2;
    /**
     * 添加
     */
    int addRelation(Relation relation);

    /**
     * 删除
     */
    int removeRelation(Long id);

    /**
     * 编辑
     */
    int editRelation(Relation relation);

    /**
     * 获取联系人信息
     */
    PageInfo<Relation> relations(RelationListParam relationListParam);

    int updateRelationStateByUserIdAndFriendId(Byte relationStatus, Long userId, Long friendId);
}
