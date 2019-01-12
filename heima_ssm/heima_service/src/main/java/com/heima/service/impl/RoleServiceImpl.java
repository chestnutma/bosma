package com.heima.service.impl;

import com.heima.dao.RoleDao;
import com.heima.service.RoleService;
import com.heima.ssm.domain.Permission;
import com.heima.ssm.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    //查询所有角色
    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    //角色添加
    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    //删除角色
    @Override
    public void deleteById(String roleId) throws Exception {
        //从user_role表中删除
        roleDao.deleteFromUser_RoleByRole(roleId);
        //从role_permission表中删除
        roleDao.deleteFromRole_PermissionByRoleId(roleId);
        //从role表中删除
        roleDao.deleteRoleById(roleId);
    }

    //第五天 根据roleId查询role
    @Override
    public Role findById(String roleId) {
        return roleDao.findById(roleId);
    }

    //第五天 根据roleId查询可以添加的权限
    @Override
    public List<Permission> findOtherPermissions(String roleId) {
        return roleDao.findOtherPermissions(roleId);
    }

    //第五天 给角色添加权限
    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
