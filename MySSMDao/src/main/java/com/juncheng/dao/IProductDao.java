package com.juncheng.dao;

import com.juncheng.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {

    /**
     * 查询所有产品的信息
     * @return
     */
    @Select("select * from product ")
    public List<Product> findAll();

    /**
     * 根据ID查询
     * @return
     */
    @Select("select * from product where id = #{pid}")
    public Product findById(@Param("pid") int pid);


    /**
     * 保存产品信息
     * @param product
     */
    @Insert("insert into product(productNum,productName, cityName, departureTime, productPrice, productDesc, productStatus) values(#{product.productNum},#{product.productName}, #{product.cityName}, #{product.departureTime}, #{product.productPrice}, #{product.productDesc}, #{product.productStatus}) ")
    public void saveProduct(@Param("product") Product product);
}
