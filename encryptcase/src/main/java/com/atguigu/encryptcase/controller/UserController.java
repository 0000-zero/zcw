package com.atguigu.encryptcase.controller;

import com.atguigu.encryptcase.bean.User;
import com.atguigu.encryptcase.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@Api(tags = "用户模块")
@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @ApiOperation(value = "添加用户")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id",value = "用户id",dataType = "int"),
            @ApiImplicitParam(name = "username",value = "账号",dataType = "String",required = true),
            @ApiImplicitParam(name = "password",value = "密码",dataType = "String",required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 1001,message = "添加失败"),
            @ApiResponse(code = 1002,message = "连接数据库超时"),
            @ApiResponse(code = 1003,message = "权限不足，无法添加")
    })
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
//    @ApiParam(name = "id",value = "用户id",required = true)
    @ApiImplicitParam(name = "id",dataType = "Integer",value = "用户id",required = true)
    @RequestMapping(value = "/delUser", method = RequestMethod.DELETE)
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

    @ApiOperation(value = "获取所有用户信息")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<User> findAll() {
        return userService.findAll();
    }
}