package com.juncheng.service.impl;

import com.github.pagehelper.PageHelper;
import com.juncheng.dao.IPermissionDao;
import com.juncheng.domain.Permission;
import com.juncheng.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {
   private IPermissionDao permissionDao;

   @Autowired
    public PermissionServiceImpl(IPermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    @Override
    public List<Permission> findAll(int page, int size) {
        PageHelper.startPage(page,size);
       return permissionDao.findAll();
    }

    @Override
    public void savePermission(Permission permission) {
        permissionDao.savePermission(permission);
    }

    @Override
    public List<Permission> findOtherPermission(int rid) {
        return permissionDao.findOtherPermission(rid);
    }

    @Override
    public void deletePermission(int id) {
        permissionDao.deleteRole_PermissionByPid(id);
        permissionDao.deletePermission(id);
    }
}
