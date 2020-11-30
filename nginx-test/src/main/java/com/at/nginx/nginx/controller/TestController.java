package com.at.nginx.nginx.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zero
 * @create 2020-11-29 16:16
 */
@Controller
public class TestController {

    @Value("${server.port}")
    Integer port;

    @ResponseBody
    @GetMapping("/hello")
    public String hello(){

        return "当前Port:"+port;
    }




}
