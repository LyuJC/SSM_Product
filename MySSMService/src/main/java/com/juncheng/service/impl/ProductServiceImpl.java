package com.juncheng.service.impl;

import com.github.pagehelper.PageHelper;
import com.juncheng.dao.IProductDao;
import com.juncheng.domain.Product;
import com.juncheng.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("productService")
@Transactional
public class ProductServiceImpl implements IProductService {

    private IProductDao productDao;

    @Autowired
    public ProductServiceImpl(IProductDao productDao) {
        this.productDao = productDao;
    }

    public ProductServiceImpl() {
    }

    public List<Product> findAll(int page ,int size) {

        PageHelper.startPage(page,size);
        return productDao.findAll();
        }

    public void saveProduct(Product product) {
        productDao.saveProduct(product);

    }
}


