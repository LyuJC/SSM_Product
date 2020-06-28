package com.juncheng.service;

import com.juncheng.domain.Role;

import java.util.List;

public interface IRoleService {
    public List<Role> findAll(int page,int size);

    public void saveRole(Role role);
    public Role findById(int id);

    public List<Role> findOtherRole(int id);

    public void deleteRole(int id);

    public void addPermissionsToRole(int roleId,Integer[] ids);

}
