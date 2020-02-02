package com.lym.controller.admin;

import com.lym.anno.Auth;
import com.lym.entity.FileLog;
import com.lym.entity.Result;
import com.lym.entity.param.FileLogListParam;
import com.lym.service.FileManageService;
import com.lym.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Date 2020/2/1
 * @auth linyimin
 * @Desc
 **/
@Controller
@RequestMapping("/admin2020/file")
public class FileManageController {

    @Autowired
    private FileManageService fileManageService;

    @GetMapping("/list")
    public ModelAndView getList(ModelAndView mv) {
        mv.setViewName("admin/file/file-records");
        return mv;
    }

    @Auth(flag = 1)
    @PostMapping("/list")
    @ResponseBody
    public Result postList(@RequestBody FileLogListParam param) {
        return ResultUtil.getSuccess(fileManageService.records(param));
    }

    @Auth(flag = 1)
    @PostMapping("/remove")
    @ResponseBody
    public Result upload(HttpServletRequest httpServletRequest, Long id) {
        FileLog fileLog = new FileLog();
        fileLog.setId(id);
        return fileManageService.removeFile(fileLog);
    }
}
