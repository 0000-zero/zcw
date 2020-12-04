package com.at.zcw.mqtest.springamqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zero
 * @create 2020-12-04 14:36
 */
@RestController
public class Send {

    @Autowired
    AmqpTemplate template;


    @GetMapping("/send1")
    public String testSend(){
        String msg = "springamqp rabbit send1";
        template.convertAndSend("spring.test.exchange","a.b",msg);
        return "发送成功";
    }



}
