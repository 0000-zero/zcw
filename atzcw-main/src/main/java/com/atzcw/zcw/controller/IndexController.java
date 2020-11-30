package com.atzcw.zcw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zero
 * @create 2020-11-23 17:12
 */
@Controller
public class IndexController {

    @ResponseBody
    @PostMapping("/testAb")
    public String testAb(Integer id){
        System.out.println("id = " + id);
        return "xxx";
    }

    /*
        ab -n 1000 -c 200 -T application/x-www-form-urlencoded -p pfile.txt -r  http://192.168.43.58:8080/atzcwmain/testAb
        http://192.168.43.58:8080/seckillwar/doSk

     */

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
