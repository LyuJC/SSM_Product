package com.juncheng.dao;

import com.juncheng.domain.Role;
import com.juncheng.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface IUserDao {

    @Select("select * from users where username=#{name}")
    @Results({
            @Result( id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.juncheng.dao.IRoleDao.findByUid"))

    })
    public UserInfo findByUsername(String name);


    @Select("select * from users where id=#{id}")
    @Results({
            @Result( id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.juncheng.dao.IRoleDao.findDetailRoleByUid"))

    })
    public UserInfo findById(int id);

    @Select("select * from users ")
    public List<UserInfo> findAll();


    @Insert("insert into users(email,username,password,phoneNum,status) values(#{user.email},#{user.username},#{user.password},#{user.phoneNum},#{user.status})")
    public void saveUser(@Param("user") UserInfo userInfo);

    @Delete("delete from user_role where userId=#{id} ")
    public void deleteFromUser_RoleByUid(int id);

    @Delete("delete from users where id= #{id}")
    public void deleteUser(int id);

    @Insert("INSERT INTO user_role values(#{userId},#{roleId}) ")
    public void addRoleToUser(@Param("userId") int userId, @Param("roleId") int roleId);


}
