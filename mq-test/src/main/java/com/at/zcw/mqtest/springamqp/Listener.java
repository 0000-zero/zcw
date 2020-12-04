package com.at.zcw.mqtest.springamqp;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zero
 * @create 2020-12-04 14:35
 */
@Component
public class Listener {


    @RabbitListener(bindings =
        @QueueBinding(
                value = @Queue(value = "spring.test.queue", durable = "true"),
                exchange = @Exchange(
                        value = "spring.test.exchange",
                        ignoreDeclarationExceptions = "true",
                        type = ExchangeTypes.TOPIC
                ),
                key = {"*.*"}
        )
    )
    public void listen1(String msg) {
        int i = 9/0;
        System.out.println("listen1 接收到消息：==>"+msg);
    }

}
