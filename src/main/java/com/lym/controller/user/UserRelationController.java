package com.lym.controller.user;

import com.lym.anno.Auth;
import com.lym.entity.Relation;
import com.lym.entity.Result;
import com.lym.entity.User;
import com.lym.entity.param.RelationListParam;
import com.lym.service.AdminService;
import com.lym.service.RelationService;
import com.lym.service.UserService;
import com.lym.utils.JwtUtil;
import com.lym.utils.ResultUtil;
import com.lym.utils.SnowFlakeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Date 2020/1/15
 * @auth linyimin
 * @Desc 个人通讯录controller
 **/
@Controller
@RequestMapping("/user/relation")
public class UserRelationController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RelationService relationService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ModelAndView list(ModelAndView mv) {
        mv.setViewName("user/relation/relation");
        return mv;
    }

    @GetMapping("/listAdmin")
    public ModelAndView listAdmin(ModelAndView mv) {
        mv.setViewName("user/relation/relation-admin");
        return mv;
    }

    @GetMapping("/msg")
    public ModelAndView message(ModelAndView mv) {
        mv.setViewName("user/relation/message");
        return mv;
    }

    @Auth
    @PostMapping("/list")
    @ResponseBody
    public Result postList(HttpServletRequest request, @RequestBody RelationListParam param) {
        Long userId = (Long) request.getAttribute(JwtUtil.ID_KEY);
        param.setUserId(userId);
        return ResultUtil.getSuccess(relationService.relations(param));
    }

    @Auth
    @PostMapping("/listAdmin")
    @ResponseBody
    public Result listAmdin(HttpServletRequest request, @RequestBody RelationListParam param) {
        Long userId = (Long) request.getAttribute(JwtUtil.ID_KEY);
        param.setUserId(userId);
        return ResultUtil.getSuccess(adminService.admins(param));
    }

    @Auth
    @PostMapping("/add")
    @ResponseBody
    public Result addRelation(HttpServletRequest request, @RequestBody Relation relation) {
        Long userId = (Long) request.getAttribute(JwtUtil.ID_KEY);
        relation.setUserid(userId);
        relation.setId(SnowFlakeUtil.nextId());
        User userByPEN = userService.getUserOrByPEN(relation.getPone(), relation.getEmail(), relation.getName());
        if (Objects.nonNull(userByPEN)) {
            relation.setFriendid(userByPEN.getId());
            relation.setRelationStatus(RelationService.SYS_USER);
        }else{
            relation.setFriendid(0L);
            relation.setRelationStatus(RelationService.SYS_VISITOR);
        }
        return ResultUtil.getSuccess(relationService.addRelation(relation));
    }

    @Auth
    @PostMapping("/friend/add")
    @ResponseBody
    public Result addFriend(HttpServletRequest request, @RequestBody Relation relation) {
        Long userId = (Long) request.getAttribute(JwtUtil.ID_KEY);
        if (Objects.isNull(relation) || Objects.isNull(relation.getFriendid())) {
            return ResultUtil.getError();
        }
        return ResultUtil.getSuccess(relationService.updateRelationStateByUserIdAndFriendId(RelationService.SYS_FRIEND, userId, relation.getFriendid()));
    }

    @Auth
    @PostMapping("/remove")
    @ResponseBody
    public Result removeRelation(Long id) {
        return ResultUtil.getSuccess(relationService.removeRelation(id));
    }

    @Auth
    @PostMapping("/update")
    @ResponseBody
    public Result updateRelation(@RequestBody Relation relation) {
        return ResultUtil.getSuccess(relationService.editRelation(relation));
    }

}
