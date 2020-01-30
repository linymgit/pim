package com.lym.controller.user;


import com.lym.anno.Auth;
import com.lym.entity.FileLog;
import com.lym.entity.Result;
import com.lym.entity.param.FileLogListParam;
import com.lym.service.FileManageService;
import com.lym.utils.JwtUtil;
import com.lym.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Date 2020/1/15
 * @auth linyimin
 * @Desc 文件中心
 **/
@Controller
@RequestMapping("/user/file")
public class UserFileController {

    @Autowired
    private FileManageService fileManageService;

    @GetMapping("/list")
    public ModelAndView getList(ModelAndView mv) {
        mv.setViewName("user/file/file-records");
        return mv;
    }

    @GetMapping("/upload")
    public ModelAndView getUpload(ModelAndView mv) {
        mv.setViewName("user/file/file-upload");
        return mv;
    }

    @Auth
    @PostMapping("/list")
    @ResponseBody
    public Result postList(HttpServletRequest request, @RequestBody FileLogListParam param) {
        Long userId = (Long) request.getAttribute(JwtUtil.ID_KEY);
        param.setUserId(userId);
        return ResultUtil.getSuccess(fileManageService.records(param));
    }


    @Auth
    @PostMapping("/upload")
    @ResponseBody
    public Result upload(HttpServletRequest httpServletRequest, @RequestParam(value = "file") MultipartFile file) {
        Long userId = (Long) httpServletRequest.getAttribute(JwtUtil.ID_KEY);
        return fileManageService.upload(file, userId);
    }

    @Auth
    @PostMapping("/remove")
    @ResponseBody
    public Result upload(HttpServletRequest httpServletRequest, Long id) {
        Long userId = (Long) httpServletRequest.getAttribute(JwtUtil.ID_KEY);
        FileLog fileLog = new FileLog();
        fileLog.setUserid(userId);
        fileLog.setId(id);
        return fileManageService.removeFile(fileLog);
    }
}
