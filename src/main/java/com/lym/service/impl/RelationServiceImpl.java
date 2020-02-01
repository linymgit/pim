package com.lym.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lym.entity.Relation;
import com.lym.entity.RelationExample;
import com.lym.entity.param.RelationListParam;
import com.lym.mapper.RelationMapper;
import com.lym.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Date 2020/1/30
 * @auth linyimin
 * @Desc
 **/
@Service
public class RelationServiceImpl implements RelationService {
    
    @Autowired
    private RelationMapper relationMapper;

    @Override
    public int addRelation(Relation relation) {
        return relationMapper.insertSelective(relation);
    }

    @Override
    public int removeRelation(Long id) {
        return relationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int editRelation(Relation relation) {
        return relationMapper.updateByPrimaryKeySelective(relation);
    }

    @Override
    public PageInfo<Relation> relations(RelationListParam relationListParam) {
        PageHelper.startPage(relationListParam.getPageNum(), relationListParam.getPageSize());
        RelationExample relationExample = new RelationExample();
        RelationExample.Criteria criteria = relationExample.createCriteria().andUseridEqualTo(relationListParam.getUserId());
        if (Objects.nonNull(relationListParam.getKeyWord()) && !relationListParam.getKeyWord().equals("")) {
            criteria.andNameLike(relationListParam.getKeyWord());
        }
        List<Relation> Relations = relationMapper.selectByExample(relationExample);
        return new PageInfo(Relations);
    }

    @Override
    public int updateRelationStateByUserIdAndFriendId(Byte relationStatus, Long userId, Long friendId) {
        Relation relation = new Relation();
        relation.setUserid(userId);
        relation.setFriendid(friendId);
        relation.setRelationStatus(relationStatus);
        RelationExample relationExample = new RelationExample();
        relationExample.createCriteria().andUseridEqualTo(userId).andFriendidEqualTo(friendId);
        return relationMapper.updateByExampleSelective(relation, relationExample);
    }

}
