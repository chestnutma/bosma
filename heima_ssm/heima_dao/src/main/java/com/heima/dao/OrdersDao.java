package com.heima.dao;

import com.heima.ssm.domain.Orders;
import com.heima.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface OrdersDao {
    @Select("select * from orders")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "product",javaType = Product.class,one = @One(select = "com.heima.dao.Product.findById")),
    })
    public List<Orders> findAll() throws Exception;

    //多表操作
    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "product",column = "product",one = @One(select = "com.heima.dao.ProductDao.findById")),
            @Result(property = "member",column = "member",one = @One(select = "com.heima.dao.MemberDao.findById")),
            @Result(property = "travellers",column = "id",many = @Many(select = "com.heima.dao.TravellerDao.findByOrdersId"))
    })
    public Orders findById(String ordersId) throws Exception;
}
