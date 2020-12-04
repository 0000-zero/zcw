package com.atguigu.encryptcase.service.impl;

import com.atguigu.encryptcase.bean.User;
import com.atguigu.encryptcase.dao.UserDao;
import com.atguigu.encryptcase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public void addUser(User user) {
        userDao.save(user);
    }
    @Override
    public void delUser(Integer id) {
        User user = userDao.getOne(id);
        if(user!=null)
            userDao.delete(user);
    }
    @Override
    public void updateUser(User user) {
        userDao.save(user);
    }
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}