package com.heima.service.impl;

import com.github.pagehelper.PageHelper;
import com.heima.dao.OrdersDao;
import com.heima.service.OrdersService;
import com.heima.ssm.domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page,int size) throws Exception {
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        PageHelper.startPage(page,size  );
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String ordersId) throws Exception{
        return ordersDao.findById(ordersId);
    }
}
