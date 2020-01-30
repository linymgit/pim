package com.lym.controller.user;

import com.lym.anno.Auth;
import com.lym.entity.*;
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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

    @GetMapping("/income/barchart")
    public ModelAndView barchart(HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("user/income/bar-chart");
        return mv;
    }

    @GetMapping("/income/pieChart")
    public ModelAndView pieChart(ModelAndView mv) {
        mv.setViewName("user/income/pie-chart");
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
    @PostMapping("/barData")
    @ResponseBody
    public Result barData(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute(JwtUtil.ID_KEY);
        List<Income> incomes = incomeService.incomes(userId);
        IncomeBarData incomeBarData = new IncomeBarData();
        List<String> dataAxis = new ArrayList<>();
        List<BigDecimal> data = new ArrayList<>();
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        incomes.stream().sorted((o1, o2) -> (int) (o2.getIncomeTime().getTime()-o1.getIncomeTime().getTime())).forEach(income -> {
            dataAxis.add(sdf.format(income.getIncomeTime())+"("+income.getType()+")");
            data.add(income.getValue());
        });
        Optional<BigDecimal> max = incomes.stream().map(Income::getValue).max(BigDecimal::compareTo);
        BigDecimal yMax = max.get();
        incomeBarData.setDataAxis(dataAxis);
        incomeBarData.setData(data);
        incomeBarData.setyMax(yMax);
        return ResultUtil.getSuccess(incomeBarData);
    }

    @Auth
    @PostMapping("/pieData")
    @ResponseBody
    public Result pieData(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute(JwtUtil.ID_KEY);
        List<Income> incomes = incomeService.incomes(userId);
        IncomePieData incomePieData = new IncomePieData();
        List<String> types = incomes.stream().map(Income::getType).collect(Collectors.toList());
        List<IncomeVN> values = incomes.stream().map(income -> {
            IncomeVN incomeVN = new IncomeVN();
            incomeVN.setName(income.getType());
            incomeVN.setValue(income.getValue());
            return incomeVN;
        }).collect(Collectors.toList());
        incomePieData.setTypes(types);
        incomePieData.setValues(values);
        return ResultUtil.getSuccess(incomePieData);
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
