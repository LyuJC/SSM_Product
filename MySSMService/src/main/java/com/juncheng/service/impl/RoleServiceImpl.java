package com.juncheng.service.impl;

import com.github.pagehelper.PageHelper;
import com.juncheng.dao.IRoleDao;
import com.juncheng.domain.Role;
import com.juncheng.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements IRoleService {
    private IRoleDao roleDao;

    @Autowired
    public RoleServiceImpl(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> findAll(int page,int size) {
        PageHelper.startPage(page, size);
        return roleDao.findAll();
    }

    @Override
    public Role findById(int id) {
        return roleDao.findById(id);
    }

    @Override
    public void saveRole(Role role) {
        roleDao.saveRole(role);
    }

    @Override
    public List<Role> findOtherRole(int id) {
        return roleDao.findOtherRole(id);
    }

    public void deleteRole(int id){
        roleDao.deleteRole_PermissionByRid(id);
        roleDao.deleteUser_RoleByRid(id);
        roleDao.deleteById(id);
    }

    @Override
    public void addPermissionsToRole(int roleId, Integer[] ids) {
        for (int permissionId : ids){
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
