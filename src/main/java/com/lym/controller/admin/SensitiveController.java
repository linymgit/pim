package com.lym.controller.admin;

import com.github.pagehelper.PageInfo;
import com.lym.anno.Auth;
import com.lym.entity.Page;
import com.lym.entity.Result;
import com.lym.entity.Sensitive;
import com.lym.service.SensitiveService;
import com.lym.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Date 2020/2/7
 * @auth linyimin
 * @Desc
 **/
@Controller
@RequestMapping("/admin2020/sensitive")
public class SensitiveController {

    @Autowired
    private SensitiveService sensitiveService;

    @GetMapping("/list")
    public ModelAndView getList(ModelAndView mv) {
        mv.setViewName("admin/sensitive/sensitive");
        return mv;
    }

    @Auth(flag = 1)
    @PostMapping("/list")
    @ResponseBody
    public Result postList(@RequestBody Page page) {
        final PageInfo<Sensitive> sensitives = sensitiveService.sensitives(page);
        return ResultUtil.getSuccess(sensitives);
    }

    @Auth(flag = 1)
    @PostMapping("/add")
    @ResponseBody
    public Result add(@RequestBody Sensitive sensitive) {
        return ResultUtil.getSuccess(sensitiveService.addSensitive(sensitive));
    }

    @Auth(flag = 1)
    @PostMapping("/remove")
    @ResponseBody
    public Result remove(Integer id) {
        return ResultUtil.getSuccess(sensitiveService.removeRelation(id));
    }
}
