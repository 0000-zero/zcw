package com.atzcw.zcw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zero
 * @create 2020-11-23 17:12
 */
@Controller
public class IndexController {

    //跳转到登录页面
    @GetMapping("/login.html")
    public String toLoginPage(){
        System.out.println("登录页面");
        return "login";
    }

    //首页
    @RequestMapping(value = {"/index","/"})
    public String indexPage(){
        return "index";
    }

}
