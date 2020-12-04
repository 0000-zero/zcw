package com.atguigu.encryptcase.service;

import com.atguigu.encryptcase.bean.User;
import java.util.List;

public interface UserService {
    void addUser(User user);
    void delUser(Integer id);
    void updateUser(User user);
    List<User> findAll();
}
