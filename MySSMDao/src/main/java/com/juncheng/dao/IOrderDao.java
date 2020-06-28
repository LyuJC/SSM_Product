package com.juncheng.dao;

import com.juncheng.domain.Member;
import com.juncheng.domain.Order;
import com.juncheng.domain.Product;
import com.juncheng.domain.Traveller;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrderDao {


    /**
     * 查询订单及其相关信息
     * @return
     */
    @Select("select * from orders ")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product", column = "productId",javaType = Product.class,one = @One(select = "com.juncheng.dao.IProductDao.findById"))
    })
    public List<Order> findAll();



    @Select("Select * from orders where id=#{oid}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product", column = "productId",javaType = Product.class,one = @One(select = "com.juncheng.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one=@One(select = "com.juncheng.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,many = @Many(select = "com.juncheng.dao.ITravellerDao.findByOid"))
    }
    )
    public Order findById(@Param("oid") int id);

}
