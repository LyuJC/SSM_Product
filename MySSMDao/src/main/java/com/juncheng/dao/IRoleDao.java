package com.juncheng.dao;

import com.juncheng.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {


    @Select("select * from role where id in (select roleId from user_role where userId=#{uid}) ")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.juncheng.dao.IPermissionDao.findByRid"))
    })
    public List<Role> findDetailRoleByUid(int uid);


    @Select("select * from role where id in (select roleId from user_role where userId=#{uid}) ")
    public List<Role> findByUid(int uid);


    @Select("select * from role ")
    public List<Role> findAll();

    @Insert("insert into role(roleName,roleDesc) values(#{role.roleName},#{role.roleDesc}) ")
    public void saveRole(@Param("role") Role iRole);


    @Select("select * from role where id =#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.juncheng.dao.IPermissionDao.findByRid"))
    })
    public Role findById(int id);

    @Select("select * from role where id not in(select roleId from user_role where userId=#{id})")
    public List<Role> findOtherRole(int id);


    @Delete("delete from role_permission where roleId=#{id} ")
    public void deleteRole_PermissionByRid(int id);

    @Delete("delete from user_role where roleId=#{id} ")
    public void deleteUser_RoleByRid(int id);

    @Delete("delete from role where id=#{id}")
    public void deleteById(int id);

    @Insert("insert into role_permission values(#{rid},#{pid})")
    public void addPermissionToRole(@Param("rid") int roleId,@Param("pid") int permissionId);


}
