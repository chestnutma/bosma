package com.heima.service;

import com.heima.ssm.domain.Product;

import java.util.List;

public interface ProductService {
    //查询所有的产品信息
    public List<Product> findAll() throws Exception;

    void save(Product product) throws Exception;
}
