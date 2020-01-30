package com.lym.controller.user;

import com.lym.anno.Auth;
import com.lym.entity.Income;
import com.lym.entity.Output;
import com.lym.entity.Result;
import com.lym.entity.param.IncomeListParam;
import com.lym.entity.param.OutputListParam;
import com.lym.service.IncomeService;
import com.lym.service.OutputService;
import com.lym.utils.JwtUtil;
import com.lym.utils.ResultUtil;
import com.lym.utils.SnowFlakeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Date 2020/1/15
 * @auth linyimin
 * @Desc 用户资产controller
 **/
@Controller
@RequestMapping("/user/assets")
public class UserAssetsController {

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private OutputService outputService;

    @GetMapping("/income")
    public ModelAndView income(ModelAndView mv) {
        mv.setViewName("user/income/income");
        return mv;
    }

    @GetMapping("/output")
    public ModelAndView output(ModelAndView mv) {
        mv.setViewName("user/output/output");
        return mv;
    }

    @Auth
    @PostMapping("/income")
    @ResponseBody
    public Result postIncomes(HttpServletRequest request, @RequestBody IncomeListParam param) {
        Long userId = (Long) request.getAttribute(JwtUtil.ID_KEY);
        param.setUserId(userId);
        return ResultUtil.getSuccess(incomeService.incomes(param));
    }

    @Auth
    @PostMapping("/output")
    @ResponseBody
    public Result postOutput(HttpServletRequest request, @RequestBody OutputListParam param) {
        Long userId = (Long) request.getAttribute(JwtUtil.ID_KEY);
        param.setUserId(userId);
        return ResultUtil.getSuccess(outputService.outputs(param));
    }


    @Auth
    @PostMapping("/income/remove")
    @ResponseBody
    public Result removeIncome(Long id) {
        return ResultUtil.getSuccess(incomeService.removeIncome(id));
    }

    @Auth
    @PostMapping("/output/remove")
    @ResponseBody
    public Result removeOutput(Long id) {
        return ResultUtil.getSuccess(outputService.removeOutput(id));
    }

    @Auth
    @PostMapping("/income/update")
    @ResponseBody
    public Result upateIncome(HttpServletRequest request, @RequestBody Income income) {
        Long userId = (Long) request.getAttribute(JwtUtil.ID_KEY);
        income.setUserid(userId);
        return ResultUtil.getSuccess(incomeService.editIncome(income));
    }

    @Auth
    @PostMapping("/output/update")
    @ResponseBody
    public Result updateOutput(HttpServletRequest request, @RequestBody Output output) {
        Long userId = (Long) request.getAttribute(JwtUtil.ID_KEY);
        output.setUserid(userId);
        return ResultUtil.getSuccess(outputService.editOutput(output));
    }

    @Auth
    @PostMapping("/income/add")
    @ResponseBody
    public Result addIncome(HttpServletRequest request, @RequestBody Income income) {
        Long userId = (Long) request.getAttribute(JwtUtil.ID_KEY);
        income.setUserid(userId);
        income.setId(SnowFlakeUtil.nextId());
        return ResultUtil.getSuccess(incomeService.addIncome(income));
    }

    @Auth
    @PostMapping("/output/add")
    @ResponseBody
    public Result addOutput(HttpServletRequest request, @RequestBody Output output) {
        Long userId = (Long) request.getAttribute(JwtUtil.ID_KEY);
        output.setUserid(userId);
        output.setId(SnowFlakeUtil.nextId());
        return ResultUtil.getSuccess(outputService.addOutput(output));
    }

}
