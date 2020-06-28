package com.juncheng.controller;

import com.github.pagehelper.PageInfo;
import com.juncheng.domain.Permission;
import com.juncheng.domain.Role;
import com.juncheng.service.IPermissionService;
import com.juncheng.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    private IRoleService roleService;
    private IPermissionService permissionService;

    @Autowired
    public RoleController(IRoleService roleService, IPermissionService permissionService) {
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "2")Integer size){
        ModelAndView modelAndView=new ModelAndView();
        List<Role> roleList=roleService.findAll(page,size);

        PageInfo pageInfo=new PageInfo(roleList);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("role-page-list");


        return modelAndView;
    }

    @RequestMapping("/save.do")
    public String save(Role role){
        roleService.saveRole(role);
        return "redirect:/role/findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id" ,required = true) Integer id){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("role",roleService.findById(id));
        modelAndView.setViewName("role-show");
        return modelAndView;
    }


    @RequestMapping("/deleteRole.do")
    public String deleteRole(Integer id){

        roleService.deleteRole(id);
        return "redirect:/role/findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(Integer id){
        ModelAndView modelAndView=new ModelAndView();
        Role role=roleService.findById(id);
        List<Permission> permissionList =permissionService.findOtherPermission(id);

        modelAndView.addObject("role",role);
        modelAndView.addObject("permissionList",permissionList);
        modelAndView.setViewName("role-permission-add");

        return modelAndView;
    }

    @RequestMapping("/addPermissionsToRole")
    public String addPermissionsToRole(Integer roleId,Integer[] ids){
        System.out.println("============"+roleId+"=========="+ids[0]);
        roleService.addPermissionsToRole(roleId,ids);
        return "redirect:/role/findAll.do";
    }

}
