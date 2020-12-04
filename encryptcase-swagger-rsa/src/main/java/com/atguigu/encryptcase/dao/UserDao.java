package com.atguigu.encryptcase.dao;

import com.atguigu.encryptcase.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
}
