package com.juncheng.dao;

import com.juncheng.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{rid}) ")
    public List<Permission> findByRid(int rid);

    @Select("select * from permission ")
    public List<Permission> findAll();


    @Insert("insert into permission(permissionName, url) values(#{permissionName},#{url}) ")
    public void savePermission(Permission permission);


    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{rid}) ")
    public List<Permission> findOtherPermission(int rid);

    @Delete("delete from role_permission where permissionId =#{id} ")
    public void deleteRole_PermissionByPid(int id);

    @Delete("delete from permission where id =#{id } ")
    public void deletePermission(int id);

}
