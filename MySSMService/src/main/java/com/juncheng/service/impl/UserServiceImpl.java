package com.juncheng.service.impl;

import com.github.pagehelper.PageHelper;
import com.juncheng.dao.IUserDao;
import com.juncheng.domain.Role;
import com.juncheng.domain.UserInfo;
import com.juncheng.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("userService")
@Transactional
public class UserServiceImpl  implements IUserService {

    private IUserDao userDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(IUserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(s);
        UserInfo userInfo=userDao.findByUsername(s);
        //处理自己的User对象，将其封装成UserDetails
        //User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
        User user=new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    /**
     * 返回一个list集合，集合中装入的是角色的描述
     * @return
     */
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for (Role role:roles){
        list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;

    }

    public List<UserInfo> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return userDao.findAll();
    }


    public void saveUser(UserInfo userInfo) {
        //service层对密码进行加密
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.saveUser(userInfo);
    }


    public UserInfo findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public void deleteUser(int id) {
        //首先删除关联
        userDao.deleteFromUser_RoleByUid(id);
        //再删除用户
        userDao.deleteUser(id);
    }

    public void addRoleToUser(int userId,Integer[] ids){
        for (int rid :ids){
            userDao.addRoleToUser(userId,rid);
        }
    }
}
