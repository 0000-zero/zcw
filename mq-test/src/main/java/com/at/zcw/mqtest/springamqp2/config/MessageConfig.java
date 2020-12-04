package com.at.zcw.mqtest.springamqp2.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author zero
 * @create 2020-12-04 18:48
 */
@Configuration
public class MessageConfig {

    @Autowired
    RabbitTemplate rabbitTemplate;


    @PostConstruct
    public void init() {

        rabbitTemplate.setMandatory(true);

        //正确的发送到 exchange 回调
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {

//                System.out.println("正确的发送到 exchange 回调  ==============>");
//
//                System.out.println("correlationData:" + correlationData);
//                System.out.println("ack:" + ack);
//                System.out.println("cause:" + cause);
//
//                System.out.println("正确的发送到 exchange 回调  ==============>");

            }
        });


        //错误的发送到 queue 回调
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {

                System.out.println("错误的发送到 queue 回调  ==============>");

                System.out.println("message:" + message);
                System.out.println("replyCode:" + replyCode);
                System.out.println("replyText:" + replyText);
                System.out.println("exchange:" + exchange);
                System.out.println("routingKey:" + routingKey);

                System.out.println("错误的发送到 queue 回调  ==============>");
            }
        });

    }


}
