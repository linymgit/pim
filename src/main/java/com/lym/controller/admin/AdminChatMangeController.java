package com.lym.controller.admin;

import com.lym.anno.Auth;
import com.lym.entity.Chat;
import com.lym.entity.ChatVo;
import com.lym.entity.Result;
import com.lym.entity.User;
import com.lym.service.ChatService;
import com.lym.service.UserService;
import com.lym.utils.JwtUtil;
import com.lym.utils.ResultUtil;
import com.lym.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Date 2020/2/2
 * @auth linyimin
 * @Desc
 **/
@Controller
@RequestMapping("/admin2020/chat")
public class AdminChatMangeController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ModelAndView list(ModelAndView mv) {
        mv.setViewName("admin/chat/chat");
        return mv;
    }

    @Auth(flag = 1)
    @PostMapping("/list")
    @ResponseBody
    public Result postList(HttpServletRequest request, Long userId) {
        Long id = (Long) request.getAttribute(JwtUtil.ID_KEY);
        List<Chat> chats1 = chatService.chats(id, userId);
        List<Chat> chats2 = chatService.chats(userId, id);
        List<Chat> chats = new ArrayList<>();
        chats.addAll(chats1);
        chats.addAll(chats2);
        List<Chat> results = chats.stream().sorted((o1, o2) -> (int) (o2.getCreateTime().getTime() - o1.getCreateTime().getTime())).collect(Collectors.toList());
        List<Long> ids = results.stream().mapToLong(Chat::getToUserId).boxed().collect(Collectors.toList());
        List<User> users = userService.listByUserIds(ids);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(User::getId, u -> u));

        List<ChatVo> r = results.stream().map(chat -> {
            User user = userMap.get(chat.getToUserId());
            ChatVo chatVo = new ChatVo();
            BeanUtils.copyProperties(chat, chatVo);
            if (Objects.nonNull(user)) {
                chatVo.setAvatar(user.getAvatar());
                chatVo.setName(user.getName());
            }
            return chatVo;
        }).collect(Collectors.toList());
        return ResultUtil.getSuccess(r);
    }

    @Auth(flag = 1)
    @PostMapping("/add")
    @ResponseBody
    public Result add(HttpServletRequest request, @RequestBody Chat chat) {
        if (StringUtil.isBlank(chat.getMsg())) {
            return ResultUtil.getSuccess();
        }
        Long id = (Long) request.getAttribute(JwtUtil.ID_KEY);
        chat.setFromUserId(id);
        return ResultUtil.getSuccess(chatService.addChat(chat));
    }

    @Auth(flag = 1)
    @PostMapping("/remove")
    @ResponseBody
    public Result remove(Long id) {
        return ResultUtil.getSuccess(chatService.deleteChat(id));
    }

}
