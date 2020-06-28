package com.juncheng.service.impl;

import com.github.pagehelper.PageHelper;
import com.juncheng.dao.IOrderDao;
import com.juncheng.domain.Order;
import com.juncheng.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements IOrderService {
    private IOrderDao orderDao;

    @Autowired
    public OrderServiceImpl(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<Order> findAll(int page,int size) {
        //pageNum是页码值，pageSize是每页显示条数，必须紧跟在真正查询语句的前面
        PageHelper.startPage(page,size);
        return orderDao.findAll();
    }

    @Override
    public Order findById(int id) {
        return orderDao.findById(id);
    }
}
