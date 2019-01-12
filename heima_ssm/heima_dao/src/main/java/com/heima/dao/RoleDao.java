package com.heima.dao;

import com.heima.ssm.domain.Permission;
import com.heima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {
    //根据用户id查询出所有对应的角色
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "permissions",column = "id",many = @Many(select = "com.heima.dao.PermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;


    //查询所有角色
    @Select("select * from role")
    List<Role> findAll() throws Exception;

    //角色添加
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    //从user_role表中删除
    @Delete("delete from users_role where roleId = #{roleId}")
    void deleteFromUser_RoleByRole(String roleId);

    //从role_permission表中删除
    @Delete("delete from role_permission where roleId = #{roleId}")
    void deleteFromRole_PermissionByRoleId(String roleId);

    //从role表中删除
    @Delete("delete from role where id = #{roleId}")
    void deleteRoleById(String roleId);

    //第五天 根据roleId查询role
    @Select("select * from role where id = #{roleId}")
    Role findById(String roleId);

    //第五天 根据roleId查询可以添加的权限
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermissions(String roleId);

    //第五天 给角色添加权限
    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId);
}
