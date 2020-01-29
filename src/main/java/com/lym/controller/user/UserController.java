package com.lym.controller.user;

import com.lym.anno.Auth;
import com.lym.entity.Code;
import com.lym.entity.Result;
import com.lym.entity.Schedule;
import com.lym.entity.User;
import com.lym.entity.param.IndexResult;
import com.lym.service.CodeService;
import com.lym.service.ScheduleService;
import com.lym.service.UserService;
import com.lym.utils.JwtUtil;
import com.lym.utils.MailUtil;
import com.lym.utils.ResultUtil;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    @Qualifier("tencentSmsCodeServiceImpl")
    private CodeService smsCodeService;

    @Autowired
    @Qualifier("mail163CodeServiceImpl")
    private CodeService mailCodeService;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/login")
    public ModelAndView login(ModelAndView mv) {
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

    /**
     * 重置密码
     *
     * @param user
     * @return
     */
    @PostMapping("/pw/reset")
    @ResponseBody
    public Result reset(@RequestBody User user) {
        User userById = userService.getUserById(user.getId());
        if (userById.getEmailVertify() <= 0) {
            return ResultUtil.getUserEmailNotVeritfy();
        }
        return userService.updateUserById(userById) > 0 ? ResultUtil.getSuccess() : ResultUtil.getError();
    }

    @PostMapping("/phone/login")
    @ResponseBody
    public Result PhoneLogin(@RequestBody Code code) {
        if (Objects.isNull(code)) {
            return ResultUtil.getError();
        }
        User userByPEN = userService.getUserByPEN(code.getTo(), "", "");
        if (Objects.isNull(userByPEN)) {
            return ResultUtil.getUserNotExistError();
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
            return ResultUtil.getUserNotExistError();
        }
        boolean ok = mailCodeService.isOk(code.getTo(), code.getCode());
        if (!ok) {
            return new Result(ResultUtil.INVALIDE_CAPTCHA, "无效的邮箱验证码");
        }
        return ResultUtil.getSuccess(jwtUtil.genToken(userByPEN.getId()));
    }

    @Auth
    @PostMapping("/email/verify")
    @ResponseBody
    public Result sendEmailVerify(HttpServletRequest request) {
        Long id = (Long) request.getAttribute(JwtUtil.ID_KEY);
        User userById = userService.getUserById(id);
        if (Objects.isNull(userById)) {
            return ResultUtil.getUserNotExistError();
        }
        if (userById.getEmailVertify()>0) {
            return ResultUtil.getSuccess();
        }
        String s = "<a href='http://localhost:8080/user/email/verify?token=%s'>点击激活邮箱</a>";
        HashMap<String, Object> map = new HashMap<>();
        map.put(JwtUtil.ID_KEY, userById.getId());
        s = String.format(s, jwtUtil.createTokenHS256(map));
        boolean ok = mailUtil.sendEmail(userById.getEmail(), "xxx个人信息管理系统", s);
        if (ok) {
            userById.setEmailVertify(MailUtil.VERIFING);
            userService.updateUserById(userById);
            return ResultUtil.getSuccess();
        }
        return ResultUtil.getError();
    }

    @GetMapping("/email/verify")
    public ModelAndView EmailVerifyByUrl(ModelAndView mv, String token) {
        mv.setViewName("user/result_tips");
        Result result = jwtUtil.getResult(token);
        if (ResultUtil.isError(result)) {
            mv.addObject("msg", result.getMsg());
            return mv;
        }
        JSONObject data = (JSONObject) result.getData();
        Long id = (Long) data.get(JwtUtil.ID_KEY);
        if (Objects.isNull(id) || id <= 0) {
            mv.addObject("msg", "找不到该用户的id");
            return mv;
        }
        User user = new User();
        user.setId(id);
        user.setEmailVertify(MailUtil.VERIFED);
        int i = userService.updateUserById(user);
        if (i < 0) {
            mv.addObject("msg", "验证失败");
        } else {
            mv.addObject("msg", "验证成功");
        }
        return mv;
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

    @GetMapping("/index")
    public ModelAndView indexGet(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) {
        mv.setViewName("user/index");
        return mv;
    }

    @Auth
    @PostMapping("/index")
    @ResponseBody
    public Result indexPost(HttpServletRequest request) {
        Long id = (Long) request.getAttribute(JwtUtil.ID_KEY);
        User userById = userService.getUserById(id);
        if (Objects.isNull(userById)) {
            return ResultUtil.getUserNotExistError();
        }
        List<Schedule> schedules = scheduleService.userSchedules(id);
        IndexResult indexResult = new IndexResult();
        indexResult.setUser(userById);
        indexResult.setSchedules(schedules);
        return ResultUtil.getSuccess(indexResult);
    }

    @Auth
    @GetMapping("/mail/verify")
    public ModelAndView mailVertify(HttpServletRequest request, ModelAndView mv) {
        Long id = (Long) request.getAttribute(JwtUtil.ID_KEY);
        User userById = userService.getUserById(id);
        mv.addObject("user", userById);
        mv.setViewName("user/email-verify");
        return mv;
    }

    @Auth
    @GetMapping("/info")
    public ModelAndView information(HttpServletRequest request, ModelAndView mv) {
        Long id = (Long) request.getAttribute(JwtUtil.ID_KEY);
        User userById = userService.getUserById(id);
        mv.addObject("user", userById);
        mv.setViewName("user/information");
        return mv;
    }

    @Auth
    @ResponseBody
    @RequestMapping(value = "/info/modify",method = RequestMethod.POST)
    public Result ModifyUser(@RequestParam(value = "avatar", required = false) MultipartFile file, MultipartHttpServletRequest request) throws IOException {
        Long id = (Long) request.getAttribute(JwtUtil.ID_KEY);
        User userById = userService.getUserById(id);
        boolean need=false;
        if(Objects.nonNull(file)){
            String path;// 文件路径
            String type;//文件类型
            String fileName=file.getOriginalFilename();// 文件原名
            logger.info("上传的源文件名称:"+fileName);
            type= fileName.contains(".") ?fileName.substring(fileName.lastIndexOf(".")+1):null;
            if(type != null){
                if("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())){
                    // 项目在容器中实际发布运行的根路径
                    String realPath= System.getProperty("java.io.tmpdir");
                    // 自定义的文件名称
                    String trueFileName= System.currentTimeMillis() +fileName;
                    // 设置存放图片文件的路径
                    path=realPath+"/upload/"+trueFileName;
                    logger.info("图片存储的路径是:"+path);
                    // 转存文件到指定的路径
                    file.transferTo(new File(path));
                    userById.setAvatar("/resources/"+trueFileName);
                }else {
                    return ResultUtil.getError("文件类型错误");
                }
            }else{
                return ResultUtil.getError("文件类型为空");
            }
        }

        if (!request.getParameter("relname").equals(userById.getRelname())) {
            userById.setRelname(request.getParameter("relname"));
            need = true;
        }
        if (!request.getParameter("sex").equals(userById.getSex())) {
            userById.setSex(Byte.valueOf(request.getParameter("sex")));
            need = true;
        }
        if (!request.getParameter("job").equals(userById.getJob())) {
            userById.setJob(request.getParameter("job"));
            need = true;
        }
        if (!request.getParameter("address").equals(userById.getAddress())) {
            userById.setAddress(request.getParameter("address"));
            need = true;
        }
        if (need) {
            userService.updateUserById(userById);
        }
        return ResultUtil.getSuccess();
    }

    @Auth
    @GetMapping("/ws")
    public ModelAndView ws(ModelAndView mv, HttpServletRequest request) {
        Long id = (Long) request.getAttribute(JwtUtil.ID_KEY);
        User userById = userService.getUserById(id);
        mv.addObject("user", userById);
        mv.setViewName("user/websocket-test");
        return mv;
    }

}
