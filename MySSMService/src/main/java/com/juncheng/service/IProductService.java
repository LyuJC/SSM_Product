package com.juncheng.service;

import com.juncheng.domain.Product;

import java.util.List;

public interface IProductService {
    /**
     * 查询所有产品的信息
     * @return
     */
    public List<Product> findAll(int page,int size);

    public void saveProduct(Product product);
}
