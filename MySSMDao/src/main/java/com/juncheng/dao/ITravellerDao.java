package com.juncheng.dao;

import com.juncheng.domain.Traveller;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {
   /* @Select("select t.* from traveller as t,order_traveller as o2t where o2t.orderId=#{oid} and t.id =o2t.travellerId ")*/
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId =#{oid})")
    public List<Traveller> findByOid(@Param("oid") int id);
}
