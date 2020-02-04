package com.lym.service;

import com.lym.entity.Chat;

import java.util.List;

/**
 * @Date 2020/2/2
 * @auth linyimin
 * @Desc
 **/
public interface ChatService {

    int addChat(Chat chat);

    int updateChat(Chat chat);

    int deleteChat(Long id);

    List<Chat> chats(Long fromUserId, Long toUserId);
}
