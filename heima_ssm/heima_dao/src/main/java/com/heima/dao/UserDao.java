package com.heima.dao;

import com.heima.ssm.domain.Role;
import com.heima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "roles",column = "id",
                    many = @Many(select = "com.heima.dao.RoleDao.findRoleByUserId"))
    })
    @Select("select * from users where username=#{username}")
    public UserInfo findByUsername(String username) throws Exception;

    //查询所有用户操作
    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    //用户添加
    @Select("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    public void save(UserInfo userInfo) throws Exception;

    //用户详情查询
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roles",column = "id",many = @Many(select = "com.heima.dao.RoleDao.findRoleByUserId"))
    })
    UserInfo findById(String id) throws Exception;

    //第五天 2.根据用户id查询可以添加的角色
    @Select("select * from role where id not in (select roleId from users_role where userId = #{userId})")
    List<Role> findOtherRoles(String userId);

    //第五天 给用户添加角色
    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
