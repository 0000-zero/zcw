package com.at.zcw.mqtest.springamqp2.send;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zero
 * @create 2020-12-04 19:00
 */
@Api(tags = "发送消息服务")
@RestController
public class SendTest {

    @Autowired
    RabbitTemplate rabbitTemplate;



    @ApiOperation(value = "test1")
    @ApiImplicitParam(name = "msg",value = "需要发送的消息",required = true,dataTypeClass = String.class)
    @GetMapping("/test1")
    public String test1(String msg){
        for (int i = 0; i < 10; i++) {

            rabbitTemplate.convertAndSend("test_exchange_confirm","confirm",msg+i);
        }
        return "OK";
    }


    @ApiOperation(value = "test2")
    @GetMapping("/test2")
    public String test2(){

//        MessagePostProcessor processor = new MessagePostProcessor() {
//            @Override
//            public Message postProcessMessage(Message message) throws AmqpException {
//                message.getMessageProperties().setExpiration("5000");
//                return null;
//            }
//        };

        for (int i = 0; i < 10; i++) {
            if(i%2==0){
                MessagePostProcessor processor = new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        message.getMessageProperties().setExpiration("5000");
                        return message;
                    }
                };
                rabbitTemplate.convertAndSend("test_exchange_ttl","ttl.hehe",i+"",processor);
            }else{
                rabbitTemplate.convertAndSend("test_exchange_ttl","ttl.hehe",i+"");
            }
        }

        return "fs成功";
    }


}
