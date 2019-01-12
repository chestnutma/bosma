package com.heima.service;

import com.heima.ssm.domain.Permission;
import com.heima.ssm.domain.Role;

import java.util.List;

public interface RoleService {
    //查询所有角色
    public List<Role> findAll() throws Exception;

    //角色添加
    public void save(Role role) throws Exception;

    //删除角色
    void deleteById(String roleId) throws Exception;

    //第五天 根据roleId查询role
    Role findById(String roleId);

    //第五天 根据roleId查询可以添加的权限
    List<Permission> findOtherPermissions(String roleId);

    //第五天 给角色添加权限
    void addPermissionToRole(String roleId, String[] permissionIds);
}
