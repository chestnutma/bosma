package com.heima.service;

import com.heima.ssm.domain.Role;
import com.heima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    //查询所有用户操作
    List<UserInfo> findAll() throws Exception;

    //用户添加
    public void save(UserInfo userInfo) throws Exception;

    //根据用户id查询出所有对应的角色
    UserInfo findById(String id) throws Exception;

    //第五天 2.根据用户id查询可以添加的角色
    List<Role> findOtherRoles(String userId);

    //第五天 给用户添加角色
    public void addRoleToUser(String userId, String[] roleIds);
}
