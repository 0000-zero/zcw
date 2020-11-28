package com.atzcw.zcw.web;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zero
 * @create 2020-11-24 19:44
 */
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /*
        * @GetMapping("/add.html")
    public String toAddPage(){
        return "admins/add";
    }
        * */
//        registry.addViewController("/add.html").setViewName("admins/add");
    }
}
