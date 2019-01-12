package com.heima.service.impl;

import com.heima.dao.PermissionDao;
import com.heima.service.PermissionService;
import com.heima.ssm.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    //查询所有资源权限
    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    //添加资源权限
    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }
}
