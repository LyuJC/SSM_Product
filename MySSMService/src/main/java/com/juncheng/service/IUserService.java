package com.juncheng.service;

import com.juncheng.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {


    public List<UserInfo> findAll(int page,int size);

    public void saveUser(UserInfo userInfo);

    public UserInfo findById(int id);

    public void deleteUser(int id);

    public void addRoleToUser(int userId,Integer[] ids);
}
