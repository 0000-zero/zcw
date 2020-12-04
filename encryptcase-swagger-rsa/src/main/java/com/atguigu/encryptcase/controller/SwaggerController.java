package com.atguigu.encryptcase.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swagger")
@Api(value = "swagger2的demo例子")
public class SwaggerController {
    @RequestMapping("/swagger")
    @ResponseBody
    @ApiOperation(value = "根据用户名获取用户的信息",notes = "查询数据库中的记录",httpMethod = "POST",response = String.class)
    @ApiImplicitParam(name = "userName",value = "用户名",required = true,dataType = "String",paramType = "query")
    public String getUserInfo(String userName) {
        System.out.println("userName = " + userName);
        return "1234";
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "nickName",value = "用户的昵称",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "id",value = "用户的ID",paramType = "query",dataType = "Integer",required = true)
    })
    public String getUserInfoByNickName(String nickName, Integer id) {
        return "1234";
    }

}

