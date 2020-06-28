package com.juncheng.controller;

import com.github.pagehelper.PageInfo;
import com.juncheng.domain.Permission;
import com.juncheng.service.IPermissionService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    private IPermissionService permissionService;

    @Autowired
    public PermissionController(IPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "2")Integer size){
        ModelAndView modelAndView=new ModelAndView();
        List<Permission> permissionList=permissionService.findAll(page, size);

        PageInfo pageInfo=new PageInfo(permissionList);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("permission-page-list");
        return modelAndView;
    }

    @RequestMapping("/save.do")
    public String save(Permission permission){
        permissionService.savePermission(permission);
        return "redirect:/permission/findAll.do";
    }

    @RequestMapping("/deletePermission.do")
    public String deletePermission(Integer id){
        permissionService.deletePermission(id);
        return "redirect:/permission/findAll.do";
    }

}
