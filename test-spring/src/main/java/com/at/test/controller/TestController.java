package com.at.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zero
 * @create 2020-11-27 20:04
 */
@Controller
public class TestController {

    @GetMapping("/testview")
    public String view(){
        return "tview";
    }

}
