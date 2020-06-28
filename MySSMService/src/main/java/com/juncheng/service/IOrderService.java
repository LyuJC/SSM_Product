package com.juncheng.service;

import com.juncheng.domain.Order;

import java.util.List;

public interface IOrderService {

    public List<Order> findAll(int page,int size);


    public Order findById(int id);
}
