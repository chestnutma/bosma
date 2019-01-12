package com.heima.service;

import com.heima.ssm.domain.Permission;

import java.util.List;

public interface PermissionService {
    //查询所有资源权限
    public List<Permission> findAll() throws Exception;

    //添加资源权限
    void save(Permission permission) throws Exception;
}
