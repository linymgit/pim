package com.lym.controller.user;

import com.github.pagehelper.PageInfo;
import com.lym.anno.Auth;
import com.lym.entity.*;
import com.lym.entity.param.RelationListParam;
import com.lym.entity.param.UserListParam;
import com.lym.service.*;
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
@RequestMapping("/user/chat")
public class UserChatMangeController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private RelationService relationService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private SensitiveService sensitiveService;

    @GetMapping("/list")
    public ModelAndView list(ModelAndView mv) {
        mv.setViewName("user/chat/chat");
        return mv;
    }

    @GetMapping("/admins")
    public ModelAndView getAdmins(ModelAndView mv) {
        mv.setViewName("user/chat/chat2admin");
        return mv;
    }

    @Auth
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

    @Auth
    @PostMapping("/friends")
    @ResponseBody
    public Result friends(HttpServletRequest request, @RequestBody UserListParam userListParam) {
        Long userId = (Long) request.getAttribute(JwtUtil.ID_KEY);
        RelationListParam relationListParam = new RelationListParam();
        relationListParam.setUserId(userId);
        relationListParam.setPageNum(userListParam.getPageNum());
        relationListParam.setPageSize(userListParam.getPageSize());
        PageInfo<Relation> relations = relationService.relations(relationListParam);
        List<Long> friendsId = relations.getList().stream().mapToLong(Relation::getFriendid).boxed().collect(Collectors.toList());
        return ResultUtil.getSuccess(userService.listByUserIds(friendsId));
    }

    @Auth
    @PostMapping("/admins")
    @ResponseBody
    public Result admins(@RequestBody UserListParam userListParam) {
        Page page = new Page();
        page.setPageNum(userListParam.getPageNum());
        page.setPageSize(userListParam.getPageSize());
        PageInfo<Admin> admins = adminService.admins(page);
        return ResultUtil.getSuccess(admins);
    }

    @Auth
    @PostMapping("/add")
    @ResponseBody
    public Result add(HttpServletRequest request, @RequestBody Chat chat) {
        if (StringUtil.isBlank(chat.getMsg())) {
            return ResultUtil.getSuccess();
        }
        if (!sensitiveService.isOk(chat.getMsg())) {
            return ResultUtil.getError("发送失败，因为信息包含了敏感信息");
        }
        Long id = (Long) request.getAttribute(JwtUtil.ID_KEY);
        chat.setFromUserId(id);
        return ResultUtil.getSuccess(chatService.addChat(chat));
    }

    @Auth
    @PostMapping("/remove")
    @ResponseBody
    public Result remove(Long id) {
        return ResultUtil.getSuccess(chatService.deleteChat(id));
    }

}
