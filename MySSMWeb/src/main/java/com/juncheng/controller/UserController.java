package com.juncheng.controller;

import com.github.pagehelper.PageInfo;
import com.juncheng.domain.Role;
import com.juncheng.domain.UserInfo;
import com.juncheng.service.IRoleService;
import com.juncheng.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private IUserService userService;
    private IRoleService roleService;

    @Autowired
    public UserController(IUserService userService, IRoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "2")Integer size){
        ModelAndView modelAndView=new ModelAndView();
        List<UserInfo> users= userService.findAll(page, size);

        PageInfo pageInfo=new PageInfo(users);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("user-page-list");
        return modelAndView;
    }

    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username=='吕俊成'")
    public String saveUser(UserInfo userInfo){
        userService.saveUser(userInfo);
        return "redirect:/user/findAll.do";
    }


    @RequestMapping("/findById.do")
    public ModelAndView findById(Integer id){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("user",userService.findById(id));
        modelAndView.setViewName("user-show");
        return modelAndView;
    }

    @RequestMapping("/deleteById.do")
    public String deleteById(Integer id){
        userService.deleteUser(id);
        return "redirect:/user/findAll.do";
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(Integer id){
    ModelAndView modelAndView=new ModelAndView();
    UserInfo user=userService.findById(id);
    List<Role> roles=roleService.findOtherRole(id);
    for (Role role:roles){
        System.out.println("======================"+role.getRoleName()+"=====================");
    }
    modelAndView.addObject("user",user);
    modelAndView.addObject("roleList",roles);

    modelAndView.setViewName("user-role-add");
    return modelAndView;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(int userId,Integer[] ids){
        userService.addRoleToUser(userId,ids);
        return "redirect:/user/findAll.do";
    }

}
