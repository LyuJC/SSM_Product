package com.juncheng.service;

import com.juncheng.domain.Permission;

import java.util.List;

public interface IPermissionService {
    public List<Permission> findAll(int page, int size);

    public void savePermission(Permission permission);

    public List<Permission> findOtherPermission(int rid);

    public void deletePermission(int id);
}
