package com.lym.controller.admin;

import com.github.pagehelper.PageInfo;
import com.lym.anno.Auth;
import com.lym.entity.Result;
import com.lym.entity.User;
import com.lym.entity.param.UserListParam;
import com.lym.service.UserService;
import com.lym.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Date 2020/2/1
 * @auth linyimin
 * @Desc
 **/
@Controller
@RequestMapping("/admin2020/user")
public class UsersMangeController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ModelAndView getList(ModelAndView mv) {
        mv.setViewName("admin/user/list");
        return mv;
    }

    @Auth(flag = 1)
    @PostMapping("/list")
    @ResponseBody
    public Result postList(@RequestBody UserListParam userListParam) {
        PageInfo<User> users = userService.users(userListParam);
        users.getList().forEach(user -> {
            if (Objects.nonNull(user.getBanTime()) && user.getBanTime().before(new Date())) {
                user.setBanTime(null);
            }
        });
        return ResultUtil.getSuccess(users);
    }

    @Auth(flag = 1)
    @PostMapping("/resetpw")
    @ResponseBody
    public Result resetpw(@RequestBody User user) {
        String password = user.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(md5Password);
        return ResultUtil.getSuccess(userService.updateUserById(user));
    }

    @Auth(flag = 1)
    @PostMapping("/ban")
    @ResponseBody
    public Result ban(@RequestBody User user) {
        User u = new User();
        if (Objects.nonNull(user) && Objects.nonNull(user.getId()) && user.getId() > 0) {
            Long userId = user.getId();
            u.setId(userId);
            Date banTime = user.getBanTime();
            if (banTime.before(new Date())) {
                return ResultUtil.getError("封禁日期不符合要求");
            }
            u.setBanTime(user.getBanTime());
        }
        return ResultUtil.getSuccess(userService.updateUserById(u));
    }

    @Auth(flag = 1)
    @PostMapping("/unban")
    @ResponseBody
    public Result unban(@RequestBody User user) {
        User u = new User();
        if (Objects.nonNull(user) && Objects.nonNull(user.getId()) && user.getId() > 0) {
            Long userId = user.getId();
            u.setId(userId);
            u.setBanTime(new Date());
        }
        return ResultUtil.getSuccess(userService.updateUserById(u));
    }


}
