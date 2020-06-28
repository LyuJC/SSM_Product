package com.juncheng.controller;

import com.github.pagehelper.PageInfo;
import com.juncheng.domain.SysLog;
import com.juncheng.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    private ISysLogService sysLogService;

    @Autowired
    public SysLogController(ISysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "2")Integer size){
        ModelAndView modelAndView=new ModelAndView();
        List<SysLog> sysLogs=sysLogService.findAll(page,size);

        PageInfo pageInfo=new PageInfo(sysLogs);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("syslog-page-list");

        return modelAndView;
    }
}
