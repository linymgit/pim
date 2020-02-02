package com.lym.controller.admin;

import com.lym.anno.Auth;
import com.lym.entity.Admin;
import com.lym.entity.Code;
import com.lym.entity.Result;
import com.lym.service.AdminService;
import com.lym.service.CodeService;
import com.lym.utils.JwtUtil;
import com.lym.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Date 2020/2/1
 * @auth linyimin
 * @Desc
 **/
@Controller
@RequestMapping("/admin2020")
public class LoginController {

    @Autowired
    private AdminService adminService;

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
        mv.setViewName("admin/login");
        return mv;
    }

    @GetMapping("/index")
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("admin/index");
        return mv;
    }

    @Auth(flag = 1,isRedirect = true)
    @PostMapping("/index")
    @ResponseBody
    public Result index(HttpServletRequest request) {
        Long id = (Long) request.getAttribute(JwtUtil.ID_KEY);
        return ResultUtil.getSuccess(adminService.getAdminById(id));
    }


    @PostMapping("/pw/login")
    @ResponseBody
    public Result loginHandler(@RequestBody Admin admin) {
        String md5Password = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());
        admin.setPassword(md5Password);
        Admin adminByNameAndPassword = adminService.getAdminByNameAndPassword(admin.getName(), admin.getPassword());
        if (Objects.isNull(adminByNameAndPassword)) {
            return new Result(ResultUtil.INVALIDE_NAME_PW, "用户名或者密码不正确");
        }
        return ResultUtil.getSuccess(jwtUtil.genTokenWithAdminRole(adminByNameAndPassword.getId()));
    }

    @PostMapping("/phone/login")
    @ResponseBody
    public Result PhoneLogin(@RequestBody Code code) {
        if (Objects.isNull(code)) {
            return ResultUtil.getError();
        }
        boolean ok = smsCodeService.isOk(code.getTo(), code.getCode());
        if (!ok) {
            return new Result(ResultUtil.INVALIDE_CAPTCHA, "无效的短信验证码");
        }
        Admin adminByPhone = adminService.getAdminByPhone(code.getTo());
        if (Objects.isNull(adminByPhone)) {
            return ResultUtil.getUserNotExistError();
        }
        return ResultUtil.getSuccess(jwtUtil.genTokenWithAdminRole(adminByPhone.getId()));

    }

    @PostMapping("/email/login")
    @ResponseBody
    public Result EmailLogin(@RequestBody Code code) {
        if (Objects.isNull(code) || Objects.isNull(code.getTo())) {
            return ResultUtil.getError();
        }
        boolean ok = mailCodeService.isOk(code.getTo(), code.getCode());
        if (!ok) {
            return new Result(ResultUtil.INVALIDE_CAPTCHA, "无效的邮箱验证码");
        }
        Admin adminByEmail = adminService.getAdminByEmail(code.getTo());

        if (Objects.isNull(adminByEmail)) {
            return ResultUtil.getUserNotExistError();
        }
        return ResultUtil.getSuccess(jwtUtil.genTokenWithAdminRole(adminByEmail.getId()));

    }


}
