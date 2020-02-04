package com.lym.service.impl;

import com.lym.entity.Chat;
import com.lym.entity.ChatExample;
import com.lym.mapper.ChatMapper;
import com.lym.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date 2020/2/2
 * @auth linyimin
 * @Desc
 **/
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatMapper chatMapper;

    @Override
    public int addChat(Chat chat) {
        return chatMapper.insertSelective(chat);
    }

    @Override
    public int updateChat(Chat chat) {
        return chatMapper.updateByPrimaryKeySelective(chat);
    }

    @Override
    public int deleteChat(Long id) {
        return chatMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Chat> chats(Long fromUserId, Long toUserId) {
        ChatExample chatExample = new ChatExample();
        ChatExample.Criteria criteria = chatExample.createCriteria();
        criteria.andFromUserIdEqualTo(fromUserId).andToUserIdEqualTo(toUserId);
        return chatMapper.selectByExample(chatExample);
    }
}
