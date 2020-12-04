package com.atguigu.encryptcase.controller;

import com.atguigu.encryptcase.bean.User;
import com.atguigu.encryptcase.service.UserService;
import com.atguigu.encryptcase.utils.RSAdemo;
import com.atguigu.encryptcase.utils.SignatureDemo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;
import java.util.List;


@RequestMapping("/swagger")
@Api(tags = "提供用户的增删改查的功能")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "添加用户")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(User user) {
        try {
            userService.addUser(user);
            return "添加用户成功";
        } catch (Exception e) {
            return "添加用户失败:" + e.getMessage();
        }
    }

    @ApiOperation(value = "删除用户")
    @RequestMapping(value = "/addUser", method = RequestMethod.DELETE)
    public String delUser(int id) {
        try {
            userService.delUser(id);
            return "删除用户成功";
        } catch (Exception e) {
            return "删除用户失败:" + e.getMessage();
        }
    }

    @ApiOperation(value = "更新用户")
    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public String updateUser(User user) {
        try {
            userService.updateUser(user);
            return "更新用户成功";
        } catch (Exception e) {
            return "更新用户失败:" + e.getMessage();
        }
    }

    @ApiOperation(value = "查询用户")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<User> findAll() {
        return userService.findAll();
    }

    @ApiOperation(value = "购物")
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public String buy(String price, String num, String signature) {
        try {
            // 获取公钥
            PublicKey publicKey = RSAdemo.loadPublicKeyFromFile("RSA", "a.pub");
            // 第一个参数：原文
            // 第二个参数：算法
            // 第三个参数：公钥
            // 第四个参数：签名
            boolean result = SignatureDemo.verifySignature(price + num, "SHA256withRSA", publicKey, signature);
            if (result) {
                return "购物成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "购物失败";
    }

}