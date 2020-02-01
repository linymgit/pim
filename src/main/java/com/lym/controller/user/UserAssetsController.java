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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public ModelAndView incomeBarchart(HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("user/income/bar-chart");
        return mv;
    }

    @GetMapping("/income/pieChart")
    public ModelAndView incomePieChart(ModelAndView mv) {
        mv.setViewName("user/income/pie-chart");
        return mv;
    }

    @GetMapping("/output/barchart")
    public ModelAndView outputBarchart(HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("user/output/bar-chart");
        return mv;
    }

    @GetMapping("/output/pieChart")
    public ModelAndView outputPieChart(ModelAndView mv) {
        mv.setViewName("user/output/pie-chart");
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
        AssetsBarData incomeBarData = new AssetsBarData();
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
        AssertsPieData incomePieData = new AssertsPieData();
        List<String> types = incomes.stream().map(Income::getType).distinct().collect(Collectors.toList());
        List<AssertsVN> values = incomes.stream().map(income -> {
            AssertsVN incomeVN = new AssertsVN();
            incomeVN.setName(income.getType());
            incomeVN.setValue(income.getValue());
            return incomeVN;
        }).collect(Collectors.toList());
        incomePieData.setTypes(types);
        incomePieData.setValues(values);
        return ResultUtil.getSuccess(incomePieData);
    }

    @Auth
    @PostMapping("/output/barData")
    @ResponseBody
    public Result outputBarData(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute(JwtUtil.ID_KEY);
        List<Output> outputs = outputService.outputs(userId);
        AssetsBarData outputBarData = new AssetsBarData();
        List<String> dataAxis = new ArrayList<>();
        List<BigDecimal> data = new ArrayList<>();
        BigDecimal yMax = BigDecimal.valueOf(0L);
        outputs.stream().collect(Collectors.groupingBy(Output::getType)).forEach((s, goutputs) -> {
             dataAxis.add(s);
            double sum = goutputs.stream().map(Output::getCost).mapToDouble(BigDecimal::doubleValue).sum();
            data.add(BigDecimal.valueOf(sum));
        });
        Optional<BigDecimal> max = data.stream().max(BigDecimal::compareTo);
        if (max.isPresent()) {
            yMax = max.get();
        }
        outputBarData.setDataAxis(dataAxis);
        outputBarData.setData(data);
        outputBarData.setyMax(yMax);
        return ResultUtil.getSuccess(outputBarData);
    }

    @Auth
    @PostMapping("/output/pieData")
    @ResponseBody
    public Result outputPieData(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute(JwtUtil.ID_KEY);
        List<Output> outputs = outputService.outputs(userId);
        AssertsPieData outputPieData = new AssertsPieData();
        List<String> types = new ArrayList<>();
        List<AssertsVN> values = new ArrayList<>();
        outputs.stream().collect(Collectors.groupingBy(Output::getType)).forEach((s, goutputs) -> {
            types.add(s);
            double sum = goutputs.stream().map(Output::getCost).mapToDouble(BigDecimal::doubleValue).sum();
            AssertsVN assertsVN = new AssertsVN();
            assertsVN.setName(s);
            assertsVN.setValue(BigDecimal.valueOf(sum));
            values.add(assertsVN);
        });
        outputPieData.setTypes(types);
        outputPieData.setValues(values);
        return ResultUtil.getSuccess(outputPieData);
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
