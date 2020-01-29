package com.lym.controller.user;

import com.github.pagehelper.PageInfo;
import com.lym.anno.Auth;
import com.lym.entity.Result;
import com.lym.entity.Schedule;
import com.lym.entity.param.ScheduleListParam;
import com.lym.service.ScheduleRemindService;
import com.lym.service.ScheduleService;
import com.lym.task.RemindItem;
import com.lym.utils.JwtUtil;
import com.lym.utils.ResultUtil;
import com.lym.utils.SnowFlakeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

/**
 * @Date 2020/1/15
 * @auth linyimin
 * @Desc 日程管理controller
 **/
@Controller
@RequestMapping("/user/schedule")
public class UserScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScheduleRemindService scheduleRemindService;

    @Auth
    @GetMapping("/list")
    public ModelAndView list(ModelAndView mv, HttpServletRequest request) {
        mv.setViewName("user/schedule");
        return mv;
    }

    @GetMapping("/add")
    public ModelAndView add(ModelAndView mv) {
        mv.setViewName("user/addSchedule");
        return mv;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(ModelAndView mv, @PathVariable Long id) {
        mv.addObject("id", id);
        mv.setViewName("user/editSchedule");
        return mv;
    }

    @Auth
    @PostMapping("/get")
    @ResponseBody
    public Result Get(Long id, HttpServletRequest request) {
        return ResultUtil.getSuccess(scheduleService.get(id));
    }

    @Auth
    @PostMapping("/edit")
    @ResponseBody
    public Result editPost(@RequestBody Schedule schedule, HttpServletRequest request) {
        scheduleService.edit(schedule);
        return ResultUtil.getSuccess();
    }

    @Auth
    @PostMapping("/add")
    @ResponseBody
    public Result addPost(@RequestBody Schedule schedule, HttpServletRequest request) {
        Long id = (Long) request.getAttribute(JwtUtil.ID_KEY);
        schedule.setId(SnowFlakeUtil.nextId());
        schedule.setUserId(id);
        schedule.setRemindCount(0);
        schedule.setEndTime(schedule.getStartTime());
        int add = scheduleService.add(schedule);
        if (add > 0) {
            Date startTime = schedule.getStartTime();
            long dur = startTime.getTime() - System.currentTimeMillis();
            if (dur > 0 && schedule.getRemindSum() > 0) {
                scheduleRemindService.addRemindItem(new RemindItem(dur, schedule));
            }
            return ResultUtil.getSuccess();
        }
        return ResultUtil.getError("新增日程失败！！！");
    }

    @Auth
    @PostMapping("/remind/cancel")
    @ResponseBody
    public Result remindCancel(Long id, HttpServletRequest request) {
        return ResultUtil.getSuccess(scheduleRemindService.removeRemindItem(id));
    }

    @Auth
    @PostMapping("/list")
    @ResponseBody
    public Result listPost(HttpServletRequest request, @RequestBody ScheduleListParam param) {
        Long id = (Long) request.getAttribute(JwtUtil.ID_KEY);
        param.setUserId(id);
        PageInfo<Schedule> pageInfo = scheduleService.list(param);
        return ResultUtil.getSuccess(pageInfo);
    }


    @Auth
    @PostMapping("/isRepeat")
    @ResponseBody
    public Result isRepeat(HttpServletRequest request, String dateTime) {
        Long id = (Long) request.getAttribute(JwtUtil.ID_KEY);
        if (Objects.isNull(dateTime) || dateTime.equals("")) {
            return ResultUtil.getSuccess(true);
        }
        ScheduleListParam scheduleListParam = new ScheduleListParam();
        scheduleListParam.setDateTime(dateTime);
        scheduleListParam.setUserId(id);
        PageInfo<Schedule> pageInfo = scheduleService.list(scheduleListParam);
        if (pageInfo.getSize() > 0) {
            return ResultUtil.getSuccess(true);
        }
        return ResultUtil.getSuccess(false);
    }

    @Auth
    @RequestMapping(value = "/del/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result addUser4(@PathVariable Long id, ModelAndView mv) {
        scheduleService.del(id);
        return ResultUtil.getSuccess();
    }
}
