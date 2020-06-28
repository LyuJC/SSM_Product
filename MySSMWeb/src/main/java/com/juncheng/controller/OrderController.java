package com.juncheng.controller;


import com.github.pagehelper.PageInfo;
import com.juncheng.domain.Order;
import com.juncheng.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    private IOrderService orderService;

    @Autowired
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 查询全部订单（未分页）
     * @return
     */
/*    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView modelAndView=new ModelAndView();
       List<Order> orderList= orderService.findAll();
       modelAndView.addObject("orders",orderList);
       modelAndView.setViewName("orders-list");

       return modelAndView;
    }*/
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "2")Integer size){
        ModelAndView modelAndView=new ModelAndView();
        List<Order> orderList= orderService.findAll(page,size);

        //PageInfo 可以看成一个分页Bean
        PageInfo pageInfo=new PageInfo(orderList);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("orders-page-list");

        return modelAndView;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) Integer id){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("orders",orderService.findById(id));
        modelAndView.setViewName("orders-show");
        return modelAndView;
    }
}
