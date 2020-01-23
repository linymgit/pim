package com.lym.controller.user;

import com.lym.entity.Code;
import com.lym.entity.Result;
import com.lym.entity.User;
import com.lym.service.CodeService;
import com.lym.service.UserService;
import com.lym.utils.JwtUtil;
import com.lym.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @Date 2020/1/15
 * @auth linyimin
 * @Desc 用户登录注册、个人信息controller
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Autowired
    @Qualifier("tencentSmsCodeServiceImpl")
    private CodeService smsCodeService;

    @Autowired
    @Qualifier("mail163CodeServiceImpl")
    private CodeService mailCodeService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/login")
    public ModelAndView login(ModelAndView mv) {
        List<User> list = userService.list();
        System.out.println(list);
        mv.setViewName("user/login");
        return mv;
    }


    @PostMapping("/pw/login")
    @ResponseBody
    public Result loginHandler(@RequestBody User user) {
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Password);
        User userByNP = userService.getUserByNP(user.getName(), user.getPassword());
        if (Objects.isNull(userByNP)) {
            return new Result(ResultUtil.INVALIDE_NAME_PW, "用户名或者密码不正确");
        }
        return ResultUtil.getSuccess(jwtUtil.genToken(userByNP.getId()));
    }

    @PostMapping("/phone/login")
    @ResponseBody
    public Result PhoneLogin(@RequestBody Code code) {
        if (Objects.isNull(code)) {
            return ResultUtil.getError();
        }
        User userByPEN = userService.getUserByPEN(code.getTo(), "", "");
        if (Objects.isNull(userByPEN)) {
            return new Result(ResultUtil.USER_WAS_NOT_EXIST, "用户不存在");
        }
        boolean ok = smsCodeService.isOk(code.getTo(), code.getCode());
        if (!ok) {
            return new Result(ResultUtil.INVALIDE_CAPTCHA, "无效的短信验证码");
        }
        return ResultUtil.getSuccess(jwtUtil.genToken(userByPEN.getId()));
    }

    @PostMapping("/email/login")
    @ResponseBody
    public Result EmailLogin(@RequestBody Code code) {
        if (Objects.isNull(code) || Objects.isNull(code.getTo())) {
            return ResultUtil.getError();
        }
        User userByPEN = userService.getUserByPEN("", code.getTo(), "");
        if (Objects.isNull(userByPEN)) {
            return new Result(ResultUtil.USER_WAS_NOT_EXIST, "用户不存在");
        }
        boolean ok = mailCodeService.isOk(code.getTo(), code.getCode());
        if (!ok) {
            return new Result(ResultUtil.INVALIDE_CAPTCHA, "无效的邮箱验证码");
        }
        return ResultUtil.getSuccess(jwtUtil.genToken(userByPEN.getId()));
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView mv) {
        mv.setViewName("user/register");
        return mv;
    }

    @PostMapping("/do/register")
    @ResponseBody
    public Result registerHandler(@RequestBody User user) {
        User userByPEN = userService.getUserByPEN(user.getPhone(), user.getEmail(), user.getName());
        if (Objects.nonNull(userByPEN)) {
            String msg = "";
            if (userByPEN.getPhone().equals(user.getPhone())) {
                msg += "手机号已注册 ";
            }
            if (userByPEN.getEmail().equals(user.getEmail())) {
                msg += "邮箱已注册 ";
            }
            if (userByPEN.getName().equals(user.getName())) {
                msg += "该用户名已被注册 ";
            }
            return new Result(ResultUtil.REPEAT_REGISTER, msg);
        }
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Password);
        int result = userService.register(user);
        if (result > 0) {
            return ResultUtil.getSuccess();
        }
        return ResultUtil.getError();
    }
}
