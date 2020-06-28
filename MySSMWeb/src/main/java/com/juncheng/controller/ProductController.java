package com.juncheng.controller;

import com.github.pagehelper.PageInfo;
import com.juncheng.domain.Product;
import com.juncheng.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

 /*   *//**
     * 查询所有
     * @return
     *//*
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView modelAndView=new ModelAndView();
        List<Product> products=productService.findAll();

*//*
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        for(Product product:products){
                product.setDepartureTimeStr(sdf.format(product.getDepartureTime()));
                product.setProductStatusStr(Integer.toString(product.getProductStatus()));
        }
*//*

        modelAndView.addObject("productList",products);
        modelAndView.setViewName("product-list");

        return modelAndView;
    }*/


    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/findAll.do")
    @RolesAllowed({"ADMIN","USER"})
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "2")Integer size){
        ModelAndView modelAndView=new ModelAndView();
        List<Product> products=productService.findAll(page, size);


        PageInfo pageInfo=new PageInfo(products);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("product-page-list");

        return modelAndView;
    }

    /**
     * 添加新产品
     * @param product
     */
    @RequestMapping("/save.do")
    public String save(Product product){
        productService.saveProduct(product);
        return "redirect:/product/findAll.do";
    }




}
